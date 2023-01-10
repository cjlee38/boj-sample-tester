package io.github.cjlee38.bojsampletester.data

data class Grade(
    val gradeStatus: GradeStatus,
    val input: String,
    val expectedOutput: String,
    val actualOutput: String
) {
    val isCorrect: Boolean
        get() = actualOutput.trim() == expectedOutput.trim()
}
