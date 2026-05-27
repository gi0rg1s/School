from turtle import Turtle, Screen
UP = 90
DOWN = 270
RIGHT = 0
LEFT = 180

class Snake:

    def __init__(self):
        self.snake_body = []
        for i in range(3):
            t = Turtle()
            t.shape("square")
            t.penup()
            t.color("pink")
            t.goto(-i * 20, 0)  
            self.snake_body.append(t)
            
        self.head = self.snake_body[0]

    def move(self):
        for i in range(len(self.snake_body) - 1, 0, -1):
            self.snake_body[i].goto(self.snake_body[i - 1].xcor(), self.snake_body[i - 1].ycor())
        self.snake_body[0].forward(20)

    def up(self):
        if self.head.heading() != DOWN: self.head.setheading(UP)

    def down(self):
        if self.head.heading() != UP: self.head.setheading(DOWN)

    def left(self):
        if self.head.heading() != RIGHT: self.head.setheading(LEFT)

    def right(self):
        if self.head.heading() != LEFT: self.head.setheading(RIGHT)

    def addSegment(self, position):
        new_segment = Turtle("square")
        new_segment.color("pink")
        new_segment.penup()
        new_segment.goto(position)
        self.snake_body.append(new_segment)

    def extend(self):
        self.addSegment(self.snake_body[-1].position())