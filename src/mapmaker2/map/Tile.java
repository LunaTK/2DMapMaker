/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.map;

import java.util.Comparator;
import org.json.simple.JSONObject;

/**
 *
 * @author LunaTK
 */
public class Tile implements Comparable<Tile> {
    private final int x,y,xSize,ySize;

    public Tile(int x, int y, int xSize, int ySize) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
    }
    
    public Tile(Object o){
        JSONObject jo = (JSONObject)o;
        x = Integer.parseInt(jo.get("x").toString());
        y = Integer.parseInt(jo.get("y").toString());
        xSize = Integer.parseInt(jo.get("xSize").toString());
        ySize = Integer.parseInt(jo.get("ySize").toString());
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    @Override
    public String toString() {
        return "x : " + x + " y : " + y + " xSize : " + xSize + " ySize : " + ySize;
    }

    @Override
    public int compareTo(Tile o) {
        if(this.x!=o.getX())
            return 1;
        if(this.y!=o.getY())
            return 1;
        return 0;
    }
    
    
    
    
}
