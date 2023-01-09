package io.github.cjlee38.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.RegisterToolWindowTask
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import io.github.cjlee38.bojsampletester.SampleTester
import io.github.cjlee38.bojsampletester.data.Solution
import javax.swing.JTextArea

private const val id = "boj-sample-tester"

class ExecutionAction : AnAction() {

    override fun actionPerformed(actionEvent: AnActionEvent) {
        val problemNumber: String = inputProblemNumber()

        val file = actionEvent.getData(VIRTUAL_FILE) ?: return
        val path = file.canonicalPath ?: return

        val editor = actionEvent.getData(EDITOR) ?: return
        val text = editor.document.text

        val solution = Solution(path, text)
        val grades = SampleTester().run(solution, problemNumber)

        val popup = JBPopupFactory.getInstance()
            .createComponentPopupBuilder(JTextArea(grades.toString()), null)
            .createPopup()
        popup.showInFocusCenter()
    }

    // todo : upgrade to tool window
    private fun getOrRegisterToolWindow(actionEvent: AnActionEvent): ToolWindow {
        return ToolWindowManager.getInstance(actionEvent.project!!).getToolWindow(id)
            ?: ToolWindowManager.getInstance(actionEvent.project!!).registerToolWindow(
                RegisterToolWindowTask(id, contentFactory = MyToolWindowFactory())
            )
    }

    private fun inputProblemNumber() =
        Messages.showInputDialog("input problem number", "boj-sample-tester", Messages.getQuestionIcon())
            ?: throw IllegalArgumentException("problem number should not be null")
}

