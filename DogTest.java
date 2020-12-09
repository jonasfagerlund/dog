import java.util.Arrays;

public class DogTest {
  public static void main(String[] args){


  Assignment assignment = new Assignment();


  new Owner("Henrik");
  new Owner("Olle");

  assignment.addDog(new Dog("Annie", "tax", 7, 2));
  assignment.addDog(new Dog("Ida", "tax", 5, 10));
  assignment.addDog(new Dog("Banne", "tax", 8, 6));

  assignment.addOwner(new Owner("Henrik"));
  assignment.addOwner(new Owner("Olle"));

  Owner henrik = assignment.findOwner("Henrik");
  Owner olle = assignment.findOwner("Olle");

  Dog banne = assignment.findDog("Banne");
  Dog annie = assignment.findDog("Annie");
  Dog ida = assignment.findDog("Ida");

  assignment.listOwners();
    

  assignment.listDogs();

  assignment.giveDogOwner();
  assignment.giveDogOwner();
  assignment.giveDogOwner();

  assignment.listDogs();


  assignment.listOwners();

  System.out.println(Arrays.toString(olle.getOwnedDogs()));


  }
}
