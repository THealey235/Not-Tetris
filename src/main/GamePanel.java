package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel{
    // SREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48*48
    final int maxScreenCol = 10;//length of game board
    final int maxScreenRow = 18;//height of game board
    final int screenWidth = tileSize * (maxScreenCol*2);//960
    final int screenHeight = (tileSize * maxScreenRow) + 100;//964, i add 100 to create a border

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
    }

}
