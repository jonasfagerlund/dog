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

  public void addDogToOwner(Dog d){

    Dog[] longerArray = new Dog[ownedDogs.length + 1];

    for(int i = 0; i < ownedDogs.length; i++){
      longerArray[i] = ownedDogs[i];
    }

    longerArray[longerArray.length - 1] = d;

    ownedDogs = longerArray;

    d.addOwnerToDog(this);

  }

  public Dog[] getOwnedDogs(){
    return ownedDogs;
  }

  public String toString(){
    return String.format("name=%s", name);
  }
  
}
