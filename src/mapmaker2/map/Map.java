/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.map;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.TreeSet;
import mapmaker2.display.handler.PalletHandler;
import mapmaker2.display.handler.PaperHandler;
import mapmaker2.json.JSONHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author LunaTK
 */
public class Map {
    
    int width, height;
    BufferedImage bgImg, tileImg;
    TreeSet<Path> path;
    TreeSet<Portal> portal;
    TreeSet<Tile> tile;
    
    public Map(JSONObject map){
        path = new TreeSet<Path>();
        portal = new TreeSet<Portal>();
        tile = new TreeSet<Tile>();
        if(map!=null){
            JSONArray jArr;
            jArr = (JSONArray)map.get("Info");
            width = Integer.parseInt(jArr.get(0).toString());
            height = Integer.parseInt(jArr.get(1).toString());
            jArr = (JSONArray)map.get("Tile");
            for(Object o : jArr){
                tile.add(new Tile(o));
                System.out.println("TIle add : " + o);
            }
            jArr = (JSONArray)map.get("Path");
    //        for(Object o : jArr){
    //            path.add(new Path(o));
    //        }
            for(Tile t : tile){
                System.out.println(t);
            }
    //        for(Path p : path){
    //            System.out.println(p);
    //        }
        } else {
            width = 800;
            height = 400;
        }
        construct();
        
    }
    
    public static void main(String[] args){
//        JSONObject map = (JSONObject) JSONHandler.getJSONObject("/json/map.json");
 //       Map m = new Map(map);

//        TreeSet<String> set = new TreeSet<String>();
//        set.add("first");
//        set.add("second");
//        set.add("third");
//        Iterator<String> i = set.iterator();
//        i.next();
//        i.next();
//        i.remove();
//        for(String s : set){
//            System.out.println(s);
//        }
    }
    
    public JSONObject toJSONObject(){
        JSONObject jobj = new JSONObject();
        jobj.put("Tile", 12);
        return jobj;
    }
    
    private JSONObject toJSONObject(TreeSet<?> ts){ //구성요소 TreeSet들을 JSONObject로 만들어줌
        //TODO : TreeSet to JSONObject 구현
        return null;
    }
    
    public void addTile(Tile t){
        tile.add(t);
    }
    
    public void removeTile(int x, int y){
        Iterator<Tile> i = tile.iterator();
        Tile tmp;
        while(i.hasNext()){
            tmp = i.next();
            if(tmp.getX()==x && tmp.getY()==y)
                i.remove();
        }
    }
    
    public void removeTile(Tile t){
        tile.remove(t);
    }
    
    public BufferedImage getBG(){
        return bgImg;
    }
    
    public BufferedImage getTileImg(){
        return tileImg;
    }
    
    public void construct(){
        tileImg = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        Iterator<Tile> i = tile.iterator();
        Tile tmp;
        while(i.hasNext()){
            tmp = i.next();
            int x = tmp.getX();
            int y = tmp.getY();
            int n = tmp.getxSize();
            int m = tmp.getySize();
            for(int c1 = 0;c1<n;c1++)
                for(int c2 = 0;c2<m;c2++)
                    tileImg.getGraphics().drawImage(PalletHandler.getData(tmp.getType()), x+21*c1, y+21*c2, null);
        }
        
    }

}
