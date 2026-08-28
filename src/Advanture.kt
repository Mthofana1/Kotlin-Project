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

fun move(currentRoom: String, direction: String?): String? {
    return rooms[currentRoom]
}

fun main() {

    var currentRoom = "entrance"
    var playing = true

    while (playing && currentRoom != "exit") {

        println()
        println(rooms[currentRoom])

        print("What do you want to do? ")

        when {
            command == "quit" -> {
                println("Goodbye!")
                playing = false
            }

            command == "look" -> {
                println(rooms[currentRoom].)
            }

            command?.startsWith("go ") == true -> {

               val direction : String?

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
        println(rooms[currentRoom])
        println("You completed the game!")
    }
}
