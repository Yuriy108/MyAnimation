package com.example.myanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private Bitmap bitmap;
    private List<Rect> frames;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private double frameTime;
    private double timeForCurrentFrame;
    private double x;
    private double y;

    private double velocityX;
    private double velocityY;

    private int padding;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = Math.abs(currentFrame);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public double getVx() {
        return velocityX;
    }

    public void setVx(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVy() {
        return velocityY;
    }

    public void setVy(double velocityY) {
        this.velocityY = velocityY;
    }
    public int getFrameCount(

    ){
        return frames.size();
    }
    public void addFrame(Rect frame){
        frames.add(frame);

    }
    public void update (int ms) {

        timeForCurrentFrame += ms;

        if (timeForCurrentFrame >= frameTime) {
            currentFrame = (currentFrame + 1) % frames.size();
            timeForCurrentFrame = timeForCurrentFrame - frameTime;
        }

        x = x + velocityX * ms/1000.0;
        y = y + velocityY * ms/1000.0;
    }


    public Sprite( double x, double y, double velocityX, double velocityY, Rect initialFrame,Bitmap bitmap) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.frames=new ArrayList<>();
        this.frames.add(initialFrame);
        this.timeForCurrentFrame=0.0;
        this.frameTime=0.1;
        this.currentFrame=0;
        this.padding=20;
        this.frameWidth=initialFrame.width();
        this.frameHeight=initialFrame.height();
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        Rect destination = new Rect((int)x, (int)y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(bitmap, frames.get(currentFrame), destination,  p);
    }
    public Rect getBoundingBoxRect () {
        return new Rect((int)x+padding, (int)y+padding, (int)(x + frameWidth - 2 *padding),
                (int)(y + frameHeight - 2* padding));
    }
    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }




}
