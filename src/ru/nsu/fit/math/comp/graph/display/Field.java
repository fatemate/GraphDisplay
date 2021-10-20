package ru.nsu.fit.math.comp.graph.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Field {
    private double centerX = 0.0;
    private double centerY = 0.0;
    private double scale = 1.0;
    private int gridStep = 100;
    private JLabel[] xLabels;
    private JLabel[] yLabels;
    private int centerColumn;
    private int centerRow;
    private double step;

    public Field(int width, int height, JFrame frame){
        step = scale / gridStep;
        centerColumn = width / 2;
        centerRow = height / 2;

        gridLabelsMake(width, height);
        addLabels(frame);
    }

    public void setGridStep(int gridStep, JFrame frame, BufferedImage image){
        if(gridStep > 0) {
            this.gridStep = gridStep;
            step = scale / gridStep;
            removeLabels(frame);
            gridLabelsMake(image.getWidth(), image.getHeight());
            addLabels(frame);
        }
    }

    public int getGridStep(){
        return this.gridStep;
    }

    public double getX(int column){
        return centerX + (column - centerColumn) * step;
    }

    public double getY(int row){
        return centerY + (row - centerRow) * step;
    }

    public int getColumn(double x){
        if (Double.isInfinite(x)) return -1;
        return centerColumn + (int)Math.round((x - centerX) / step);
    }

    public int getRow(double y){
        if (Double.isInfinite(y)) return -1;
        return centerRow - (int)Math.round((y - centerY) / step);
    }

    public void zoomIn(){
        scale /= 2;
        step = scale / gridStep;
    }

    public void zoomOut(){
        scale *= 2;
        step = scale / gridStep;
        centerX -= centerX % scale;
        centerY -= centerY % scale;
    }

    public void moveToCenter() {
        centerX = 0.0;
        centerY = 0.0;
    }
    public void normolize() {
        centerX = 0.0;
        centerY = 0.0;
        scale = 1.0;
        step = scale / gridStep;
    }

    public void moveUp(){
        centerY += scale;
    }

    public void moveDown(){
        centerY -= scale;
    }

    public void moveRight(){
        centerX += scale;
    }

    public void moveLeft(){
        centerX -= scale;
    }

    public void paintGrid(BufferedImage image, int color){
        int width = image.getWidth();
        int height = image.getHeight();
        int j = 0;

        for(int i = centerColumn; i >= 0; i -= gridStep) {
            paintLine(image, color, i, true);
            setDoubleLabel(xLabels, j, getX(i));
            j++;
        }
        for(int i = centerColumn; i < width; i += gridStep) {
            paintLine(image, color, i, true);
            setDoubleLabel(xLabels, j, getX(i));
            j++;
        }

        j = 0;
        for(int i = centerRow; i >= 0; i -= gridStep) {
            paintLine(image, color, i, false);
            setDoubleLabel(yLabels, j, getY(i));
            j++;
        }
        for(int i = centerRow; i < height; i += gridStep) {
            paintLine(image, color, i, false);
            setDoubleLabel(yLabels, j, getY(i));
            j++;
        }

        paintLine(image, color, getColumn(0.0) - 1, true);
        paintLine(image, color, getColumn(0.0), true);
        paintLine(image, color, getColumn(0.0) + 1, true);
        paintLine(image, color, getRow(0.0) - 1, false);
        paintLine(image, color, getRow(0.0), false);
        paintLine(image, color, getRow(0.0) + 1, false);
    }

    private void paintLine(BufferedImage image, int color, int offset, boolean isX){
        if(isX) {
            if ((offset < 0) || (offset >= image.getWidth()))
                return;

            for (int y = image.getHeight() - 1; y > 0; y--)
                image.setRGB(offset, y, color);
        }
        else {
            if ((offset < 0) || (offset >= image.getHeight()))
                return;

            for (int x = image.getHeight() - 1; x > 0; x--)
                image.setRGB(x, offset, color);
        }
    }

    private void gridLabelsMake(int width, int height){
        xLabels = new JLabel[width / gridStep + 2];
        yLabels = new JLabel[height / gridStep + 2];
        int j = 0;

        for(int i = centerColumn; i >= 0; i -= gridStep) {
            initLabelX(xLabels, j, i - (gridStep / 2), 0);
            j++;
        }

        for(int i = centerColumn; i < width; i += gridStep) {
            initLabelX(xLabels, j, i - (gridStep / 2), 0);
            j++;
        }

        j = 0;
        for(int i = centerRow; i >= 0; i -= gridStep) {
            initLabelY(yLabels, j, width - gridStep, i - (gridStep / 2));
            j++;
        }

        for(int i = centerRow; i < height; i += gridStep){
            initLabelY(yLabels, j, width - gridStep, i - (gridStep / 2));
            j++;
        }
    }

    private void initLabelX(JLabel[] label, int index, int posX, int posY){
        label[index] = new JLabel();
        label[index].setHorizontalAlignment(JLabel.CENTER);
        label[index].setVerticalAlignment(JLabel.TOP);
        label[index].setBounds(posX, posY, gridStep, gridStep);
        label[index].setBackground(Color.WHITE);
        label[index].setFont(new Font("Arial", 0, 10));
    }

    private void initLabelY(JLabel[] label, int index, int posX, int posY){
        label[index] = new JLabel();
        label[index].setHorizontalAlignment(JLabel.RIGHT);
        label[index].setVerticalAlignment(JLabel.CENTER);
        label[index].setBounds(posX, posY, gridStep, gridStep);
        label[index].setBackground(Color.WHITE);
        label[index].setFont(new Font("Arial", 0, 10));
    }

    private void addLabels(JFrame frame){
        for(int i = 0; i < xLabels.length; i++)
            if(xLabels[i] != null) frame.add(xLabels[i]);
        for(int i = 0; i < yLabels.length; i++)
            if(yLabels[i] != null) frame.add(yLabels[i]);
    }

    private void removeLabels(JFrame frame){
        for(int i = 0; i < xLabels.length; i++)
            if(xLabels[i] != null) frame.remove(xLabels[i]);
        for(int i = 0; i < yLabels.length; i++)
            if(yLabels[i] != null) frame.remove(yLabels[i]);
    }

    private void setDoubleLabel(JLabel[] label, int index, double value){
        if((Math.abs(value) < 0.001) || (Math.abs(value) > 99999))
            label[index].setText(String.format("%.2e" , value));
        else
            label[index].setText(String.format("%4.3f" , value));
    }
}
