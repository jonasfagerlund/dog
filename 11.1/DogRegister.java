/**
 * @author Jonas Andersson Fagerlund joan1043
 */

public class DogRegister {

    enum Commando {
        REGISTER_NEW_DOG,
        LIST_DOGS, INCREASE_AGE,
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

    Commando command;
    KeyboardInput scanner = new KeyboardInput();
    Assignment a = new Assignment();

    public void run() {
      System.out.println("Välkommen!");
      printMenu();
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
            String input = readCommand();
            handleCommand(input);
            runSwitch();
        } while (command != Commando.EXIT);
    }

    private String readCommand() {
        String userInput = scanner.readEnum("Command");
        return userInput;

    }

    private void handleCommand(String s) {
        try {
            command = Commando.valueOf(s);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown command");
            printMenu();
            handleCommand(readCommand());
        }
    }

    private void runSwitch() {
        switch (command) {

            case REGISTER_NEW_DOG:
                a.registerNewDog();
                break;

            case INCREASE_AGE:
                a.increaseAgeByOne();
                break;

            case LIST_DOGS:
                a.listDogs();
                break;

            case REMOVE_DOG:
                a.removeDog();
                break;

            case REGISTER_NEW_OWNER:
                a.registerNewOwner();
                break;

            case LIST_OWNERS:
                a.listOwners();
                break;

            case GIVE_DOG:
                a.giveDogOwner();
                break;

            case REMOVE_OWNER:
                a.removeOwner();
                break;

            case START_AUCTION:
                a.startAuction();
                break;

            case LIST_AUCTIONS:
                a.listAuctions();
                break;

            case LIST_BIDS:
                a.listBids();
                break;

            case MAKE_BID:
                a.makeBid();
                break;

            case CLOSE_AUCTION:
                a.closeAuction();
                break;
        }

    }

    public static void main(String[] args) {

        DogRegister dogRegister = new DogRegister();

        dogRegister.run();
    }
}
