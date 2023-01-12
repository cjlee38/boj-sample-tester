package io.github.cjlee38.bojsampletester.data

data class Problem(
    val number: String = "",
    val time: Long = 2000L,
    val samples: List<Sample> = emptyList()
)
