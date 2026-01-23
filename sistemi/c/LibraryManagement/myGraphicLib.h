#ifndef MYLIB_H
#define MYLIB_H

#include <SDL2/SDL.h>

void clearScreen(SDL_Renderer* renderer);

void menuScreen(SDL_Renderer* renderer);

void drawFilledCircle(SDL_Renderer* renderer, int cx, int cy, int radius);

void drawRoundedRect(SDL_Renderer* renderer, int x, int y, int w, int h, int radius);

#endif