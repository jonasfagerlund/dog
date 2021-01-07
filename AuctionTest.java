import java.util.ArrayList;

public class AuctionTest {
  
  public static void main(String[] args){

    Dog d1 = new Dog("Fido", "Spaniel", 7, 8);
    Dog d2 = new Dog("Milou", "Dachshund", 5, 6);
    // a.addDog(new Dog("Karo", "Labrador", 1, 2));
    // a.addDog(new Dog("Milo", "Tax", 3, 4));
    
    Auction a1 = new Auction(d1);
    Auction a2 = new Auction(d2);

    ArrayList<int> asa = new ArrayList<>();

    asa.add(1);

    System.out.println(a1);
    System.out.println(a2);

    System.out.println(d1.inAuction());
    d1.removeAuctionFromDog();
    System.out.println(d1.inAuction());

  }

}
