public class Sheep extends Animal
{
    /*****************/
    public Sheep()
    {
        super();
        this.aImage = "S";
        this.addProperty("sheep");
        this.aValues.put("timeBeforeStarving",5);
        this.aValues.put("timeBeforeDie",50);
        this.aValues.put("timeBeforeProcreate",6);
    }
    /*****************/
    public Animal giveBirth(){return new Sheep();}
    /*****************/
    public void interact(Animal pAnimal)
    {
        if(pAnimal.hasProperty("sheep") && this.hasProperty("male") && pAnimal.hasProperty("female"))
            if(this.getValue("timeBeforeProcreate") <= 0 && pAnimal.getValue("timeBeforeProcreate") <= 0)
            {
                this.setValue("timeBeforeProcreate",6);
                pAnimal.setValue("timeBeforeProcreate",6);
                pAnimal.addProperty("pregnant");
            }
    }
    /*****************/
    public boolean grassInteract(final boolean pGrass)
    {
        if(pGrass)
            this.setValue("timeBeforeStarving",5);
        return false;
    }
}
