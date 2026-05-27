import turtle as t

tim = t.Turtle()
s = t.Screen()
s.listen()

def onForward():
    tim.forward(10)
    
def onBackwards():
    tim.backward()
    
def rotateLeft():
    tim.left(10)
    
def rotateRight():
    tim.right(10)
    
def clear():
    tim.clear()
    tim.penup()
    tim.setposition(0, 0)
    tim.pendown()
    
def subtract(n1, n2):
    return n1 - n2
def multiply(n1, n2):
    return n1 * n2
def divide (n1, n2):
    return n1 / n2
def subtract(n1, n2):
    return n1 - n2
    
def calculator(n1, n2, func):
    func(n1, n2)
    

s.onkey(key='w', fun=onForward)
s.onkey(key= 's', fun=onBackwards)
s.onkey(key='a', fun=rotateLeft)
s.onkey(key='d', fun=rotateRight)
s.onkey(key='c', fun=clear)


s.exitonclick()