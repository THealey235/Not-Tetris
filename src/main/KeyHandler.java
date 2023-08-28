package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler  implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //returns number of key pressed

        if (code == KeyEvent.VK_W || code == 38){ // 38 = up arrow
            upPressed = true;
        }
        if (code == KeyEvent.VK_A || code == 37){  // 37 = left arrow
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S || code == 40){ // 40 = down arrow
            downPressed = true;
        }
        if (code == KeyEvent.VK_D || code == 39){ // 39 = right arrow
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == 38){ // 38 = up arrow
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == 37){  // 37 = left arrow
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == 40){ // 40 = down arrow
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == 39){ // 39 = right arrow
            rightPressed = false;
        }
    }
}
