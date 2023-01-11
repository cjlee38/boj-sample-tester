package io.github.cjlee38.plugin

import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel

class GradeTableModel(private val origin: DefaultTableModel) : TableModel by origin {

    init {
        origin.setColumnIdentifiers(headers)
    }

    fun getRow(index: Int): List<String> = dataVector[index].map { it }

    /* override TableModel */
    override fun isCellEditable(row: Int, column: Int): Boolean {
        return column <= 1
    }

    /* delegate properties and functions to DefaultTableModel */
    val dataVector: List<List<String>>
        get() = origin.dataVector.map { it.map { any -> any as String } }
    fun setRowCount(rowCount: Int) = run { origin.rowCount = rowCount }
    fun addRow(row: Array<String>) {
        println("GradeTableModel.addRow")
        origin.addRow(row)
        origin.dataVector
    }

    companion object MetaData {
        private val headers = arrayOf("예제 입력", "예제 출력", "결과", "정답 여부")
    }
}
