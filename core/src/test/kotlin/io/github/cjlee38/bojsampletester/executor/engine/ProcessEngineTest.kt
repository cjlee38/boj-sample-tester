package io.github.cjlee38.bojsampletester.executor.engine

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull

// todo : path as relative
private const val SUCCESS_FILE_PATH =
    "/Users/cjlee/Desktop/workspace/boj-sample-tester/core/src/test/kotlin/io/github/cjlee38/bojsampletester/executor/engine/test.py"

private const val SLEEP_FILE_PATH =
    "/Users/cjlee/Desktop/workspace/boj-sample-tester/core/src/test/kotlin/io/github/cjlee38/bojsampletester/executor/engine/sleep3000.py"

class ProcessEngineTest : StringSpec({

    val engine = ProcessEngine()

    "run process" {
        val output = engine.run(ProcessCommand.PYTHON, SUCCESS_FILE_PATH)
        output.shouldNotBeNull()
    }

    "throw if undefined file" {
        shouldThrow<IllegalArgumentException> {
            engine.run(ProcessCommand.PYTHON, "undefined.py")
        }
    }

    "throw if timeout" {
        shouldThrow<IllegalArgumentException> {
            engine.run(ProcessCommand.PYTHON, SLEEP_FILE_PATH, "", timeout = 1000L)
        }
    }
})
