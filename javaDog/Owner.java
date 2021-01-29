/**
 * @author Jonas Andersson Fagerlund joan1043
 */

package javaDog;

import java.util.Arrays;

public class Owner {

    private String name;
    private Dog[] ownedDogs = new Dog[0];

    public Owner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean ownSpecificDog(Dog searchedDog) {
        for (Dog d : ownedDogs) {
            if (d == searchedDog) {
                return true;
            }
        }
        return false;
    }

    public void addDogToOwner(Dog d) {
        if (!checkOwnedDog(d)) {

            Dog[] longerArray = new Dog[ownedDogs.length + 1];

            for (int i = 0; i < ownedDogs.length; i++) {
                longerArray[i] = ownedDogs[i];
            }

            longerArray[longerArray.length - 1] = d;

            ownedDogs = longerArray;

            d.addOwnerToDog(this);
        }
    }

    public void removeAllDogsFromOwner() {
        for (Dog dogToRemove : ownedDogs) {
            removeDogFromOwner(dogToRemove);
        }
    }

    public void removeDogFromOwner(Dog d) {
        Dog[] shorterArray = new Dog[ownedDogs.length - 1];
        int indexCounter = 0;

        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] == d) {
                continue;
            }
            shorterArray[indexCounter] = ownedDogs[i];
            indexCounter++;
        }

        ownedDogs = shorterArray;
    }

    public boolean haveDogs() {
        return ownedDogs.length > 0;
    }

    @Override
    public String toString() {
        return String.format("<%s owns%s>", name, Arrays.toString(nameOfOwnedDogs(ownedDogs)));
    }

    private boolean checkOwnedDog(Dog d) {
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] == d) {
                return true;
            }
        }
        return false;
    }

    private String[] nameOfOwnedDogs(Dog[] ownedDogs) {
        String[] nameOfDogs = new String[ownedDogs.length];
        for (int i = 0; i < ownedDogs.length; i++) {
            nameOfDogs[i] = ownedDogs[i].getName();
        }
        return nameOfDogs;
    }

}
