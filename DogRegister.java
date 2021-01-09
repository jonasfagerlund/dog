/**
 * @author Jonas Andersson Fagerlund joan1043
 */

import java.util.ArrayList;

public class DogRegister {

    enum Commando {
        REGISTER_NEW_DOG,
        INCREASE_AGE,
        LIST_DOGS,
        REMOVE_DOG,
        REGISTER_NEW_OWNER,
        GIVE_DOG,
        LIST_OWNERS,
        REMOVE_OWNER,
        START_AUCTION,
        MAKE_BID,
        LIST_BIDS,
        LIST_AUCTIONS,
        CLOSE_AUCTION,
        EXIT
    }

    private ArrayList<Dog> listOfDogs = new ArrayList<>();
    private ArrayList<Owner> listOfOwners = new ArrayList<>();
    private ArrayList<Auction> listOfAuctions = new ArrayList<>();
    private Commando command;
    private KeyboardInput scanner = new KeyboardInput();

    // Metoder för att köra programmet
    public void run() {
        System.out.println("Välkommen!");
        readCommand();
        runCommandLoop();
        System.out.println("Hej då");
    }

    private void printMenu() {
        System.out.println("Följande kommandon finns");
        System.out.println("* register new dog");
        System.out.println("* increase age");
        System.out.println("* list dogs");
        System.out.println("* remove dog");
        System.out.println("* register new owner");
        System.out.println("* list owners");
        System.out.println("* give dog");
        System.out.println("* remove owner");
        System.out.println("* start auction");
        System.out.println("* list auctions");
        System.out.println("* list bids");
        System.out.println("* make bid");
        System.out.println("* close auction");
        System.out.println("* exit");
    }

    private void runCommandLoop() {
        do {
            runSwitch();
            readCommand();
        } while (command != Commando.EXIT);
    }

    private void readCommand() {
        String userInput;
        do {
          printMenu();
          userInput = scanner.readEnum("Command");
        } while (!isInputValid(userInput));
        command = Commando.valueOf(userInput);
    }

    private boolean isInputValid(String s){
      for(Commando c : Commando.values()) {
        if(c.name().equals(s)) {
          return true;
        }
      }
      return false;
    }

    private void runSwitch() {
        switch (command) {

            case REGISTER_NEW_DOG:
                registerNewDog();
                break;

            case INCREASE_AGE:
                increaseAgeByOne();
                break;

            case LIST_DOGS:
                listDogs();
                break;

            case REMOVE_DOG:
                removeDog();
                break;

            case REGISTER_NEW_OWNER:
                registerNewOwner();
                break;

            case LIST_OWNERS:
                listOwners();
                break;

            case GIVE_DOG:
                giveDogOwner();
                break;

            case REMOVE_OWNER:
                removeOwner();
                break;

            case START_AUCTION:
                startAuction();
                break;

            case LIST_AUCTIONS:
                listAuctions();
                break;

            case LIST_BIDS:
                listBids();
                break;

            case MAKE_BID:
                makeBid();
                break;

            case CLOSE_AUCTION:
                closeAuction();
                break;
        }

    }

    // Metoder med faktiskt funktionalitet
    private void registerNewDog() {
        String name = scanner.readString("Name");
        String breed = scanner.readString("Breed");
        int age = scanner.readInt("Age");
        int weight = scanner.readInt("Weight");
        listOfDogs.add(new Dog(name, breed, age, weight));
        System.out.printf("%s added to the register\n", name);
    }

    private void increaseAgeByOne() {
        String name = scanner.readString("Enter the name of the dog");
        Dog dog = findDog(name);
        if (dog != null) {
            dog.increaseAge(1);
            System.out.println(dog.getName() + " is now one year older");
        } else {
            System.out.println("Error: no such dog");
        }
    }

    private void listDogs() {
        if (listOfDogs.size() == 0) {
            System.out.println("Error: no dogs in register");
            return;
        }
        ArrayList<Dog> dogsToDisplay = new ArrayList<>();
        double tailLength = scanner.readDouble("Smallest tail length to display");
        for (Dog dog : listOfDogs) {
            if (dog.getTailLength() >= tailLength) {
                dogsToDisplay.add(dog);
            }
        }
        if (dogsToDisplay.size() == 0) {
            System.out.println("Error: no dogs match that criteria");
            return;
        }
        sortDogs(dogsToDisplay);
        for (Dog dog : dogsToDisplay) {
            System.out.println(dog);
        }
    }

    private void sortDogs(ArrayList<Dog> list) {
        for (int i = 1; i < list.size(); i++) {

            int indexCounter = i - 1;

            Dog dogInAction = list.get(i);
            double dogInActionTailLength = dogInAction.getTailLength();

            while (indexCounter >= 0 && dogInActionTailLength <= list.get(indexCounter).getTailLength()) {

                if (dogInActionTailLength < list.get(indexCounter).getTailLength()) {
                    changeListPosition(list, dogInAction, indexCounter);

                } else if (dogInActionTailLength == list.get(indexCounter).getTailLength()) {

                    if (dogInAction.getName().compareTo(list.get(indexCounter).getName()) < 0) {
                        changeListPosition(list, dogInAction, indexCounter);
                    }
                }
                indexCounter--;
            }
        }
    }

    private void changeListPosition(ArrayList<Dog> l, Dog d, int position) {
        l.remove(d);
        l.add(position, d);
    }

    private void removeDog() {
        String name = scanner.readString("Enter the name of the dog");
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

    private void registerNewOwner() {
        String name = scanner.readString("Name");
        listOfOwners.add(new Owner(name));
        System.out.printf("%s added to the register\n", name);
    }

    private void giveDogOwner() {
        String dogName = scanner.readString("Enter the name of the dog");
        Dog d = findDog(dogName);

        if (d == null) {
            System.out.println("Error: no dog with that name");
        } else if (d.getOwner() != null) {
            System.out.println("Error: the dog already has an owner");
        } else {
            String onwerName = scanner.readString("Enter the name of the new owner");
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

    private void listOwners() {

        if (listOfOwners.isEmpty()) {
            System.out.println("Error: no owners in register");
            return;
        }

        for (Owner o : listOfOwners) {

            System.out.println(o);
        }
    }

    private void removeOwner() {
        String name = scanner.readString("Enter name of the user");
        Owner o = findOwner(name);

        if (o != null) {
            if (o.haveDogs()) {
              removeAllDogsFromOwner(o);
            }
            
            removeAllBidsFromOwner(o);
            listOfOwners.remove(o);
            System.out.println(o.getName() + " is removed from the register");
        } else {
            System.out.println("Error: no such owner");
        }
    }

    private void removeAllDogsFromOwner(Owner o) {
        ArrayList<Dog> dogsToRemove = new ArrayList<>();

        for (Dog dog : listOfDogs) {
          if(dog.getOwner() == o){
            dogsToRemove.add(dog);
          }
        }
        for(Dog dog : dogsToRemove){
          listOfDogs.remove(dog);
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

    private void startAuction() {
        String dogName = scanner.readString("Enter name of the dog");
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

    private void makeBid() {
        String ownerName = scanner.readString("Enter name of the user");
        Owner o = findOwner(ownerName);

        if (o == null) {
            System.out.println("Error: no such user");
            return;
        }

        String dogName = scanner.readString("Enter name of the dog");
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
            bidAmount = scanner.readInt("Amount to bid (min " + lowestAmount + ")");
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

    private void listBids() {
        String name = scanner.readString("Enter name of the dog");
        Dog d = findDog(name);

        if (d == null) {
          System.out.println("Error: no such dog");
          return;
        }

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

    private void listAuctions() {
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

    private void closeAuction() {
        String name = scanner.readString("Enter the name of the dog");
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
            d.setAuction(null);
        }
    }

    private Dog findDog(String name) {
        for (int i = 0; i < listOfDogs.size(); i++) {
            if (name.equals(listOfDogs.get(i).getName())) {
                return listOfDogs.get(i);
            }
        }
        return null;
    }

    private Owner findOwner(String name) {
        for (int i = 0; i < listOfOwners.size(); i++) {
            if (name.equals(listOfOwners.get(i).getName())) {
                return listOfOwners.get(i);
            }
        }
        return null;
    }

    private Auction findAuction(Dog d) {
        if (d.inAuction()) {
            return d.getAuction();
        }
        return null;
    }


    public static void main(String[] args) {

        DogRegister dogRegister = new DogRegister();

        dogRegister.run();
    }
}
