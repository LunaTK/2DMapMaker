/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.display.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import mapmaker2.display.handler.PaperHandler;

/**
 *
 * @author LunaTK
 */
public class MouseDragListener implements MouseMotionListener {

    private PaperHandler ph;
    
    public MouseDragListener(PaperHandler ph){
        this.ph = ph;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Dragged!");
        System.out.println(MouseClickListener.getLastX()+" "+MouseClickListener.getLastY());
        ph.updateBlock(MouseClickListener.getLastX(), MouseClickListener.getLastY(), e.getX(), e.getY(), true);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
