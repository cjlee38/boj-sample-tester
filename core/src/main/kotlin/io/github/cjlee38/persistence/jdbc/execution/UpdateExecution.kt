package io.github.cjlee38.persistence.jdbc.execution

import io.github.cjlee38.persistence.jdbc.execution.support.ArgumentsSetter
import java.sql.PreparedStatement

class UpdateExecution(
    override val sql: String,
    private vararg val arguments: Any
) : Execution<Int> {
    override fun execute(statement: PreparedStatement): Int {
        ArgumentsSetter.setArguments(statement, *arguments)
        return statement.executeUpdate()
    }
}
