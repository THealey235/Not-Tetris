package main;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    static final int originalTileSize = 16; //16x16 tile
    static final int scale = 3;

    static final int tileSize = originalTileSize * scale; //48*48
    final int maxScreenCol = 10;//length of game board
    final int maxScreenRow = 18;//height of game board
    final int screenWidth = (tileSize * maxScreenCol) + 240;//720, I added 240 to create a border
    final int screenHeight = (tileSize * maxScreenRow) + 100;//964, I added 100 to create a border

    Color BG_colour = Color.black;
    Color borderColour= Color.white;
    Color colourGrey = new Color(90,90,90);
    public static Color colourPurple = new Color(128,0,128);


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Set piece's default position
    static int pieceX = 4; //not actual x and y values, just used for index ing of Tile.board
    int pieceY = 2;
    public static String currentPiece = "T_Piece";
    static int pieceRotation = 0;//in degrees going clockwise
//    Tile[] tilesInPiece = new Tile[n]; fix for collision


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(BG_colour);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //in short: the game loop
    @Override
    public void run() {

        while(gameThread != null){
           //1 UPDATE: update information such as character positions
           update();
           //2 DRAW: draw the screen with the update information
           repaint();
        }

    }
    public void update(){
        Tile.UpdateColourOfTiles(pieceX, pieceY);
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(borderColour);
        g2.drawRect(59, 49, 482, 866);

        g2.setColor(colourGrey);
        for (int x = 108; x<539; x += 48){
            g2.drawLine(x,51,x,913);
        }
        for (int y = 98; y<913; y += 48){
            g2.drawLine(61, y,539, y);
        }
        for (Tile[] row: Tile.board){
            for (Tile tile: row){
                if (tile.colour != null) {
                    g2.setColor(tile.colour);
                    g2.fillRect(tile.x, tile.y, tileSize, tileSize);
                    g2.setColor(borderColour);
                    g2.drawRect(tile.x,tile.y,tileSize,tileSize);
                }
                }
            }
        g2.dispose();
        }
}
