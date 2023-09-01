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


    public static KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Set piece's default position
    static int pieceX = 4; //not actual x and y values, just used for indexing of Tile.board
    static int pieceY = 16;
    public static String currentPiece = "L_Left";
    static int pieceRotation = 0;//in degrees going clockwise


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




    //the game loop
    @Override
    public void run() {

        int FPS = 30;
        double drawInterval = 1000000000d/FPS; //0.01666666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime-lastTime) / drawInterval;

            lastTime = currentTime;

            //for when I do fast drop: drawCount == FPS/3 || drawCount == (FPS/3)*2 ||

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                gravity();
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }
    public void update(){
        Tile.UpdateColourOfTiles(pieceX, pieceY);
        Tile.UpdateStationary();
    }

    public void gravity(){
        if (Tile.CollisionCheck() != 1){
            pieceY += 1;
            Tile.ResetMovingTiles();
            Tile.UpdatePieceList(GamePanel.pieceX, GamePanel.pieceY);
        }
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
