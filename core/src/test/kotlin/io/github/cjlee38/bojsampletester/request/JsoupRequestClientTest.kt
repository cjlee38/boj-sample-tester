package io.github.cjlee38.bojsampletester.request


import io.github.cjlee38.bojsampletester.request.JsoupRequestClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class JsoupRequestClientTest : StringSpec({

    "request problem".config(enabled = false) {
        val client = JsoupRequestClient()
        val problem = client.request("1111")
        problem.number shouldBe "1111"
    }
})
