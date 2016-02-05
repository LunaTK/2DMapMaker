/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.display.listener;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import mapmaker2.display.handler.CanvasHandler;

/**
 *
 * @author LunaTK
 */
public class BarListener implements AdjustmentListener {
    
    private CanvasHandler ch;
    private int orientation;
    public static final int HORIZONTAL = 0, VERTICAL = 1;
    
    public BarListener(CanvasHandler ch, int orientation){
        this.ch = ch;
        this.orientation = orientation;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        switch(orientation){
            case HORIZONTAL:
                ch.updateX(e.getAdjustable().getValue());
                break;
            case VERTICAL:
                ch.updateY(e.getAdjustable().getValue());
                break;
        }
    }
    
}
