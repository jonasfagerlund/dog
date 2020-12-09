import java.util.*;

/**
 * @author Jonas Andersson Fagerlund joan1043
 */
public class Owner {

  private String name;
  private Dog[] ownedDogs = new Dog[0];


  public Owner(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public boolean ownSpecificDog(Dog searchedDog){
    for(Dog d: ownedDogs){
      if (d == searchedDog){
        return true;
      }
    }
    return false;
  }

  public void addDogToOwner(Dog d){
    // Söker igenom ägarens lista av hundar för att se om den redan äger hunden.
    // Behövs eftersom man kan etablera en relation mellan hund och ägare både
    // från hund och ägarklassen. Utan detta och liknande som finns i dog.java
    // skulle uppförandet av relation fastna i en evig loop.
    if (!(Arrays.asList(ownedDogs).contains(d))){
    
      Dog[] longerArray = new Dog[ownedDogs.length + 1];

      for(int i = 0; i < ownedDogs.length; i++){
        longerArray[i] = ownedDogs[i];
      }

      longerArray[longerArray.length - 1] = d;

      ownedDogs = longerArray;

      d.addOwnerToDog(this);
    }
  }

  public void removeAllDogsFromOwner(){
    for (Dog dogToRemove : ownedDogs) {
      removeDogFromOwner(dogToRemove);
    }
  }

  public void removeDogFromOwner(Dog d){
    ArrayList<Dog> tempHolding = new ArrayList<>();
    Dog[] shorterArray = new Dog[ownedDogs.length - 1];

    for(Dog dogToCopy : ownedDogs){
      if(dogToCopy != d){
        tempHolding.add(dogToCopy);
      } else {
        d.removeOwnerFromOwner();
      }
    }

    for(int i = 0; i < tempHolding.size(); i++){
      shorterArray[i] = tempHolding.get(i);
    }

    ownedDogs = shorterArray;
  }

  public String[] getNameOfOwnedDogs(){
    String[] nameOfDogs = new String[ownedDogs.length];
    for(int i = 0; i < ownedDogs.length; i++){
      nameOfDogs[i] = ownedDogs[i].getName();
    }
    return nameOfDogs;
  }

  public boolean haveDogs(){
    return ownedDogs.length > 0;
  }

  public String toString(){
    return String.format("<%s owns%s>", name, Arrays.toString(nameOfOwnedDogs(ownedDogs)));
  }

  private String[] nameOfOwnedDogs(Dog[] ownedDogs){
    String[] nameOfDogs = new String[ownedDogs.length];
    for(int i = 0; i < ownedDogs.length; i++){
      nameOfDogs[i] = ownedDogs[i].getName();
    }
    return nameOfDogs;
  }
  
}
