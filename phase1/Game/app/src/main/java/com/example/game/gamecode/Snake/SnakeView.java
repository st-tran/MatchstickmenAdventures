package com.example.game.gamecode.Snake;

import android.content.Context;
import android.graphics.Color;

import com.example.game.gamecode.GameThread;
import com.example.game.gamecode.GameView;

public class SnakeView extends GameView {

  public SnakeView(Context context) {
    super(context);
    thread = new GameThread(getHolder(), this);
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

  public String getStatistics(){
    return ((SnakeBackend)gameBackend).getStatistics();
  }

  public String getValue(){
    return gameBackend.toString();
  }
}
