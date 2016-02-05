/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapmaker2.display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import mapmaker2.display.handler.*;
import mapmaker2.display.listener.*;
import mapmaker2.json.JSONHandler;

/**
 *
 * @author LunaTK
 */
public class Display {
    
    private final JFrame frame;
    private final Canvas paper, pallet;
    private final JPanel paperPanel, palletPanel, buttonPanel;
    private final JScrollBar[] jsb;
    private final JButton unselectBtn,fillBtn, deleteBtn, openBtn, saveBtn;
    private CanvasHandler paperHandler, palletHandler;
    
    
    
    public Display(){
        //Frame Initialize
        frame = new JFrame("Map Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1470, 725);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        
        //JScrollBar Initialize
        jsb = new JScrollBar[4];
        for(int i = 0;i<4;i++)
            jsb[i] = new JScrollBar();
        
        //Paper Panel Initialize
        paperPanel = new JPanel();
        paperPanel.setLayout(null);
        paperPanel.setBounds(10, 10, 1020, 620);
        paper = new Canvas();        
        paper.setBackground(Color.white);
        paper.setBounds(0,0,1000,600);
        jsb[0].setOrientation(JScrollBar.HORIZONTAL);
        jsb[0].setBounds(0,600,1000,20);
        jsb[1].setBounds(1000,0,20,600);
        paperPanel.add(paper);
        paperPanel.add(jsb[0]);
        paperPanel.add(jsb[1]);
        
        //Pallet Panel Initialize
        palletPanel = new JPanel();
        palletPanel.setBounds(1040,10,420,620);
        palletPanel.setLayout(null);
        pallet = new Canvas();
        pallet.setBackground(Color.white);
        pallet.setBounds(0,0,400,600);
        jsb[2].setOrientation(JScrollBar.HORIZONTAL);
        jsb[2].setBounds(0,600,400,20);
        jsb[3].setBounds(400,0,20,600);
        palletPanel.add(pallet);
        palletPanel.add(jsb[2]);
        palletPanel.add(jsb[3]);
        
        //Button Panel Initialize
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        unselectBtn = new JButton("선택해제");
        unselectBtn.setBounds(0,0,300,40);
        fillBtn = new JButton("채우기");
        fillBtn.setBounds(350,0,300,40);
        deleteBtn = new JButton("선택삭제");
        deleteBtn.setBounds(700,0,300,40);
        openBtn = new JButton("열기");
        openBtn.setBounds(1050,0,100,40);
        saveBtn = new JButton("저장");
        saveBtn.setBounds(1200,0,100,40);
        buttonPanel.setBounds(10,640,1450,40);
        buttonPanel.add(unselectBtn);
        buttonPanel.add(fillBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(openBtn);
        
        //Frame Add
        frame.add(paperPanel);
        frame.add(palletPanel);
        frame.add(buttonPanel);
        
        //CanvasHandler Initialize
        paperHandler = new PaperHandler(paper);
        palletHandler = new PalletHandler(pallet);
        jsb[0].addAdjustmentListener(new BarListener(paperHandler, BarListener.HORIZONTAL));
        jsb[1].addAdjustmentListener(new BarListener(paperHandler, BarListener.VERTICAL));
        jsb[2].addAdjustmentListener(new BarListener(palletHandler,BarListener.HORIZONTAL));
        jsb[3].addAdjustmentListener(new BarListener(palletHandler,BarListener.VERTICAL));
        unselectBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ((PalletHandler)palletHandler).updateBlock(0, 0, false);
            }
        });
        fillBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((PaperHandler)paperHandler).fillRect(false);
            }
        });
        deleteBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ((PaperHandler)paperHandler).deleteRect();
            }
        });
        openBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                //FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON FILE", ".json");
                //chooser.addChoosableFileFilter(filter);
                if(chooser.getSelectedFile()!=null)
                    ((PaperHandler)paperHandler).setJSON(chooser.getSelectedFile());
            }
        });
        saveBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JSONHandler.save(((PaperHandler)paperHandler).getJobj());
            }
        });
        
        paperHandler.init();
        palletHandler.init();
        /*
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        String filePath = chooser.getSelectedFile().getPath();
        System.out.println(filePath);
*/

       
    }
    
}
