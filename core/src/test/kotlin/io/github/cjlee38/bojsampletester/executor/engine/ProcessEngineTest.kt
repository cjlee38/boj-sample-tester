package io.github.cjlee38.bojsampletester.executor.engine

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull

private const val BASE_PATH = "src/test/java/solutions/python/"

class ProcessEngineTest : StringSpec({

    val engine = ProcessEngine()

    "run process" {
        val output = engine.run(ProcessCommand.PYTHON, BASE_PATH + "normal.py")
        output.shouldNotBeNull()
    }

    "throw if undefined file" {
        shouldThrow<IllegalArgumentException> {
            engine.run(ProcessCommand.PYTHON, "undefined.py")
        }
    }

    "throw if timeout" {
        shouldThrow<IllegalStateException> {
            engine.run(ProcessCommand.PYTHON, BASE_PATH + "timeout.py", timeout = 1000L)
        }
    }

    "throw if an exception occurs" {
        shouldThrow<IllegalArgumentException> {
            engine.run(ProcessCommand.PYTHON, BASE_PATH + "exception.py")
        }
    }
})
