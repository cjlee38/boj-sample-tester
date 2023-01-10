package io.github.cjlee38.bojsampletester.executor.engine

import java.nio.file.Files
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path

class ProcessEngine {

    fun run(command: ProcessCommand, path: String, input: String = "", timeout: Long = 1000L): String {
        val process = createProcess(command, path)
        process.write(input)
        val isExited = process.waitFor(timeout, TimeUnit.MILLISECONDS)
        process.throwOnError(isExited)
        return process.read()
    }

    private fun createProcess(command: ProcessCommand, path: String): Process {
        validateFileExists(path)
        return Runtime.getRuntime().exec("${command.command} $path")
    }

    private fun validateFileExists(path: String) {
        if (!Files.exists(Path(path))) {
            throw IllegalArgumentException("Invalid path given : $path")
        }
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
            throw IllegalArgumentException("fails on process execution : ${exitValue()} ${String(errorStream.readAllBytes())}")
        }
    }
}
