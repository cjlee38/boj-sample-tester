package io.github.cjlee38.plugin.swing

import java.awt.Component
import javax.swing.BorderFactory
import javax.swing.JTable
import javax.swing.JTextArea
import javax.swing.table.TableCellRenderer

class TextAreaCellRenderer : TableCellRenderer {
    private val textArea: JTextArea = JTextArea()

    init {
        textArea.lineWrap = true
        textArea.border = BorderFactory.createEmptyBorder(1, 5, 1, 5)
    }

    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        if (isSelected) {
            textArea.foreground = table.selectionForeground
            textArea.background = table.selectionBackground
        } else {
            textArea.foreground = table.foreground
            textArea.background = table.background
        }
//        textArea.setFont(table.getFont())
        textArea.text = value?.toString().orEmpty()
        return textArea
    }
}
