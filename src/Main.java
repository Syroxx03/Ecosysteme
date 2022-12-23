public class Main
{
    /*****************/
    public static void main(String[] args)
    {
        //Param
        int vClmnNbr = 10;
        int vRowNbr = 10;
        int vSheepNbr = 6;
        int vWolfNbr = 2;
        int vTestNbr = 1000;


        int vTotalRound = 0;
        GameEngine vGE = new GameEngine ();
        for(int i = 0; i < vTestNbr; i++)
        {
            Universe vUniverse = new Universe(vClmnNbr ,vRowNbr, vSheepNbr , vWolfNbr);
            vGE.startGameLoop(vUniverse);
            //System.out.println("Universe dead at round "+vUniverse.getRound());
            vTotalRound += vUniverse.getRound();
        }
        System.out.println("Test nbr : "+vTestNbr);
        System.out.println("Universe Size : "+vClmnNbr + "*"+ vRowNbr);
        System.out.println("Animals : "+vSheepNbr + " sheep," + vWolfNbr + " wolf");
        System.out.println("The universe dies on average in round " +vTotalRound / vTestNbr);

    }
}