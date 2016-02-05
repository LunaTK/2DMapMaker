/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.display.handler;

import mapmaker2.imageloader.ImageLoader;
import java.awt.Canvas;
import java.awt.image.BufferedImage;
import mapmaker2.display.listener.MouseClickListener;

/**
 *
 * @author LunaTK
 */
public class PalletHandler extends CanvasHandler {
    
    private static BufferedImage blockImg;
    private static BufferedImage[][] data;
    private static int blockX, blockY;
    private static boolean blocked;

    public PalletHandler(Canvas canvas) {
        super(canvas, 23);
    }

    @Override
    public void render() {
        g.clearRect(0, 0, width, height);
        g.drawImage(content, movX, movY, null);
        if(blocked){
            g.drawImage(blockImg,blockX + movX,blockY + movY,null);
        }
        bs.show();
//        g.dispose();
    }
    
    public void updateBlock(int x, int y, boolean onoff) {
        blocked = onoff;
        if(!onoff){
            blockX = 0;
            blockY = 0;
        } else {
            x -= movX;
            y -= movY;
            blockX = x-((x-3)%size);
            blockY = y-((y-3)%size);
        }
    //    System.out.println("blockX : " + blockX + " blockY : " + blockY );
        render();
    }

    @Override
    public void init() {
        setContent(ImageLoader.loadImage("/textures/spritesheet.png"));
        blockImg = ImageLoader.loadImage("/textures/block.png");
        width = canvas.getWidth();
        height = canvas.getHeight();
        data = new BufferedImage[30][16];
        for(int i = 0;i<30;i++)
            for(int j=0;j<16;j++)
                data[i][j] = content.getSubimage(3+i*23, 3+j*23, 21, 21);
        canvas.addMouseListener(new MouseClickListener(this));
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockY() {
        return blockY;
    }
    
    public static boolean isBlocked() {
        return blocked;
    }
  
    public static BufferedImage getCurrentBlock(){
 //       System.out.println("blockX : " + blockX + " blockY : " + blockY );
 //       System.out.println("X : " + (blockX - 3)%23 + " Y : " + (blockY - 3)%23);
        return data[(blockX - 3)/23][(blockY - 3)/23];
    }
    
    public static int getCurrentBlockType(){
        return (blockX-3)/23+(blockY-3)*30/23;
    }
    
    public static BufferedImage getData(int x, int y){
        return data[x][y];
    }
    
    public static BufferedImage getData(int type){
        return data[type%30][type/30];
    }
    
}
