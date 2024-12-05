
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

    //----Blessy End----------//
    
    
    
    //----Ate Raven Start----//

    //----Ate Raven End----------//
    
    
    
    //----Robert Start----//

    //----Robert End----------//
    
    
    
    //----Kishi Start---//

    //----Kishi End----------//
    
    
    
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