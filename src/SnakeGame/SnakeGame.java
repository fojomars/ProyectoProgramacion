/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakeGame;

import Fruta.FruitClass;
import Utilidades.pedirDatos;
import claseSnake.Player;
import claseSnake.SnakeClass;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author christian and fred
 */
public class SnakeGame extends JFrame implements KeyListener {
// entendemmos el jframe àra crear la ventana del juego y keyListener para poder controlarlo con teclado

    private int windowWidth = 800;// Variable para modificar el ancho de la ventana
    private int windowHeight = 600;// Variable para modificar el alto de la ventana
    private SnakeClass snake;
    private FruitClass fruit;
    private int Score; // Variable para almacenar la puntuación
    private int maxScore = 200;
    private long goal;
    private int velocidad;// Variable para la velocidad
    private String usuario;

    private String name;
    public JFrame jframe;
    public Player miPlayer;
    ArrayList<String> lista = new ArrayList<String>();
    
    
    public SnakeGame() throws IOException {
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
    
    public void nick(){
        usuario = pedirDatos.pedirString("Introduce tu nick: ");
        JOptionPane.showMessageDialog(jframe, name);
    }
    
    public void saveScore() throws IOException {
        int text = JOptionPane.showConfirmDialog(jframe, "La puntacion es: " + Score);
        if(text == JOptionPane.YES_OPTION){
            lista.add(name);
            lista.add(Integer.toString(Score));
            
            for (String lista2 : lista){
                FileWriter fichero = new FileWriter("puntuacion.txt");
                PrintWriter pw = null;
                
                try{
                    pw = new PrintWriter(fichero,true);
                    pw.println("Nombre,");
                    for (String x: lista){
                        pw.print( x + ",");
                    }
                    pw.println(",Score");
                    pw.close();
                }catch (Exception e){
                    System.out.println("Error al guardar la puntación" + e.getMessage());
                } finally {
                    try{
                        if(null != fichero);
                    } catch (Exception e2){
                        System.out.println("Error al guardar");
                    }
                }
                break;
            }
        }
        if (text == JOptionPane.CANCEL_OPTION){
            JOptionPane.showConfirmDialog(jframe, "La puntación no se guardará");      
        }
        if(text == JOptionPane.CLOSED_OPTION){
            System.exit(text);
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

    private void inicializoObjetos() throws IOException {
        menu();
        
        snake = new SnakeClass();
        snake.crecimientoSnake();
        fruit = new FruitClass();
        fruit.newFruit();
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

    private void chequearColision() throws IOException {
        if (snake.getLargo().get(0).equals(fruit.getFruit())) {
            fruit.newFruit();
            snake.crecimientoSnake();
            Score += 10;
        }

        else if (snake.getLargo().get(0).x < 0 || snake.getLargo().get(0).x > 39 || snake.getLargo().get(0).y < 1 || snake.getLargo().get(0).y > 29) {
            inicializoObjetos();
            saveScore();
            
        }

        for (int x = 1; x < snake.getLargo().size(); x++) {
            if (snake.getLargo().get(0).equals(snake.getLargo().get(x)) && snake.getLargo().size() > 2) {
                inicializoObjetos();
                saveScore();
                
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
    
    
    private void juego() throws IOException {
        snake.moveSnake();
        chequearColision();
        dibujoPantalla();
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
            case KeyEvent.VK_SPACE:
                System.exit(0);
                break;
                
        }
    }


    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
