/*****************/
public class Sheep extends Animal
{
    public static int LifeTime = 50;
    public static int ProcreationInterval = 6;
    public static int MeatMaxInterval = 6;

    /*****************/
    public Sheep()
    {
        super();
        this.aTimeBeforeDie = LifeTime;
        this.aTimeBeforeStarving = MeatMaxInterval;
        this.aTimeBeforeProcreate = 0;
    }
    /*****************/
    @Override public Animal giveBirth(){return new Sheep();}
    /*****************/
    @Override public void interact(Animal pAnimal)
    {
        if(this.canReproduceWith(pAnimal))
        {
            this.aTimeBeforeProcreate = ProcreationInterval;
            pAnimal.setTimeBeforeProcreate(ProcreationInterval);
            pAnimal.addProperty("pregnant");
        }
    }
    /*****************/
    @Override public boolean grassInteract(final boolean pGrass)
    {
        if(pGrass)
            this.aTimeBeforeStarving = MeatMaxInterval;
        return false;
    }
}
