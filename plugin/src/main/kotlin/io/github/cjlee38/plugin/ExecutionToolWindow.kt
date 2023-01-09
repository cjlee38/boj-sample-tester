package io.github.cjlee38.plugin

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import io.github.cjlee38.bojsampletester.SampleTester
import io.github.cjlee38.bojsampletester.data.Grades
import io.github.cjlee38.bojsampletester.data.Solution
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableModel

class ExecutionToolWindow(private val project: Project) {
    lateinit var panel: JPanel
    private lateinit var table: JTable
    private lateinit var textField: JTextField
    private lateinit var button: JButton
    private lateinit var tableModel: DefaultTableModel

    init {
        button.addActionListener { e: ActionEvent? -> execute() }
        if (table.model is DefaultTableModel) {
            tableModel = table.model as DefaultTableModel
            tableModel.setColumnIdentifiers(arrayOf("input", "expected", "actual", "isCorrect"))
        }
        draw(Grades(emptyList()))
    }

    private fun execute() {
        println("ExecutionToolWindow.execute")
        try {
            val solution = requireSolution()
            val problemNumber = requireProblemNumber()
            val grades = SampleTester().run(solution, problemNumber)
            draw(grades)
        } catch (e: Exception) {
            println("exception on ExecutionToolWindow.execute")
            e.printStackTrace()
        }
    }

    private fun draw(grades: Grades) {
        tableModel.rowCount = 0
        grades.grades.forEach { grade ->
            tableModel.addRow(
                arrayOf(
                    grade.input,
                    grade.expectedOutput,
                    grade.actualOutput,
                    if (grade.isCorrect) "✅" else "❌"
                )
            )
        }
    }

    private fun requireSolution(): Solution {
        val editor = FileEditorManager.getInstance(project).selectedTextEditor
            ?: throw IllegalArgumentException("user not editing currently")
        val file = FileDocumentManager.getInstance().getFile(editor.document)
            ?: throw IllegalArgumentException("no document")
        val code = editor.document.text
        return Solution(file.path, code) // todo : or canonical path ?
    }

    private fun requireProblemNumber(): String {
        return textField.text
    }
}
