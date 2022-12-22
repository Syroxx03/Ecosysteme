public class GameEngine
{
    private Universe aUniverse;
    private UserInterface aGUI;
    /*****************/
    public GameEngine(Universe pUniverse)
    {
        this.aUniverse = pUniverse;
        this.aGUI = new UserInterface(this.aUniverse);
    }
    /*****************/
    public void startGameLoop()
    {
        int vFPS = 10;
        while(true)
        {
            this.aUniverse.update();
            this.aGUI.repaint();
            try{Thread.sleep(1000/vFPS);}
            catch(InterruptedException e){return;}
        }
    }
}