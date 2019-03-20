/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package claseSnake;


/**
 *
 * @author christian and fred
 */
public class Player implements Comparable<Player>{
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
        @Override
    public String toString() {
        return name + "," + score ;
    }

    @Override
    public int compareTo(Player o) {
        if (this.score < o.score) {
            return 1;
        } else if (this.score > o.score) {
            return -1;
        } else {
            return 0;
        }

    }
    

    
    
}
