from snake import Snake
from turtle import Screen
from food import Food
from border import Border
from scoreboard import Scoreboard
import time

s = Screen()
s.setup(width=600, height=600)
s.bgcolor("black")
s.title("my snake game")


s.tracer(0)
snake = Snake()
food = Food()
border = Border()
score = Scoreboard()


s.listen()
s.onkey(snake.up, "Up")
s.onkey(snake.down, "Down")
s.onkey(snake.left, "Left")
s.onkey(snake.right, "Right")

game_is_on = True
while game_is_on:
    s.update()
    time.sleep(0.1)

    snake.move()

    score.increase_score()
    
    # when a collision with food is detected
    if snake.head.distance(food) < 15:
        food.refresh()
        snake.extend()
        score.increase_score()

    # when a collision with the wall is detected
    if snake.head.xcor() > 280 or snake.head.xcor() < -280 or snake.head.ycor() > 280 or snake.head.ycor() < -280:
        game_is_on = False
        score.game_over()


    # when a collision with the tail is detected
    for segment in snake.snake_body[1::]:
        if snake.head.distance(segment) < 10:
            game_is_on = False
            score.game_over()

s.exitonclick()