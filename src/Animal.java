import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Animal
{
    protected String aImage;
    protected boolean aGender;
    protected int aTimeBeforeRepro;
    protected int aTimeBeforeStarve;
    protected ArrayList<String> aProperties;
    /*****************/
    public Animal()
    {
        this.aGender = (new Random()).nextBoolean();
        this.aProperties = new ArrayList<String>();
    }
    /*****************/
    public boolean getGender()
    {
        return this.aGender;
    }
    /*****************/
    public String getImage()
    {
        return this.aImage;
    }
    /*****************/
    public void interact(Animal pAnimal)
    {

    }
}
