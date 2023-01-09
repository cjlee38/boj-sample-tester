package io.github.cjlee38.bojsampletester.data

data class Grade(val input: String, val expectedOutput: String, val actualOutput: String) {
    constructor(sample: Sample, actual: String) : this(sample.input, sample.output, actual)

    val isCorrect: Boolean
        get() = actualOutput.trim() == expectedOutput.trim()
}
