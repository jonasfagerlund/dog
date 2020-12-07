/**
 * @author Jonas Andersson Fagerlund joan1043
 */

import java.util.Arrays;

public class Dog{

  private String name;
  private int age;
  private String breed;
  private int weight;
  private double tailLength;
  private Owner owner;

  // konstruktor
  public Dog(String name, String breed, int age, int weight){
    this.name = formatName(name);
    this.breed = formatName(breed);
    this.age = age;
    this.weight = weight;
    setTailLength(age, formatName(breed), weight);
  }

  public void increaseAge(int years) {
    if (years > 0){
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

  // antagligen inte nödvändig
  // public boolean haveOwner(){
  //   if (owner == null) {
  //     return false;
  //   } else {
  //     return true;
  //   }
  // }

  public int getWeight() {
    return weight;
  }

  public Owner getOwner(){
    return owner;
  }

  public double getTailLength() {
    return tailLength;
  }

  public void addOwnerToDog(Owner o){
      owner = o;
  }

  public String toString() {
    return String.format("name=%s age=%d breed=%s weight=%d tail length=%s", name, age, breed, weight, tailLength);
  }

  // Metod för att räkna ut tailLength
  private void setTailLength(int age, String breed, double weight) {
    String[] taxBreed = {"Tax", "Dachshund", "Mäyräkoira", "Teckel"};
    final double TAX_TAIL_LENGTH = 3.7;
    if (Arrays.stream(taxBreed).anyMatch(breed::equals)) {
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