
/**
 * @author Jonas Andersson Fagerlund joan1043
 */

import java.util.ArrayList;

public class Auction {

    private static int id = 1;

    private int auctionId;
    private Dog dog;
    private ArrayList<Bid> bids = new ArrayList<>();

    public Auction(Dog d) {
        this.dog = d;
        this.auctionId = Auction.id;
        Auction.id++;

        d.setAuction(this);
    }

    public void addBid(Bid b) {
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getBidder() == b.getBidder())
                removeBid(bids.get(i));
        }
        bids.add(b);
    }

    public void removeBid(Bid b) {
        bids.remove(b);
    }

    public boolean haveBid() {
        return !bids.isEmpty();
    }

    public void printBids() {
        for (int i = bids.size() - 1; i >= 0; i--) {
            System.out.println(bids.get(i));
        }
    }

    public void printThreeBids() {
        int counter = bids.size();
        if (bids.size() > 2) {
            counter = 3;
        }
        for (int i = bids.size() - 1; i >= bids.size() - counter; i--) {
            System.out.println(bids.get(i));
        }
    }

    public Bid getHighestBid() {
        if (bids.size() > 0) {
            return bids.get(bids.size() - 1);
        }
        return null;
    }

    public Bid getBidFromGivenUser(Owner o) {
        for (Bid b : bids) {
            if (b.getBidder() == o) {
                return b;
            }
        }
        return null;
    }

    public void removeBidFromGivenUser(Owner o) {
        for (Bid b : bids) {
            if (b.getBidder() == o) {
                removeBid(b);
            }
        }
    }

    public Dog getDog() {
        return dog;
    }

    public int getAuctionId() {
        return auctionId;
    }

    @Override
    public String toString() {
        return String.format("%s is being sold on auction number %s", dog, auctionId);
    }

}
