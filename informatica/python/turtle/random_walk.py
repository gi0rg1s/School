import turtle as t
import random

def generate_random_color():
    return(random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))

t.colormode(255)
tim = t.Turtle()

########### Challenge 4 - Random Walk ########
angles = [0, 90, 180, 270]
tim.shape("turtle")
tim.pensize(10)
tim.speed(500)

for _ in range(0, 2000):
    tim.color(generate_random_color())
    tim.setheading(random.choice(angles))
    tim.forward(random.randint(10, 50))
    
s = t.Screen()
s.exitonclick()
