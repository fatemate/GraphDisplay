package ru.nsu.fit.math.comp.graph.display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class InputHandler {
    Graph graph;
    GraphKeyListener keyListener = new GraphKeyListener();

    public InputHandler(Graph graph){
        this.graph = graph;
        graph.frame.addKeyListener(this.keyListener);
    }

    private class GraphKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    graph.field.moveUp();
                    break;

                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    graph.field.moveDown();
                    break;

                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    graph.field.moveRight();
                    break;

                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    graph.field.moveLeft();
                    break;

                case KeyEvent.VK_PLUS:
                case KeyEvent.VK_EQUALS:
                    graph.field.zoomIn();
                    break;

                case KeyEvent.VK_MINUS:
                    graph.field.zoomOut();
                    break;

                case KeyEvent.VK_SPACE:
                    graph.field.moveToCenter();
                    break;

                case KeyEvent.VK_SHIFT:
                    graph.field.normolize();
                    break;

                case KeyEvent.VK_ESCAPE:
                    graph.frame.dispatchEvent(new WindowEvent( graph.frame, WindowEvent.WINDOW_CLOSING));
                    break;

                default:
                    break;
            }
            graph.display();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
