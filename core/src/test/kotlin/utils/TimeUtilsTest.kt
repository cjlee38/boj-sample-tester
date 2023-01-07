package utils

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class TimeUtilsTest : StringSpec({
    "convert KR seconds to milliseconds" {
        listOf(
            "1 초" to 1000,
            "2 초" to 2000,
            "0.1 초" to 100,
            "0.1 초(추가시간없음)" to 100
        ).forAll { (str, expected) -> str.toMillis() shouldBe expected }
    }
})
