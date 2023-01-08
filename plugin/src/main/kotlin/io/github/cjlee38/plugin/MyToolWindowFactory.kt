package io.github.cjlee38.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory




class MyToolWindowFactory: ToolWindowFactory {

    /*
    //        val registerToolWindowTaskBuilder = RegisterToolWindowTaskBuilder()
//        registerToolWindowTaskBuilder.contentFactory = ToolWindowFactory({project, toolWindow ->  toolWindow.set})
        ToolWindowManager.getInstance(actionEvent.project!!).registerToolWindow("hello window", { println("hello tool")})
        println("done")

     */
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.myToolWindowContent, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
