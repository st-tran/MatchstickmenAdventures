package com.example.game.gamecode.Snake;

import android.content.Context;
import android.graphics.Color;

import com.example.game.gamecode.GameThread;
import com.example.game.gamecode.GameView;
import com.example.game.leaderboardcode.Saver;

public class SnakeView extends GameView {

  public SnakeView(Context context) {
    super(context);
    thread = new GameThread(getHolder(), this, (Saver) context);
    thread.setUpdateInterval(100);

    int screenHeight = 1650;
    int screenWidth = 960;
    gameBackend = new SnakeBackend(screenHeight, screenWidth);

    ((SnakeBackend) gameBackend).createObjects();
    setCanvasColor(Color.DKGRAY);
  }

  @Override
  public void update() {
    super.update();
  }

  public void setCanvasColor(int color){
    ((SnakeBackend) gameBackend).setCanvasColor(color);
  }

  public String[][] getStatistics(){
    return ((SnakeBackend)gameBackend).getStatistics();
  }

  public String getValue(){
    return gameBackend.toString();
  }

    public void setDifficulty(int difficulty) {
      this.thread.setUpdateInterval(Math.max(500 - (difficulty * 50), 30));
    }

    public void setCharacter(SnakeShape shape) {
        ((SnakeBackend)this.gameBackend).setShape(shape);
    }

    public void setBackground(int background) {
        ((SnakeBackend)this.gameBackend).setCanvasColor(background);
    }
}
