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
        int vFPS = 1000;
        boolean vStop = false;
        while(!vStop)
        {
            vStop = pUniverse.update();
            this.aGUI.repaint();
            try{Thread.sleep(1000/vFPS);}
            catch(InterruptedException e){return;}
        }
        this.aGUI.remove(pUniverse);
    }
}