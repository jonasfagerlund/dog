/**
 * @author Jonas Andersson Fagerlund joan1043
 */

public class Bid {
  
  private int amount;
  private Owner bidder;

  public Bid(int i, Owner o) {
    this.amount = i;
    this.bidder = o;
  }

  public int getAmount(){
    return amount;
  }

  public Owner getBidder(){
    return bidder;
  }

  @Override
  public String toString() {
    return String.format("%s %s kr", bidder.getName(), amount);
  }

}
