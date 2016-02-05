/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.display.handler;

import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.File;
import mapmaker2.display.listener.MouseClickListener;
import mapmaker2.display.listener.MouseDragListener;
import mapmaker2.imageloader.ImageLoader;
import mapmaker2.json.JSONHandler;
import org.json.simple.*;

/**
 *
 * @author LunaTK
 */
public class PaperHandler extends CanvasHandler {
    private int startX,startY, endX, endY,xCount,yCount;

 
    private boolean blocked;
    private static BufferedImage blockImg;
    private JSONObject jobj;

    public PaperHandler(Canvas canvas) {
        super(canvas, 21);
        jobj = new JSONObject();
        jobj.put("Tile", new JSONArray());
        jobj.put("Mob", new JSONArray());
        jobj.put("Portal", new JSONArray());
        jobj.put("Npc", new JSONArray());
    }

    @Override
    public void render() {
        g.clearRect(0, 0, width, height);
        g.drawImage(content, movX, movY, null);
        if(blocked){
            //System.out.println(startX + " " + startY + " " + endX + " " + endY);
            xCount = Math.abs(startX-endX)/size;
            yCount = Math.abs(startY-endY)/size;
           // System.out.println(xCount + " : " + yCount);
            for(int i = 0;i<xCount;i++)
                for(int j=0;j<yCount;j++){
                    g.drawImage(blockImg, startX+size*i+movX, startY+size*j+movY, null);
                }
        }
        bs.show();
    }

    public int getxCount() {
        return xCount;
    }

    public int getyCount() {
        return yCount;
    }

    public void updateBlock(int startX, int startY, int endX, int endY, boolean onoff) {
        this.startX = startX;
        this.startX -= movX;
        this.startY = startY;
        this.startY -= movY;
        this.endX = endX;
        this.endX -= movX;
        this.endY = endY;
        this.endY -= movY;
        this.startX -= this.startX%size;
        this.startY -= this.startY%size;
        this.endX -= this.endX%size;
        this.endY -= this.endY%size;
        
        this.blocked = onoff;
        
        render();
    }
    
    public void newPage(int x, int y){
        content = new BufferedImage(x,y,BufferedImage.TYPE_4BYTE_ABGR);
        width = x;
        height = y;
        render();
    }

    @Override
    public void init() {
        width = canvas.getWidth()*2;
        height = canvas.getHeight()*2;
        canvas.addMouseListener(new MouseClickListener(this));
        canvas.addMouseMotionListener(new MouseDragListener(this));
        blockImg = ImageLoader.loadImage("/textures/block.png");
        newPage(width,height);
    }
    
    public void addBlock(BufferedImage img, int x,int y,int xSize,int ySize, boolean isConstruct){
        x = x-(x)%21;
        y = y-(y)%21;
        for(int i = 0;i<xSize;i++)
            for(int j = 0;j<ySize;j++)
                content.getGraphics().drawImage(img, x+i*size, y+j*size, null);
        //content.getGraphics().drawImage(img, x, y, null);
        if(!isConstruct){
            JSONObject tile = new JSONObject();
            tile.put("x", x);
            tile.put("y", y);
            tile.put("xSize",xSize);
            tile.put("ySize",ySize);
            tile.put("type", PalletHandler.getCurrentBlockType());
            ((JSONArray)jobj.get("Tile")).add(tile);
            render();
        }
    }
    
    public void fillRect(boolean isConstruct){
//        for(int i = 0;i<xCount;i++)
//            for(int j = 0;j<yCount;j++){
//                //addBlock(PalletHandler.getCurrentBlock(),startX+movX+size*i,startY+movY+j*size);
//                content.getGraphics().drawImage(PalletHandler.getCurrentBlock(), startX+movX+size*i, startY+movY+j*size, null);
//            }
        addBlock(PalletHandler.getCurrentBlock(),startX+movX,startY+movY,xCount,yCount,isConstruct);
        render();
        updateBlock(0, 0, 0, 0, false);
    }
    
    public void deleteRect(){
        content.getGraphics().fillRect(startX+movX, startY+movY, xCount*size, yCount*size);
        render();
        
    }
    
    public void setJSON(String path){
        jobj = (JSONObject) JSONHandler.getJSONObject(path);
        construct();
    }
    
    public void setJSON(File file){
        jobj = (JSONObject) JSONHandler.getJSONObject(file);
        construct();
    }
    
    public JSONObject getJobj() {
        return jobj;
    }
    
    public void construct(){
        content.getGraphics().fillRect(0, 0, width, height);
        if(jobj!=null){
            JSONArray jarr = (JSONArray) jobj.get("Tile");
            for(Object o : jarr){
                JSONObject jo = (JSONObject)o;
                System.out.println(jo.get("x").toString());
                addBlock(PalletHandler.getData(Integer.parseInt(jo.get("type").toString())),
                        Integer.parseInt(jo.get("x").toString()),Integer.parseInt(jo.get("y").toString()),
                        Integer.parseInt(jo.get("xSize").toString()),Integer.parseInt(jo.get("ySize").toString()),true);
            }
            render();
        } else {
            System.err.println("Null Map Construction!");
        }
    }
    
}
