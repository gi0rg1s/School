import kaplay from "https://unpkg.com/kaplay@3001.0.19/dist/kaplay.mjs";
//kaplay();

const k = kaplay({
    width: 1200,
    height: 600
})
k.loadSprite("fluttershy", "sprites/fluttershy.png")
k.loadSprite("bg", "sprites/background.png")
k.loadSprite("flower0", "sprites/flower0.png")
k.loadSprite("flower1", "sprites/flower1.png")
k.loadSprite("flower2", "sprites/flower2.png")
k.setGravity(2000);

const bg = k.add([
    k.sprite("bg"),
    k.pos(0, 0)
])

k.onLoad(() => {
    const bgData = k.getSprite("bg")
    const scaleX = 1200 / bgData.data.width
    const scaleY = 600 / bgData.data.height
    bg.scale = k.vec2(scaleX, scaleY)
})
const flowersNames = ["flower0", "flower1", "flower2"]

const ground = k.add([
    k.rect(1200, 400),
    k.pos(0, 500),
    k.area(),
    k.body({isStatic: true})
])

const player = k.add([
    k.sprite("fluttershy"),
    k.pos(0, 0),
    k.area(),
    K.body(),
])
k.onLoad(() => {
    const playerData = k.getSprite("fluttershy")
    const scaleX = 200 / playerData.data.width
    const scaleY = 200 / playerData.data.width
    player.scale = k.vec2(scaleX, scaleY)
})

onkeypress("space", () =>{
    if(player.isGrounded())player.jump(100)
})