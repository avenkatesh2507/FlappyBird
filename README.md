# Flappy Bird

A Flappy Bird clone built with Java (Swing) and playable in the browser.

## Play online

**[Play the game](https://avenkatesh2507.github.io/FlappyBird/)** (GitHub Pages)

### One-time setup (repo owner)

If the link above does not work yet:

1. Open [Settings → Pages](https://github.com/avenkatesh2507/FlappyBird/settings/pages)
2. Under **Build and deployment**, set **Source** to **Deploy from a branch**
3. Choose branch **`gh-pages`**, folder **`/ (root)`**, then **Save**
4. Wait 1–2 minutes for the workflow to finish, then refresh the play link

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
