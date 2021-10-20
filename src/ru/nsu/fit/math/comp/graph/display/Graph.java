package ru.nsu.fit.math.comp.graph.display;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Graph extends Thread{
    public static final int BLACK = 0x000000;
    public static final int GRAY = 0x808080;
    public static final int SILVER = 0xC0C0C0;
    public static final int WHITE = 0xFFFFFF;

    public static final int RED = 0xFF0000;
    public static final int FUCHSIA = 0xFF00FF;
    public static final int PURPLE = 0x800080;
    public static final int MAROON = 0x800000;

    public static final int YELLOW = 0xFFFF00;
    public static final int OLIVE = 0x808000;
    public static final int ORANGE = 0xFFA500;
    public static final int ORANGE_RED = 0xFF4500;

    public static final int GREEN = 0x008000;
    public static final int LIME = 0x00FF00;
    public static final int DARK_GREEN = 0x006400;
    public static final int LIGHT_GREEN = 0x90EE90;

    public static final int BLUE = 0x0000FF;
    public static final int AQUA = 0x00FFFF;
    public static final int DARK_BLUE = 0x00008B;
    public static final int LIGHT_BLUE = 0xADD8E6;


    JFrame frame;
    JPanel pane;
    BufferedImage image;
    ArrayList <Function> functions = new ArrayList<>();
    Field field;
    private InputHandler handler;
    private int gridColor = SILVER;
    private int backgroundColor = WHITE;



    public Graph(int width, int height){
        int w, h;
        if (width % 2 == 0) w = width + 1; else w = width;
        if (height % 2 == 0) h = height + 1; else h = height;

        frame = new JFrame("Graph");
        frame.setSize(w+16, h+39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        handler = new InputHandler(this);
        field = new Field(w, h, frame);
        image = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        frame.add(pane);
        frame.setVisible(true);
    }

    public void setBackgroundColor(int color){
        this.backgroundColor = color;
    }

    public void setGridColor(int color){
        this.gridColor = color;
    }

    public void setGridStep(int step) {
        field.setGridStep(step, frame, image);
        display();
    }

    @Override
    public void run(){ }

    public void display() {
        int width = image.getWidth();
        int height = image.getHeight();

        fillImage(image, backgroundColor);
        field.paintGrid(image, gridColor);
        for (int i = 0; i < functions.size(); i++){
            displayFunction(functions.get(i), width, height);
        }

        frame.repaint();
    }

    public void addFunction(Function function){
        functions.add(function);
        display();
    }

    public void deleteFunction(Function function){
        int id = function.getId();
        for (int i = functions.size() - 1; i > 0; i--){
            if (functions.get(i).getId() == id) {
                functions.remove(i);
                return;
            }
        }
    }


    private void displayFunction(Function f, int width, int height){
        int oldRow = 0;
        int row, gap;
        boolean isContinue = true;

        for(int i = 0; i < width; i++){
            row = field.getRow(f.getValue(field.getX(i)));

            if ((row >= height) || (row < 0)) {
                isContinue = true;
                continue;
            }

            if(isContinue){
                oldRow = row;
                isContinue = false;
            }

            gap = oldRow - row;
            if (Math.abs(gap) > 1) {
                for(int j = row + gap / 2; j != oldRow; j += signumInt(gap))
                    image.setRGB(i-1, j, f.getColor());
                for(int j = row + gap / 2; j != row; j -= signumInt(gap))
                    image.setRGB(i, j, f.getColor());
            }

            image.setRGB(i, row, f.getColor());
            oldRow = row;
        }
    }

    private void fillImage(BufferedImage image, int color){
        int width = image.getWidth();
        int height = image.getHeight();
        int[] arr = new int[width * height];

        for(int i = 0; i < width * height; i++)
            arr[i] = color;
        image.setRGB(0,0, width, height, arr,0,0);
    }

    private int signumInt(int x){
        if(x > 0) return 1;
        else{
            if(x < 0) return (-1);
            else return 0;
        }
    }
}
