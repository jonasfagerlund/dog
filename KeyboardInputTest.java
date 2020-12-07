public class KeyboardInputTest {

  public static void main(String[] args){

    KeyboardInput keyboardInput = new KeyboardInput();

    String firstName = keyboardInput.readString("Namn");

    int age = keyboardInput.readInt("ålder");

    System.out.println(firstName + " " + age);

    String lastName = keyboardInput.readString("Efternamn");

    System.out.printf("%s %s %s", firstName, lastName, age);

  }
  
}
