import random
import turtle 

def generate_random_color():
    return(random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))

turtle.colormode(255)
t = turtle.Turtle()
t.speed("fastest")
t.hideturtle()

distanza = 10

for i in range (0, int(360/distanza)):
    t.pencolor(generate_random_color())
    t.circle(100)
    t.setheading(distanza * i)


s = turtle.Screen()
s.exitonclick()