import java.util.ArrayList;
import java.util.HashMap;

public class GameEngine
{
    private final UserInterface aGUI;
    /*****************/
    public GameEngine()
    {
        this.aGUI = new UserInterface();
    }
    /*****************/
    public void startGameLoop(final Universe pUniverse)
    {
        this.aGUI.add(pUniverse);
        this.aGUI.revalidate();
        int vFPS = 50;
        boolean vStop = false;
        while(!vStop)
        {
            vStop = pUniverse.update();
            this.aGUI.repaint();
            try{Thread.sleep(1000/vFPS);}
            catch(InterruptedException e){return;}
        }
        getUniversStatistics(pUniverse);
        this.aGUI.remove(pUniverse);
    }

    private void getUniversStatistics(Universe pUnivers){
        int vMaxRound = pUnivers.getRound();
        ArrayList<Integer> vStatsSheeps = new ArrayList<Integer>(vMaxRound+1);
        HashMap<Integer, UniverseStatistics> vUS = pUnivers.getUniverseStatistics();
        for(int i=0; i <= vMaxRound; i++){
           vStatsSheeps.add(vUS.get(i).getStatMap().get("LivingSheeps"));
        }
        System.out.println("Stats - Sheeps : "+vStatsSheeps.toString());
    }

}

