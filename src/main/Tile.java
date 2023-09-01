package main;

import java.awt.Color;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

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
    static int collisionType;
    static int randInt;
    static Random random = new Random();

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
            case "Line" -> UpdateLine(x, y);
            case "L_Left" -> UpdateL_Left(x, y);
            case "L_Right" -> UpdateL_Right(x, y);
            // the square doesn't rotate
            case "Square" -> {for(int r = x; r<x+2; r++){board[r][y].colour = Color.yellow;}
                              for(int r = x; r<x+2; r++){board[r][y-1].colour = Color.yellow;}}
            case "S_Left" -> UpdateS_Left(x, y);
            case "S_Right" -> UpdateS_Right(x, y);
            case "T_Piece" -> UpdateT_Piece(x, y);
        }

    }

    public static void UpdatePieceList(int x, int y) {
        switch (GamePanel.currentPiece) {
            case "Line" -> UpdateListLine(x, y);
            case "L_Left" -> UpdateListL_Left(x, y);
            case "L_Right" -> UpdateListL_Right(x, y);
            case "Square" -> {
                for (int r = x; r < x + 2; r++) {
                    partOfTetromino.add(board[r][y]);
                }
                for (int r = x; r < x + 2; r++) {
                    partOfTetromino.add(board[r][y - 1]);
                }
            }
            case "S_Left" -> UpdateListS_Left(x, y);
            case "S_Right" -> UpdateListS_Right(x, y);
            case "T_Piece" -> UpdateListT_Piece(x, y);

        }
    }

    public static void NewPiece(){
        randInt = random.nextInt(7);
        switch (randInt){
            case 0 -> GamePanel.currentPiece = "Line";
            case 1 -> GamePanel.currentPiece = "L_Left";
            case 2 -> GamePanel.currentPiece = "L_Right";
            case 3 -> GamePanel.currentPiece = "Square";
            case 4 -> GamePanel.currentPiece = "S_Left";
            case 5 -> GamePanel.currentPiece = "S_Right";
            case 6 -> GamePanel.currentPiece = "T_Piece";
        }
        GamePanel.pieceX = 4;
        GamePanel.pieceY = 2;
        Tile.ResetMovingTiles();
        Tile.UpdatePieceList(GamePanel.pieceX, GamePanel.pieceY);
    }

    public static void UpdateStationary(){
            for (Tile tile: partOfTetromino){
                try{
                    if (board[tile.X][tile.Y+1].stationary){
                        for (Tile currentTile: partOfTetromino){
                            currentTile.stationary = true;
                        }
                        break;
                    }
                }
                catch (ArrayIndexOutOfBoundsException ignored){}
            }
    }

    public static int CollisionCheck(){
        collisionType = 0;
        for (Tile currentTile: partOfTetromino){
            if (currentTile.stationary) {
                collisionType = 4;
                NewPiece();
                break;
            }
            if (currentTile.Y == 17){
                for(Tile tile: partOfTetromino)
                    tile.stationary = true;
                collisionType = 1;
                NewPiece();
                break;
            }
            if (currentTile.X == 0){
                System.out.println("Yep");
                GamePanel.keyH.leftBlocked = true;
                collisionType = 2;
                break;
            }
            if (currentTile.X == 9){
                GamePanel.keyH.rightBlocked = true;
                collisionType = 3;
                break;
            }
        }
        return collisionType;
    }

    public static void ResetMovingTiles() {
        for (Tile[] row : board) {
            for (Tile tile : row) {
                if (!tile.stationary) {
                    tile.colour = null;
                }
            }
        }
        for (int i = 0; i<4; i++){
            partOfTetromino.clear();
        }
        UpdatePieceList(GamePanel.pieceX, GamePanel.pieceY);
    }

    public static void UpdateLine(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {board[i][y].colour = Color.cyan;}
                board[x-1][y].colour = Color.cyan;
            }
            case 90 -> {
                for (int i = y; i < y + 3; i++) {board[x][i].colour = Color.cyan;}
                board[x][y - 1].colour = Color.cyan;
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {board[i][y].colour = Color.cyan;}
                board[x+1][y].colour = Color.cyan;
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {board[x][i].colour = Color.cyan;}
                board[x][y + 1].colour = Color.cyan;
            }
        }
    }

    public static void UpdateL_Left(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {board[i][y].colour = Color.blue;}
                board[x][y - 1].colour = Color.blue;
            }
            case 90 -> {
                for (int i = y; i < y + 3; i++) {board[x][i].colour = Color.blue;}
                board[x + 1][y].colour = Color.blue;
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {board[i][y].colour = Color.blue;}
                board[x][y + 1].colour = Color.blue;
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {board[x][i].colour = Color.blue;}
                board[x - 1][y].colour = Color.blue;
            }
        }
    }

    public static void UpdateL_Right(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for(int i = x; i>x-3; i--){board[i][y].colour = Color.orange;}
                board[x][y-1].colour = Color.orange;
            }
            case 90 -> {
                for (int i = y; i > y - 3; i--) {board[x][i].colour = Color.orange;}
                board[x + 1][y].colour = Color.orange;
            }
            case 180 -> {
                for(int i = x; i<x+3; i++){board[i][y].colour = Color.orange;}
                board[x][y+1].colour = Color.orange;
            }
            case 270 -> {
                for (int i = y; i < y + 3; i++) {board[x][i].colour = Color.orange;}
                board[x - 1][y].colour = Color.orange;
            }
        }
    }

    public static void UpdateS_Left (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            board[x][y].colour = Color.red;board[x-1][y].colour = Color.red;
            board[x][y+1].colour = Color.red;board[x+1][y+1].colour = Color.red;
        }
        else{
            board[x][y].colour = Color.red;board[x][y-1].colour = Color.red;
            board[x-1][y].colour = Color.red;board[x-1][y+1].colour = Color.red;
        }

    }

    public static void UpdateS_Right (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            board[x][y].colour = Color.green;board[x+1][y].colour = Color.green;
            board[x][y-1].colour = Color.green;board[x-1][y-1].colour = Color.green;
        }
        else{
            board[x-1][y-1].colour = Color.green;board[x-1][y].colour = Color.green;
            board[x][y-1].colour = Color.green;board[x][y-2].colour = Color.green;
        }

    }

    public static void UpdateT_Piece(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                board[x][y].colour = GamePanel.colourPurple;board[x+1][y].colour = GamePanel.colourPurple;
                board[x-1][y].colour = GamePanel.colourPurple;board[x][y-1].colour = GamePanel.colourPurple;
            }
            case 90 -> {
                board[x][y].colour = GamePanel.colourPurple;board[x][y+1].colour = GamePanel.colourPurple;
                board[x][y-1].colour = GamePanel.colourPurple;board[x+1][y].colour = GamePanel.colourPurple;
            }
            case 180 -> {
                board[x][y].colour = GamePanel.colourPurple;board[x+1][y].colour = GamePanel.colourPurple;
                board[x-1][y].colour = GamePanel.colourPurple;board[x][y+1].colour = GamePanel.colourPurple;
            }
            case 270 -> {
                board[x][y].colour = GamePanel.colourPurple;board[x][y+1].colour = GamePanel.colourPurple;
                board[x][y-1].colour = GamePanel.colourPurple;board[x-1][y].colour = GamePanel.colourPurple;
            }
        }
    }

    public static void UpdateListLine(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {partOfTetromino.add(board[i][y]);}
                partOfTetromino.add(board[x-1][y]);
            }
            case 90 -> {
                partOfTetromino.addAll(Arrays.asList(board[x]).subList(y, y + 3));
                partOfTetromino.add(board[x][y - 1]);
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {partOfTetromino.add(board[i][y]);}
                partOfTetromino.add(board[x+1][y]);
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(board[x][i]);}
                partOfTetromino.add(board[x][y + 1]);
            }
        }
    }

    public static void UpdateListL_Left(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for (int i = x; i < x + 3; i++) {partOfTetromino.add(board[i][y]);}
                partOfTetromino.add(board[x][y - 1]);
            }
            case 90 -> {
                partOfTetromino.addAll(Arrays.asList(board[x]).subList(y, y + 3));
                partOfTetromino.add(board[x + 1][y]);
            }
            case 180 -> {
                for (int i = x; i > x - 3; i--) {partOfTetromino.add(board[i][y]);}
                partOfTetromino.add(board[x][y + 1]);
            }
            case 270 -> {
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(board[x][i]);}
                partOfTetromino.add(board[x - 1][y]);
            }
        }
    }

    public static void UpdateListL_Right(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                for(int i = x; i>x-3; i--){partOfTetromino.add(board[i][y]);}
                partOfTetromino.add(board[x][y-1]);
            }
            case 90 -> {
                for (int i = y; i > y - 3; i--) {partOfTetromino.add(board[x][i]);}
                partOfTetromino.add(board[x + 1][y]);
            }
            case 180 -> {
                for(int i = x; i<x+3; i++){partOfTetromino.add(board[i][y]);}
                partOfTetromino.add(board[x][y+1]);
            }
            case 270 -> {
                partOfTetromino.addAll(Arrays.asList(board[x]).subList(y, y + 3));
                partOfTetromino.add(board[x - 1][y]);
            }
        }
    }

    public static void UpdateListS_Left (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            partOfTetromino.add(board[x][y]);partOfTetromino.add(board[x-1][y]);
            partOfTetromino.add(board[x][y+1]);partOfTetromino.add(board[x+1][y+1]);
        }
        else{
            partOfTetromino.add(board[x][y]);partOfTetromino.add(board[x][y-1]);
            partOfTetromino.add(board[x-1][y]);partOfTetromino.add(board[x-1][y+1]);
        }

    }

    public static void UpdateListS_Right (int x, int y){
        if (GamePanel.pieceRotation == 0 || GamePanel.pieceRotation == 180) {
            partOfTetromino.add(board[x][y]);partOfTetromino.add(board[x+1][y]);
            partOfTetromino.add(board[x][y-1]);partOfTetromino.add(board[x-1][y-1]);
        }
        else{
            partOfTetromino.add(board[x-1][y-1]);partOfTetromino.add(board[x-1][y]);
            partOfTetromino.add(board[x][y-1]);partOfTetromino.add(board[x][y-2]);
        }

    }

    public static void UpdateListT_Piece(int x, int y){
        switch (GamePanel.pieceRotation) {
            case 0 -> {
                partOfTetromino.add(board[x][y]);partOfTetromino.add(board[x+1][y]);
                partOfTetromino.add(board[x-1][y]);partOfTetromino.add(board[x][y-1]);
            }
            case 90 -> {
                partOfTetromino.add(board[x][y]);partOfTetromino.add(board[x][y+1]);
                partOfTetromino.add(board[x][y-1]);partOfTetromino.add(board[x+1][y]);
            }
            case 180 -> {
                partOfTetromino.add(board[x][y]);partOfTetromino.add(board[x+1][y]);
                partOfTetromino.add(board[x-1][y]);partOfTetromino.add(board[x][y+1]);
            }
            case 270 -> {
                partOfTetromino.add(board[x][y]);partOfTetromino.add(board[x][y+1]);
                partOfTetromino.add(board[x][y-1]);partOfTetromino.add(board[x-1][y]);
            }
        }
    }


}
