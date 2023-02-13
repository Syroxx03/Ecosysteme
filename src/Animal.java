import java.util.ArrayList;
import java.util.Random;
/*****************/
public abstract class Animal
{
    protected String aSpecies;
    protected String aGender;

    protected int aTimeBeforeProcreate;
    protected int aTimeBeforeStarving;
    protected int aTimeBeforeDie;

    protected ArrayList<String> aProperties;
    /*****************/
    public Animal()
    {
        this.aSpecies = this.getClass().getName();
        this.aGender = (new Random().nextBoolean() ? "Male" : "Female");
        this.aProperties = new ArrayList<String>();
    }
    /*****************/
    public void update()
    {
        this.aTimeBeforeDie--;
        this.aTimeBeforeStarving--;
        this.aTimeBeforeProcreate--;
        if(this.aTimeBeforeStarving == 0 || this.aTimeBeforeDie == 0)
            this.addProperty("naturaldead");
    }
    /*****************/
    public boolean canReproduceWith(final Animal pAnimal)
    {
        return (this.aGender.equals("Male") && pAnimal.getGender().equals("Female")
            && this.aSpecies.equals(pAnimal.getSpecies())
            && this.getTBProcreate() <= 0 && pAnimal.getTBProcreate() <= 0);
    }
    /*****************/
    public void setTimeBeforeProcreate(final int pTime){this.aTimeBeforeProcreate = pTime;}
    /*****************/
    public String getGender(){return this.aGender;}
    public String getSpecies(){return this.aSpecies;}
    public int getTBProcreate(){return this.aTimeBeforeProcreate;}
    /*****************/
    public boolean hasProperty(final String pProperty) {return this.aProperties.contains(pProperty);}
    public void removeProperty(final String pProperty){this.aProperties.remove(pProperty);}
    public void addProperty(final String pProperty) {this.aProperties.add(pProperty);}
    /*****************/
    public abstract Animal giveBirth();
    public abstract void interact(Animal pAnimal);
    public abstract boolean grassInteract(final boolean pGrass);
}
