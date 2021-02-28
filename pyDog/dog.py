class Dog:

    def __init__(self, name, breed, age, weight):
        self.__name = name
        self.__breed = breed
        self.__age = age
        self.__weight = weight
        self.__tail_length = self.__calc_tail_length()
        self.__owner = None

    def get_tail_length(self):
        return self.__tail_length

    def get_owner(self):
        return self.__owner

    def get_name(self):
        return self.__name

    def add_owner(self, owner):
        if self.have_owner():
            print('Error: dog already have owner')
        else:
            self.__owner = owner
            owner.add_dog(self)

    def have_owner(self):
        if self.__owner == None:
            return False
        else:
            return True

    def get_age(self):
        return self.__age


    def increase_age(self):
        self.__age += 1

    def __calc_tail_length(self):
        taxes = ['tax', 'dashound']
        if self.__breed.lower() in taxes:
            return 3.7
        else:
            return self.__age * (self.__weight / 10)
        

    def __str__(self):
        if self.have_owner():
            return f'{self.__name}, {self.__tail_length}, {self.__breed}, {self.__age}, {self.__weight}, owner: {self.__owner.get_name()}'
        else:
            return f'{self.__name}, {self.__tail_length}, {self.__breed}, {self.__age}, {self.__weight}'
