package io.github.cjlee38.plugin

import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import io.github.cjlee38.bojsampletester.SampleTester
import io.github.cjlee38.bojsampletester.data.Grades
import io.github.cjlee38.bojsampletester.data.Sample
import io.github.cjlee38.bojsampletester.data.Solution
import io.github.cjlee38.bojsampletester.request.JsoupRequestClient
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableModel

class ExecutionToolWindow(private val project: Project) {
    lateinit var panel: JPanel
    private lateinit var problemNumberField: JTextField
    private lateinit var gradeButton: JButton
    private lateinit var loadButton: JButton
    private lateinit var addRowButton: JButton
    private lateinit var gradesTable: JTable
    private lateinit var tableModel: GradeTableModel

    init {
        gradeButton.addActionListener { execute() }
        loadButton.addActionListener { load() }
        addRowButton.addActionListener { addRow() }

        tableModel = GradeTableModel(gradesTable.model as DefaultTableModel)
        gradesTable.model = tableModel
        draw(Grades(emptyList()))
    }

    private fun load() {
        TODO("Not yet implemented")
        val client = JsoupRequestClient()
        val (problem, samples) = client.request(problemNumberField.text)

    }

    private fun execute() {
        try {
            val solution = requireSolution()
            val problemNumber = requireProblemNumber()
            val grades = SampleTester().run(solution, problem)
            draw(grades)
        } catch (e: Exception) {
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
        return Solution(file.path, code)
    }

    private fun addRow() {
        tableModel.addRow(arrayOf())
    }
}
