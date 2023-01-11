package io.github.cjlee38.persistence.jdbc

import java.sql.ResultSet

fun interface RowMapper<T> {
    fun rowMap(resultSet: ResultSet, rowNum: Int): T
}
