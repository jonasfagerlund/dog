import java.util.*;

public class Sort {
  public static void main(String[] args) {
    
    ArrayList<String> listOfStrings = new ArrayList<>();

    System.out.println("Ceasar".compareTo("Boole"));
    System.out.println("-------------------------");

    listOfStrings.add("Jonas");
    listOfStrings.add("Fido");
    listOfStrings.add("Hanna");
    listOfStrings.add("Banne");
    listOfStrings.add("Hej");
    listOfStrings.add("AAAA");

    for (int i = 1; i < listOfStrings.size(); i++) {
      String stringInAction = listOfStrings.get(i);
      int indexCounter = i - 1;

      System.out.println(stringInAction.compareTo(listOfStrings.get(indexCounter)));
      while (indexCounter >= 0 && stringInAction.compareTo(listOfStrings.get(indexCounter)) < 0) {
        listOfStrings.remove(stringInAction);
        listOfStrings.add(indexCounter, stringInAction);
        indexCounter--;
      }
    }

    System.out.println(listOfStrings);
    // ArrayList<Double> listOfDoubles = new ArrayList<>();

    // listOfDoubles.add(3.7);
    // listOfDoubles.add(2.5);
    // listOfDoubles.add(7.6);
    // listOfDoubles.add(8.0);
    // listOfDoubles.add(3.3);
    // listOfDoubles.add(2.3);

    // for (int i = 1; i < listOfDoubles.size(); i++) {
    //   Double numberInAction = listOfDoubles.get(i);
    //   int indexCounter = i - 1;

    //   while (indexCounter >= 0 && numberInAction < listOfDoubles.get(indexCounter)) {
    //     listOfDoubles.remove(numberInAction);
    //     listOfDoubles.add(indexCounter, numberInAction);
    //     indexCounter--;
    //   }
    // }

    // System.out.println(listOfDoubles);
  }
}
