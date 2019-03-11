/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fojomars
 */
public class SnakeGame extends JFrame implements KeyListener{
    private int windowWidth = 800;
    private int windowHeight = 600;
    private SnakeClass snake;
    private FruitClass fruit;
    private int Score;
    private long goal;
    private int velocidad = 80;
   
   
    public static void main(String[] args) {
        new SnakeGame();
    }
   
    public SnakeGame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);
       
        this.createBufferStrategy(2);
        this.addKeyListener(this);
       
        inicializoObjetos();
       
        while(true) {
            juego();
            sleep();
        }
        
    }
   
    private void inicializoObjetos() {
        snake = new SnakeClass();
        snake.crecimientoSnake();
        fruit = new FruitClass();
        fruit.newFruit();
        Score=0;
        
    }
   
    private void dibujoPantalla() {
        
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
       
        try {
            g = bf.getDrawGraphics();
                       
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, windowWidth, windowHeight);
            
            fruit.dibujoFruit(g);
            snake.dibujoSnake(g);
            muestroPuntos(g);
            
        } finally {
            g.dispose();
        }
               
        bf.show();
     
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void chequearColision(){
        if(snake.getLargo().get(0).equals(fruit.getFruit())) {
            fruit.newFruit();
            snake.crecimientoSnake();
            Score+=10;
        }
        
        if(snake.getLargo().get(0).x < 0 || snake.getLargo().get(0).x > 39 ||
            snake.getLargo().get(0).y < 1 || snake.getLargo().get(0).y > 29) {
            inicializoObjetos();
        }
        
        
        for(int n = 1; n < snake.getLargo().size(); n++) {
            if(snake.getLargo().get(0).equals(snake.getLargo().get(n)) && snake.getLargo().size() > 2) {
                inicializoObjetos();
            }
        }
    }
    
    private void muestroPuntos(Graphics g){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Score: " + Score, 20, 50);
            
    }
    
    private void sleep(){
        goal = ( System.currentTimeMillis() + velocidad );
        while(System.currentTimeMillis() < goal) {
        
        }
    }

    
@Override
public void keyPressed(KeyEvent e) {

    int tecla = e.getKeyCode();
   
    switch (tecla){
        case KeyEvent.VK_UP:
            snake.direccion("arriba");
            break;
        case KeyEvent.VK_DOWN:
            snake.direccion("abajo");
            break;
        case KeyEvent.VK_LEFT:
            snake.direccion("izquierda");
            break;
        case KeyEvent.VK_RIGHT:
            snake.direccion("derecha");
            break;
        case KeyEvent.VK_E:
            System.exit(0);
      
    }
}

    private void juego() {
        
        snake.muevoSnake();
        chequearColision();
        dibujoPantalla();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JOptionPane.showMessageDialog(null, "Tecla errónea");
    }

    @Override
    public void keyReleased(KeyEvent e) {
         
    }

}
