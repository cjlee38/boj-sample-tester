import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Sample

fun createProblem(
    number: String = "1000",
    time: Long = 1000L,
    samples: List<Sample> = listOf(createSample())
): Problem {
    return Problem(number, time, samples)
}

fun createSample(
    input: String = "1 2",
    output: String = "3"
): Sample {
    return Sample(input, output)
}
