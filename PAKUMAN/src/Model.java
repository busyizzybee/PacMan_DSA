
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {

	private Dimension d;
    private final Font smallFont = new Font("Arial", Font.BOLD, 16);
    private boolean inGame = false;
    private boolean dying = false;

    private final int BLOCK_SIZE = 36;
    private final int N_BLOCKS = 19;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 6;
    private int PACMAN_SPEED = 3;

    private int N_GHOSTS = 6;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost, blueghost,cherry;
    private Image up, down, left, right;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;
    
    private boolean paused = false; 
    private boolean[] ghostVulnerable;
    private long vulnerableStartTime;
    private long speedStartTime;
    private boolean speedTime = false;
    private boolean ghostsVulnerable = false;
    private boolean isWin = false;
    private boolean isDead = false;
    
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    private int currentSpeed = 3;
    private short[] screenData;
    Timer timer;

    public Model() {
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
        setBackground(Color.BLACK);
    }
    
    private void initVariables() {
        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];
        ghostVulnerable = new boolean[MAX_GHOSTS];
       
        timer = new Timer(40, this);
        timer.start();
    }
    //----Blessy Start----//
	 private void loadImages() {
   	down = new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//down.gif").getImage();
   	up = new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//up.gif").getImage();
   	left = new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//left.gif").getImage();
   	right = new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//right.gif").getImage();
       ghost = new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//ghost.gif").getImage();
       blueghost =  new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//blueghost.gif").getImage();
       cherry =  new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//CherryShadow.png").getImage();
       heart = new ImageIcon("C://Users//Robert//Downloads//pacman-main//pacman-main//images//heart.png").getImage();
   }

 private void drawGhost(Graphics2D g2d, int x, int y, boolean isVulnerable) {
       if (isVulnerable) {
           g2d.drawImage(blueghost, x + 5, y + 5, this);
       } else {
           g2d.drawImage(ghost, x + 5, y + 5, this);
       }
   }
  
   private void drawFruit(Graphics2D g2d, int x, int y) {
   	g2d.drawImage(cherry, x + 5, y + 5, this);
   }

  private void drawPacman(Graphics2D g2d) {
       if (req_dx == -1) {
       	g2d.drawImage(left, pacman_x + 7, pacman_y + 6, this);
       } else if (req_dx == 1) {
       	g2d.drawImage(right, pacman_x + 7, pacman_y + 6, this);
       } else if (req_dy == -1) {
       	g2d.drawImage(up, pacman_x + 7, pacman_y + 6, this);
       } else {
       	g2d.drawImage(down, pacman_x + 7, pacman_y + 6, this);
       }
   }

public void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       g2d.setColor(Color.black);
       g2d.fillRect(0, 0, d.width, d.height);
       drawMaze(g2d);
       drawScore(g2d);
       if (paused) {
           showPausedScreen(g2d); // Show paused screen if paused
       }
       else if(isWin) {
       	showPausedScreen(g2d);
       }
       else if(!isWin) {
       	inGame = true;
           playGame(g2d);
       }
       else if(isDead) {
       	showPausedScreen(g2d);
       }
       else if(!isDead) {
       	inGame = true;
           playGame(g2d);
       }
       else if (inGame)
       {
       	inGame = true;
           playGame(g2d);
       } else if (!inGame) {
       	inGame = true;
           playGame(g2d);
       } 
       Toolkit.getDefaultToolkit().sync();
       g2d.dispose();
   }

   private void showPausedScreen(Graphics2D g2d) {
       String pausedMessage = "";
       g2d.setColor(Color.YELLOW);
       g2d.setFont(smallFont);
       FontMetrics fm = g2d.getFontMetrics();
       int messageWidth = fm.stringWidth(pausedMessage);
       g2d.drawString(pausedMessage, (SCREEN_SIZE - messageWidth) / 2, SCREEN_SIZE / 2);
   }




    //----Blessy End----------//
    
    
    
    //----Ate Raven Start----//

    //----Ate Raven End----------//
    
    
    
    //----Robert Start----//

    //----Robert End----------//
    
    

	private void death(Graphics2D g2d) {
	   	lives--;
	       if (lives == 0) {
	       	showPausedScreen(g2d);
	       	timer.stop();
	           isDead = true;
	           GameOver over = new GameOver(Model.this);
	           over.setVisible(true);
	          
	          
	       }
	       continueLevel();
	   }

	private void movePacman() {
	       int pos;
	       short ch;
	       if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
	           pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
	           ch = screenData[pos];
	           if ((ch & 16) != 0) {
	               screenData[pos] = (short) (ch & 15);
	               score += 10;
	              
	           } else if ((ch & 32) != 0) {
	               score += 100;
	               currentSpeed = 1;
	               screenData[pos] = (short) (ch & 31);
	               for (int i = 0; i < N_GHOSTS; i++) {
	               	currentSpeed = 1;
	               	
	                   ghostVulnerable[i] = true;
	               }
	               ghostsVulnerable = true;
	               vulnerableStartTime = System.currentTimeMillis();
	              
	           } else if ((ch & 64) != 0) {
	               screenData[pos] = (short) (ch & 63);
	               score += 50;
	               PACMAN_SPEED = 6;
	               speedStartTime = System.currentTimeMillis();
	               speedTime = true;
	           }
	           if (req_dx != 0 || req_dy != 0) {
	               if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
	                       || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
	                       || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
	                       || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
	                   pacmand_x = req_dx;
	                   pacmand_y = req_dy;
	               }
	           }
	           if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
	                   || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
	                   || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
	                   || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
	               pacmand_x = 0;
	               pacmand_y = 0;
	           }
	       }
	       pacman_x += PACMAN_SPEED * pacmand_x;
	       pacman_y += PACMAN_SPEED * pacmand_y;
	       if (pacman_x < 0) pacman_x = 0;
	       if (pacman_x >= SCREEN_SIZE) pacman_x = SCREEN_SIZE - 1;
	       if (pacman_y < 0) pacman_y = 0;
	       if (pacman_y >= SCREEN_SIZE) pacman_y = SCREEN_SIZE - 1;
	}

	private void resetGhost(int ghostIndex) {
	       ghost_x[ghostIndex] = (N_BLOCKS / 2) * BLOCK_SIZE;
	       ghost_y[ghostIndex] = (N_BLOCKS / 2) * BLOCK_SIZE;
	       ghostVulnerable[ghostIndex] = false;
	}

	class TAdapter extends KeyAdapter {
	       @Override
	       public void keyPressed(KeyEvent e) {
	           int key = e.getKeyCode();
	           if (inGame) {
	               if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
	                   req_dx = -1;
	                   req_dy = 0;
	               } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
	                   req_dx = 1;
	                   req_dy = 0;
	               } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
	                   req_dx = 0;
	                   req_dy = -1;
	               } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
	                   req_dx = 0;
	                   req_dy = 1;
	               } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
	                   inGame = false;
	               } else if (key == KeyEvent.VK_P) {
	                   if (paused) {
	                       paused = false;
	                       timer.start();
	                   } else {
	                       paused = true;
	                       timer.stop();
	                       Pause pausePanel = new Pause(Model.this);
	                       pausePanel.setVisible(true);
	                   }
	               }
	           } else {
	               if (key == KeyEvent.VK_SPACE) {
	                   inGame = true;
	                   initGame();
	               } else if (key == KeyEvent.VK_ESCAPE) {
	                   Pacman pac = new Pacman();
	                   setVisible(false);
	                   MainMenu menu = new MainMenu();
	                   menu.setVisible(true);
	               }
	           }
	       }
	   }

    
    
    
    //----Kris Start----//

    //----Kris End----------//
    
    
    public void actionPerformed(ActionEvent e) {
        repaint();               
    }
 	public void start() {
 		paused = false;
 		timer.start();
 		
 	}
 	
 	public void Continue() {
 		timer.start();
 		isWin = false;
 		inGame = true;
 	    initLevel();
 		
 	}

    
	}
