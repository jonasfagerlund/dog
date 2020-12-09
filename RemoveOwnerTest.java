import java.util.Arrays;

public class RemoveOwnerTest {
  public static void main(String[] args){

    Assignment a = new Assignment();

    new Owner("Olle");

    a.addDog(new Dog("Banne", "tax", 8, 6));
    a.addDog(new Dog("Annie", "tax", 8, 6));

    a.addOwner(new Owner("Olle"));

    Owner olle = a.findOwner("Olle");

    Dog banne = a.findDog("Banne");
    Dog annie = a.findDog("Annie");

    a.giveDogOwner();
    a.giveDogOwner();

    System.out.println(banne.getOwner());
    System.out.println(annie.getOwner());
    System.out.println(Arrays.toString(olle.getNameOfOwnedDogs()));

    System.out.println("-----------------------------------");

    a.removeOwner();

    System.out.println(a.findDog("Banne"));
    System.out.println(a.findDog("Annie"));

    System.out.println(Arrays.toString(olle.getNameOfOwnedDogs()));




  }
}
