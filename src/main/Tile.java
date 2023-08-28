package main;

import java.awt.Color;

public class Tile {

    public Color colour;
    public int x;
    public int y;
    public static Tile[][] board = new Tile[18][10];

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.colour = null;

    }

    public static void CreateTiles(){
        for (int i = 0; i<18; i++){
            for (int i1 = 0; i1<10; i1++){
                Tile.board[i][i1] = new Tile(60 + (GamePanel.tileSize*i), 50 + (GamePanel.tileSize*i1));
            }
        }
    }
}
