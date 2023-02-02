import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class GameEngine
{
    private final JFrame aFrame;
    private ConfigurationInterface aCI;
    /*****************/
    public GameEngine()
    {
        this.aFrame = new JFrame();
        this.setFrame();
        this.aCI = new ConfigurationInterface(this);
        this.aFrame.add(this.aCI);
        this.aFrame.revalidate();
    }
    /*****************/
    private void setFrame()
    {
        this.aFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.aFrame.setBackground(Color.BLACK);
        this.aFrame.setBounds(100, 100, 1000, 700);
        this.aFrame.setVisible(true);
    }
    /*****/
    public void startGameLoop(final Universe pUniverse, final int pFPS)
    {
        boolean vStop = false;
        while(!vStop)
        {
            vStop = pUniverse.update();
            this.aFrame.repaint();
            try{Thread.sleep(1000/pFPS);}
            catch(InterruptedException e){return;}
        }
    }
}