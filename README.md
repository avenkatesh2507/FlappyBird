# Flappy Bird

A Flappy Bird clone built with Java (Swing) and playable in the browser.

## Play online

**[Play the game](https://avenkatesh2507.github.io/FlappyBird/)** (GitHub Pages)

## Run locally (Java desktop)

```bash
javac *.java
java Main
```

Controls: **SPACE** to flap · **R** or **SPACE** to restart after game over

## Run locally (browser)

Open `index.html` in a browser, or serve the folder:

```bash
python3 -m http.server 8080
```

Then visit http://localhost:8080

## Project structure

| File | Description |
|------|-------------|
| `Main.java` | Entry point |
| `GameFrame.java` | Window setup |
| `GamePanel.java` | Game logic and rendering |
| `index.html` / `game.js` | Browser version (GitHub Pages) |
