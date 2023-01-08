package io.github.cjlee38.bojsampletester.executor.engine

import java.util.concurrent.TimeUnit

class ProcessEngine {
    fun run(command: String, input: String, timeout: Long = 1000L): String {
        val process = createProcess(command)
        process.write(input)
        val isExited = process.waitFor(timeout, TimeUnit.MILLISECONDS)
        process.throwOnError(isExited)
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

    private fun Process.throwOnError(isExited: Boolean) {
        if (!isExited or (exitValue() != 0)) {
            throw IllegalArgumentException("fails on process execution : ${exitValue()}")
        }
    }
}
