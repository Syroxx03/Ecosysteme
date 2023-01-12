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
    /*****************/
    public void start(final int... pParam)
    {
        this.aFrame.remove(this.aCI);
        this.aFrame.revalidate();
        this.aFrame.repaint();
        this.aFrame.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this.aFrame);
        for(int i = 0; i < pParam[4]; i++)
        {
            Universe vUniverse = new Universe(pParam[0] ,pParam[1], pParam[2] , pParam[3]);
            this.aFrame.add(vUniverse);
            this.aFrame.revalidate();
            this.startGameLoop(vUniverse, pParam[5]);
            this.aFrame.remove(vUniverse);
        }
        this.aFrame.add(this.aCI);
        this.aFrame.revalidate();
    }
    /*****************/
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