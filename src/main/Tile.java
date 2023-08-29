package main;

import java.awt.Color;
import java.util.List;

public class Tile {

    public Color colour = null;
    public int x;
    public int y;
    public static Tile[][] board = new Tile[10][18];
    private boolean stationary = false;
    public static List<Tile> partOfTetromino;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static void CreateTiles(){
        for (int i = 0; i<10; i++){
            for (int i1 = 0; i1<18; i1++){
                Tile.board[i][i1] = new Tile(60 + (GamePanel.tileSize*i), 50 + (GamePanel.tileSize*i1));
            }
        }
    }
    public static void UpdateColourOfTiles(int x, int y){
        switch (GamePanel.currentPiece){
            case "Line" -> Tile.UpdateLine(x, y);
            case "L_Left" -> Tile.UpdateL_Left(x, y);
            case "L_Right" -> Tile.UpdateL_Right(x, y);
            // the square doesn't rotate
            case "Square" -> {for(int r = x; r<x+2; r++){Tile.board[r][y].colour = Color.yellow;}
                              for(int r = x; r<x+2; r++){Tile.board[r][y-1].colour = Color.yellow;}}
            case "S_Left" -> Tile.UpdateS_Left(x, y);
            case "S_Right" -> Tile.UpdateS_Right(x, y);
            case "T_Piece" -> Tile.UpdateT_Piece(x, y);
        }
    }

    public static void ResetMovingTiles() {
        for (Tile[] row : Tile.board) {
            for (Tile tile : row) {
                if (!tile.stationary) {
                    tile.colour = null;
                }
            }
        }
        for (int i = 0; i<4; i++){
            Tile.partOfTetromino.clear();
        }
    }

    public static void UpdateLine(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {Tile.board[i][y].colour = Color.cyan;}
                Tile.board[x-1][y].colour = Color.cyan;
                for (int i = x; i < x + 3; i++) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x-1][y]);
            }
            case 90 -> {
                for (int i = y; i < y + 3; i++) {Tile.board[x][i].colour = Color.cyan;}
                Tile.board[x][y - 1].colour = Color.cyan;
                for (int i = y; i < y + 3; i++) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x][y - 1]);
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {Tile.board[i][y].colour = Color.cyan;}
                Tile.board[x+1][y].colour = Color.cyan;
                for (int i = x; i > x - 3; i--) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x+1][y]);
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {Tile.board[x][i].colour = Color.cyan;}
                Tile.board[x][y + 1].colour = Color.cyan;
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x][y + 1]);
            }
        }
    }
    public static void UpdateL_Left(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {Tile.board[i][y].colour = Color.blue;}
                Tile.board[x][y - 1].colour = Color.blue;
                for (int i = x; i < x + 3; i++) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y - 1]);
            }
            case 90 -> {
                for (int i = y; i < y + 3; i++) {Tile.board[x][i].colour = Color.blue;}
                Tile.board[x + 1][y].colour = Color.blue;
                for (int i = y; i < y + 3; i++) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x + 1][y]);
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {Tile.board[i][y].colour = Color.blue;}
                Tile.board[x][y + 1].colour = Color.blue;
                for (int i = x; i > x - 3; i--) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y + 1]);
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {Tile.board[x][i].colour = Color.blue;}
                Tile.board[x - 1][y].colour = Color.blue;
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x - 1][y]);
            }
        }
    }

    public static void UpdateL_Right(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for(int i = x; i>x-3; i--){Tile.board[i][y].colour = Color.orange;}
                Tile.board[x][y-1].colour = Color.orange;
                for(int i = x; i>x-3; i--){partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y-1]);
            }
            case 90 -> {
                for (int i = y; i > y - 3; i--) {Tile.board[x][i].colour = Color.orange;}
                Tile.board[x + 1][y].colour = Color.orange;
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x + 1][y]);
            }
            case 180 -> {
                for(int i = x; i<x+3; i++){Tile.board[i][y].colour = Color.orange;}
                Tile.board[x][y+1].colour = Color.orange;
                for(int i = x; i<x+3; i++){partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y+1]);
            }
            case 270 -> {
                for (int i = y; i < y + 3; i++) {Tile.board[x][i].colour = Color.orange;}
                Tile.board[x - 1][y].colour = Color.orange;
                for (int i = y; i < y + 3; i++) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x - 1][y]);
            }
        }
    }

    public static void UpdateS_Left (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            Tile.board[x][y].colour = Color.red;Tile.board[x-1][y].colour = Color.red;
            Tile.board[x][y+1].colour = Color.red;Tile.board[x+1][y+1].colour = Color.red;
            partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x-1][y]);
            partOfTetromino.add(Tile.board[x][y+1]);partOfTetromino.add(Tile.board[x+1][y+1]);
        }
        else{
            Tile.board[x][y].colour = Color.red;Tile.board[x][y-1].colour = Color.red;
            Tile.board[x-1][y].colour = Color.red;Tile.board[x-1][y+1].colour = Color.red;
            partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x][y-1]);
            partOfTetromino.add(Tile.board[x-1][y]);partOfTetromino.add(Tile.board[x-1][y+1]);
        }

    }

    public static void UpdateS_Right (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            Tile.board[x][y].colour = Color.green;Tile.board[x+1][y].colour = Color.green;
            Tile.board[x][y-1].colour = Color.green;Tile.board[x-1][y-1].colour = Color.green;
            partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x+1][y]);
            partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x-1][y-1]);
        }
        else{
            Tile.board[x-1][y-1].colour = Color.green;Tile.board[x-1][y].colour = Color.green;
            Tile.board[x][y-1].colour = Color.green;Tile.board[x][y-2].colour = Color.green;
            partOfTetromino.add(Tile.board[x-1][y-1]);partOfTetromino.add(Tile.board[x-1][y]);
            partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x][y-2]);
        }

    }

    public static void UpdateT_Piece(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x+1][y].colour = GamePanel.colourPurple;
                Tile.board[x-1][y].colour = GamePanel.colourPurple;Tile.board[x][y-1].colour = GamePanel.colourPurple;
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x+1][y]);
                partOfTetromino.add(Tile.board[x-1][y]);partOfTetromino.add(Tile.board[x][y-1]);
            }
            case 90 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x][y+1].colour = GamePanel.colourPurple;
                Tile.board[x][y-1].colour = GamePanel.colourPurple;Tile.board[x+1][y].colour = GamePanel.colourPurple;
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x][y+1]);
                partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x+1][y]);
            }
            case 180 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x+1][y].colour = GamePanel.colourPurple;
                Tile.board[x-1][y].colour = GamePanel.colourPurple;Tile.board[x][y+1].colour = GamePanel.colourPurple;
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x+1][y]);
                partOfTetromino.add(Tile.board[x-1][y]);partOfTetromino.add(Tile.board[x][y+1]);
            }
            case 270 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x][y+1].colour = GamePanel.colourPurple;
                Tile.board[x][y-1].colour = GamePanel.colourPurple;Tile.board[x-1][y].colour = GamePanel.colourPurple;
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x][y+1]);
                partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x-1][y]);
            }
        }
    }

    public static boolean CollisionCheck(){
        for (int i = 0; i < 4; i++){
            Tile currentTile = Tile.partOfTetromino.get(i);
            //Finish collision
        }
    }
}
