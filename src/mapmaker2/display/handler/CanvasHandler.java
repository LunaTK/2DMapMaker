/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.display.handler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 *
 * @author LunaTK
 */
public abstract class CanvasHandler {
    
    protected Canvas canvas;
    protected BufferStrategy bs;
    protected Graphics g;
    protected int movX, movY, width, height;
    protected BufferedImage content;
    protected int size;
    //protected boolean blocked;
    
    public CanvasHandler(Canvas canvas, int size){
        this.canvas = canvas;
        this.size = size;
        if(canvas.getBufferStrategy() == null){
            canvas.createBufferStrategy(3);
        }
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }
    
    public abstract void init();
    
    public abstract void render();
    
    public void setContent(BufferedImage img){
        content = img;
        render();
    }
    
    public void updateX(int x){
        movX = -(int)((x/90.0f)*(Math.abs(content.getWidth()-canvas.getWidth())));
        render();
    }
    
    public void updateY(int y){
        movY = -(int)((y/90.0f)*(Math.abs(content.getHeight()-canvas.getHeight())));
        render();
    }
    
    public void newPage(int x,int y){
        System.out.println("Shadow Effect Error!");
    }
    
    public int getSize(){
        return size;
    }
    
}
