import java.util.*

class Dog(name: String, breed: String, age: Int, weight: Int) {
    val name: String
    var age: Int
        private set
    val breed: String
    val weight: Int
    var tailLength = 0.0
        private set
    var owner: Owner? = null
        private set
    var auction: Auction? = null
    fun increaseAge(years: Int) {
        if (years > 0) {
            age += years
            setTailLength(age, breed, weight.toDouble())
        }
    }

    fun haveOwner(): Boolean {
        return owner != null
    }

    fun addOwnerToDog(o: Owner?) {
        if (!haveOwner()) {
            owner = o
            owner!!.addDogToOwner(this)
        }
    }

    fun removeOwnerFromOwner() {
        if (haveOwner()) {
            owner = null
        }
    }

    fun inAuction(): Boolean {
        return auction != null
    }

    fun removeAuctionFromDog() {
        auction = null
    }

    override fun toString(): String {
        return if (haveOwner()) {
            String.format("<%s %d %s %d tail %s owner=%s>", name, age, breed, weight, tailLength, owner!!.name)
        } else String.format("<%s %d %s %d tail %s", name, age, breed, weight, tailLength)
    }

    // Metod för att räkna ut tailLength
    private fun setTailLength(age: Int, breed: String, weight: Double) {
        tailLength = if (Arrays.stream(TAX_BREED).anyMatch { anObject: String? -> breed.equals(anObject) }) {
            TAX_TAIL_LENGTH
        } else {
            age * weight / 10
        }
    }

    private fun formatName(str: String): String {
        var str = str
        str = str.trim { it <= ' ' }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase()
    }

    companion object {
        private const val TAX_TAIL_LENGTH = 3.7
        private val TAX_BREED = arrayOf("Tax", "Dachshund", "Mäyräkoira", "Teckel")
    }

    // konstruktor
    init {
        this.name = formatName(name)
        this.breed = formatName(breed)
        this.age = age
        this.weight = weight
        setTailLength(age, formatName(breed), weight.toDouble())
    }
}