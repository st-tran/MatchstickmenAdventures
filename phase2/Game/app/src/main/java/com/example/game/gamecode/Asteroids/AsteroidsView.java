package com.example.game.gamecode.Asteroids;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.example.game.R;
import com.example.game.gamecode.GameObject;
import com.example.game.gamecode.GameThread;
import com.example.game.gamecode.GameView;
import com.example.game.leaderboardcode.Saver;

import java.util.HashMap;

class AsteroidsView extends GameView implements AsteroidsDrawer<Canvas, Bitmap> {
  private float density;

  public AsteroidsView(Context context, int playAreaWidth, int playAreaHeight) {
    super(context);
    this.thread = new GameThread(getHolder(), this, (Saver) context);
    this.gameBackend = new AsteroidGameManager(playAreaWidth, playAreaHeight);
    setPresenter(new AsteroidsPresenter<>(this, this.gameBackend));
    density = context.getResources().getDisplayMetrics().density;
  }


  @Override
  public HashMap<Class<? extends GameObject>, Bitmap> getClassToSprite() {
    HashMap<Class<? extends GameObject>, Bitmap> classToSprites = new HashMap<>();
    classToSprites.put(Asteroid.class, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid));
    classToSprites.put(Projectile.class, BitmapFactory.decodeResource(getResources(), R.drawable.laser));
    classToSprites.put(Ship.class, BitmapFactory.decodeResource(getResources(), R.drawable.ship));
    return classToSprites;
  }

  @Override
  public HashMap<Class<? extends GameObject>, Integer> getClassToColor() {
    HashMap<Class<? extends GameObject>, Integer> classToColor = new HashMap<>();
    classToColor.put(Asteroid.class, AsteroidCustomizations.asteroidColor[AsteroidCustomizations.themeIndex]);
    classToColor.put(Projectile.class, AsteroidCustomizations.projectileColor[AsteroidCustomizations.themeIndex]);
    classToColor.put(Ship.class, AsteroidCustomizations.shipColor[AsteroidCustomizations.themeIndex]);
    return classToColor;
  }

  @Override
  public float getDensity() {
    return density;
  }

  @Override
  public void drawSprite(
          Canvas drawingSurface,
          Bitmap sprite,
          int color,
          double x,
          double y,
          double angle,
          double width,
          double height) {
    Paint paint = new Paint();
    ColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
    paint.setColorFilter(filter);
    Matrix matrix = new Matrix();
    matrix.preRotate((float) Math.toDegrees(angle), sprite.getWidth() / 2, sprite.getHeight() / 2);
    matrix.postScale(
            (float) (width / (float) sprite.getWidth()),
            (float) (height / (float) sprite.getHeight()),
            sprite.getWidth() / 2,
            sprite.getHeight() / 2);
    matrix.postTranslate(
            (float) (x - sprite.getWidth() / 2.0), (float) (y - sprite.getHeight() / 2.0));
    drawingSurface.drawBitmap(sprite, matrix, paint);
  }

  @Override
  public void drawSolidBackground(Canvas drawingSurface, int color) {
    Paint backgroundPaint = new Paint();
    backgroundPaint.setStyle(Paint.Style.FILL);
    backgroundPaint.setColor(color);
    drawingSurface.drawRect(drawingSurface.getClipBounds(), backgroundPaint);
  }

  @Override
  public void drawText(Canvas drawingSurface, String text, int color, float x, float y, float fontSize, int rowOffset) {
    Paint paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(fontSize);
    paint.setTextAlign(Paint.Align.RIGHT);
    drawingSurface.drawText(text, x, y + rowOffset*paint.getTextSize(), paint);
  }
}