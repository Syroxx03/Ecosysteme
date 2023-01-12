import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame
{
    /*****************/
    public UserInterface()
    {
        super();
        this.setFrame();
    }
    /*****************/
    private void setFrame()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setBounds(100, 100, 1000, 700);
        this.setVisible(true);
    }
}
