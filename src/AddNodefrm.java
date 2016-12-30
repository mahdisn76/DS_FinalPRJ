import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by MSN on 12/30/2016.
 */
public class AddNodefrm extends JDialog {
    private JButton addbtn;
    private JButton cancelbtn;

    private JTextField nametxt;
    private JTextField atttxt;
    private JTextField datatxt;

    private JLabel namelbl;
    private JLabel attlbl;
    private JLabel datalbl;

    private JRadioButton singlerdb, doublerdb;
    private ButtonGroup rdbGroup;

    AddNodefrm() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        addbtn = new JButton("Add");
        cancelbtn = new JButton("cancel");
        nametxt = new JTextField();
        atttxt = new JTextField();
        datatxt = new JTextField();

        namelbl = new JLabel("name : ");
        attlbl = new JLabel("attribute : ");
        datalbl = new JLabel("data : ");

        rdbGroup = new ButtonGroup();
        singlerdb = new JRadioButton("Single Tag", false);
        doublerdb = new JRadioButton("Double Tag", true);
        rdbGroup.add(singlerdb);
        rdbGroup.add(doublerdb);

        JPanel pnll = new JPanel(new GridLayout(5, 1));
        JPanel pnlr = new JPanel(new GridLayout(5, 1));

        pnll.add(namelbl);
        pnll.add(attlbl);
        pnll.add(datalbl);
        pnll.add(singlerdb);
        pnll.add(cancelbtn);

        pnlr.add(nametxt);
        pnlr.add(atttxt);
        pnlr.add(datatxt);
        pnlr.add(doublerdb);
        pnlr.add(addbtn);

        pnll.setBorder(new EmptyBorder(1, 1, 1, 1));

        getContentPane().add(pnll, BorderLayout.WEST);
        getContentPane().add(pnlr, BorderLayout.CENTER);

        setSize(200, 200);

        addbtn.addActionListener((ActionEvent e) -> {

            if (nametxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "your tag must have name", "add new tag", JOptionPane.INFORMATION_MESSAGE);
            } else if (!singlerdb.isSelected() && !doublerdb.isSelected()) {
                JOptionPane.showMessageDialog(null, "your have to choose single or double", "add new tag", JOptionPane.INFORMATION_MESSAGE);
            } else {
                GlobalVariable.tgName = nametxt.getText();
                GlobalVariable.tgAtt = atttxt.getText();
                GlobalVariable.tgData = datatxt.getText();
                GlobalVariable.tgIsSingle = singlerdb.isSelected();
                setVisible(false);
            }

        });

        cancelbtn.addActionListener((ActionEvent e) -> {
            GlobalVariable.tgName = null;
            GlobalVariable.tgAtt = null;
            GlobalVariable.tgData = null;
            setVisible(false);
        });

        singlerdb.addActionListener((ActionEvent e) -> {
            if (singlerdb.isSelected()) {
                datatxt.setText("");
                datatxt.setEnabled(false);
            } else {
                datatxt.setEnabled(true);
            }
        });

        doublerdb.addActionListener((ActionEvent e) -> {
            if (singlerdb.isSelected()) {
                datatxt.setText("");
                datatxt.setEnabled(false);
            } else {
                datatxt.setEnabled(true);
            }
        });


    }
}
