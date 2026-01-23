#include "myGraphicLib.h"
#include <SDL2/SDL_ttf.h>

void clearScreen(SDL_Renderer* renderer) {
    SDL_SetRenderDrawColor(renderer, 0, 0, 0, 255);
    SDL_RenderClear(renderer);
}

//draw a filled circle
void drawFilledCircle(SDL_Renderer* renderer, int cx, int cy, int radius) {
    for (int w = 0; w < radius * 2; w++) {
        for (int h = 0; h < radius * 2; h++) {
            int dx = radius - w;
            int dy = radius - h;
            if ((dx*dx + dy*dy) <= (radius * radius)) {
                SDL_RenderDrawPoint(renderer, cx + dx, cy + dy);
            }
        }
    }
}

//draw a rounded rectangle
void drawRoundedRect(SDL_Renderer* renderer, int x, int y, int w, int h, int radius) {
    // Draw the four corners as circles
    drawFilledCircle(renderer, x + radius, y + radius, radius);                    // Top-left
    drawFilledCircle(renderer, x + w - radius, y + radius, radius);                // Top-right
    drawFilledCircle(renderer, x + radius, y + h - radius, radius);                // Bottom-left
    drawFilledCircle(renderer, x + w - radius, y + h - radius, radius);            // Bottom-right
    
    // Draw the rectangles connecting the corners
    SDL_Rect topRect = {x + radius, y, w - 2 * radius, radius};
    SDL_RenderFillRect(renderer, &topRect);
    
    SDL_Rect middleRect = {x, y + radius, w, h - 2 * radius};
    SDL_RenderFillRect(renderer, &middleRect);
    
    SDL_Rect bottomRect = {x + radius, y + h - radius, w - 2 * radius, radius};
    SDL_RenderFillRect(renderer, &bottomRect);
}

// Example menu screen function
void menuScreen(SDL_Renderer* renderer) {
    // Draw something (white rectangle)
    SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);
    for(int i = 0; i < 5; i++){
        drawRoundedRect(renderer, 275, (i *100) + 80, 200, 75, 20);
    }
    // Draw menu text
    TTF_Font* font = TTF_OpenFont("/usr/share/fonts/TTF/Hack-Bold.ttf", 24);
    if (font == NULL) {
        printf("Error: Could not load font. %s\n", TTF_GetError());
        return;
    }
    
    SDL_Color textColor = {0, 0, 0, 255};
    SDL_Surface *txt = TTF_RenderText_Solid(font, "Menu", textColor);               // Render text to surface
    SDL_Texture *texture = SDL_CreateTextureFromSurface(renderer, txt);             // Create texture from surface


    SDL_Rect dstRect = {375, 20, txt->w, txt->h};                                   // Destination rectangle
    printf("Copying texture to renderer\n");
    SDL_RenderCopy(renderer, texture, NULL, &dstRect);
    printf("Texture copied\n");
    SDL_FreeSurface(txt);                                                           // Free the surface
    printf("Surface freed\n");
    SDL_DestroyTexture(texture);                                                    // Destroy the texture
    printf("Texture destroyed\n");
    TTF_CloseFont(font);                                                            // Close the font
    printf("Font closed\n");
}