import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class Frame extends JFrame implements MouseListener{

    private int q;

    //private boolean flipping = false;

    private Card[] tiles;

    private int[] colors;

    private Card lastClicked;



    private void randomizer(){
        //randomizes the colors
        int[] temp = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8};
        this.colors = new int[temp.length];
        System.arraycopy(temp, 0, colors, 0, temp.length);
        Random random = new Random();
        //randomly swaps the numbers
        for (int i = temp.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tempColor = colors[i];
            colors[i] = colors[j];
            colors[j] = tempColor;
        }
    }

    Frame(){
        randomizer();
        q = 0;

        //organizing frame
        this.setTitle("Matching Game");
        this.setSize(600,600);
        this.setDefaultCloseOperation(3);
        this.setLayout(new GridLayout(4,4));
        tiles = new Card[16];

        //create the cards
        for (int i = 0; i < 16; i++){
            tiles[i] = new Card(colors[i]);
            tiles[i].addMouseListener(this);
            this.add(tiles[i]);
        }

        this.setVisible(true);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        Card clickedCard = (Card) e.getSource();

        if (!clickedCard.getFlipped() || clickedCard.getLocked()) {
            return;
        }

        if (q == 0) {
            // First card selection
            q = 1;
            lastClicked = clickedCard;
        }
        else {
            // Second card selection
            q = 0;

            if (lastClicked.getColor() == clickedCard.getColor()) {
                // Cards matched
                lastClicked.lock();
                clickedCard.lock();
            }
            else {
                // Cards not matched
                Timer timer = new Timer(600, e1 -> {
                    lastClicked.flipAgain();
                    clickedCard.flipAgain();
                    lastClicked.setBackground(Color.orange);
                    clickedCard.setBackground(Color.orange);
                });
                timer.setRepeats(false);
                timer.start();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
