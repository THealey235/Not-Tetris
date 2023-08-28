package main;

import javax.swing.JFrame;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tetris");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Tile.CreateTiles();

        Main.UpdatePieceColour();

        gamePanel.startGameThread();
    }

    public static void UpdatePieceColour(){
        switch (GamePanel.currentPiece) {
            case "Line" -> GamePanel.pieceColour = Color.cyan;
            case "L_left" -> GamePanel.pieceColour = Color.blue;
            case "L_right" -> GamePanel.pieceColour = Color.orange;
            case "Square" -> GamePanel.pieceColour = Color.yellow;
            case "S_left" -> GamePanel.pieceColour = Color.red;
            case "S_right" -> GamePanel.pieceColour = Color.green;
            case "T_piece" -> GamePanel.pieceColour = GamePanel.colourPurple;
        }
    }
}
