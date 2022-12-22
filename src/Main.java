public class Main
{
    /*****************/
    public static void main(String[] args)
    {
        Universe vUniverse = new Universe(2 ,2, 4 , 0);
        GameEngine vGE = new GameEngine ( vUniverse );
        vGE.startGameLoop();
    }
}