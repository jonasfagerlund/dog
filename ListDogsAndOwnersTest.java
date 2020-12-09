import java.util.*;

public class ListDogsAndOwnersTest {

  public static void main(String[] args) {
    Assignment a = new Assignment();

    // Vilka hundar man lägger in innan man sorterar beror på vad det är man vill
    // testa. I början kanske man bara har två och ser att de byter plats, eller
    // inte byter plats, som de ska. Därefter lägger man till fler, byter ordning på
    // dem, etc.
    a.addDog(new Dog("Fido", "Spaniel", 7, 8)); // längsta svanslängd
    a.addDog(new Dog("Milou", "Dachshund", 5, 6));
    a.addDog(new Dog("Karo", "Labrador", 1, 2)); // minsta svanslängd
    a.addDog(new Dog("Milo", "Tax", 3, 4)); // Borde komma före Milou pga namnet

    // Alternativt, om man har svårt att komma på testdata kan man skapa hundar
    // slumpmässigt. Genom att variera antalet hundar, och slumpintervallen, kan man
    // snabbt skapa många scenarier.
    Random rnd = new Random();
    for (int i = 0; i < 10; i++) {
      a.addDog(new Dog("Dog" + i, "Ras", rnd.nextInt(5), rnd.nextInt(5)));
    }

    // Vill man ha en slumpmässig ordning på hundarna i listan kan man använda denna
    // metod. Då måste man dock köra testet många gånger, eftersom man kan få olika
    // resultat varje gång om algoritmen inte är korrekt implementerad.
    Collections.shuffle(a.getDogs());

    // Detta är metoden vi vill testa
    a.sortDogs();

    // Skriv ut resultatet av testet
    for (Dog d : a.getDogs()) {
      System.out.printf("%s\t%5.2f%n", d.getName(), d.getTailLength());
    }
  }

}