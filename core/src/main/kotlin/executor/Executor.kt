package executor

import Problem
import Grades

interface Executor {
    fun execute(problem: Problem): Grades
}
