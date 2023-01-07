package request

import Problem

interface RequestClient {
    fun request(problemNumber: String): Problem
}
