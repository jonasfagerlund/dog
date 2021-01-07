
/**
 * @author Jonas Andersson Fagerlund joan1043
 */

import java.util.Arrays;

public class Dog {

    private static final double TAX_TAIL_LENGTH = 3.7;
    private static final String[] TAX_BREED = {"Tax", "Dachshund", "Mäyräkoira", "Teckel"};

    private String name;
    private int age;
    private String breed;
    private int weight;
    private double tailLength;
    private Owner owner;
    private Auction auction;

    // konstruktor
    public Dog(String name, String breed, int age, int weight) {
        this.name = formatName(name);
        this.breed = formatName(breed);
        this.age = age;
        this.weight = weight;
        setTailLength(age, formatName(breed), weight);
    }

    public void increaseAge(int years) {
        if (years > 0) {
            age += years;
            setTailLength(age, breed, weight);
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public int getWeight() {
        return weight;
    }

    public Owner getOwner() {
        return owner;
    }

    public double getTailLength() {
        return tailLength;
    }

    public boolean haveOwner() {
        return owner != null;
    }

    public void addOwnerToDog(Owner o) {
        if (!haveOwner()) {
            owner = o;
            this.owner.addDogToOwner(this);
        }
    }

    public void removeOwnerFromOwner() {
        if (haveOwner()) {
            owner = null;
        }
    }

    public boolean inAuction() {
        return auction != null;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction a) {
        this.auction = a;
    }

    public void removeAuctionFromDog() {
        this.auction = null;
    }

    public String toString() {
        if (haveOwner()) {
            return String.format("<%s %d %s %d tail %s owner=%s>", name, age, breed, weight, tailLength, owner.getName());
        }
        return String.format("<%s %d %s %d tail %s", name, age, breed, weight, tailLength);
    }

    // Metod för att räkna ut tailLength
    private void setTailLength(int age, String breed, double weight) {
        if (Arrays.stream(TAX_BREED).anyMatch(breed::equals)) {
            tailLength = TAX_TAIL_LENGTH;
        } else {
            tailLength = age * weight / 10;
        }
    }

    private String formatName(String str) {
        str = str.trim();
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

}