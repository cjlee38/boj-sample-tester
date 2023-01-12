package io.github.cjlee38.plugin.swing

import java.awt.Component
import java.awt.EventQueue
import java.awt.event.ActionEvent
import java.awt.event.InputEvent
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.util.EventObject
import javax.swing.AbstractAction
import javax.swing.BorderFactory
import javax.swing.JComponent
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.JTextArea
import javax.swing.KeyStroke
import javax.swing.event.CellEditorListener
import javax.swing.event.ChangeEvent
import javax.swing.event.EventListenerList
import javax.swing.table.TableCellEditor

class TextAreaCellEditor : TableCellEditor {
    private val scroll: JScrollPane
    private val textArea: JTextArea = JTextArea()

    override fun getTableCellEditorComponent(
        table: JTable, value: Any?, isSelected: Boolean, row: Int, column: Int
    ): Component {
//        textArea.setFont(table.getFont())
        textArea.text = value?.toString().orEmpty()
        EventQueue.invokeLater {
            textArea.caretPosition = textArea.text.length
            textArea.requestFocusInWindow()
        }
        return scroll
    }

    override fun getCellEditorValue(): Any {
        return textArea.text
    }

    override fun isCellEditable(e: EventObject): Boolean {
        if (e is MouseEvent) {
            return e.clickCount >= 2
        }
        EventQueue.invokeLater {
            if (e is KeyEvent && Character.isUnicodeIdentifierStart(e.keyChar)) {
                textArea.text = textArea.text + e.keyChar
            }
        }
        return true
    }

    protected var listenerList: EventListenerList = EventListenerList()

    @Transient
    protected var changeEvent: ChangeEvent? = null

    init {
        scroll = JScrollPane(textArea)
        scroll.border = BorderFactory.createEmptyBorder()
        scroll.viewportBorder = BorderFactory.createEmptyBorder()
        textArea.lineWrap = true
        textArea.border = BorderFactory.createEmptyBorder(1, 5, 1, 5)

        val inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED)

        val enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0)
        val enter: Any = inputMap.get(enterStroke)
        val shiftEnterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.SHIFT_DOWN_MASK)
        inputMap.put(shiftEnterStroke, enter)
        inputMap.put(enterStroke, object : AbstractAction() {
            override fun actionPerformed(e: ActionEvent) {
                stopCellEditing()
            }
        })
    }

    override fun shouldSelectCell(e: EventObject?): Boolean = true
    override fun stopCellEditing(): Boolean {
        fireEditingOn { it.editingStopped(changeEvent) }
        return true
    }
    override fun cancelCellEditing() = fireEditingOn { it.editingCanceled(changeEvent) }
    override fun addCellEditorListener(listener: CellEditorListener) =
        listenerList.add(CellEditorListener::class.java, listener)
    override fun removeCellEditorListener(listener: CellEditorListener) =
        listenerList.remove(CellEditorListener::class.java, listener)

    private fun fireEditingOn(action: (CellEditorListener) -> Unit) {
        val listeners: Array<Any> = listenerList.listenerList
        var i = listeners.size - 2
        while (i >= 0) {
            if (listeners[i] === CellEditorListener::class.java) {
                if (changeEvent == null) {
                    changeEvent = ChangeEvent(this)
                }
                action(listeners[i + 1] as CellEditorListener)
            }
            i -= 2
        }
    }
}
