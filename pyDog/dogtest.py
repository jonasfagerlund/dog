from dog import Dog
from dogregister import DogRegister

a = Dog('jonas', 'tax', 22, 88)
b = Dog('banne', 'labbis', 2, 8)
c = Dog('mille', 'pudel', 5, 5)

register = DogRegister()

register.add_dog(a)
register.add_dog(b)
register.add_dog(c)

register.list_dogs()

print(a)
