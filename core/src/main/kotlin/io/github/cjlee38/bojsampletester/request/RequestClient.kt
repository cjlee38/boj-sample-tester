package io.github.cjlee38.bojsampletester.request

import io.github.cjlee38.bojsampletester.Problem

interface RequestClient {
    fun request(problemNumber: String): Problem
}
