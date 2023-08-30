package main;

import java.awt.Color;
import java.util.Arrays;
import java.util.ArrayList;

public class Tile {

    public Color colour = null;
    public int x;
    public int y;
    //explained in constructor
    public int X;
    public int Y;
    public static Tile[][] board = new Tile[10][18];
    private boolean stationary = false;
    public static ArrayList<Tile> partOfTetromino = new ArrayList<>();
    static boolean isColliding = false;
    boolean touchingStatic = false;

    public Tile(int x, int y){
        //Relative to the GamePanel
        this.x = x;
        this.y = y;
        //Relative to the board
        this.X = (x-60)/48;
        this.Y = (y-50)/48;

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
    public static void UpdatePieceList(int x, int y) {
        switch (GamePanel.currentPiece) {
            case "Line" -> Tile.UpdateListLine(x, y);
            case "L_Left" -> Tile.UpdateListL_Left(x, y);
            case "L_Right" -> Tile.UpdateListL_Right(x, y);
            case "Square" -> {
                for (int r = x; r < x + 2; r++) {
                    Tile.partOfTetromino.add(Tile.board[r][y]);
                }
                for (int r = x; r < x + 2; r++) {
                    Tile.partOfTetromino.add(Tile.board[r][y - 1]);
                }
            }
            case "S_Left" -> Tile.UpdateListS_Left(x, y);
            case "S_Right" -> Tile.UpdateListS_Right(x, y);
            case "T_Piece" -> Tile.UpdateListT_Piece(x, y);
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean CollisionCheck(){
        isColliding = false;
        for (Tile currentTile: partOfTetromino){
            if (currentTile.touchingStatic) {
                System.out.println("passed 1");
                isColliding = true;
                for (Tile tile : partOfTetromino)
                    tile.stationary = true;
                break;
            }
            if (currentTile.Y == 17){
                System.out.println("passed 2");
                isColliding = true;
                for(Tile tile: partOfTetromino)
                    tile.stationary = true;
            }
            if (currentTile.X == 0){
                System.out.println("passed 3");
                isColliding = true;
            }
            if (currentTile.X == 9){
                System.out.println("Passed 4");
                isColliding = true;
            }
        }
        return isColliding;
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
            }
            case 90 -> {
                for (int i = y; i < y + 3; i++) {Tile.board[x][i].colour = Color.cyan;}
                Tile.board[x][y - 1].colour = Color.cyan;
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {Tile.board[i][y].colour = Color.cyan;}
                Tile.board[x+1][y].colour = Color.cyan;
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {Tile.board[x][i].colour = Color.cyan;}
                Tile.board[x][y + 1].colour = Color.cyan;
            }
        }
    }

    public static void UpdateL_Left(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {Tile.board[i][y].colour = Color.blue;}
                Tile.board[x][y - 1].colour = Color.blue;
            }
            case 90 -> {
                for (int i = y; i < y + 3; i++) {Tile.board[x][i].colour = Color.blue;}
                Tile.board[x + 1][y].colour = Color.blue;
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {Tile.board[i][y].colour = Color.blue;}
                Tile.board[x][y + 1].colour = Color.blue;
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {Tile.board[x][i].colour = Color.blue;}
                Tile.board[x - 1][y].colour = Color.blue;
            }
        }
    }

    public static void UpdateL_Right(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for(int i = x; i>x-3; i--){Tile.board[i][y].colour = Color.orange;}
                Tile.board[x][y-1].colour = Color.orange;
            }
            case 90 -> {
                for (int i = y; i > y - 3; i--) {Tile.board[x][i].colour = Color.orange;}
                Tile.board[x + 1][y].colour = Color.orange;
            }
            case 180 -> {
                for(int i = x; i<x+3; i++){Tile.board[i][y].colour = Color.orange;}
                Tile.board[x][y+1].colour = Color.orange;
            }
            case 270 -> {
                for (int i = y; i < y + 3; i++) {Tile.board[x][i].colour = Color.orange;}
                Tile.board[x - 1][y].colour = Color.orange;
            }
        }
    }

    public static void UpdateS_Left (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            Tile.board[x][y].colour = Color.red;Tile.board[x-1][y].colour = Color.red;
            Tile.board[x][y+1].colour = Color.red;Tile.board[x+1][y+1].colour = Color.red;
        }
        else{
            Tile.board[x][y].colour = Color.red;Tile.board[x][y-1].colour = Color.red;
            Tile.board[x-1][y].colour = Color.red;Tile.board[x-1][y+1].colour = Color.red;
        }

    }

    public static void UpdateS_Right (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            Tile.board[x][y].colour = Color.green;Tile.board[x+1][y].colour = Color.green;
            Tile.board[x][y-1].colour = Color.green;Tile.board[x-1][y-1].colour = Color.green;
        }
        else{
            Tile.board[x-1][y-1].colour = Color.green;Tile.board[x-1][y].colour = Color.green;
            Tile.board[x][y-1].colour = Color.green;Tile.board[x][y-2].colour = Color.green;
        }

    }

    public static void UpdateT_Piece(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x+1][y].colour = GamePanel.colourPurple;
                Tile.board[x-1][y].colour = GamePanel.colourPurple;Tile.board[x][y-1].colour = GamePanel.colourPurple;
            }
            case 90 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x][y+1].colour = GamePanel.colourPurple;
                Tile.board[x][y-1].colour = GamePanel.colourPurple;Tile.board[x+1][y].colour = GamePanel.colourPurple;
            }
            case 180 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x+1][y].colour = GamePanel.colourPurple;
                Tile.board[x-1][y].colour = GamePanel.colourPurple;Tile.board[x][y+1].colour = GamePanel.colourPurple;
            }
            case 270 -> {
                Tile.board[x][y].colour = GamePanel.colourPurple;Tile.board[x][y+1].colour = GamePanel.colourPurple;
                Tile.board[x][y-1].colour = GamePanel.colourPurple;Tile.board[x-1][y].colour = GamePanel.colourPurple;
            }
        }
    }

    public static void UpdateListLine(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x-1][y]);
            }
            case 90 -> {
                partOfTetromino.addAll(Arrays.asList(Tile.board[x]).subList(y, y + 3));
                partOfTetromino.add(Tile.board[x][y - 1]);
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x+1][y]);
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x][y + 1]);
            }
        }
    }

    public static void UpdateListL_Left(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y - 1]);
            }
            case 90 -> {
                partOfTetromino.addAll(Arrays.asList(Tile.board[x]).subList(y, y + 3));
                partOfTetromino.add(Tile.board[x + 1][y]);
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y + 1]);
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x - 1][y]);
            }
        }
    }

    public static void UpdateListL_Right(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for(int i = x; i>x-3; i--){partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y-1]);
            }
            case 90 -> {
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(Tile.board[x][i]);}
                partOfTetromino.add(Tile.board[x + 1][y]);
            }
            case 180 -> {
                for(int i = x; i<x+3; i++){partOfTetromino.add(Tile.board[i][y]);}
                partOfTetromino.add(Tile.board[x][y+1]);
            }
            case 270 -> {
                partOfTetromino.addAll(Arrays.asList(Tile.board[x]).subList(y, y + 3));
                partOfTetromino.add(Tile.board[x - 1][y]);
            }
        }
    }

    public static void UpdateListS_Left (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x-1][y]);
            partOfTetromino.add(Tile.board[x][y+1]);partOfTetromino.add(Tile.board[x+1][y+1]);
        }
        else{
            partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x][y-1]);
            partOfTetromino.add(Tile.board[x-1][y]);partOfTetromino.add(Tile.board[x-1][y+1]);
        }

    }

    public static void UpdateListS_Right (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x+1][y]);
            partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x-1][y-1]);
        }
        else{
            partOfTetromino.add(Tile.board[x-1][y-1]);partOfTetromino.add(Tile.board[x-1][y]);
            partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x][y-2]);
        }

    }

    public static void UpdateListT_Piece(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x+1][y]);
                partOfTetromino.add(Tile.board[x-1][y]);partOfTetromino.add(Tile.board[x][y-1]);
            }
            case 90 -> {
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x][y+1]);
                partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x+1][y]);
            }
            case 180 -> {
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x+1][y]);
                partOfTetromino.add(Tile.board[x-1][y]);partOfTetromino.add(Tile.board[x][y+1]);
            }
            case 270 -> {
                partOfTetromino.add(Tile.board[x][y]);partOfTetromino.add(Tile.board[x][y+1]);
                partOfTetromino.add(Tile.board[x][y-1]);partOfTetromino.add(Tile.board[x-1][y]);
            }
        }
    }


}
