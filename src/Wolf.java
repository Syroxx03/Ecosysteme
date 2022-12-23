public class Wolf extends Animal
{
    public static int LifeTime = 60;
    public static int ProcreationInterval = 6;
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
    public Animal giveBirth(){return new Wolf();}
    /*****************/
    public void interact(Animal pAnimal)
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
    public boolean grassInteract(final boolean pGrass){return pGrass;}
    /*****************/
    public void move()
    {

    }
}