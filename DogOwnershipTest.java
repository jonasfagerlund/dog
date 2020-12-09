
// Detta test testar INTE metoden för att ge bort en hund, utan metoderna i klasserna 
// Dog och Owner som ska upprätthålla relationen mellan objekten.
public class DogOwnershipTest {

  public static void main(String[] args) {
    Dog d1 = new Dog("D1", "Ras", 1, 2);
    Dog d2 = new Dog("D2", "Ras", 1, 2);
    Dog d3 = new Dog("D3", "Ras", 1, 2);
    Dog d4 = new Dog("D4", "Ras", 1, 2);

    Owner o1 = new Owner("O1");
    Owner o2 = new Owner("O2");

    // Först testar vi att ägarskapet registreras på bägge sidorna om vi sätter
    // hundens ägare. Vi ska inte behöva anropa motsvarande metod på o1 här, detta
    // ska ske automatiskt.
    d1.addOwnerToDog(o1);

    // Här måste vi skriva ut information om hundens ägare, och ägarens hundar.
    // Hur detta går till beror på vilka möjligheter att komma åt dessa attribut du
    // har implementerat. Eventuellt kan det vara nödvändigt att TEMPORÄRT ta bort
    // private-modifieraren från attributen och titta direkt på dem, men det är
    // bättre att implementera lämpliga metoder för access, eller uppdatera toString
    // vilket är vad nedanstående två rader förutsätter att man gjort.
    System.out.println("Ska ägas av O1:\t" + d1);
    System.out.println("Ska äga D1:\t" + o1);

    // Därefter testar vi att ägarskapet registreras på bägge sidorna om vi lägger
    // till en hund till en ägare. Vi ska inte behöva anropa motsvarande metod på d2
    // här, detta ska ske automatiskt.
    o2.addDogToOwner(d2);

    System.out.println("Ska ägas av O2:\t" + d2);
    System.out.println("Ska äga D2:\t" + o2);

    // Slutligen testar vi att lägga till flera hundar till samma ägare, på bägge
    // sätten.
    d3.addOwnerToDog(o2);
    o2.addDogToOwner(d4);

    System.out.println("Ska ägas av O2:\t" + d3);
    System.out.println("Ska ägas av O2:\t" + d4);
    System.out.println("Ska äga D2, D3 & D4:\t" + o2);
  }

}
