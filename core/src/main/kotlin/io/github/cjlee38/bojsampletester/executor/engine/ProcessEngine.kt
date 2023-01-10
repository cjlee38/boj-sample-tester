package io.github.cjlee38.bojsampletester.executor.engine

import java.nio.file.Files
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path

class ProcessEngine(
    private val command: ProcessCommand,
    private val path: String,
    private val timeout: Long = 2000L
) {
    init {
        require(Files.exists(Path(path))) { "Invalid path given : $path" }
    }

    fun run(input: String): EngineResult {
        val process = createProcess(command, path)
        process.write(input)
        val isExited = process.waitFor(timeout, TimeUnit.MILLISECONDS)
        if (!isExited) {
            return EngineResult(EngineStatus.TIMEOUT, "timeout")
        }
        return if (process.exitValue() != 0) {
            val output = String(process.errorStream.readAllBytes())
            if (output.contains("error")) {
                EngineResult(EngineStatus.ERROR, output)
            } else {
                EngineResult(EngineStatus.EXCEPTION, output)
            }
        } else {
            val output = String(process.inputStream.readAllBytes())
            EngineResult(EngineStatus.FINISHED, output)
        }
    }

    private fun createProcess(command: ProcessCommand, path: String): Process {
        return Runtime.getRuntime().exec("${command.command} $path")
    }

    private fun Process.write(input: String) {
        outputStream.write(input.toByteArray())
        outputStream.flush()
        outputStream.close()
    }
}



