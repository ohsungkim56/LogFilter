import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class LogFilterExtDialog extends JDialog{

    private JPanel[] panels;
    private JTextArea[] taFilter;
    private JCheckBox[] chkFilterEnable;

    private int numberOfExt;

    public LogFilterExtDialog(Frame owner, String title, boolean modal, int numberOfExt)//, String[] savedFilter, boolean[] filterEnabled)
    {
        super(owner, title, modal);
        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new GridLayout(5,1,0,0));

        this.numberOfExt = numberOfExt;
        
        panels = new JPanel[numberOfExt];
        taFilter = new JTextArea[numberOfExt];
        chkFilterEnable = new JCheckBox[numberOfExt];

        setLocationRelativeTo(owner);
        setResizable(false);
        //setMinimumSize(new Dimension(500,500));

        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 10, 10);
        for(int i=0;i<numberOfExt;i++)
        {
            panels[i] = new JPanel();
            panels[i].setLayout(layout);
            taFilter[i] = new JTextArea("", 5, 50);
            panels[i].add(taFilter[i]);
            chkFilterEnable[i] = new JCheckBox();
            panels[i].add(chkFilterEnable[i]);

            add(panels[i]);
        }
        pack();
    }

    void setExtFilter(int i, String s)
    {
        taFilter[i].setText(s);
    }

    void setExtFilterEnabled(int i, boolean b)
    {
        chkFilterEnable[i].setSelected(b);
    }

    public String getEnabledExtFilter()
    {
        String s = "";
        String strFilter;
        for(int i=0;i<numberOfExt;i++)
        {
            if(getExtFilterSelected(i))
            {
                strFilter = getExtFilter(i).trim().replace("\r\n", "|").replace("\n", "|");
                if(strFilter.length() > 0){
                    if(s.length() > 0){
                        s += '|';
                    }
                }
                s += strFilter;
            }
        }
        return s;
    }

    public String getExtFilter(int i)
    {
        return taFilter[i].getText();
    }

    public boolean getExtFilterSelected(int i)
    {
       return chkFilterEnable[i].isSelected();
    }

    public void addCheckBoxEventListener(int i, ActionListener l){
        chkFilterEnable[i].addActionListener(l);
    }

    public void addTextAreaEventListener(int i, KeyListener l){
        taFilter[i].addKeyListener(l);
    }

    public boolean hasCheckBox(Object jcb)
    {
        for(int i=0;i<numberOfExt;i++)
        {
            if(chkFilterEnable[i].equals(jcb)){
                return true;
            }
        }
        return false;
    }

    public int hasTextArea(Object jtb)
    {
        for(int i=0;i<numberOfExt;i++)
        {
            if(taFilter[i].equals(jtb)){
                return i;
            }
        }
        return -1;
    }
}
