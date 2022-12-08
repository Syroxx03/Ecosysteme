import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame
{
    public UserInterface()
    {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setBounds(100, 100, 700, 700);
    }
    public void addComponent(JComponent pComponent)
    {
        this.getContentPane().add(pComponent);
    }
    public void removeComponent(JComponent pComponent)
    {
        this.remove(pComponent);
    }
}
