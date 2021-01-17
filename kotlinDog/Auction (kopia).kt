import java.util.*

class Auction(val dog: Dog) {
    val auctionId: Int
    private val bids = ArrayList<Bid>()
    fun addBid(b: Bid) {
        for (i in bids.indices) {
            if (bids[i].bidder === b.bidder) removeBid(bids[i])
        }
        bids.add(b)
    }

    fun removeBid(b: Bid?) {
        bids.remove(b)
    }

    fun haveBid(): Boolean {
        return !bids.isEmpty()
    }

    fun printBids() {
        for (i in bids.indices.reversed()) {
            System.out.println(bids[i])
        }
    }

    fun printThreeBids() {
        var counter = bids.size
        if (bids.size > 2) {
            counter = 3
        }
        for (i in bids.size - 1 downTo bids.size - counter) {
            System.out.println(bids[i])
        }
    }

    val highestBid: Bid?
        get() = if (bids.size > 0) {
            bids[bids.size - 1]
        } else null

    fun getBidFromGivenUser(o: Owner): Bid? {
        for (b in bids) {
            if (b.bidder === o) {
                return b
            }
        }
        return null
    }

    fun removeBidFromGivenUser(o: Owner) {
        for (b in bids) {
            if (b.bidder === o) {
                removeBid(b)
            }
        }
    }

    override fun toString(): String {
        return java.lang.String.format("%s is being sold on auction number %s", dog, auctionId)
    }

    companion object {
        private var id = 1
    }

    init {
        auctionId = id
        id++
        dog.auction = this
    }
}