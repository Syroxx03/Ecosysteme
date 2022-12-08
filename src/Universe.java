import javax.swing.*;
import java.awt.*;

public class Universe extends JComponent
{
    private Animal[][] aAnimals;
    private boolean[][] aGrass;
    private boolean[][] aMinerals;
    public Universe(int pClmnNbr, int pRowNbr)
    {
        this.aAnimals = new Animal[pClmnNbr][pRowNbr];
        this.aGrass = new boolean[pClmnNbr][pRowNbr];
        this.aMinerals = new boolean[pClmnNbr][pRowNbr];
    }
    public void addAnimal(Animal pAnimal, int pClmn, int pRow)
    {
        if(this.aAnimals[pClmn][pRow] == null)
            this.aAnimals[pClmn][pRow] = pAnimal;
    }
    public void addGrass(int pClmn, int pRow)
    {
        this.aGrass[pClmn][pRow] = true;
    }
    public void addMineral(int pClmn, int pRow)
    {
        this.aMinerals[pClmn][pRow] = true;
    }
    @Override public void paintComponent(Graphics g) {
        int vCaseSize = 32;

        for (int vRow = 0; vRow < this.aAnimals[0].length; vRow++) {
            for (int vClmn = 0; vClmn < this.aAnimals.length; vClmn++) {
                //GRASS

                if (this.aGrass[vClmn][vRow])
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.ORANGE);
                g.fillRect(vClmn * vCaseSize + 2, vRow * vCaseSize + 2, vCaseSize - 4 , vCaseSize - 4);
                if(this.aAnimals[vClmn][vRow]!=null)
                {
                    g.setColor(Color.BLACK);
                    g.drawString(this.aAnimals[vClmn][vRow].getImage(), vClmn * vCaseSize + 10, vRow * vCaseSize + 21);
                }
            }
        }
    }
}
