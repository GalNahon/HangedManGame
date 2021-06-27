package com.company;

import javax.swing.*;
import java.awt.*;

public class PaintComponent extends JPanel {

    public static int drawingLevel = 0;
    public static int clearPanel;

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int midWidth = width / 2, midHeight = height / 2, stageW = width / 100, stageH = height / 100;
            g.clearRect(0, 0, width, height);
            g.setColor(new Color(152, 76, 233));
            g.drawLine(midWidth - 45 * stageW, 98 * stageH, midWidth + 40 * stageW, 98 * stageH);
            g.drawLine(midWidth - 40 * stageW, 98 * stageH, midWidth - 40 * stageW, 5 * stageH);
            g.drawLine(midWidth - 40 * stageW, 5 * stageH, midWidth, 5 * stageH);
            g.drawLine(midWidth, 5 * stageH, midWidth, 10 * stageH);
            g.setColor(Color.DARK_GRAY);
            if(clearPanel == 1){
                g.dispose();
                clearPanel = 0;
                drawingLevel = 0;
            }
            for (int i = 0; i < drawingLevel; i++) {
                switch (i) {
                    case 0:
                        g.drawOval(midWidth - 10 * stageW, 10 * stageH, 20 * stageW, 20 * stageH);
                        break;
                    case 1:
                        g.drawLine(midWidth, 30 * stageH, midWidth, 75 * stageH);
                        break;
                    case 2:
                        g.drawLine(midWidth, 45 * stageH, midWidth + 15 * stageW, 60 * stageH);
                        break;
                    case 3:
                        g.drawLine(midWidth, 45 * stageH, midWidth - 15 * stageW, 60 * stageH);
                        break;
                    case 4:
                        g.drawLine(midWidth, 75 * stageH, midWidth + 20 * stageW, 95 * stageH);
                        break;
                    case 5:
                        g.drawLine(midWidth, 75 * stageH, midWidth - 20 * stageW, 95 * stageH);
                        break;
                    case 6:
                        g.setColor(Color.red);
                        g.drawLine(midWidth - 20 * stageW, 10 * stageH, midWidth + 20 * stageW, 95 * stageH);
                        g.setColor(Color.black);
                        break;
                }
            }
        }

    public void drawNextLineInMan(){
        drawingLevel ++;}
    public boolean isEndOfGame(){
        return drawingLevel>=7;}
}
