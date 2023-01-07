package executor

import Problem
import Solution

interface Executor {
    fun execute(problem: Problem): Solution
}
