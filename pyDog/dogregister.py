from dog import Dog

class DogRegister:

    def __init__(self):
        self.list_of_dogs = list()

    def register_new_dog(self):
        name = input('Name?> ')
        breed = input('Breed?> ')
        age = input('Age?> ')
        age = int(age)
        weight = input('Weight?> ')
        weight = int(weight)
        self.list_of_dogs.append(Dog(name, breed, age, weight))

    def list_dogs(self):
        if len(self.list_of_dogs) == 0:
            print('Error: no dogs in register')
        else:
            taillength = float(input('smallest tail to display'))
            for dog in self.list_of_dogs:
                if dog.get_tail_length() > taillength:
                    print(dog)

    def add_dog(self, Dog):
        self.list_of_dogs.append(Dog)

