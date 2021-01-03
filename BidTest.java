import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

public class BidTest {

  public static void main(String[] args) {
    
    Assignment assignment = new Assignment();

    Dog d1 = (new Dog("Annie", "tax", 7, 2));
    Dog d2 = (new Dog("Ida", "tax", 7, 2));

    assignment.addDog(d1);
    assignment.addDog(d2);
    assignment.addOwner(new Owner("Olle"));
    assignment.addOwner(new Owner("Jonas"));
    assignment.addOwner(new Owner("David"));
    assignment.addOwner(new Owner("Banne"));

    
    assignment.startAuction();
    assignment.startAuction();

    assignment.makeBid();
    assignment.makeBid();

    assignment.listAuctions();

    assignment.removeOwner();

    assignment.listAuctions();

    assignment.closeAuction();

    assignment.listAuctions();

  }
  
}
