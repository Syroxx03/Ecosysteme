import javax.swing.*;
import java.awt.*;

public class UniverseCanvas  extends JComponent
{
    private Universe aUni;
    private int aCaseSize;
    /*****************/
    public UniverseCanvas()
    {
        this.aCaseSize = 32;
        this.setPreferredSize(new Dimension(500,500));
    }
    public void setUni(Universe pUni)
    {
        this.aUni = pUni;
        if(this.aUni != null)
            this.aCaseSize = Math.min(this.getWidth()/this.aUni.getClmnNbr(),this.getHeight()/this.aUni.getRowNbr());
    }
    public Universe getUni(){return this.aUni;}
    /*****************/
    @Override public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,this.getWidth()-1,this.getHeight()-1);
        if(this.aUni==null)return;
        for(int vRow = 0; vRow < this.aUni.getRowNbr(); vRow++)
            for(int vClmn = 0; vClmn < this.aUni.getClmnNbr(); vClmn++)
            {
                if(this.aUni.hasGrass(vClmn,vRow))
                    g.setColor(new Color(116, 193, 127));
                else
                    g.setColor(new Color(109, 100, 94));
                g.fillRect(vClmn*aCaseSize , vRow*aCaseSize , aCaseSize-1, aCaseSize-1);
                g.setColor(Color.BLUE);
                if(this.aUni.hasMinerals(vClmn,vRow))
                    g.drawOval(vClmn*aCaseSize , vRow*aCaseSize, aCaseSize-1, aCaseSize-1);
                Animal vAnimal = this.aUni.getAnimal(vClmn,vRow);
                if(vAnimal != null)
                {
                    switch (vAnimal.getSpecies()) {
                        case "Wolf" -> g.setColor(Color.BLACK);
                        case "Sheep" -> g.setColor(Color.WHITE);
                        default -> g.setColor(Color.red);
                    }
                    g.fillOval(vClmn*aCaseSize, vRow * aCaseSize, aCaseSize-1, aCaseSize-1);
                    if(vAnimal.getGender().equals("female"))
                    {
                        g.setColor(Color.PINK);
                        g.fillOval(vClmn*aCaseSize + aCaseSize/4  , vRow*aCaseSize+aCaseSize/4, aCaseSize/2-1, aCaseSize/2-1);
                    }
                    if(vAnimal.getTBProcreate()<=0)
                    {
                        g.setColor(Color.red);
                        g.drawOval(vClmn*aCaseSize, vRow*aCaseSize, aCaseSize-1, aCaseSize-1);
                    }
                }
            }
    }
}
