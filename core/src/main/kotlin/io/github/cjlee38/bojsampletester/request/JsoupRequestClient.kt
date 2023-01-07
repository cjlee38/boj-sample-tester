package io.github.cjlee38.bojsampletester.request

import io.github.cjlee38.bojsampletester.Problem
import io.github.cjlee38.bojsampletester.Sample
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import io.github.cjlee38.bojsampletester.utils.toMillis


class JsoupRequestClient : RequestClient {
    override fun request(problemNumber: String): Problem {
        val document = Jsoup.connect(BASE_URL + problemNumber).userAgent(HEADER_USER_AGENT).get()
        val timeElement = (document.selectFirst("#problem-info > tbody > tr > td:nth-child(1)")
            ?: throw IllegalArgumentException("fails on find time"))

        val elements: Elements = document.select("pre.sampledata")
        val samples = elements.chunked(2)
            .map { (input, output) -> Sample(input.ownText(), output.ownText()) }
        return Problem(problemNumber, timeElement.ownText().toMillis(), samples)
    }

    companion object {
        private const val BASE_URL = "https://www.acmicpc.net/problem/"
        private const val HEADER_USER_AGENT =
            "'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Whale/3.18.154.8 Safari/537.36'"
    }
}
