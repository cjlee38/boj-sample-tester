package io.github.cjlee38.bojsampletester.executor.engine

import java.util.concurrent.TimeUnit

class ProcessEngine {
    fun run(command: String, input: String, timeout: Long = 1L): String {
        val process = createProcess(command)
        process.write(input)
        process.waitFor(timeout, TimeUnit.SECONDS)
        process.throwOnError()
        return process.read()
    }

    private fun createProcess(command: String): Process {
        return Runtime.getRuntime().exec(command)
    }

    private fun Process.write(input: String) {
        outputStream.write(input.toByteArray())
        outputStream.flush()
        outputStream.close()
    }

    private fun Process.read(): String {
        return String(inputStream.readAllBytes())
    }

    private fun Process.throwOnError() {
        if (exitValue() != 0) {
            throw IllegalArgumentException("fails on process execution : ${exitValue()}")
        }
    }
}
