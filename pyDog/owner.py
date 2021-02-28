class Owner:

    def __init__(self, n):
        self.__name = n
        self.owned_dogs = list()

    def add_dog_to_list(self, dog):
        list.append(dog)

    def get_name(self):
        return self.__name

    def have_dogs(self):
        if len(self.owned_dogs) > 0:
            return True
        else:
            return False

    def add_dog(self, dog):
        if not(self.owns_dog(dog)):
            self.owned_dogs.append(dog)
            dog.add_owner(self)

    def remove_dog_from_owner(self, dog):
        self.owned_dogs.remove(dog)

    def owns_dog(self, dog):
        for d in self.owned_dogs:
            if d == dog:
                return True
        return False

    def __str__(self):
        return self.__name + ' owns: ' + ''.join(map(str, self.owned_dogs))