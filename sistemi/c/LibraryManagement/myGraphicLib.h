#ifndef MYLIB_H
#define MYLIB_H

#include <SDL2/SDL_ttf.h>
#include <SDL2/SDL.h>

typedef struct{
    unsigned char r;
    unsigned char g;
    unsigned char b;
    unsigned char a;
}Color_t;

typedef struct {
    int x;                                  //position
    int y;
    char txt[100];                          //text
    Color_t color;                         //alpha
    TTF_Font* font;                         //font
    SDL_Texture *texture;                   //texture
    SDL_Renderer* renderer;                 //renderer
} Text_t;

typedef struct {
    int radius;
    int centreX;
    int centreY;
    SDL_Renderer* renderer;
    Color_t color;
} Circle_t;

typedef struct{
    int x;
    int y;
    int w;
    int h;
    int radius;
    Color_t color;
    SDL_Renderer* renderer; 
}Rectangle_t;


//clear the screen
void clearScreen(SDL_Renderer* renderer);

//menu screen function
void menuScreen(SDL_Renderer* renderer, Text_t* menuText);

//cleanup menu screen resources
void menuScreenCleanup(Text_t* menuText);

//draw filled circle
void drawFilledCircle(Circle_t* circle);

//draw rounded rectangle
void drawRoundedRect(Rectangle_t* rect);

#endif