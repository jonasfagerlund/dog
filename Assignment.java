/**
 * @author Jonas Andersson Fagerlund joan1043
 */

import java.util.*;

public class Assignment {

  /*
   * Allt eftersom du skriver dina metoder fyller du i deras namn i konstanterna
   * nedan. Testprogrammet använder dessa konstanter för att hitta dina metoder,
   * så det är viktigt att namnen stämmer.
   */
  public static final String REGISTER_NEW_DOG_METHOD = "registerNewDog"; // U7.1
  public static final String LIST_DOGS_METHOD = "listDogs"; // U7.2 och U8.4
  public static final String FIND_DOG_METHOD = "findDog"; // U7.3 - hjälpmetod tänkt att användas i de följande stegen
  public static final String INCREASE_AGE_METHOD = "increaseAgeByOne"; // U7.4
  public static final String REMOVE_DOG_METHOD = "removeDog"; // U7.5, U8.6 och U9.6
  public static final String SORT_DOGS_METHOD = "sortDogs"; // U7.6
  public static final String REGISTER_NEW_OWNER_METHOD = "registerNewOwner"; // U8.1
  public static final String FIND_OWNER_METHOD = "findOwner"; // U8.2 - hjälpmetod tänkt att användas i de följande
                                                              // stegen
  public static final String GIVE_DOG_METHOD = "giveDogOwner"; // U8.3 och framåt
  public static final String LIST_OWNERS_METHOD = "listOwners"; // U8.4
  public static final String OWNER_OF_DOG_METHOD = ""; // U8.5, obs! metoden ska ligga i Owner-klassen
  public static final String REMOVE_OWNER_METHOD = ""; // U8.7 och U9.6
  public static final String START_AUCTION_METHOD = ""; // U9.1 och framåt
  public static final String FIND_AUCTION_METHOD = ""; // U9.2 - hjälpmetod tänkt att användas i de följande stegen
  public static final String MAKE_BID_METHOD = ""; // U9.3 och framåt
  public static final String LIST_BIDS_METHOD = ""; // U9.4 och framåt
  public static final String LIST_AUCTIONS_METHOD = ""; // U9.5 och framåt
  public static final String CLOSE_AUCTION_METHOD = ""; // U9.6

  private ArrayList<Dog> listOfDogs = new ArrayList<>();
  private ArrayList<Owner> listOfOwners = new ArrayList<>();

  private KeyboardInput keyboardInput = new KeyboardInput();

  public void waitForUserInput() { // Varför behövs denna metod?
    // Ersätt raden nedan med NAMNPÅSCANNER.nextLine() eller motsvarande anrop på
    // din egen klass
    keyboardInput.readNextLine();
  }

  public void registerNewDog() {
    String name = keyboardInput.readString("Name");
    String breed = keyboardInput.readString("Breed");
    int age = keyboardInput.readInt("Age");
    int weight = keyboardInput.readInt("Weight");
    listOfDogs.add(new Dog(name, breed, age, weight));
    System.out.printf("%s added to the register\n", name);
  }

  public void registerNewOwner() {
    String name = keyboardInput.readString("Name");
    listOfOwners.add(new Owner(name));
    System.out.printf("%s added to the register\n", name);
  }

  public List<Owner> getOwners() {
    return listOfOwners;
  }

  public void increaseAgeByOne() {
    String name = keyboardInput.readString("Enter the name of the dog");
    Dog dog = findDog(name);
    if (dog != null) {
      dog.increaseAge(1);
      System.out.println(dog.getName() + " is now one year older");
    } else {
      System.out.println("Error: no such dog");
    }
  }

  public void removeDog() {
    String name = keyboardInput.readString("Enter the name of the dog");
    Dog dog = findDog(name);
    if (dog != null) {
      listOfDogs.remove(listOfDogs.indexOf(dog));
      System.out.println(dog.getName() + " is removed from the register");
    } else {
      System.out.println("Error: no such dog");
    }
  }

  public void addDog(Dog d) {
    listOfDogs.add(d);
  }

  public void addOwner(Owner o) {
    listOfOwners.add(o);
  }

  public Dog findDog(String name) {
    for (int i = 0; i < listOfDogs.size(); i++) {
      if (name.equals(listOfDogs.get(i).getName())) {
        return listOfDogs.get(i);
      }
    }
    return null;
  }

  public Owner findOwner(String name) {
    for (int i = 0; i < listOfOwners.size(); i++) {
      if (name.equals(listOfOwners.get(i).getName())) {
        return listOfOwners.get(i);
      }
    }
    return null;
  }

  /**
   * Försök att göra en genrell metod för att söka igenom listorna som finns.
   * Problem uppstår när metoden ska retunera olika objekt beroende på vilka
   * parametrar som förs in.
   */
  /*
   * public void find(String name, ArrayList arrList, String type) { for(int i =
   * 0; i < arrList.size(); i ++) { if (name.equals(arrList.get(i).getName())) {
   * return arrList.get(i); } } System.out.println("Error: no such " + type);
   * return null; }
   */

  public List<Dog> getDogs() {
    return listOfDogs;
  }

  public void listOwners() {
    if (listOfOwners.size() == 0) {
      System.out.println("Error: no owners in register");
    } else {
      for (int i = 0; i < listOfOwners.size(); i++) {
        Owner o = listOfOwners.get(i);
        Dog[] dogs = o.getOwnedDogs();
        String[] nameOfDogs = new String[dogs.length];
        for (int n = 0; n < nameOfDogs.length; n++) {
          nameOfDogs[n] = dogs[n].getName();
        }
        System.out.println(o.getName() + " " + Arrays.toString(nameOfDogs));
      }
    }
  }

  public void giveDogOwner() {
    String dogName = keyboardInput.readString("Enter the name of the dog");
    Dog d = findDog(dogName);

    if (d == null) {
      System.out.println("Error: no dog with that name");
    } else if (d.getOwner() != null) {
      System.out.println("Error: the dog already has an owner");
    } else {
      String onwerName = keyboardInput.readString("Enter the name of the new owner");
      Owner o = findOwner(onwerName);

      if (o == null) {
        System.out.println("Error: no such owner");
      } else {
        o.addDogToOwner(d);
        d.addOwnerToDog(o);
        System.out.printf("%s now owns %s\n", o.getName(), d.getName());
      }
    }
  }

  /**
   * Sortering enligt insertion sort. Algoritmen börjar med objektet på index 1
   * och jämför med objektet ett steg till vänster. Är det mindre byter de plats.
   * Sen går det vidare till index 2. Det objektet jämför sig först med objektet
   * ett steg till vänster och byter plats om det är mindre och jämför sig sedan
   * men obejktet ett steg till vänster till.
   */
  public List<Dog> sortDogs() {
    for (int i = 1; i < listOfDogs.size(); i++) {
      if (listOfDogs.get(i).getTailLength() == listOfDogs.get(i - 1).getTailLength()) {
        sortDogsByName(listOfDogs, i);
      }
      sortDogsByTailLength(listOfDogs, i);
    }
    return listOfDogs;
  }

  private List<Dog> sortDogsByName(List<Dog> list, int i) {
    Dog dogInAction = listOfDogs.get(i);
    int indexCounter = i - 1;

    while (indexCounter >= 0 && dogInAction.getName().compareTo(listOfDogs.get(indexCounter).getName()) < 0) {
      listOfDogs.remove(dogInAction);
      listOfDogs.add(indexCounter, dogInAction);
      indexCounter--;
    }
    return listOfDogs;
  }

  private List<Dog> sortDogsByTailLength(List<Dog> list, int i) {
    Dog dogInAction = listOfDogs.get(i);
    int indexCounter = i - 1;

    while (indexCounter >= 0 && dogInAction.getTailLength() < listOfDogs.get(indexCounter).getTailLength()) {
      listOfDogs.remove(dogInAction);
      listOfDogs.add(indexCounter, dogInAction);
      indexCounter--;
    }
    return listOfDogs;
  }

  public void listDogs() {
    ArrayList<Dog> dogsToDisplay = new ArrayList<>();
    if (listOfDogs.size() == 0) {
      System.out.println("Error: no dogs in register");
    } else {
      double tailLength = keyboardInput.readDouble("Smallest tail length to display");
      for (int i = 0; i < listOfDogs.size(); i++) {
        if (listOfDogs.get(i).getTailLength() >= tailLength) {
          dogsToDisplay.add(listOfDogs.get(i));
        }
      }
      if (dogsToDisplay.size() == 0) {
        System.out.println("Error: no dogs match that criteria");
      } else {
        System.out.println(dogsToDisplay);
      }
    }
  }

}