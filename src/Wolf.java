public class Wolf extends Animal
{
    /*****************/
    public Wolf()
    {
        super();
        this.aImage = "W";
        this.addProperty("wolf");
        this.aValues.put("timeBeforeStarving",10);
        this.aValues.put("timeBeforeDie",60);
        this.aValues.put("timeBeforeProcreate",6);
    }
    /*****************/
    public Animal giveBirth(){return new Wolf();}

    /*****************/
    public void interact(Animal pAnimal)
    {
        if(pAnimal.hasProperty("prey"))
        {
            pAnimal.addProperty("dead");
            this.setValue("timeBeforeStarving",10);
        }
        if(pAnimal.hasProperty("wolf") && this.hasProperty("male") && pAnimal.hasProperty("female"))
            if(this.getValue("timeBeforeProcreate") <= 0 && pAnimal.getValue("timeBeforeProcreate") <= 0)
            {
                this.setValue("timeBeforeProcreate",6);
                pAnimal.setValue("timeBeforeProcreate",6);
                pAnimal.addProperty("pregnant");
            }
    }
    /*****************/
    public boolean grassInteract(final boolean pGrass){return pGrass;}
}