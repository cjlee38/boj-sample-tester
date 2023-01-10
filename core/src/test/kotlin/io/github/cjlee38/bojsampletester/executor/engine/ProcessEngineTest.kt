package io.github.cjlee38.bojsampletester.executor.engine

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

private const val BASE_PATH = "src/test/java/solutions/python/"

class ProcessEngineTest : StringSpec({

    "run process" {
        val engine = ProcessEngine(ProcessCommand.PYTHON, BASE_PATH + "normal.py")
        val result = engine.run("")
        result.shouldNotBeNull()
    }

    "throw if undefined file" {
        shouldThrow<IllegalArgumentException> {
            ProcessEngine(ProcessCommand.PYTHON, "undefined.py")
        }
    }

    "status should be TIMEOUT if timeout" {
        val engine = ProcessEngine(ProcessCommand.PYTHON, BASE_PATH + "timeout.py")
        val result = engine.run("")
        result.status shouldBe EngineStatus.TIMEOUT
    }

    "status should be EXCEPTION if an exception occurs" {
        val engine = ProcessEngine(ProcessCommand.PYTHON, BASE_PATH + "exception.py")
        val result = engine.run("")
        result.status shouldBe EngineStatus.EXCEPTION
    }

    "status should be ERROR if an error occurs" {
        val engine = ProcessEngine(ProcessCommand.PYTHON, BASE_PATH + "error.py")
        val result = engine.run("")
        result.status shouldBe EngineStatus.ERROR
    }
})
