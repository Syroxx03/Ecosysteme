import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Universe extends JComponent
{
    private int aRound;
    final private Animal[][] aAnimals;
    final private boolean[][] aMinerals;
    final private boolean[][] aGrass;
    /*****************/
    public Universe(int pClmnNbr, int pRowNbr, int pSheepNbr, int pWolfNbr)
    {
        this.aRound = 0;
        this.aGrass = new boolean[pClmnNbr][pRowNbr];
        for(int vRow = 0; vRow < this.aGrass[0].length; vRow++)
            for(int vClmn = 0; vClmn< this.aGrass.length; vClmn++)
                this.aGrass[vClmn][vRow] = true;
        this.aMinerals = new boolean[pClmnNbr][pRowNbr];
        this.aAnimals = new Animal[pClmnNbr][pRowNbr];
        for(int i = 0; i < pSheepNbr; i++)
            this.addAnimal(new Sheep());
        for(int i = 0; i < pWolfNbr; i++)
            this.addAnimal(new Wolf());
    }
    /*****************/
    public void update()
    {
        this.aRound++;
    }
    /*****************/
    public void addAnimal(Animal pAnimal)
    {
        ArrayList<Point> vEmptyCells = new ArrayList<Point>();
        for(int vRow = 0; vRow < this.aAnimals[0].length; vRow++)
            for(int vClmn = 0; vClmn< this.aAnimals.length; vClmn++)
                if(this.aAnimals[vClmn][vRow] == null)
                    vEmptyCells.add(new Point(vClmn,vRow));
        if(!vEmptyCells.isEmpty())
        {
            Point vPoint = vEmptyCells.get((new Random()).nextInt(vEmptyCells.size()));
            this.aAnimals[vPoint.x][vPoint.y] = pAnimal;
        }
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
    /*****************/
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
                g.setColor(Color.WHITE);
                Animal vAnimal = this.aAnimals[vClmn][vRow];
                if(vAnimal != null)
                    g.drawString( vAnimal.getImage(),vClmn*vCaseSize+15, vRow*vCaseSize+ 25);
            }
    }
}