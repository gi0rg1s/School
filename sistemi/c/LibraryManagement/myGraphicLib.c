#include "myGraphicLib.h"
#include <SDL2/SDL_ttf.h>

//clear the screen
void clearScreen(SDL_Renderer* renderer) {
    SDL_SetRenderDrawColor(renderer, 0, 0, 0, 255);
    SDL_RenderClear(renderer);
}

//draw a filled circle
void drawFilledCircle(Circle_t* circle) {
    for (int w = 0; w < circle->radius * 2; w++) {
        for (int h = 0; h < circle->radius * 2; h++) {
            int dx = circle->radius - w;
            int dy = circle->radius - h;
            if ((dx*dx + dy*dy) <= (circle->radius * circle->radius)) {
                SDL_RenderDrawPoint(circle->renderer, circle->centreX + dx, circle->centreY + dy);
            }
        }
    }
}

//draw a rounded rectangle
void drawRoundedRect(Rectangle_t* rect) {
    // Draw the four corners as circles
    drawFilledCircle(rect->renderer, rect->x + rect->radius, rect->y + rect->radius, rect->radius);                    // Top-left
    drawFilledCircle(rect->renderer, rect->x + rect->w - rect->radius, rect->y + rect->radius, rect->radius);          // Top-right
    drawFilledCircle(rect->renderer, rect->x + rect->radius, rect->y + rect->h - rect->radius, rect->radius);          // Bottom-left
    drawFilledCircle(rect->renderer, rect->x + rect->w - rect->radius, rect->y + rect->h - rect->radius, rect->radius);// Bottom-right
    
    // Draw the rectangles connecting the corners
    SDL_Rect topRect = {rect->x + rect->radius, rect->y, rect->w - 2 * rect->radius, rect->radius};
    SDL_RenderFillRect(rect->renderer, &topRect);
    
    SDL_Rect middleRect = {rect->x, rect->y + rect->radius, rect->w, rect->h - 2 * rect->radius};
    SDL_RenderFillRect(rect->renderer, &middleRect);
    
    SDL_Rect bottomRect = {rect->x + rect->radius, rect->y + rect->h - rect->radius, rect->w - 2 * rect->radius, rect->radius};
    SDL_RenderFillRect(rect->renderer, &bottomRect);
}


static void ensureMenuText(SDL_Renderer* renderer, Text_t* menuText) {
    if (renderer == NULL) {
        printf("Error: renderer is NULL. %s\n", SDL_GetError());
        return;
    }

    if (menuText->renderer != NULL && menuText->renderer == renderer) {
        return;
    }

    if (menuText->texture != NULL) {
        SDL_DestroyTexture(menuText->texture);
        menuText->texture = NULL;
    }
    if (menuText->font != NULL) {
        TTF_CloseFont(menuText->font);
        menuText->font = NULL;
    }

    menuText->font = TTF_OpenFont("/usr/share/fonts/TTF/Hack-Bold.ttf", 24);
    if (menuText->font == NULL) {
        printf("Error: Could not load font. %s\n", TTF_GetError());
        return;
    }

    SDL_Color textColor = {255, 255, 255, 255};                 //rgb alpha
    SDL_Surface *txt = TTF_RenderText_Solid(menuText->font, "Menu", textColor);
    SDL_Rect dstRect = {375, 20, menuText->x, 0};
    if (txt == NULL) {
        printf("Error: Could not render text surface. %s\n", TTF_GetError());
        TTF_CloseFont(menuText->font);
        menuText->font = NULL;
        return;
    }

    menuText->texture = SDL_CreateTextureFromSurface(renderer, txt);
    if (menuText->texture == NULL) {
        printf("Error: Could not create texture from surface. %s\n", SDL_GetError());
        SDL_FreeSurface(txt);
        TTF_CloseFont(menuText->font);
        menuText->font = NULL;
        return;
    }

    menuText->x = txt->w;
    menuText->y = txt->h;
    menuText->renderer = renderer;
    SDL_FreeSurface(txt);
}

// Example menu screen function
void menuScreen(SDL_Renderer* renderer) {
    // Draw something (white rectangle)
    SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);
    for(int i = 0; i < 5; i++){
        drawRoundedRect(renderer, 275, (i *100) + 80, 200, 75, 20);
    }

    Text_t *menuText = {0};
    ensureMenuText(renderer, menuText);
    if (menuText->texture == NULL) {
        return;
    }

    SDL_Rect dstRect = {375, 20, menuText->x, menuText->y};
    if (SDL_RenderCopy(renderer, menuText->texture, NULL, &dstRect) != 0) {
        printf("Error: Could not render texture. %s\n", SDL_GetError());
    }
}

void menuScreenCleanup(Text_t* menuText) {
    if (menuText->texture != NULL) {
        SDL_DestroyTexture(menuText->texture);
        menuText->texture = NULL;
    }
    if (menuText->font != NULL) {
        TTF_CloseFont(menuText->font);
        menuText->font = NULL;
    }
    menuText->renderer = NULL;
    menuText->x = 0;
    menuText->y = 0;
}