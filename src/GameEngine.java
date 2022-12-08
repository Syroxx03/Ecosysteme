public class GameEngine
{
    private UserInterface aGUI;
    private Universe aUniverse;
    public GameEngine(final UserInterface pGUI)
    {
        this.aGUI = pGUI;
        this.aUniverse = new Universe(10, 15);
        this.aGUI.addComponent(aUniverse);
        this.test();
        this.aGUI.setVisible(true);
        this.aGUI.repaint();
    }
    public void test()
    {
        this.aUniverse.addAnimal(new Sheep(), 6, 6);
    }
}
