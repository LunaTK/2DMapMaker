/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.map;

import org.json.simple.JSONObject;

/**
 *
 * @author LunaTK
 */
class Path {

    private float startX,startY,endX,endY;

    public Path(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    
    public Path(Object o){
        JSONObject jo = (JSONObject)o;
        startX = Integer.parseInt(jo.get("startX").toString());
        startY = Integer.parseInt(jo.get("startY").toString());
        endX = Integer.parseInt(jo.get("endX").toString());
        endY = Integer.parseInt(jo.get("endY").toString());
    }
    
    @Override
    public String toString() {
        return "startX : " + startX + " startY : " + startY + " endX : " + endX + " endY : " + endY;
    }
    
    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }
    
}
