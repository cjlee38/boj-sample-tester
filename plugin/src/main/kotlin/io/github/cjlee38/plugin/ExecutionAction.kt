package io.github.cjlee38.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys.EDITOR
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys.VIRTUAL_FILE
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.vfs.VirtualFile

class ExecutionAction : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val file = actionEvent.getData(VIRTUAL_FILE) ?: return
        val name = file.name
    }
}

