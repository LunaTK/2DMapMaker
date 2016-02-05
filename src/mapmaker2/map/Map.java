/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.map;

import java.awt.image.BufferedImage;
import java.util.TreeSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author LunaTK
 */
public class Map {
    BufferedImage bgImg, tileImg;
    TreeSet path;
    TreeSet portal;
    TreeSet<Tile> tile;
    
    public Map(JSONObject map){
        tile = new TreeSet();
        JSONArray tileArr = (JSONArray)map.get("Tile");
        for(Object o : tileArr){
            tile.add(new Tile(o));
        }
        for(Tile t : tile){
            System.out.println(t);
        }
    }
    
    public static void main(String[] args){
       // Map m = new Map();
    }
    
    public JSONObject toJSONObject(){
        JSONObject jobj = new JSONObject();
        jobj.put("Tile", 12);
        return jobj;
    }
    
    private JSONObject toJSONObject(TreeSet<?> ts){
        return null;
    }

}
