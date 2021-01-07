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
    public static final String OWNER_OF_DOG_METHOD = "ownSpecificDog"; // U8.5, obs! metoden ska ligga i Owner-klassen
    public static final String REMOVE_OWNER_METHOD = "removeOwner"; // U8.7 och U9.6
    public static final String START_AUCTION_METHOD = "startAuction"; // U9.1 och framåt
    public static final String FIND_AUCTION_METHOD = "findAuction"; // U9.2 - hjälpmetod tänkt att användas i de följande
    // stegen
    public static final String MAKE_BID_METHOD = "makeBid"; // U9.3 och framåt
    public static final String LIST_BIDS_METHOD = "listBids"; // U9.4 och framåt
    public static final String LIST_AUCTIONS_METHOD = "listAuctions"; // U9.5 och framåt
    public static final String CLOSE_AUCTION_METHOD = "closeAuction"; // U9.6

    private ArrayList<Dog> listOfDogs = new ArrayList<>();
    private ArrayList<Owner> listOfOwners = new ArrayList<>();
    private ArrayList<Auction> listOfAuctions = new ArrayList<>();

    private KeyboardInput keyboardInput = new KeyboardInput();

    public void waitForUserInput() { // Varför behövs denna metod?
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

    public void listAuctions() {
        if (listOfAuctions.isEmpty()) {
            System.out.println("Error: no auctions in progress");
        } else {
            for (int i = 0; i < listOfAuctions.size(); i++) {
                Auction a = listOfAuctions.get(i);
                System.out.printf("Auction #%s: %s. Top bids:\n", a.getAuctionId(), a.getDog().getName());
                a.printThreeBids();
            }
        }
    }

    public void listBids() {
        String name = keyboardInput.readString("Enter name of the dog");
        Dog d = findDog(name);

        if (!d.inAuction()) {
            System.out.println("Error: this dog is not up for auction");
            return;
        }

        Auction a = findAuction(d);

        if (!a.haveBid()) {
            System.out.println("No bids registred for this auction");
        } else {
            a.printBids();
        }

    }

    public void makeBid() {
        String ownerName = keyboardInput.readString("Enter name of the user");
        Owner o = findOwner(ownerName);

        if (o == null) {
            System.out.println("Error: no such user");
            return;
        }

        String dogName = keyboardInput.readString("Enter name of the dog");
        Dog d = findDog(dogName);

        if (d == null) {
            System.out.println("Error: no such dog");
            return;
        } else if (!d.inAuction()) {
            System.out.println("Error: this dog is not up for auction");
        }

        Auction a = findAuction(d);
        receiveBid(a, o);

    }

    private void receiveBid(Auction a, Owner o) {
        int lowestAmount = getLowestAllowedBid(a);

        int bidAmount = 0;
        do {
            bidAmount = keyboardInput.readInt("Amount to bid (min " + lowestAmount + ")");
            if (bidAmount < lowestAmount) {
                System.out.println("Error: bid too low bid");
            }
        } while (bidAmount < lowestAmount);

        a.addBid(new Bid(bidAmount, o));

        System.out.println("Bid made");

    }

    private int getLowestAllowedBid(Auction a) {
        if (!a.haveBid()) {
            return 1;
        } else {
            return a.getHighestBid().getAmount() + 1;
        }

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
        Dog d = findDog(name);
        if (d != null) {
            if (d.haveOwner()) {
                d.getOwner().removeDogFromOwner(d);
            }

            if (d.inAuction()) {
                removeAuction(d);
            }

            listOfDogs.remove(d);

            System.out.println(d.getName() + " is removed from the register");

        } else {
            System.out.println("Error: no such dog");
        }
    }

    public void removeOwner() {
        String name = keyboardInput.readString("Enter name of the user");
        Owner o = findOwner(name);

        if (o != null) {
            if (o.haveDogs()) {
                for (Dog d : listOfDogs) {
                    if (d.getOwner() == o) {
                        listOfDogs.remove(d);
                    }
                }
                // removeDogsWithGivenOwner(o);
                // o.removeAllDogsFromOwner(); tveksamt om den är nödvändig
            }
            removeAllBidsFromOwner(o);
            listOfOwners.remove(o);
            System.out.println(o.getName() + " is removed from the register");
        } else {
            System.out.println("Error: no such owner");
        }
    }

    private void removeDogsWithGivenOwner(Owner o) {
        for (int i = 0; i < o.getNameOfOwnedDogs().length; i++) {
            String nameOfDogToDelete = o.getNameOfOwnedDogs()[i];
            Dog dogToDelete = findDog(nameOfDogToDelete);
            listOfDogs.remove(dogToDelete);
        }
    }

    private void removeAllBidsFromOwner(Owner o) {
        ArrayList<Bid> bidsToRemove = new ArrayList<>();

        for (Auction a : listOfAuctions) {
            Bid b = a.getBidFromGivenUser(o);
            if (b != null) {
                bidsToRemove.add(b);
            }
        }
        for (Bid b : bidsToRemove) {
            for (Auction a : listOfAuctions) {
                a.removeBid(b);
            }

        }
    }

    public Auction findAuction(Dog d) {
        if (d.inAuction()) {
            return d.getAuction();
        }
        return null;
    }

    public void startAuction() {
        String dogName = keyboardInput.readString("Enter name of the dog");
        Dog d = findDog(dogName);

        if (d == null) {
            System.out.println("Error: no such dog");
        } else if (d.haveOwner()) {
            System.out.println("Error: this dog already has an owner");
        } else if (d.inAuction()) {
            System.out.println("Error: this dog is already up for auction");
        } else {
            initializeAuctions(d);
        }
    }

    private void initializeAuctions(Dog d) {
        Auction a = new Auction(d);
        d.setAuction(a);
        listOfAuctions.add(a);
    }

    public void closeAuction() {
        String name = keyboardInput.readString("Enter the name of the dog");
        Dog d = findDog(name);
        if (d == null || !d.inAuction()) {
            System.out.println("Error: this dog is not up for auction");
        } else if (d.getAuction().getHighestBid() == null) {
            System.out.println("The auction is closed. No bids were made for " + d.getName());
            removeAuction(d);
        } else {
            Bid winningBid = d.getAuction().getHighestBid();
            Owner winningOwner = winningBid.getBidder();
            System.out.printf("The auction is closed. The winning bid was %skr and was made by %s", winningBid.getAmount(),
                    winningOwner.getName());
            listOfAuctions.remove(d.getAuction());
            d.addOwnerToDog(winningOwner);
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

    public List<Dog> getDogs() {
        return listOfDogs;
    }

    public void listOwners() {

        if (listOfOwners.size() == 0) {
            System.out.println("Error: no owners in register");
            return;
        }

        for (Owner o : listOfOwners) {

            System.out.println(o);
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
                System.out.printf("%s now owns %s\n", o.getName(), d.getName());
                removeAuction(d);
            }
        }
    }

    private void removeAuction(Dog d) {
        listOfAuctions.remove(findAuction(d));
    }

    /**
     * Sortering enligt insertion sort. Metoden bygger på en överliggande for-loop
     * som plockar ut en hund i listan. Den börjar på index 1 och jämför med index
     * 0. Metoden går in i while-loopen om hunden ett steg till vänster har större
     * eller lika lång svans. I while-loopen finns en if-sats som gör att hunden i
     * fråga flyttar ett steg till vänster om hunden som står till vänster har
     * längre svans eller om de har samma längd men olika namn. I slutet av
     * while-loopen minskar variabeln som håller koll på vilket index som är ett
     * steg bakom hunden som sorteras. Blir indexCounter -1 betyder det att hunden
     * som sorteras är på index 0 och kan därmed inte jämföras med något. Då kommer
     * while-loopen avslutas eftersom ett vilkor är att indexCounter måste vara lika
     * med eller större än 0. När det är klart sorteras nästa hund på listan.
     */
    public void sortDogs(List<Dog> list) {
        for (int i = 1; i < list.size(); i++) {

            int indexCounter = i - 1;

            Dog dogInAction = list.get(i);
            double dogInActionTailLength = dogInAction.getTailLength();

            while (indexCounter >= 0 && dogInActionTailLength <= list.get(indexCounter).getTailLength()) {

                if (dogInActionTailLength < list.get(indexCounter).getTailLength()) {
                    changeListPosition(list, dogInAction, indexCounter);
                } else if (dogInActionTailLength == list.get(indexCounter).getTailLength()) {

                    // Av någon anledning fick jag för mig att compareTo() inte skulle funka i detta
                    // fallet
                    // och skrev därför en helt ny metod för att jämföra strängar i bokstavordning.
                    // i
                    // retrospekt förstår jag att compareTo() funkar fint men låter min metod stå.
                    if (compareStrings(dogInAction.getName(), list.get(indexCounter).getName())
                            .equals(dogInAction.getName())) {
                        changeListPosition(list, dogInAction, indexCounter);
                    }
                }
                indexCounter--;
            }
        }
    }

    private void changeListPosition(List<Dog> l, Dog d, int position) {
        l.remove(d);
        l.add(position, d);
    }

    private String compareStrings(String firstStr, String secondStr) {
        char[] firstStrArr = firstStr.toCharArray();
        char[] secondStrArr = secondStr.toCharArray();

        String shortestString = firstStrArr.length < secondStrArr.length ? firstStr : secondStr;

        for (int i = 0; i < shortestString.length(); i++) {

            if (firstStrArr[i] < secondStrArr[i]) {
                return firstStr;
            } else if (firstStrArr[i] > secondStrArr[i]) {
                return secondStr;
            }
        }
        return shortestString;
    }

    public void listDogs() {
        ArrayList<Dog> dogsToDisplay = new ArrayList<>();
        if (listOfDogs.size() == 0) {
            System.out.println("Error: no dogs in register");
        } else {
            double tailLength = keyboardInput.readDouble("Smallest tail length to display");
            for (Dog listOfDog : listOfDogs) {
                if (listOfDog.getTailLength() >= tailLength) {
                    dogsToDisplay.add(listOfDog);
                }
            }
            if (dogsToDisplay.size() == 0) {
                System.out.println("Error: no dogs match that criteria");
            } else {
                sortDogs(dogsToDisplay);
                for (Dog dog : dogsToDisplay) {
                    System.out.println(dog);
                }
            }
        }
    }

}
