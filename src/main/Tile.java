package main;

import java.awt.Color;

public class Tile {

    public Color colour;
    public int x;
    public int y;
    public static Tile[][] board = new Tile[10][18];

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.colour = null;

    }

    public static void CreateTiles(){
        for (int i = 0; i<10; i++){
            for (int i1 = 0; i1<18; i1++){
                Tile.board[i][i1] = new Tile(60 + (GamePanel.tileSize*i), 50 + (GamePanel.tileSize*i1));
            }
        }
    }
    public static void UpdateColourOfTiles(int row, int col){
        switch (GamePanel.currentPiece){
            case "Line" :
                for(int r = row; r<row+4; r++){Tile.board[r][col].colour = Color.cyan;}
            case "L_left" :
                GamePanel.pieceColour = Color.blue; break;
            case "L_right" :
                GamePanel.pieceColour = Color.orange; break;
            case "Square" :
                GamePanel.pieceColour = Color.yellow; break;
            case "S_left" :
                GamePanel.pieceColour = Color.red; break;
            case "S_right":
                GamePanel.pieceColour = Color.green; break;
            case "T_piece":
                GamePanel.pieceColour = GamePanel.colourPurple;
        }
    }
}
