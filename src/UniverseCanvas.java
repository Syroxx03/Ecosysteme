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
        if(this.aUni.isDead())
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Serif",Font.BOLD,40));
            int stringWidth = g.getFontMetrics().stringWidth("Univers mort au tour "+this.aUni.getRound());
            g.drawString("Univers mort au tour "+this.aUni.getRound(), this.getWidth()/2 - stringWidth/2, this.getHeight()/2);
            return;
        }
        for(int vRow = 0; vRow < this.aUni.getRowNbr(); vRow++)
            for(int vClmn = 0; vClmn < this.aUni.getClmnNbr(); vClmn++)
            {
                int vX = vClmn*aCaseSize + (this.getWidth() - this.aUni.getRowNbr()*this.aCaseSize)/2;
                int vY = vRow*aCaseSize + (this.getHeight() - this.aUni.getClmnNbr()*this.aCaseSize)/2;
                if(this.aUni.hasGrass(vClmn,vRow))
                    g.setColor(new Color(116, 193, 127));
                else
                    g.setColor(new Color(109, 100, 94));
                g.fillRect(vX , vY , aCaseSize-1, aCaseSize-1);
                g.setColor(Color.BLUE);
                if(this.aUni.hasMinerals(vClmn,vRow))
                    g.drawOval(vX , vY ,aCaseSize-1, aCaseSize-1);
                Animal vAnimal = this.aUni.getAnimal(vClmn,vRow);
                if(vAnimal != null)
                {
                    switch (vAnimal.getSpecies()) {
                        case "Wolf" -> g.setColor(Color.BLACK);
                        case "Sheep" -> g.setColor(Color.WHITE);
                        default -> g.setColor(Color.red);
                    }
                    g.fillOval(vX, vY , aCaseSize-1, aCaseSize-1);
                    if(vAnimal.getGender().equals("female"))
                    {
                        g.setColor(Color.PINK);
                        g.fillOval(vX + aCaseSize/4  , vY +aCaseSize/4, aCaseSize/2-1, aCaseSize/2-1);
                    }
                    if(vAnimal.getTBProcreate()<=0)
                    {
                        g.setColor(Color.red);
                        g.drawOval(vX , vY , aCaseSize-1, aCaseSize-1);
                    }
                }
            }
    }
}
