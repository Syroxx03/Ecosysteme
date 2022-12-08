import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame
{
    /*****************/
    public UserInterface(final JComponent pComponent)
    {
        super();
        this.setFrame();
        this.add(pComponent);
    }
    /*****************/
    private void setFrame()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setBounds(100, 100, 700, 700);
        this.setVisible(true);
    }
}
