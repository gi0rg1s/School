import turtle as t
import random

s = t.Screen()
s.setup(width=500, height=400)
user_bet = s.textinput(title="Make your bet!", prompt="Which turtle is going to win the race? Enter a color: ")

colors = ["red", "orange", "yellow", "green", "blue", "purple"]
turtles = []

for i in range(6):
    tim = t.Turtle(shape='turtle')
    tim.penup()
    tim.goto(x=-230, y=-100 + (i * 30))
    tim.color(colors[i])
    turtles.append(tim)

def check_win():
    for i in turtles:
        if i.getx() >= 230: return False
    return True

if user_bet: raceon = True

traguardo = t.Turtle()
traguardo.penup()
traguardo.hideturtle()
traguardo.right(90)
traguardo.goto(x=230, y=100)
traguardo.pendown()
traguardo.forward(200)

while raceon:
    for i in turtles:
        i.forward(random.randint(0, 10))
        raceon = check_win()

s.exitonclick()