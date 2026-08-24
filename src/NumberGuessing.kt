import kotlin.random.Random

fun numberGuessing() {
    val myNumber = Random.nextInt(from = 1, until = 100)
    var retries =7


    while (retries >= 1) {
        print("Enter number: ")
        retries -= 1

            val guessedNumber = readlnOrNull()?.toIntOrNull()

            if (guessedNumber != null) {
                when {
                    guessedNumber == myNumber -> {
                        println("correct")
                        break
                    }

                    guessedNumber > myNumber -> {
                        println("Too high")
                        println("$retries Tries remaining")
                    }

                    else -> {
                        println("Too low")
                        println("$retries Tries remaining")
                    }
                }

            } else {
                println("Enter only numbers")

                if (retries == 0) {
                    println("Game over !! The correct number was $myNumber ")
                }

            }

        }
    }

