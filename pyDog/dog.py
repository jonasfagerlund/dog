class Dog:

    def __init__(self, name, breed, age, weight):
        self.__name = name
        self.__breed = breed
        self.__age = age
        self.__weight = weight
        self.__tail_length = self.__calc_tail_length()

    def get_tail_length(self):
        return self.__tail_length

    def __calc_tail_length(self):
        taxes = ['tax', 'dashound']
        if self.__breed.lower() in taxes:
            return 3.7
        else:
            return self.__age * (self.__weight / 10)
        

    def __str__(self):
        return f'name: {self.__name}, tl: {self.__tail_length}, breed: {self.__breed}, age: {self.__age} and weight: {self.__weight}'
