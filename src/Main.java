public class Main
{
    /*****************/
    public static void main(String[] args)
    {
        Universe vUniverse = new Universe(15 ,15, 114 , 0);
        GameEngine vGE = new GameEngine ( vUniverse );
        vGE.startGameLoop();
    }
}