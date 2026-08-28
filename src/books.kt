
class Book(
    val title: String,
    val author: String,
    val isbn: String
)

class Member(
    val name: String
)

class Library {

    private val books = mutableListOf<Book>()
    private val checkedOutIsbns = mutableSetOf<String>()


    private val dueDates = mutableMapOf<String, Int>()


    private val checkedOutBy = mutableMapOf<String, Member>()


    fun addBook(book: Book) {
        books.add(book)
    }


    fun checkOut(isbn: String): Boolean {

        // Make sure the book exists
        val book = findByIsbn(isbn)

        if (book == null) {
            return false
        }

        if (isbn in checkedOutIsbns) {
            return false
        }

        checkedOutIsbns.add(isbn)

        return true
    }

    fun checkOut(
        isbn: String,
        days: Int,
        member: Member
    ): Boolean {

        if (days <= 0) {
            return false
        }

        val book = findByIsbn(isbn)

        if (book == null) {
            return false
        }

        if (isbn in checkedOutIsbns) {
            return false
        }

        checkedOutIsbns.add(isbn)
        dueDates[isbn] = days
        checkedOutBy[isbn] = member

        return true
    }

    fun returnBook(isbn: String): Boolean {

        if (isbn !in checkedOutIsbns) {
            return false
        }

        checkedOutIsbns.remove(isbn)

        // Remove stretch-goal information
        dueDates.remove(isbn)
        checkedOutBy.remove(isbn)

        return true
    }

    fun findByAuthor(author: String): List<Book> {

        return books
            .filter {
                it.author.equals(author, ignoreCase = true)
            }
            .sortedBy {
                it.title
            }
    }


    fun findByIsbn(isbn: String): Book? {

        return books.find {
            it.isbn == isbn
        }
    }

    fun overdueBooks(): List<Book> {

        return dueDates
            .filter { it.value <= 0 }
            .mapNotNull { findByIsbn(it.key) }
            .sortedBy { it.title }
    }


    fun advanceDays(days: Int) {

        if (days <= 0) {
            return
        }

        val isbns = dueDates.keys.toList()

        for (isbn in isbns) {
            dueDates[isbn] = dueDates[isbn]!! - days
        }
    }
}

fun main() {

    val library = Library()


    val book1 = Book(
        "The Hobbit",
        "J.R.R. Tolkien",
        "111"
    )

    val book2 = Book(
        "The Lord of the Rings",
        "J.R.R. Tolkien",
        "222"
    )

    val book3 = Book(
        "1984",
        "George Orwell",
        "333"
    )

    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)

    val member = Member("Alice")

    println("===== LIBRARY DEMO =====")

    val firstCheckout = library.checkOut(
        "111",
        7,
        member
    )

    println("First checkout: $firstCheckout")

    val secondCheckout = library.checkOut(
        "111",
        7,
        member
    )

    println("Second checkout: $secondCheckout")

    val returned = library.returnBook("111")

    println("Book returned: $returned")

    println()
    println("Books by J.R.R. Tolkien:")

    val TolkienBooks = library.findByAuthor("j.r.r. tolkien")

    for (book in TolkienBooks) {
        println("${book.title} - ${book.author}")
    }

    println()
    val foundBook = library.findByIsbn("333")

    if (foundBook != null) {
        println("Found: ${foundBook.title}")
    } else {
        println("Book not found.")
    }

    val missingBook = library.findByIsbn("999")

    println("Missing book: $missingBook")

    library.checkOut("222", 3, member)

    library.advanceDays(3)

    println()
    println("Overdue books:")

    for (book in library.overdueBooks()) {
        println(book.title)
    }
}