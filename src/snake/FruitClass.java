/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author christian and fred
 */
public class FruitClass {
    // Creamos una variable random para que coloque la fruta en un aposicion aleatoria
    private Random random;
    private Point fruit;
    
    public FruitClass(){
        random = new Random();
        fruit = new Point();
    }

    // Método para aññar fruta
    public void newFruit() {
        fruit.x = random.nextInt(39);
        fruit.y = random.nextInt(28) + 1;
    }
    
    // Método para la posicion de la fruta, tamao y color
    public void drawFruit(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(fruit.x*20, fruit.y*20, 20, 20);
    }
    // Método para poder acceder a fruit
    public Point getFruit() {
        return fruit;
    }
}
