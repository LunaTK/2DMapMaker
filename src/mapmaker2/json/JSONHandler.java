/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.json;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.json.simple.*;
import org.json.simple.parser.*;


/**
 *
 * @author LunaTK
 * 
 */
public class JSONHandler {
    
    private static final JSONParser parser = new JSONParser();
    private static File currentFile;
    
    public static Object getJSONObject(String path){
        if(path.charAt(0) == '/'){
            return getJSONObject(new File(JSONHandler.class.getResource(path).getPath()));
        } else {
            return getJSONObject(new File(path));
        }
    }
    
    public static Object getJSONObject(File file){
        Object obj = null;
        currentFile = file;
        try {
            FileReader fr = new FileReader(currentFile);
            obj = parser.parse(fr);
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return obj;
    }
    
    public static void save(JSONObject jobj){
        JFileChooser chooser = new JFileChooser();
        BufferedWriter bw;
        chooser.setDialogTitle("Save");
        chooser.setApproveButtonText("Save");
        if(currentFile!=null)
            chooser.setSelectedFile(currentFile);
        chooser.showOpenDialog(null);
        if(chooser.getSelectedFile()!=null){
            try {
                File f = chooser.getSelectedFile();
                if(!f.exists()){
                    f.createNewFile();
                }
                bw = new BufferedWriter(new FileWriter(f));
                bw.write(jobj.toString());
                bw.close();
                System.out.println("save : " + jobj);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
         
    }
}
