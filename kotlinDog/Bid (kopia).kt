/**
 * @author Jonas Andersson Fagerlund joan1043
 */
class Bid(val amount: Int, val bidder: Owner) {

    override fun toString(): String {
        return String.format("%s %s kr", bidder.name, amount)
    }

}