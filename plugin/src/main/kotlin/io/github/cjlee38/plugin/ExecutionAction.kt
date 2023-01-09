package io.github.cjlee38.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.RegisterToolWindowTask
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager

private const val id = "boj-sample-tester"

class ExecutionAction : AnAction() {

    override fun actionPerformed(actionEvent: AnActionEvent) {
        getOrRegisterToolWindow(actionEvent)
    }

    private fun getOrRegisterToolWindow(actionEvent: AnActionEvent): ToolWindow {
        return ToolWindowManager.getInstance(actionEvent.project!!).getToolWindow(id)
            ?: ToolWindowManager.getInstance(actionEvent.project!!).registerToolWindow(
                RegisterToolWindowTask(id, contentFactory = MyToolWindowFactory())
            )
    }
}

