import kaplay from "https://unpkg.com/kaplay@3001.0.19/dist/kaplay.mjs";
//kaplay();

const k = kaplay({
    width: 1900,
    height: 800
})

k.loadSprite("fluttershy", "sprites/fluttershy.png")
k.loadSprite("bg", "sprites/background.png")
k.loadSprite("flower0", "sprites/flower0.png")
k.loadSprite("flower1", "sprites/flower1.png")
k.loadSprite("flower2", "sprites/flower2.png")

k.setGravity(2000)


//GAME SCENE
k.scene("game", ()=> {
    const flowersNames = ["flower0", "flower1", "flower2"]

    // load bg
    const bg = k.add([
        k.sprite("bg"),
        k.pos(0, 0),
        k.z(0)          //last layer
    ])
    const bgData = k.getSprite("bg");
    bg.scale = k.vec2(
        1900 / bgData.data.width,
        800  / bgData.data.height
    );

    const GROUND_Y = 800
    const ground = k.add([
        k.rect(1900, 200),
        k.pos(0, GROUND_Y),
        k.color(0, 0, 0, 0),        //transparent ground
        k.area(),
        k.body({isStatic: true}),
        k.z(1)                      //layer 1 (over the bg)
    ])

    //-----PLAYER-----
    const player = k.add([
        k.sprite("fluttershy"),
        k.pos(150, GROUND_Y),
        k.anchor("bot"),            //pos() refers to the sprite feet
        k.area(),
        k.body(),
        k.z(2)                      //layer 2 (over the ground)
    ])

    k.onLoad(() => {
        const playerData = k.getSprite("fluttershy")
        player.scale = k.vec2(225 / playerData.data.width,
                            225 / playerData.data.width
        )
    })

    //---------JUMP-------------
    k.onKeyPress("space", () => {
        if (player.isGrounded()) {   //jumps only if it is on the ground
            player.jump(1000);
        }
    });

    //-------OBSTACLES-------

    const FLOWER_SPEED = 400
    function spawnFlower(){
        const flowerType = k.choose(flowersNames)

        const flower = k.add([
            k.sprite(flowerType),
            k.pos(1950, GROUND_Y),
            k.anchor("bot"),
           // k.body(),
            k.area(),
            k.z(2),
            "obstacle"
        ])

        flower.scale = k.vec2(0.5, 0.5)

        flower.onUpdate(() => {
            flower.move(-FLOWER_SPEED, 0)

            if(flower.pos.x < -100) k.destroy(flower)
        })
    }

    // spawn every 1.5,..., 3 seconds

    function scheduleNextFlower(){
        k.wait(k.rand(1.5, 3.0), () => {
            spawnFlower()
            scheduleNextFlower()
        })
    }

    //----COLLISION DETECTION-------
    player.onCollide("obstacle", () => {
        k.paused = true
        k.go("gameover")
    })

    spawnFlower()
    scheduleNextFlower()

})

k.scene("gameover", () => {

    // load bg
    const bg = k.add([
        k.sprite("bg"),
        k.pos(0, 0),
        k.z(0)          //last layer
    ])

    const bgData = k.getSprite("bg");
    bg.scale = k.vec2(
        1900 / bgData.data.width,
        800  / bgData.data.height
    );

    k.add([
        k.text("GAME OVER!", {size: 64}),
        k.color(k.Color.fromHex('#ffc5d3')),
        k.pos(k.center()),
        k.anchor("center")
    ])

    k.onKeyPress("space", () => {
        k.paused = false
        k.go("game")
    })
})

k.onLoad(() => {
    k.go("game")    
})
