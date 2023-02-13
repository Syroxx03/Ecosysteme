import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/*****************/
public class Wolf extends Animal
{
    public static int LifeTime = 60;
    public static int ProcreationInterval = 5;
    public static int MeatMaxInterval = 10;
    /*****************/
    public Wolf()
    {
        super();
        this.aTimeBeforeDie = LifeTime;
        this.aTimeBeforeStarving = MeatMaxInterval;
        this.aTimeBeforeProcreate = 0;
    }
    /*****************/
    @Override public Animal giveBirth(){return new Wolf();}
    /*****************/
    @Override public void interact(Animal pAnimal)
    {
        if(pAnimal.getSpecies().equals("Sheep"))
        {
            pAnimal.addProperty("dead");
            this.aTimeBeforeStarving =  MeatMaxInterval;
        }
        else if(this.canReproduceWith(pAnimal))
        {
            this.aTimeBeforeProcreate = ProcreationInterval;
            pAnimal.setTimeBeforeProcreate(ProcreationInterval);
            pAnimal.addProperty("pregnant");
        }
    }
    /*****************/
    @Override public boolean grassInteract(final boolean pGrass){return pGrass;}
    /*****************/
    @Override public Point feedDep(ArrayList<Point> pC, Animal[][] pA,boolean[][] pG)
    {
        int vShortestDistance = -1;
        Point vShortestCompatibleAnimal = null;
        for(int vRow = 0; vRow < pA[0].length; vRow++)
            for(int vClmn = 0; vClmn < pA.length; vClmn++)
            {
                Animal vA = pA[vClmn][vRow];
                if(vA==null)continue;
                Point vP = new Point(vClmn,vRow);
                int d = this.getDistance(pC.get(0),vP);
                if( vA.getSpecies().equals("Sheep") && (vShortestDistance==-1 || d< vShortestDistance))
                {
                    vShortestDistance = d;
                    vShortestCompatibleAnimal = vP;
                }
            }
        if(vShortestDistance!=-1)
        {
            Point vBest = pC.get(0);
            vShortestDistance=-1;
            for(Point point:pC)
            {
                int distance = this.getDistance(point,vShortestCompatibleAnimal);
                if(vShortestDistance==-1 || distance< vShortestDistance)
                {
                    vShortestDistance = distance;
                    vBest = point;
                }
            }
            return vBest;
        }
        return pC.get((new Random()).nextInt(pC.size()));
    }
}