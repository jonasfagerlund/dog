import java.util.*;

public class RemoveDogAndOwnerTest {
  public static void main(String[] args){
    Assignment a = new Assignment();

    new Owner("Olle");

    a.addDog(new Dog("Banne", "tax", 8, 6));

    a.addOwner(new Owner("Olle"));

    Owner olle = a.findOwner("Olle");

    Dog banne = a.findDog("Banne");

    a.giveDogOwner();

    System.out.println(banne.getOwner());
    System.out.println(Arrays.toString(olle.getOwnedDogs()));

    System.out.println("-----------------------------------");

    a.removeDog();

    System.out.println(banne.getOwner());
    System.out.println(Arrays.toString(olle.getOwnedDogs()));
  }
  
}
