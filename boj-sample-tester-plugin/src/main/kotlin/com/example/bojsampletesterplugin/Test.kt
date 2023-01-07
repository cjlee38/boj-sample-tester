package com.example.bojsampletesterplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class Test : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val showInputDialog = Messages.showInputDialog("Input name", "The Name", Messages.getQuestionIcon())
        println("hello test!" + showInputDialog)
    }
}
