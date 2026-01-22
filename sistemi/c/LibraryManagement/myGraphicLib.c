#include "myGraphicLib.h"

void clearScreen(SDL_Renderer* renderer) {
    SDL_SetRenderDrawColor(renderer, 0, 0, 0, 255);
    SDL_RenderClear(renderer);
}

void menuScreen(SDL_Renderer* renderer) {
    // Draw something (white rectangle)
    SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);
    SDL_Rect rect = {450, 350, 250, 100};
    SDL_RenderFillRect(renderer, &rect);
}