from dog import Dog
from owner import Owner

class DogRegister:

    def __init__(self):
        self.__list_of_dogs = list()
        self.__list_of_owners = list()

    def register_new_dog(self):
        name = input('Name?> ')
        breed = input('Breed?> ')
        age = int(input('Age?> '))
        weight = int(input('Weight?> '))
        self.__list_of_dogs.append(Dog(name, breed, age, weight))

    def register_new_owner(self):
        name = input('Name?> ')
        self.__list_of_owners.append(Owner(name))

    def give_dog(self):
        dog = self.find_dog(input('Enter name of dog?> '))
        
        if dog == None:
            print('Error no such dog')
            return None
        
        if dog.have_owner():
            print('Dog already has an owner')
            return None

        owner = self.find_owner(input('Enter name of owner?> '))

        if owner == None:
            print('Error no such owner')
            return None

        owner.add_dog(dog)
        print(f'{owner.get_name()} now owns {dog.get_name()}')

    def list_dogs(self):
        if len(self.__list_of_dogs) == 0:
            print('Error: no dogs in register')
        else:
            taillength = float(input('smallest tail to display?> '))
            for dog in self.__list_of_dogs:
                if dog.get_tail_length() > taillength:
                    print(dog)

    def increase_dogs_age(self):
        dog = self.find_dog(input('Name?> '))
        if dog == None:
            print('Error: no such dog')
        else:
            dog.increase_age()

    def add_dog(self, Dog):
        self.__list_of_dogs.append(Dog)

    def add_owner(self, owner):
        self.__list_of_owners.append(owner)

    def remove_owner(self):
        owner = self.find_owner(input('Enter name of owner?> '))

        if owner == None:
            print('Error: no such owner')
            return None

        if owner.have_dogs():
            for d in self.__list_of_dogs:
                if d.get_owner() == owner:
                    self.__list_of_dogs.remove(d)
        
        self.__list_of_owners.remove(owner)

    def find_dog(self, name):
        for dog in self.__list_of_dogs:
            if name.__eq__(dog.get_name()):
                return dog

    def find_owner(self, name):
        for owner in self.__list_of_owners:
            if name.__eq__(owner.get_name()):
                return owner

    def remove_dog(self):
        dog = self.find_dog(input('Name?> '))
        if dog == None:
            print('Error: no such dog')
        else:
            self.__list_of_dogs.remove(dog)
            print(f'{dog.get_name()} is now removed')
        
        if dog.get_owner() is not None:
            owner = dog.get_owner()
            owner.remove_dog_from_owner(dog)
    
    def list_owners(self):
        if len(self.__list_of_owners) == 0:
            print('Error: no owners')
        else:
            for o in self.__list_of_owners:
                print(o)
