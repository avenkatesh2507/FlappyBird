const canvas = document.getElementById("game");
const g = canvas.getContext("2d");

let birdX = 100;
let birdY = 250;
let velocity = 0;

let pipeX = 360;
let gapY = randomGap();

const pipeWidth = 60;
const gapHeight = 150;

let score = 0;
let startTime = Date.now();
let gameOver = false;

function randomGap() {
  return Math.floor(Math.random() * 300) + 100;
}

function endGame() {
  gameOver = true;
}

function restartGame() {
  birdX = 100;
  birdY = 250;
  velocity = 0;
  pipeX = 400;
  gapY = randomGap();
  score = 0;
  startTime = Date.now();
  gameOver = false;
}

function flap() {
  if (gameOver) return;
  velocity = -13;
}

function update() {
  if (gameOver) return;

  velocity += 1;
  birdY += velocity;

  pipeX -= 5;

  if (pipeX < -pipeWidth) {
    pipeX = 400;
    gapY = randomGap();
    score++;
  }

  const h = canvas.height;

  if (birdY < 0 || birdY > h - 30) {
    endGame();
    return;
  }

  if (birdX + 30 > pipeX && birdX < pipeX + pipeWidth) {
    const gapTop = gapY - gapHeight / 2;
    const gapBottom = gapY + gapHeight / 2;
    if (birdY < gapTop || birdY + 30 > gapBottom) {
      endGame();
    }
  }
}

function draw() {
  g.fillStyle = "cyan";
  g.fillRect(0, 0, canvas.width, canvas.height);

  g.fillStyle = "yellow";
  g.beginPath();
  g.arc(birdX + 15, birdY + 15, 15, 0, Math.PI * 2);
  g.fill();

  g.fillStyle = "green";
  const gapTop = gapY - gapHeight / 2;
  const gapBottom = gapY + gapHeight / 2;
  g.fillRect(pipeX, 0, pipeWidth, gapTop);
  g.fillRect(pipeX, gapBottom, pipeWidth, canvas.height - gapBottom);

  g.fillStyle = "black";
  g.font = "bold 30px Arial";
  g.fillText("Score: " + score, 20, 40);

  const elapsedSeconds = Math.floor((Date.now() - startTime) / 1000);
  g.fillText("Time: " + elapsedSeconds, 220, 40);

  if (gameOver) {
    g.fillStyle = "red";
    g.font = "bold 42px Arial";
    g.fillText("GAME OVER", 85, 260);

    g.fillStyle = "black";
    g.font = "bold 18px Arial";
    g.fillText("Press R or SPACE to restart", 75, 295);
  }
}

function tick() {
  update();
  draw();
}

document.addEventListener("keydown", (e) => {
  if (e.code === "Space") {
    e.preventDefault();
    if (gameOver) restartGame();
    else flap();
  }
  if (e.code === "KeyR" && gameOver) restartGame();
});

canvas.addEventListener("click", () => {
  if (gameOver) restartGame();
  else flap();
});

setInterval(tick, 20);
