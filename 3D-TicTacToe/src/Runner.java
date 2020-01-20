import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;

public class Runner extends JFrame
{
    public Runner() {
        super("TicTacToe");
        this.setSize(1500, 1500);
        this.getContentPane().add((Component)new GameBoard());
        this.setBackground(Color.white);
        this.setVisible(true);
    }
    
    public static void main(final String[] args) {
        new Runner();
    }
}