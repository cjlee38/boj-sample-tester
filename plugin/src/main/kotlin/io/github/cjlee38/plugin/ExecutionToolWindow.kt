package io.github.cjlee38.plugin

import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import io.github.cjlee38.bojsampletester.SampleTester
import io.github.cjlee38.bojsampletester.data.Grades
import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Sample
import io.github.cjlee38.bojsampletester.data.Solution
import io.github.cjlee38.bojsampletester.repository.ProblemRepository
import io.github.cjlee38.plugin.swing.TextAreaCellEditor
import io.github.cjlee38.plugin.swing.TextAreaCellRenderer
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableModel

class ExecutionToolWindow(private val project: Project, private val problemRepository: ProblemRepository) {
    lateinit var panel: JPanel
    private lateinit var problemNumberField: JTextField
    private lateinit var gradeButton: JButton
    private lateinit var loadButton: JButton
    private lateinit var addRowButton: JButton
    private lateinit var clearButton: JButton
    private lateinit var gradesTable: JTable

    private var tableModel: GradeTableModel
    private var problem: Problem = Problem()

    init {
        gradeButton.addActionListener { execute() }
        loadButton.addActionListener { load() }
        addRowButton.addActionListener { addRow() }
        clearButton.addActionListener { clear() }

        tableModel = GradeTableModel(gradesTable.model as DefaultTableModel)
        gradesTable.model = tableModel

        val tableCellRenderer = TextAreaCellRenderer()
        gradesTable.cellSelectionEnabled = true
        gradesTable.rowSelectionAllowed = false
        gradesTable.rowHeight = 100
        gradesTable.setDefaultRenderer(Any::class.java, tableCellRenderer)
        gradesTable.setDefaultEditor(Any::class.java, TextAreaCellEditor())

        draw(Grades(emptyList()))
    }

    private fun load() {
        require(problemNumberField.text.isNotBlank())
        require(problemNumberField.text.all { it.isDigit() })
        problem = problemRepository.getByNumber(problemNumberField.text)
        problem.samples.forEach { tableModel.addRow(listOf(it.input, it.output, "", "")) }
    }

    private fun execute() {
        try {
            val solution = requireSolution()
            val userSamples = tableModel.dataVector.map { Sample(it[0], it[1]) }
            val gradingProblem = Problem(problem.number, problem.time, userSamples)
            val grades = SampleTester().run(solution, gradingProblem)
            draw(grades)
        } catch (e: Exception) {
            println(e)
            e.printStackTrace()
        }
    }

    private fun draw(grades: Grades) {
        clear()
        grades.grades.forEach { grade ->
            tableModel.addRow(
                listOf(
                    grade.input,
                    grade.expectedOutput,
                    grade.actualOutput,
                    if (grade.isCorrect) "???" else "???"
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
        tableModel.addRow(listOf("", "", "", ""))
    }

    private fun clear() {
        tableModel.rowCount = 0
    }
}
