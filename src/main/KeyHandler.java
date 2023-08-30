package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler  implements KeyListener {

    private boolean upBlocked, downBlocked, rightBlocked, leftBlocked = false;

    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //returns number of key pressed

        if (code == KeyEvent.VK_W || code == 38){ // 38 = up arrow
            if (!upBlocked){
                GamePanel.pieceRotation += 90;
                if (GamePanel.pieceRotation == 360){
                    GamePanel.pieceRotation = 0;
                }
                System.out.println("W "+GamePanel.pieceRotation);
                Tile.ResetMovingTiles();
            }
        }
        if (code == KeyEvent.VK_A || code == 37){  // 37 = left arrow
             if (!leftBlocked){
                 Tile.UpdatePieceList(GamePanel.pieceX, GamePanel.pieceY);
                 if (!Tile.CollisionCheck()) {
                     GamePanel.pieceX -= 1;
                     Tile.ResetMovingTiles();
                 }
            }
        }
        if (code == KeyEvent.VK_S || code == 40){ // 40 = down arrow
            if (!downBlocked){

                if (GamePanel.pieceRotation == 0){
                    GamePanel.pieceRotation = 270;
                }
                else{
                    GamePanel.pieceRotation -= 90;
                }
                Tile.ResetMovingTiles();
                System.out.println("S "+GamePanel.pieceRotation);
            }
        }
        if (code == KeyEvent.VK_D || code == 39){ // 39 = right arrow
            if (!rightBlocked){
                Tile.UpdatePieceList(GamePanel.pieceX, GamePanel.pieceY);
                if (!Tile.CollisionCheck()) {
                    GamePanel.pieceX += 1;
                    Tile.ResetMovingTiles();
                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == 38){ // 38 = up arrow
            upBlocked = false;
        }
        if (code == KeyEvent.VK_A || code == 37){  // 37 = left arrow
            leftBlocked = false;
        }
        if (code == KeyEvent.VK_S || code == 40){ // 40 = down arrow
            downBlocked = false;
        }
        if (code == KeyEvent.VK_D || code == 39){ // 39 = right arrow
            rightBlocked = false;
        }
    }
}
