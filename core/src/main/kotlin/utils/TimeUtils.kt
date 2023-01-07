package utils

fun String.toMillis(): Long = (split(" ").first().toDouble() * 1_000).toLong()
