public class Main
{
    /*****************/
    public static void main(String[] args)
    {
        Universe vUniverse = new Universe(10 ,10, 2 , 3);
        GameEngine vGE = new GameEngine ( vUniverse );
        vGE.startGameLoop();
    }
}