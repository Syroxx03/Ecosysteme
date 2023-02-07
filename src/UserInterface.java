import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UserInterface extends JPanel implements ActionListener
{
    private final HashMap<String,JComponent> aConfigMap;
    private final HashMap<String,JComponent> aGameMap;
    private final UniverseCanvas aUniCanvas;
    private boolean aAuto;
    /****/
    public UserInterface()
    {
        this.aConfigMap = new HashMap<>();
        this.aGameMap = new HashMap<>();
        this.aUniCanvas = new UniverseCanvas();
        this.aAuto = false;
        this.setBackground(Color.white);
        this.setConfigMap();
        this.setGameMap();
        //adds
        for(JComponent c: this.aConfigMap.values())
            this.add(c);
        this.add(this.createJButton("Valider   les paramètres"));
        this.add(this.aUniCanvas);
        for(JComponent c:this.aGameMap.values())
        {
            this.add(c);
            c.setEnabled(false);
        }
    }
    /****/
    public int getInterval()
    {
        try {
            return Integer.parseInt(((JTextField) this.aGameMap.get("IntervalAuto")).getText());
        }
        catch(Exception e){return 1000;}
    }
    /****/
    public boolean auto()
    {
        return this.aAuto;
    }
    /****/
    public void nextRound()
    {
        if(this.aUniCanvas.getUni()!=null)
            this.aUniCanvas.getUni().nextRound();
    }
    /****/
    private void setGameMap()
    {
        this.aGameMap.put("Tour suivant",this.createJButton("Tour suivant"));
        this.aGameMap.put("Auto",this.createJButton("Auto"));
        this.aGameMap.put("Reset",this.createJButton("Reset"));
        this.aGameMap.put("IntervalAuto",this.createJTextField("Interval auto",100));
    }
    /****/
    private void setConfigMap()
    {
        this.aConfigMap.put("Column",this.createJTextField("Nombre de colonnes",10));
        this.aConfigMap.put("Row",this.createJTextField("Nombre de lignes",10));
        this.aConfigMap.put("Sheep",this.createJTextField("Nombre de moutons",8));
        this.aConfigMap.put("Wolf",this.createJTextField("Nombre de loups",2));
    }
    /****/
    private JTextField createJTextField(String pTitle, int pValue)
    {
        JTextField vTF = new JTextField(""+pValue,15);
        vTF.setBorder(new CompoundBorder(BorderFactory.createTitledBorder(pTitle), vTF.getBorder()));
        return vTF;
    }
    /****/
    private JButton createJButton(String pText)
    {
        JButton vB = new JButton(pText);
        vB.addActionListener(this);
        return vB;
    }
    /****/
    @Override public void actionPerformed(ActionEvent e)
    {
        JButton vB = (JButton)e.getSource();
        switch(vB.getText())
        {
            case "Valider   les paramètres":
                if(this.createUniverse())
                {
                    this.gameMode();
                    vB.setText("Modifier les paramètres");
                }break;
            case "Modifier les paramètres":
                ((JButton)this.aGameMap.get("Auto")).setText("Auto");
                this.aAuto = false;
                this.aUniCanvas.setUni(null);
                this.configMode();
                vB.setText("Valider   les paramètres");break;
            case "Tour suivant":
                if(this.aUniCanvas!=null)
                    this.aUniCanvas.getUni().nextRound();
                break;
            case "Auto":
                this.aAuto = true;
                vB.setText("Stop");
                this.aGameMap.get("Tour suivant").setEnabled(false);
                this.aGameMap.get("IntervalAuto").setEnabled(false);
                break;
            case "Stop":
                this.aAuto = false;
                vB.setText("Auto");
                this.aGameMap.get("IntervalAuto").setEnabled(true);break;
            case "Reset":this.createUniverse();break;
        }
    }
    /****/
    private void configMode()
    {
        for(JComponent c: this.aConfigMap.values())
            c.setEnabled(true);
        for(JComponent c: this.aGameMap.values())
            c.setEnabled(false);
    }
    /****/
    private void gameMode()
    {
        for(JComponent c: this.aConfigMap.values())
            c.setEnabled(false);
        for(JComponent c: this.aGameMap.values())
            c.setEnabled(true);
    }
    /****/
    private boolean createUniverse()
    {
        try
        {
            int vClmnNbr = Integer.parseInt(((JTextField)this.aConfigMap.get("Column")).getText());
            int vRowNbr = Integer.parseInt(((JTextField)this.aConfigMap.get("Row")).getText());
            int vSheepNbr = Integer.parseInt(((JTextField)this.aConfigMap.get("Sheep")).getText());
            int vWolfNbr = Integer.parseInt(((JTextField)this.aConfigMap.get("Wolf")).getText());
            this.aUniCanvas.setUni(new Universe(vClmnNbr, vRowNbr,vSheepNbr,vWolfNbr));
            this.aUniCanvas.revalidate();
            this.aUniCanvas.repaint();
            return true;
        }
        catch(Exception et){return false;}
    }
}
