package io.github.cjlee38.bojsampletester.utils

fun String.toMillis(): Long = (split(" ").first().toDouble() * 1_000).toLong()
