data class Room (

    val description: String,
    val exits: Map<String, String>

)
    val rooms = mapOf(
    "entrance" to Room(
        "You are in the entrance of a small house.",
        mapOf("north" to "hall")
    ),

    "hall" to Room(
        "You are in a hallway. There are doors to the east and north.",
        mapOf(
            "south" to "entrance",
            "east" to "kitchen",
            "north" to "exit"
        )
    ),

    "kitchen" to Room(
        "You are in the kitchen. It is quiet and empty.",
        mapOf("west" to "hall")
    ),

    "exit" to Room(
        "You are outside. You found the exit!",
        emptyMap()
    )
)

fun move(currentRoom: String, direction: String): String? {
    return rooms[currentRoom]?.exits?.get(direction)
}

fun main() {

    var currentRoom = "entrance"
    var playing = true

    while (playing && currentRoom != "exit") {

        // Print the current room description
        println()
        println(rooms[currentRoom]?.description)

        // Ask the player for a command
        print("What do you want to do? ")
        val command = readLine()?.lowercase()?.trim()

        when {
            command == "quit" -> {
                println("Goodbye!")
                playing = false
            }

            command == "look" -> {
                println(rooms[currentRoom]?.description)
            }

            command?.startsWith("go ") == true -> {

                // Get the direction after "go "
                val direction = command.removePrefix("go ").trim()

                // Move to the new room, or stay where we are if there is no exit
                currentRoom = move(currentRoom, direction)
                    ?: currentRoom.also {
                        println("You can't go that way.")
                    }
            }

            else -> {
                println("Invalid command.")
                println("Try: go north, go south, look, or quit.")
            }
        }
    }

    if (currentRoom == "exit") {
        println()
        println(rooms[currentRoom]?.description)
        println("You completed the game!")
    }
}
