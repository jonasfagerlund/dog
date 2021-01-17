import java.util.*

class Owner(val name: String) {
    private var ownedDogs = arrayOfNulls<Dog>(0)

    fun ownSpecificDog(searchedDog: Dog): Boolean {
        for (d in ownedDogs) {
            if (d === searchedDog) {
                return true
            }
        }
        return false
    }

    fun addDogToOwner(d: Dog) {
        // Söker igenom ägarens lista av hundar för att se om den redan äger hunden.
        // Behövs eftersom man kan etablera en relation mellan hund och ägare både
        // från hund och ägarklassen. Utan detta och liknande som finns i dog.java
        // skulle uppförandet av relation fastna i en evig loop.
        if (!Arrays.asList(*ownedDogs).contains(d)) {
            val longerArray = arrayOfNulls<Dog>(ownedDogs.size + 1)
            for (i in ownedDogs.indices) {
                longerArray[i] = ownedDogs[i]
            }
            longerArray[longerArray.size - 1] = d
            ownedDogs = longerArray
            d.addOwnerToDog(this)
        }
    }

    fun removeAllDogsFromOwner() {
        for (dogToRemove in ownedDogs) {
            removeDogFromOwner(dogToRemove)
        }
    }

    fun removeDogFromOwner(d: Dog?) {
        val tempHolding = ArrayList<Dog?>()
        val shorterArray = arrayOfNulls<Dog>(ownedDogs.size - 1)
        for (dogToCopy in ownedDogs) {
            if (dogToCopy !== d) {
                tempHolding.add(dogToCopy)
            } else {
                d!!.removeOwnerFromOwner()
            }
        }
        for (i in tempHolding.indices) {
            shorterArray[i] = tempHolding[i]
        }
        ownedDogs = shorterArray
    }

    fun haveDogs(): Boolean {
        return ownedDogs.size > 0
    }

    override fun toString(): String {
        return String.format("<%s owns%s>", name, Arrays.toString(nameOfOwnedDogs(ownedDogs)))
    }

    private fun nameOfOwnedDogs(ownedDogs: Array<Dog?>): Array<String?> {
        val nameOfDogs = arrayOfNulls<String>(ownedDogs.size)
        for (i in ownedDogs.indices) {
            nameOfDogs[i] = ownedDogs[i].getName()
        }
        return nameOfDogs
    }

}