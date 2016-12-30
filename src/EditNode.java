import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by KINGMAX on 12/31/2016.
 */
public class EditNode extends JDialog{


    private JButton Edit;
    private JButton Cancel;

    private JLabel nameLbl;
    private JLabel attLbl;
    private JLabel dataLbl;

    private JTextField nameTxt;
    private JTextField attTxt;
    private JTextField dataTxt;
    private  JRadioButton SingleTag , DoubleTag;
    private ButtonGroup rdbGroup;

    public EditNode(TagNode node)
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Edit = new JButton("Edit");
        Cancel = new JButton("Cancel");

        nameLbl = new JLabel("Tag Name :");
        attLbl = new JLabel("Tag Attribute :");
        dataLbl = new JLabel("Tag Data :");

        nameTxt = new JTextField(node.getTagName());
        attTxt = new JTextField(node.getTagAttribute());
        dataTxt = new JTextField(node.getTagData());

        rdbGroup = new ButtonGroup();
        SingleTag = new JRadioButton("Single Tag" , false);
        DoubleTag = new JRadioButton("Double Tag"  , false);
        rdbGroup.add(SingleTag);
        rdbGroup.add(DoubleTag);

        JPanel pnlL = new JPanel(new GridLayout(5,1));
        JPanel pnlR = new JPanel(new GridLayout(5,1));

        pnlL.add(nameLbl);
        pnlL.add(attLbl);
        pnlL.add(dataLbl);
        pnlL.add(SingleTag);
        pnlL.add(Cancel);

        pnlR.add(nameTxt);
        pnlR.add(attTxt);
        pnlR.add(dataTxt);
        pnlR.add(DoubleTag);
        pnlR.add(Edit);

        getContentPane().add(pnlL , BorderLayout.WEST);
        getContentPane().add(pnlR , BorderLayout.CENTER);

        setSize(350,250);

        Edit.addActionListener((ActionEvent e)->
                {

                    if(nameTxt.getText().isEmpty())
                        JOptionPane.showMessageDialog(null , "Your Tag must have name " , "Edit tag Error" , JOptionPane.ERROR_MESSAGE);
                    else if(SingleTag.isSelected()==false && !DoubleTag.isSelected() ==false)
                        JOptionPane.showMessageDialog(null, "your have to choose single or double", "add new tag" , JOptionPane.INFORMATION_MESSAGE);
                    else
                    {
                        node.setTagName(nameTxt.getText());
                        node.setTagAttribute(attTxt.getText());
                        node.setTagData(dataTxt.getText());
                        if(SingleTag.isSelected()==true)
                            node.setisSingleTag(true);
                        else
                            node.setisSingleTag(false);
                        setVisible(false);


                    }
                });

        Cancel.addActionListener((ActionEvent e)->
                {
                    setVisible(false);
                }
        );

        SingleTag.addActionListener((ActionEvent e) -> {
            if (SingleTag.isSelected()) {
                dataTxt.setText("");
                dataTxt.setEnabled(false);
            } else {
                dataTxt.setEnabled(true);
            }
        });

        DoubleTag.addActionListener((ActionEvent e) -> {
            if (SingleTag.isSelected()) {
                dataTxt.setText("");
                dataTxt.setEnabled(false);
            } else {
                dataTxt.setEnabled(true);
            }
        });



    }



}
