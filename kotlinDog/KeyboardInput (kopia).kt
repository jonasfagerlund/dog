import java.util.*

class KeyboardInput {
    fun readInt(expectedInput: String): Int {
        print("$expectedInput?> ")
        val userInput = keyboardInput.nextInt()
        keyboardInput.nextLine()
        return userInput
    }

    fun readDouble(expectedInput: String): Double {
        print("$expectedInput?> ")
        val userInput = keyboardInput.nextDouble()
        keyboardInput.nextLine()
        return userInput
    }

    fun readString(expectedInput: String): String {
        var userInput: String?
        do {
            print("$expectedInput?> ")
            userInput = keyboardInput.nextLine()
            if (userInput.trim { it <= ' ' } == "" || userInput == null) {
                println("Error: the name can't be blank")
            }
        } while (userInput!!.trim { it <= ' ' } == "" || userInput == null)
        return capitalize(userInput)
    }

    fun readNextLine() {
        keyboardInput.nextLine()
    }

    fun readEnum(expectedInput: String): String {
        print("$expectedInput?> ")
        val userInput = keyboardInput.nextLine()
        return enumFormat(userInput)
    }

    private fun enumFormat(s: String): String {
        var s = s
        s = s.trim { it <= ' ' }
        s = s.toUpperCase()
        s = s.replace("\\s+".toRegex(), "_")
        return s
    }

    private fun capitalize(str: String): String {
        var str = str
        str = str.trim { it <= ' ' }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase()
    }

    companion object {
        private val keyboardInput = Scanner(System.`in`)
    }
}