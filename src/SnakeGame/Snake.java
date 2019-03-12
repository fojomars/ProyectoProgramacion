/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakeGame;

import Fruta.FruitClass;
import claseSnake.SnakeClass;
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
 * @author christian and fred
 */
public class Snake extends JFrame implements KeyListener {
// entendemmos el jframe àra crear la ventana del juego y keyListener para poder controlarlo con teclado

    private int windowWidth = 800;// Variable para modificar el ancho de la ventana
    private int windowHeight = 600;// Variable para modificar el alto de la ventana
    private SnakeClass snake;
    private FruitClass fruit;
    private int Score; // Variable para almacenar la puntuación
    private long goal;
    private int velocidad;// Variable para la velocidad

    public Snake() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);

        this.createBufferStrategy(2);
        this.addKeyListener(this);

        inicializoObjetos();

        while (true) {
            juego();
            sleep();
        }

    }

    public void menu() {

        String[] opcion = {"INICIAR JUEGO ", "SALIR"}; // Iniciar partida o salir
        int start = JOptionPane.showOptionDialog(null, "Establecer Dificultad", null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcion, null);

        switch (start) { //Menu para elegir dificultad
            case 0:

                String[] dificultad = {"Facil", "Medio", "Experto"};
                int niveles = JOptionPane.showOptionDialog(null, "Elegir nivel", null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, dificultad, null);
                switch (niveles) {
                    case 0:
                        velocidad = 100;
                        break;
                    case 1:
                        velocidad = 60;
                        break;
                    case 2:
                        velocidad = 30;
                        break;
                    default:
                        break;
                }

                break;
            case 1 :
                System.exit(0);

            default:
                break;
        }

    }

    private void inicializoObjetos() {
        menu();
        snake = new SnakeClass();
        snake.crecimientoSnake();
        fruit = new FruitClass();
        fruit.newFruit();
        Score = 0;

    }

    private void dibujoPantalla() {

        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;

        try {
            g = bf.getDrawGraphics();

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, windowWidth, windowHeight);

            fruit.drawFruit(g);
            snake.drawSnake(g);
            muestroPuntos(g);

        } finally {
            g.dispose();
        }

        bf.show();

        Toolkit.getDefaultToolkit().sync();
    }

    private void chequearColision() {
        if (snake.getLargo().get(0).equals(fruit.getFruit())) {
            fruit.newFruit();
            snake.crecimientoSnake();
            Score += 10;
        }

        if (snake.getLargo().get(0).x < 0 || snake.getLargo().get(0).x > 39
                || snake.getLargo().get(0).y < 1 || snake.getLargo().get(0).y > 29) {
            inicializoObjetos();
        }

        for (int n = 1; n < snake.getLargo().size(); n++) {
            if (snake.getLargo().get(0).equals(snake.getLargo().get(n)) && snake.getLargo().size() > 2) {
                inicializoObjetos();
            }
        }
    }

    private void muestroPuntos(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + Score, 20, 50);

    }

    private void sleep() {
        goal = (System.currentTimeMillis() + velocidad);
        while (System.currentTimeMillis() < goal) {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int tecla = e.getKeyCode();

        switch (tecla) {
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

        snake.moveSnake();
        chequearColision();
        dibujoPantalla();

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
