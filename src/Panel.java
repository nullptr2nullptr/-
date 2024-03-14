import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.TreeSet;

public class Panel extends JPanel implements ActionListener, ItemListener, MouseListener, MouseMotionListener, KeyListener{

    private Asset dictator = new Asset("src/img src/example.jpg", "src/img src/example_2.jpg", 100, 100, 165, 225);
    private Timer clock = new Timer(16, this);
    private TreeSet<Integer> keycodes = new TreeSet<Integer>();
    private int mouse_x = 0, mouse_y = 0;
    private int delay, width, height;

    public Panel(int delay, int width, int height) {
        setFocusable(true);
        this.width = width;
        this.height = height;
        this.delay = delay;
        setPreferredSize(new Dimension(width, height));
        clock.start();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dictator.paint(g, this);
    }

    //Mouse Listener Stuff
    public void mouseClicked(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mousePressed(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){
        if(dictator.isTouching(mouse_x, mouse_y)){
            dictator.changeImg();
        }
        repaint();
    }

    //Mouse Motion Listener stuff
    public void mouseDragged(MouseEvent e){
        mouse_x = e.getX();
        mouse_y = e.getY();
    }

    public void mouseMoved(MouseEvent e){
        mouse_x = e.getX();
        mouse_y = e.getY();
    }

    //Key Listener Stuff
    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e){
        keycodes.add(e.getKeyCode());
    }
    
    public void keyReleased(KeyEvent e){
        keycodes.remove(e.getKeyCode());
    }

    //Action Listener Stuff
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clock){
            for(int k : keycodes){
                if(k == 38){
                    dictator.setX(dictator.getX() + 1);
                }
            }
            dictator.update(delay, width, height);
        }
        repaint();
    }

    //Item Listener Stuff
    public void itemStateChanged(ItemEvent e){}
}
