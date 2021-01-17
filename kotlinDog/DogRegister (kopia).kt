import java.util.*

class DogRegister {
    internal enum class Commando {
        REGISTER_NEW_DOG, INCREASE_AGE, LIST_DOGS, REMOVE_DOG, REGISTER_NEW_OWNER, GIVE_DOG, LIST_OWNERS, REMOVE_OWNER, START_AUCTION, MAKE_BID, LIST_BIDS, LIST_AUCTIONS, CLOSE_AUCTION, EXIT
    }

    private val listOfDogs = ArrayList<Dog>()
    private val listOfOwners = ArrayList<Owner>()
    private val listOfAuctions = ArrayList<Auction>()
    private var command: Commando? = null
    private val scanner: KeyboardInput = KeyboardInput()

    // Metoder för att köra programmet
    fun run() {
        println("Välkommen!")
        runCommandLoop()
        println("Hej då")
    }

    private fun printMenu() {
        println("Följande kommandon finns")
        println("* register new dog")
        println("* increase age")
        println("* list dogs")
        println("* remove dog")
        println("* register new owner")
        println("* list owners")
        println("* give dog")
        println("* remove owner")
        println("* start auction")
        println("* list auctions")
        println("* list bids")
        println("* make bid")
        println("* close auction")
        println("* exit")
    }

    private fun runCommandLoop() {
        do {
            readCommand()
            runSwitch()
        } while (command != Commando.EXIT)
    }

    private fun readCommand() {
        var userInput: String
        do {
            printMenu()
            userInput = scanner.readEnum("Command")
        } while (!isInputValid(userInput))
        command = Commando.valueOf(userInput)
    }

    private fun isInputValid(s: String): Boolean {
        for (c in Commando.values()) {
            if (c.name == s) {
                return true
            }
        }
        return false
    }

    private fun runSwitch() {
        when (command) {
            Commando.REGISTER_NEW_DOG -> registerNewDog()
            Commando.INCREASE_AGE -> increaseAgeByOne()
            Commando.LIST_DOGS -> listDogs()
            Commando.REMOVE_DOG -> removeDog()
            Commando.REGISTER_NEW_OWNER -> registerNewOwner()
            Commando.LIST_OWNERS -> listOwners()
            Commando.GIVE_DOG -> giveDogOwner()
            Commando.REMOVE_OWNER -> removeOwner()
            Commando.START_AUCTION -> startAuction()
            Commando.LIST_AUCTIONS -> listAuctions()
            Commando.LIST_BIDS -> listBids()
            Commando.MAKE_BID -> makeBid()
            Commando.CLOSE_AUCTION -> closeAuction()
        }
    }

    // Metoder med faktiskt funktionalitet
    private fun registerNewDog() {
        val name = scanner.readString("Name")
        val breed = scanner.readString("Breed")
        val age = scanner.readInt("Age")
        val weight = scanner.readInt("Weight")
        listOfDogs.add(Dog(name, breed, age, weight))
        System.out.printf("%s added to the register\n", name)
    }

    private fun increaseAgeByOne() {
        val name = scanner.readString("Enter the name of the dog")
        val dog = findDog(name)
        if (dog != null) {
            dog.increaseAge(1)
            println(dog.getName() + " is now one year older")
        } else {
            println("Error: no such dog")
        }
    }

    private fun listDogs() {
        if (listOfDogs.size == 0) {
            println("Error: no dogs in register")
            return
        }
        val dogsToDisplay = ArrayList<Dog>()
        val tailLength = scanner.readDouble("Smallest tail length to display")
        for (dog in listOfDogs) {
            if (dog.getTailLength() >= tailLength) {
                dogsToDisplay.add(dog)
            }
        }
        if (dogsToDisplay.size == 0) {
            println("Error: no dogs match that criteria")
            return
        }
        sortDogs(dogsToDisplay)
        for (dog in dogsToDisplay) {
            println(dog)
        }
    }

    private fun sortDogs(list: ArrayList<Dog>) {
        for (i in 1 until list.size) {
            var indexCounter = i - 1
            val dogInAction = list[i]
            val dogInActionTailLength: Double = dogInAction.getTailLength()
            while (indexCounter >= 0 && dogInActionTailLength <= list[indexCounter].getTailLength()) {
                if (dogInActionTailLength < list[indexCounter].getTailLength()) {
                    changeListPosition(list, dogInAction, indexCounter)
                } else if (dogInActionTailLength == list[indexCounter].getTailLength()) {
                    if (dogInAction.getName().compareTo(list[indexCounter].getName()) < 0) {
                        changeListPosition(list, dogInAction, indexCounter)
                    }
                }
                indexCounter--
            }
        }
    }

    private fun changeListPosition(l: ArrayList<Dog>, d: Dog, position: Int) {
        l.remove(d)
        l.add(position, d)
    }

    private fun removeDog() {
        val name = scanner.readString("Enter the name of the dog")
        val d = findDog(name)
        if (d != null) {
            if (d.haveOwner()) {
                d.getOwner().removeDogFromOwner(d)
            }
            if (d.inAuction()) {
                removeAuction(d)
            }
            listOfDogs.remove(d)
            println(d.getName() + " is removed from the register")
        } else {
            println("Error: no such dog")
        }
    }

    private fun registerNewOwner() {
        val name = scanner.readString("Name")
        listOfOwners.add(Owner(name))
        System.out.printf("%s added to the register\n", name)
    }

    private fun giveDogOwner() {
        val dogName = scanner.readString("Enter the name of the dog")
        val d = findDog(dogName)
        if (d == null) {
            println("Error: no dog with that name")
        } else if (d.getOwner() != null) {
            println("Error: the dog already has an owner")
        } else {
            val onwerName = scanner.readString("Enter the name of the new owner")
            val o = findOwner(onwerName)
            if (o == null) {
                println("Error: no such owner")
            } else {
                o.addDogToOwner(d)
                System.out.printf("%s now owns %s\n", o.name, d.getName())
                removeAuction(d)
            }
        }
    }

    private fun removeAuction(d: Dog) {
        listOfAuctions.remove(findAuction(d))
    }

    private fun listOwners() {
        if (listOfOwners.isEmpty()) {
            println("Error: no owners in register")
            return
        }
        for (o in listOfOwners) {
            println(o)
        }
    }

    private fun removeOwner() {
        val name = scanner.readString("Enter name of the user")
        val o = findOwner(name)
        if (o != null) {
            if (o.haveDogs()) {
                removeAllDogsFromOwner(o)
            }
            removeAllBidsFromOwner(o)
            listOfOwners.remove(o)
            println(o.name + " is removed from the register")
        } else {
            println("Error: no such owner")
        }
    }

    private fun removeAllDogsFromOwner(o: Owner) {
        val dogsToRemove = ArrayList<Dog>()
        for (dog in listOfDogs) {
            if (dog.getOwner() === o) {
                dogsToRemove.add(dog)
            }
        }
        for (dog in dogsToRemove) {
            listOfDogs.remove(dog)
        }
    }

    private fun removeAllBidsFromOwner(o: Owner) {
        val bidsToRemove = ArrayList<Bid>()
        for (a in listOfAuctions) {
            val b = a.getBidFromGivenUser(o)
            if (b != null) {
                bidsToRemove.add(b)
            }
        }
        for (b in bidsToRemove) {
            for (a in listOfAuctions) {
                a.removeBid(b)
            }
        }
    }

    private fun startAuction() {
        val dogName = scanner.readString("Enter name of the dog")
        val d = findDog(dogName)
        if (d == null) {
            println("Error: no such dog")
        } else if (d.haveOwner()) {
            println("Error: this dog already has an owner")
        } else if (d.inAuction()) {
            println("Error: this dog is already up for auction")
        } else {
            initializeAuctions(d)
        }
    }

    private fun initializeAuctions(d: Dog) {
        val a = Auction(d)
        d.setAuction(a)
        listOfAuctions.add(a)
    }

    private fun makeBid() {
        val ownerName = scanner.readString("Enter name of the user")
        val o = findOwner(ownerName)
        if (o == null) {
            println("Error: no such user")
            return
        }
        val dogName = scanner.readString("Enter name of the dog")
        val d = findDog(dogName)
        if (d == null) {
            println("Error: no such dog")
            return
        } else if (!d.inAuction()) {
            println("Error: this dog is not up for auction")
        }
        val a = findAuction(d)
        receiveBid(a, o)
    }

    private fun receiveBid(a: Auction?, o: Owner) {
        val lowestAmount = getLowestAllowedBid(a)
        var bidAmount = 0
        do {
            bidAmount = scanner.readInt("Amount to bid (min $lowestAmount)")
            if (bidAmount < lowestAmount) {
                println("Error: bid too low bid")
            }
        } while (bidAmount < lowestAmount)
        a!!.addBid(Bid(bidAmount, o))
        println("Bid made")
    }

    private fun getLowestAllowedBid(a: Auction?): Int {
        return if (!a!!.haveBid()) {
            1
        } else {
            a.getHighestBid().getAmount() + 1
        }
    }

    private fun listBids() {
        val name = scanner.readString("Enter name of the dog")
        val d = findDog(name)
        if (d == null) {
            println("Error: no such dog")
            return
        }
        if (!d.inAuction()) {
            println("Error: this dog is not up for auction")
            return
        }
        val a = findAuction(d)
        if (!a!!.haveBid()) {
            println("No bids registred for this auction")
        } else {
            a.printBids()
        }
    }

    private fun listAuctions() {
        if (listOfAuctions.isEmpty()) {
            println("Error: no auctions in progress")
        } else {
            for (i in listOfAuctions.indices) {
                val a = listOfAuctions[i]
                System.out.printf("Auction #%s: %s. Top bids:\n", a.getAuctionId(), a.getDog().getName())
                a.printThreeBids()
            }
        }
    }

    private fun closeAuction() {
        val name = scanner.readString("Enter the name of the dog")
        val d = findDog(name)
        if (d == null || !d.inAuction()) {
            println("Error: this dog is not up for auction")
        } else if (d.getAuction().getHighestBid() == null) {
            println("The auction is closed. No bids were made for " + d.getName())
            removeAuction(d)
        } else {
            val winningBid: Bid = d.getAuction().getHighestBid()
            val winningOwner: Owner = winningBid.getBidder()
            System.out.printf("The auction is closed. The winning bid was %skr and was made by %s", winningBid.getAmount(),
                    winningOwner.name)
            listOfAuctions.remove(d.getAuction())
            d.addOwnerToDog(winningOwner)
            d.setAuction(null)
        }
    }

    private fun findDog(name: String): Dog? {
        for (i in listOfDogs.indices) {
            if (name == listOfDogs[i].getName()) {
                return listOfDogs[i]
            }
        }
        return null
    }

    private fun findOwner(name: String): Owner? {
        for (i in listOfOwners.indices) {
            if (name == listOfOwners[i].name) {
                return listOfOwners[i]
            }
        }
        return null
    }

    private fun findAuction(d: Dog): Auction? {
        return if (d.inAuction()) {
            d.getAuction()
        } else null
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val dogRegister = DogRegister()
            dogRegister.run()
        }
    }
}