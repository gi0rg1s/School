#include <SDL2/SDL.h>
#include <SDL2/SDL_ttf.h>
#include "myGraphicLib.h"

int main(int argc, char* argv[]) {
    // Initialize SDL
    if (SDL_Init(SDL_INIT_VIDEO) < 0) {
        printf("SDL Init Error: %s\n", SDL_GetError());
        return 1;
    }

    // Initialize SDL_ttf
    if (TTF_Init() == -1) {
        printf("TTF Init Error: %s\n", TTF_GetError());
        SDL_Quit();
        return 1;
    }

    // Create window
    SDL_Window* window = SDL_CreateWindow(
        "My SDL2 Window",
        SDL_WINDOWPOS_CENTERED,
        SDL_WINDOWPOS_CENTERED,
        800, 600,               //window size
        SDL_WINDOW_SHOWN
    );
    if (window == NULL) {
        printf("SDL CreateWindow Error: %s\n", SDL_GetError());
        TTF_Quit();
        SDL_Quit();
        return 1;
    }

    // Create renderer
    SDL_Renderer* renderer = SDL_CreateRenderer(
        window, -1, SDL_RENDERER_ACCELERATED    //-1 --> initialize the first rendering driver that supports the requested flags
    );
    if (renderer == NULL) {
        printf("SDL CreateRenderer Error: %s\n", SDL_GetError());
        SDL_DestroyWindow(window);
        TTF_Quit();
        SDL_Quit();
        return 1;
    }

    // Main loop flag
    int running = 1;
    SDL_Event event;
    TTF_Font* font = TTF_OpenFont("/usr/share/fonts/TTF/Hack-Bold.ttf", 24);
    //Text_t menuText = {10, 10, "menu", {255, 255, 255, 255}, font, NULL, renderer}; 
    Text_t menuText = {0}; // Initialize with zeros

    // Game loop
    while (running) {
        // Handle events
        while (SDL_PollEvent(&event)) {
            if (event.type == SDL_QUIT) {
                running = 0;
            }
        }

        clearScreen(renderer);
        menuScreen(renderer, &menuText);

        // Sows everything on the screen
        SDL_RenderPresent(renderer);

    }

    // Cleanup
    menuScreenCleanup(&menuText);
    SDL_DestroyRenderer(renderer);
    SDL_DestroyWindow(window);
    TTF_Quit();
    SDL_Quit();

    return 0;
}