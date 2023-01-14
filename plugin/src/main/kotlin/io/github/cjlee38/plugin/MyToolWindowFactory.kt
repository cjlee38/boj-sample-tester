package io.github.cjlee38.plugin

import com.intellij.openapi.application.PathManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import io.github.cjlee38.dependency.getDataSource
import io.github.cjlee38.dependency.getProblemRepository
import io.github.cjlee38.persistence.jdbc.JdbcTemplate


class MyToolWindowFactory : ToolWindowFactory {

    override fun init(toolWindow: ToolWindow) {
        val path = PathManager.getPluginsPath() + "/plugin"
        initializeDatabase(path)
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val path = PathManager.getPluginsPath() + "/plugin"

        val repository = getProblemRepository(path)
        val myToolWindow = ExecutionToolWindow(project, repository)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.panel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    private fun initializeDatabase(basePath: String) {
        val jdbcTemplate = JdbcTemplate(getDataSource(basePath))
        jdbcTemplate.update(
            """CREATE TABLE IF NOT EXISTS problem
            (
            id      INTEGER PRIMARY KEY AUTOINCREMENT,
            number  TEXT NOT NULL UNIQUE,
            timeout INT  NOT NULL
            );""".trim()
        )
        jdbcTemplate.update(
            """CREATE TABLE IF NOT EXISTS sample
            (
                id             INTEGER PRIMARY KEY AUTOINCREMENT,
                problem_number INTEGER NOT NULL,
                input          TEXT    NOT NULL,
                output         TEXT    NOT NULL
            );
            """.trim()
        )
    }
}
