import kaplay from "https://unpkg.com/kaplay@3001.0.19/dist/kaplay.mjs";
kaplay();

const k = kaplay({
    width: 1200,
    height: 600,
    background = color(255, 255, 255)
})
k.loadsprite("fluttershy", "sprites/fluttershy.png")

const player = k.add([
    sprite("fluttershy"),
    pos(0, 0)
])
