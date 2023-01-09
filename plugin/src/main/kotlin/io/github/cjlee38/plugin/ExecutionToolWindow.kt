package io.github.cjlee38.plugin

import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableModel

class ExecutionToolWindow {
    lateinit var panel: JPanel
    private lateinit var table: JTable
    private lateinit var textField: JTextField

    init {
        draw()
    }

    fun draw() {
        if (table.model is DefaultTableModel) {
            val defaultTableModel = table.model as DefaultTableModel
            defaultTableModel.setColumnIdentifiers(arrayOf("set", "column", "identifier"))
            defaultTableModel.addRow(arrayOf("v1", "v2", "v3"))
        }
    }
}
