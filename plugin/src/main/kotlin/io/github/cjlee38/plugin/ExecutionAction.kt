package io.github.cjlee38.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys.EDITOR
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys.VIRTUAL_FILE
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.RegisterToolWindowTask
import com.intellij.openapi.wm.ToolWindowManager
import io.github.cjlee38.bojsampletester.SampleTester
import io.github.cjlee38.bojsampletester.Solution

class ExecutionAction : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val problemNumber: String = inputProblemNumber()

        val file = actionEvent.getData(VIRTUAL_FILE) ?: return
        val path: String = file.canonicalPath ?: return

        val editor = actionEvent.getData(EDITOR) ?: return
        val text = editor.document.text

        val solution = Solution(path, text)
        val grades = SampleTester().run(solution, problemNumber)
        println("grades = ${grades}")

        val task = RegisterToolWindowTask("hello window task", contentFactory = MyToolWindowFactory())
        ToolWindowManager.getInstance(actionEvent.project!!).registerToolWindow(task)
        println("done")
    }

    private fun inputProblemNumber() =
        Messages.showInputDialog("input problem number", "boj-sample-tester", Messages.getQuestionIcon())
            ?: throw IllegalArgumentException("problem number should not be null")
}

