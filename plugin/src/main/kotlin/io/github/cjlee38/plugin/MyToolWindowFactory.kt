package io.github.cjlee38.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import io.github.cjlee38.bojsampletester.repository.ProblemRepository
import io.github.cjlee38.dependency.getDataSource
import io.github.cjlee38.dependency.getProblemRepository
import io.github.cjlee38.persistence.jdbc.JdbcTemplate


class MyToolWindowFactory : ToolWindowFactory {

    override fun init(toolWindow: ToolWindow) {
        initializeDatabase()
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        init(toolWindow)
        val repository = getProblemRepository()
        val myToolWindow = ExecutionToolWindow(project, repository)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.panel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    private fun initializeDatabase() {
        val jdbcTemplate = JdbcTemplate(getDataSource())
        jdbcTemplate.update(
            """CREATE TABLE IF NOT EXISTS problem
            (
            id      INTEGER PRIMARY KEY AUTOINCREMENT,
            number  TEXT NOT NULL UNIQUE,
            timeout INT  NOT NULL
            );""".trim())
        jdbcTemplate.update(
            """CREATE TABLE IF NOT EXISTS sample
            (
                id             INTEGER PRIMARY KEY AUTOINCREMENT,
                problem_number INTEGER NOT NULL,
                input          TEXT    NOT NULL,
                output         TEXT    NOT NULL
            );
            """.trim())
    }
}
