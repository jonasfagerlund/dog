public class DogTest {
  public static void main(String[] args){

    // Dog d1 = new Dog("    karo      ", "   tecke    ", 2, 4);

    // System.out.println(d1);
    // d1.increaseAge(5);

    // System.out.println("Increased age of" + d1);

  Assignment assignment = new Assignment();

    assignment.addDog(new Dog("anna", "tax", 1, 4));
    assignment.addDog(new Dog("emma", "tax", 1, 4));
    assignment.addDog(new Dog("sofia", "tax", 14, 8));
    assignment.addDog(new Dog("Annie", "tax", 7, 2));
    assignment.addDog(new Dog("Ida", "tax", 5, 10));
    assignment.addDog(new Dog("banne", "tax", 8, 6));
    assignment.addDog(new Dog("anna", "tax", 10, 4));


    System.out.println("\n");
    System.out.println("Osorterad lista - samma svanslängd");
    for (int i = 0; i < assignment.getDogs().size(); i++) {
      System.out.println(assignment.getDogs().get(i));
    }
    System.out.println("---------------------------------------------------");
    System.out.println("\n");

    // assignment.addOwner(new Owner("Olle"));
    // assignment.addOwner(new Owner("Henrik"));

    // Owner olle = assignment.findOwner("Olle");
    // Dog anna = assignment.findDog("Anna");

    assignment.sortDogs();

    // assignment.removeDog();

    System.out.println("Sorterad lista");
    for (int i = 0; i < assignment.getDogs().size(); i++) {
      System.out.println(assignment.getDogs().get(i));
    }
    System.out.println("---------------------------------------------------");
    System.out.println("\n");


    assignment.addDog(new Dog("anna", "golden", 1, 4));
    assignment.addDog(new Dog("emma", "pudel", 1, 4));
    assignment.addDog(new Dog("sofia", "tax", 14, 8));
    assignment.addDog(new Dog("Annie", "golden", 1, 1));
    assignment.addDog(new Dog("Ida", "tax", 5, 10));
    assignment.addDog(new Dog("banne", "tax", 8, 6));
    assignment.addDog(new Dog("anna", "golden", 1, 4));

    System.out.println("Osorterad lista - blandade längder");
    for (int i = 0; i < assignment.getDogs().size(); i++) {
      System.out.println(assignment.getDogs().get(i));
    }
    System.out.println("---------------------------------------------------");
    System.out.println("\n");


    assignment.sortDogs();

    System.out.println("Sorterad lista");
    for (int i = 0; i < assignment.getDogs().size(); i++) {
      System.out.println(assignment.getDogs().get(i));
    }
    System.out.println("---------------------------------------------------");
    System.out.println("\n");


    // assignment.giveDogOwner();
    // assignment.giveDogOwner();

    // assignment.listDogs();


    // // System.out.println(assignment.findOwner("Olle").getOwnedDogs());

    // assignment.giveDogOwner();
    // assignment.giveDogOwner();

    // Owner henrik = assignment.findOwner("Henrik");

    // Dog banne = assignment.findDog("Banne");
    // System.out.println("Detta är Bannes ägare " + banne.getOwner().getName());


    // System.out.println("Följande är Olles hundar");
    // for(int i = 0; i < olle.getOwnedDogs().length; i++ ){
    //   System.out.println(olle.getOwnedDogs()[i].getName());
    // }

    // System.out.println("Följande är Henriks hundar");
    // for (int i = 0; i < henrik.getOwnedDogs().length; i++) {
    //   System.out.println(henrik.getOwnedDogs()[i].getName());
    // }

    // assignment.listOwners();
    // assignment.giveDogOwner();
    // assignment.listOwners();

    // System.out.println(anna.getOwner());
    // System.out.println(anna.haveOwner());
    // System.out.println(Arrays.toString(olle.getOwnedDogs()));


  }
}
