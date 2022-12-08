import java.awt.*;
import java.util.ArrayList;

public abstract class Animal
{
    protected ArrayList<String> aProperties;
    protected int aTimeBeforeRepro;
    protected int aTimeBeforeStarve;
    /*****************/
    public Animal()
    {
        this.aProperties = new ArrayList<String>();
    }
}
