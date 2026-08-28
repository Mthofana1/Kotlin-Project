

    class Student(
        val name: String,
        val scores: MutableList<Int>
    )


    fun addScore(student: Student, score: Int) {
        student.scores.add(score)
    }

    fun average(student: Student): Double {
        if (student.scores.isEmpty()) {
            return 0.0
        }

        return student.scores.average()
    }

    // Convert average into a letter grade
    fun letterGrade(average: Double): String {
        return when {
            average >= 80 -> "A"
            average >= 70 -> "B"
            average >= 60 -> "C"
            average >= 50 -> "D"
            else -> "F"
        }
    }


    fun studentsAtRisk(students: List<Student>): List<String> {
        return students
            .filter { average(it) < 60 }
            .map { it.name }
    }

    fun main() {

        // Create students
        val students = listOf(
            Student("Andile", mutableListOf(85, 90, 78)),
            Student("Bobo", mutableListOf(55, 62, 58)),
            Student("Charlie", mutableListOf(72, 75, 70)),
            Student("David", mutableListOf(45, 50, 48)),
            Student("Gary", mutableListOf(88, 92, 85))
        )

        addScore(students[1], 70)

        println("===== STUDENT REPORT =====")


        for (student in students) {
            val avg = average(student)
            val grade = letterGrade(avg)

            println(
                "${student.name}: Average = %.2f, Grade = $grade"
                    .format(avg)
            )
        }


        val topStudent = students.maxByOrNull {
            average(it)
        }


        val lowestStudent = students.minByOrNull {
            average(it)
        }

        println()
        println("===== CLASS RESULTS =====")

        if (topStudent != null) {
            println(
                "Top student: ${topStudent.name} " +
                        "(${average(topStudent)}%)"
            )
        }

        if (lowestStudent != null) {
            println(
                "Lowest student: ${lowestStudent.name} " +
                        "(${average(lowestStudent)}%)"
            )
        }


        val classAverage = students
            .map { average(it) }
            .average()

        println(
            "Class average: %.2f%%".format(classAverage)
        )


        val atRisk = studentsAtRisk(students)

        println("Students at risk: $atRisk")
    }
