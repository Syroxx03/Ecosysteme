import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Animal
{
    protected String aImage;
    protected HashMap<String, Integer> aValues;
    protected ArrayList<String> aProperties;
    /*****************/
    public Animal()
    {
        this.aValues = new HashMap<>();
        this.aProperties = new ArrayList<String>();
        String vGender = ((new Random()).nextInt(2)==0 ? "male" : "female");
        this.aProperties.add(vGender);
    }
    /*****************/
    public int getValue(final String pKey){return this.aValues.get(pKey);}
    /*****************/
    public void setValue(final String pKey, final int pValue){this.aValues.put(pKey, pValue);}
    /*****************/
    public void removeProperty(final String pProperty){this.aProperties.remove(pProperty);}
    /*****************/
    public boolean hasProperty(final String pProperty) {return this.aProperties.contains(pProperty);}
    /*****************/
    public void addProperty(final String pProperty) {this.aProperties.add(pProperty);}
    /*****************/
    public String getImage(){return this.aImage;}
    /*****************/
    public abstract Animal giveBirth();
    /*****************/
    public abstract void interact(Animal pAnimal);
    /*****************/
    public abstract boolean grassInteract(final boolean pGrass);
    /*****************/
    public void update()
    {
        int vTimeBD = this.aValues.get("timeBeforeDie");
        int vTimeBS  = this.aValues.get("timeBeforeStarving");
        int vTimeBP = this.aValues.get("timeBeforeProcreate");
        if(vTimeBS == 0 || vTimeBD == 0)
            this.addProperty("naturaldead");
        this.aValues.put("timeBeforeDie",vTimeBS - 1);
        this.aValues.put("timeBeforeStarving",vTimeBS - 1);
        this.aValues.put("timeBeforeProcreate",vTimeBP - 1);
    }


}
