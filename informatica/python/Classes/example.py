class Dog:
    def __init__(self, name, age = 1):
        self.name = name
        self.age = age
    def bark(self):
        print(self.name + " says Woof!")

dog = Dog("Arya", 1)
dog.bark()