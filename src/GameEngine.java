import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class GameEngine
{
    private final JFrame aFrame;
    private UserInterface aUI;
    /*****************/
    public GameEngine()
    {
        this.aFrame = new JFrame();
        this.setFrame();
        this.aUI = new UserInterface();
        this.aFrame.add(this.aUI);
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
    public void startGameLoop()
    {
        Thread repaintThread = new Thread(() ->
        {
           while(true)
           {
               this.aUI.repaint();
               try{Thread.sleep(1000/60);}
               catch(InterruptedException e){return;}
           }
        });

        Thread updateThread = new Thread(() ->
        {
            while(true)
            {
                if(this.aUI.auto())
                    this.aUI.nextRound();
                try{Thread.sleep(Math.min(this.aUI.getInterval(),1000));}
                catch(InterruptedException e){return;}
            }
        });
        repaintThread.start();
        updateThread.start();
    }
}