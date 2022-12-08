import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Universe extends JComponent
{
    private int aRound;
    private Animal[][] aAnimals;
    private boolean[][] aMinerals;
    private boolean[][] aGrass;
    /*****************/
    public Universe(int pClmnNbr, int pRowNbr)
    {
        this.aRound = 0;
        this.aAnimals = new Animal[pClmnNbr][pRowNbr];
        this.aMinerals = new boolean[pClmnNbr][pRowNbr];
        this.aGrass = new boolean[pClmnNbr][pRowNbr];
        for(int vRow = 0; vRow < this.aGrass[0].length; vRow++)
            for(int vClmn = 0; vClmn< this.aGrass.length; vClmn++)
                this.aGrass[vClmn][vRow] = true;
    }
    /*****************/
    public void update()
    {
        this.aRound++;
    }
    /*****************/
    public void addAnimal(Animal pAnimal, int pClmn, int pRow)
    {
        if(this.aAnimals[pClmn][pRow] == null)
            this.aAnimals[pClmn][pRow] = pAnimal;
    }
    /*****************/
    public void addGrass(int pClmn, int pRow)
    {
        this.aGrass[pClmn][pRow] = true;
    }
    /*****************/
    public void addMineral(int pClmn, int pRow)
    {
        this.aMinerals[pClmn][pRow] = true;
    }
    @Override public void paintComponent(Graphics g)
    {
        int vCaseSize = 30;
        for(int vRow = 0; vRow < this.aGrass[0].length; vRow++)
            for(int vClmn = 0; vClmn< this.aGrass.length; vClmn++)
            {
                if(this.aGrass[vClmn][vRow])
                    g.setColor(new Color(116, 193, 127));
                else
                    g.setColor(new Color(79, 70, 64));
                g.fillRect(vClmn*vCaseSize, vRow*vCaseSize, vCaseSize-1, vCaseSize-1);
            }
    }
}
