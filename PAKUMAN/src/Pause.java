import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pause extends JFrame {
	
private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Model model; // Reference to the Model

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Pause frame = new Pause(null); // Pass null for testing
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor that accepts a Model instance
    public Pause(Model model) {
        this.model = model;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_P) { 
                    dispose();
                    if (model != null) {
                        model.start(); // Start the timer when the pause frame is closed
                    }
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 560, 370);
        contentPane = new JPanel();
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                if (model != null) {
                    model.start(); // Start the timer when the pause frame is closed
                }
            }
        });
        lblNewLabel_1.setIcon(new ImageIcon(Pause.class.getResource("/images/pause.png")));
        lblNewLabel_1.setBounds(-111, 85, 723, 136);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Pause.class.getResource("/images/PACMAN_BG.png")));
        lblNewLabel.setBounds(0, 0, 544, 331);
        contentPane.add(lblNewLabel);
    }	
}
