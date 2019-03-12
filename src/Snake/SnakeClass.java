/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
/**
 *
 * @author fojomars
 */
public class SnakeClass {
    
    private ArrayList<Point> largo = new ArrayList<Point>();
    private int snakeX = 0;
    private int snakeY=0;
    
    
    public SnakeClass(){
        largo.add(new Point(20,15));
    }


    
    public ArrayList<Point> getLargo(){
        return largo;
    }

/**
 * Establecemos tamaño punto
 * @param g 
 */
    
    public void dibujoSnake(Graphics g){
        for(int i = 0; i < largo.size()-1; i++){
            g.setColor(Color.GREEN);
            Point p = largo.get(i);
            g.fillRect(p.x*20, p.y*20,20,20);
        }
    }
    
    
    public void muevoSnake(){
        for(int i = largo.size()-1; i>0;i--){
            largo.get(i).setLocation(largo.get(i-1));
        }
        largo.get(0).x +=snakeX;
        largo.get(0).y +=snakeY;
    }
    
    
    public void crecimientoSnake(){
        largo.add(new Point());
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
