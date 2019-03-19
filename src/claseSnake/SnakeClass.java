/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package claseSnake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
/**
 *
 * @author fojomars
 */
public class SnakeClass {
    
    private ArrayList<Point> serpiente = new ArrayList<Point>();
    private int snakeX = 0;
    private int snakeY=0;


    public SnakeClass(){
        serpiente.add(new Point(20,15));
    }

    public ArrayList<Point> getLargo(){
        return serpiente;
    }

/**
 * Establecemos tamaño punto
 * @param g 
 */
    
    public void drawSnake(Graphics g){
        for(int i = 0; i < serpiente.size()-1; i++){
            g.setColor(Color.GREEN);
            Point p = serpiente.get(i);
            g.fillRect(p.x*20, p.y*20,20,20);
        }
    }
    
    
    public void moveSnake(){
        for(int i = serpiente.size()-1; i>0;i--){
            serpiente.get(i).setLocation(serpiente.get(i-1));
        }
        serpiente.get(0).x +=snakeX;
        serpiente.get(0).y +=snakeY;
    }
    
    
    public void crecimientoSnake(){
        serpiente.add(new Point());
    }
    
    public void direccion(String d){
        switch(d){
            case "arriba":
                snakeX = 0;
                snakeY = -1;
                break;
            case "abajo":
                snakeX = 0;
                snakeY = 1;
                break;
            case "izquierda":
                snakeX = -1;
                snakeY = 0;
                break;
            case "derecha":
                snakeX = 1;
                snakeY = 0;
                break;
        }
    }
    
    
    
}
