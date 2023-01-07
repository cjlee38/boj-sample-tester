import executor.PythonExecutor
import request.JsoupRequestClient
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

fun main(args: Array<String>) {
//    val client = JsoupRequestClient()
//    val samples = client.request("1111")
//    for (sample in samples) {
//        println(sample)
//    }

//    val process = Runtime.getRuntime().exec("python3 -c \"import os\nprint(os.getcwd())\"")
//    process.waitFor() // get timeout
//    val errString = String(process.errorStream.readAllBytes())
//    val reader = BufferedReader(InputStreamReader(process.inputStream))
//    val message = reader.lines().collect(Collectors.joining("\n"))
//    println(message)

    val executor = PythonExecutor("/Users/cjlee/Desktop/workspace/boj-sample-tester/core/temp.py")
    val solution = executor.execute(Problem("1111", 1, listOf(Sample("1 5", "6"))))
    println("sol $solution")
}
