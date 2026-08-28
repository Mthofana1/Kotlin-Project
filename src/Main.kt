//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    numberGuessing()

}

fun room () {

    val rooms = mapOf(
        "entrance" to mapOf("south" to "kitchen"), "east" to "library", "south" to "entrance"
    )
    "kitchen" to mapOf("south" to "entrance")
    "library" to mapOf("west" to "entrance", "north" to "exit")
    "exit" to mapOf("south" to "library")

    var currentRoom = "Entrance"
    var exitRoom = "Exit"
    var playing = true

    while (true) {
        println("You are in a $currentRoom")
        print("Enter command :go south,go east,go north,go west")

        while (playing && currentRoom != exitRoom) {
            println()
            println("rooms[$currentRoom]")
        }

        val input = readlnOrNull()

        when {
            input == "quit" ->
                println("thank you for playing")


            input == "look" -> {
                println("rooms[$currentRoom]")
            }

            else ->
                println("unknown command , try go south ,go north,look,quit")
            }
        }

    }





