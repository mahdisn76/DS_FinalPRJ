import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Editor extends JFrame {

    public static void main(String[] args) {
        (new Editor()).showWindow();
    }

    private Editor() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TableFlipperZ HTML Editor");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));
        getContentPane().setLayout(new BorderLayout());

        // Create top panel
        JButton btnOpen = new JButton("Open");
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        JButton btnDeleteSubtree = new JButton("Delete subtree");
        JButton btnSave = new JButton("Save");
        JButton btnView = new JButton("View HTML");
        JComboBox<Integer> cmbxFontSize = new JComboBox<>();
        for (int i = 12; i <= 32; i++)
            cmbxFontSize.addItem(i);
        cmbxFontSize.setSelectedIndex(3);
        JPanel pnlTop = new JPanel(new FlowLayout());
        pnlTop.add(btnOpen);
        pnlTop.add(btnAdd);
        pnlTop.add(btnEdit);
        pnlTop.add(btnDelete);
        pnlTop.add(btnDeleteSubtree);
        pnlTop.add(btnSave);
        pnlTop.add(btnView);
        pnlTop.add(cmbxFontSize);
        getContentPane().add(pnlTop, BorderLayout.NORTH);

        // Other components
        JPanel pnlMain = new JPanel(new GridLayout(1, 2));
        JTextArea txtHTML = new JTextArea();
        txtHTML.setTabSize(2);
        txtHTML.setFont(new Font("Courier new", Font.PLAIN, 15));
        JScrollPane sbText = new JScrollPane(txtHTML);
        pnlMain.add(sbText);
        JPanel pnlTree = new JPanel() {
            public void paint(Graphics g) {
                // TODO: 2016-12-27 paint tree
                g.fillOval(getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200);
            }
        };
        pnlMain.add(pnlTree);
        pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(pnlMain, BorderLayout.CENTER);

        // Set ActionListeners
        btnOpen.addActionListener((ActionEvent e) -> {
            txtHTML.setText(FileIO.read(true));
        });

        btnSave.addActionListener((ActionEvent e) -> {
            FileIO.write(txtHTML.getText());
        });

        cmbxFontSize.addActionListener((ActionEvent e) -> {
            txtHTML.setFont(new Font("Courier new", Font.PLAIN, cmbxFontSize.getItemAt(cmbxFontSize.getSelectedIndex())));
        });

    }

    private void showWindow() {
        setSize(800, 600);
        setVisible(true);
    }

}
