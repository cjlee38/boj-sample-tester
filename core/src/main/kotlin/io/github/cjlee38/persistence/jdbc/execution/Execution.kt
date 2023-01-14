package io.github.cjlee38.persistence.jdbc.execution

import java.sql.PreparedStatement

interface Execution<T> {
    fun execute(statement: PreparedStatement): T
    val sql: String
}
