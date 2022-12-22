import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Universe extends JComponent
{
    private int aRound;
    private final int aClmnNbr;
    private final int aRowNbr;
    private Animal[][] aAnimals;
    final private boolean[][] aMinerals;
    final private boolean[][] aGrass;
    /*****************/
    public Universe(int pClmnNbr, int pRowNbr, int pSheepNbr, int pWolfNbr)
    {
        this.aRound = 0;
        this.aClmnNbr = pClmnNbr;
        this.aRowNbr = pRowNbr;

        this.aGrass = new boolean[aClmnNbr][aRowNbr];
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn< aClmnNbr; vClmn++)
                this.aGrass[vClmn][vRow] = true;

        this.aMinerals = new boolean[aClmnNbr][aRowNbr];
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn< aClmnNbr; vClmn++)
                this.aMinerals[vClmn][vRow] = false;

        this.aAnimals = new Animal[aClmnNbr][aRowNbr];
        for(int s = 0; s < pSheepNbr; s++)
            this.addAnimal(new Sheep());
        for(int w = 0; w < pWolfNbr; w++)
            this.addAnimal(new Wolf());
    }
    /*****************/
    public void update()
    {
        this.aRound++;
        this.updateAnimals();
        this.moveAnimals();
        this.makeInteract();
        this.updateGrass();
        this.removeDead();
        this.addNews();
    }
    /*****************/
    private void updateAnimals()
    {
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn < aClmnNbr; vClmn++)
                if(this.aAnimals[vClmn][vRow]!=null)
                    this.aAnimals[vClmn][vRow].update();
    }
    /*****************/
    private void moveAnimals()
    {
        Animal[][] vAnimals = new Animal[aClmnNbr][aRowNbr];
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn < aClmnNbr; vClmn++)
            {
                Animal vAnimal = this.aAnimals[vClmn][vRow];
                if(vAnimal!=null)
                {
                    ArrayList<Point> vEmptyNeighboringCells = new ArrayList<Point>();
                    for(Point vPoint: this.getNeighboringCells(vClmn, vRow))
                        if(this.aAnimals[vPoint.x][vPoint.y] == null)
                            vEmptyNeighboringCells.add(vPoint);
                    if(!vEmptyNeighboringCells.isEmpty())
                    {
                        Point vPoint = vEmptyNeighboringCells.get((new Random()).nextInt(vEmptyNeighboringCells.size()));
                        vAnimals[vPoint.x][vPoint.y] = vAnimal;
                    }
                }
            }
        this.aAnimals = vAnimals;
    }
    /*****************/
    private void makeInteract()
    {
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn < aClmnNbr; vClmn++)
            {
                Animal vAnimal = this.aAnimals[vClmn][vRow];
                if(vAnimal != null)
                {
                    this.aGrass[vClmn][vRow] = vAnimal.grassInteract(this.aGrass[vClmn][vRow]);
                    for(Point vCell:this.getNeighboringCells(vClmn , vRow))
                    {
                        //Interact with neighboring animals
                        Animal vAnimal2 = this.aAnimals[vCell.x][vCell.y];
                        if(vAnimal2 != null)
                            vAnimal.interact(vAnimal2);
                    }
                }
            }
    }
    /*****************/
    private void removeDead()
    {
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn < aClmnNbr; vClmn++)
                if(this.aAnimals[vClmn][vRow] != null && this.aAnimals[vClmn][vRow].hasProperty("naturaldead"))
                {
                    this.aAnimals[vClmn][vRow] = null;
                    this.aMinerals[vClmn][vRow] = true;
                }
                else if(this.aAnimals[vClmn][vRow] != null && this.aAnimals[vClmn][vRow].hasProperty("dead"))
                    this.aAnimals[vClmn][vRow] = null;
    }
    /*****************/
    private void addNews()
    {
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn < aClmnNbr; vClmn++)
            {
                Animal vAnimal = this.aAnimals[vClmn][vRow];
                if(vAnimal != null && vAnimal.hasProperty("pregnant"))
                {
                    vAnimal.removeProperty("pregnant");
                    ArrayList<Point> vEmptyNeighboringCells = new ArrayList<Point>();
                    for(Point vPoint: this.getNeighboringCells(vClmn, vRow))
                        if(this.aAnimals[vPoint.x][vPoint.y] == null)
                            vEmptyNeighboringCells.add(vPoint);
                    if(!vEmptyNeighboringCells.isEmpty())
                    {
                        Point vPoint = vEmptyNeighboringCells.get((new Random()).nextInt(vEmptyNeighboringCells.size()));
                        this.aAnimals[vPoint.x][vPoint.y] = vAnimal.giveBirth();
                    }
                }
            }
    }
    /*****************/
    private void updateGrass()
    {
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn < aClmnNbr; vClmn++)
                if(this.aMinerals[vClmn][vRow] && !this.aGrass[vClmn][vRow])
                    this.aGrass[vClmn][vRow] = true;
    }
    /*****************/
    ArrayList<Point> getNeighboringCells(final int pClmn, final int pRow)
    {
        ArrayList<Point> vNeighboringCells = new ArrayList<Point>();

        if(pRow-1 >= 0 && pClmn-1 >= 0){vNeighboringCells.add(new Point(pClmn - 1, pRow - 1));}
        if(pRow-1 >= 0){vNeighboringCells.add(new Point(pClmn , pRow- 1));}
        if(pRow-1 >= 0 && pClmn+1 < aClmnNbr){vNeighboringCells.add(new Point(pClmn + 1, pRow - 1));}

        if(pClmn-1 >= 0){vNeighboringCells.add(new Point(pClmn - 1, pRow));}
        vNeighboringCells.add(new Point(pClmn , pRow));
        if(pClmn+1 < aClmnNbr){vNeighboringCells.add(new Point(pClmn + 1, pRow));}

        if(pRow+1 < aRowNbr && pClmn-1 >= 0){vNeighboringCells.add(new Point(pClmn - 1, pRow + 1));}
        if(pRow+1 < aRowNbr){vNeighboringCells.add(new Point(pClmn , pRow+ 1));}
        if(pRow+1 < aRowNbr && pClmn+1 < aClmnNbr){vNeighboringCells.add(new Point(pClmn + 1, pRow + 1));}

        return vNeighboringCells;
    }
    /*****************/
    public void addAnimal(Animal pAnimal)
    {
        ArrayList<Point> vEmptyCells = new ArrayList<Point>();
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn< aClmnNbr; vClmn++)
                if(this.aAnimals[vClmn][vRow] == null)
                    vEmptyCells.add(new Point(vClmn,vRow));
        if(!vEmptyCells.isEmpty())
        {
            Point vPoint = vEmptyCells.get((new Random()).nextInt(vEmptyCells.size()));
            this.aAnimals[vPoint.x][vPoint.y] = pAnimal;
        }
    }
    /*****************/
    @Override public void paintComponent(Graphics g)
    {
        int vCaseSize = 30;
        for(int vRow = 0; vRow < aRowNbr; vRow++)
            for(int vClmn = 0; vClmn< aClmnNbr; vClmn++)
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