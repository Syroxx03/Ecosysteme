import java.util.HashMap;
import java.util.Enumeration;

public class UniverseStatistics {
    private HashMap<String, Integer> aStatMap;


    public UniverseStatistics(int pLivingSheeps, int pLivingWolves){
        aStatMap = new HashMap<String, Integer>();
        aStatMap.put("LivingSheeps",pLivingSheeps);
        aStatMap.put("LivingWolves",pLivingWolves);

    }

    public HashMap<String, Integer> getStatMap(){
        return aStatMap;
    }


}
