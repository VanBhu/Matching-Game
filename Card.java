import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Card extends JLabel {

    private int color;

    private boolean locked = false;

    private boolean flipped = false;
    private Color[] colors = {Color.blue, Color.red, Color.green, Color.yellow, Color.black, Color.white, Color.gray, Color.magenta};

    Card(int color){
        this.color = color;
        this.setBackground(Color.orange);
        this.setBorder(BorderFactory.createLineBorder(Color.cyan));
        this.setOpaque(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (locked == false){
                    setBackground(colors[color - 1]);
                    flipped = true;
                }
            }
        });
    }

    public int getColor(){
        return color;
    }

    public boolean getLocked(){
        return locked;
    }

    public boolean getFlipped(){
        return flipped;
    }

    public void lock(){
        locked = true;
    }

    public void flipAgain(){
        if (flipped == true){
            flipped = false;
        }
        else{
            flipped = true;
        }
    }






}
