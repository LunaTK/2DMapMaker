/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.display.listener;

import java.awt.event.*;
import mapmaker2.display.handler.*;

/**
 *
 * @author LunaTK
 *//**
 *
 * @author LunaTK
 */
public class MouseClickListener implements MouseListener {

    private CanvasHandler ch;
    private static int lastX, lastY;
    
    public MouseClickListener(CanvasHandler ch){
        this.ch = ch;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        System.out.println(lastX + " : " + lastY);
        if(ch instanceof PaperHandler){
            ((PaperHandler)ch).updateBlock(0, 0, 0, 0, false);
            if(PalletHandler.isBlocked()){
                ((PaperHandler)ch).addBlock(PalletHandler.getCurrentBlock(), lastX, lastY,1,1,false);
            } else {
                //TOOD
            }
        } else if(ch instanceof PalletHandler){
            PalletHandler ph = (PalletHandler)ch;
            ph.updateBlock(e.getX(), e.getY(), true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static int getLastX() {
        return lastX;
    }

    public static int getLastY() {
        return lastY;
    }

    
}
