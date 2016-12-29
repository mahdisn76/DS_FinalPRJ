import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class Editor extends JFrame {

    private Tree_cls tree;
    private JTree treeView;

    public static void main(String[] args) {
        (new Editor()).showWindow();
    }

    private Editor() {
        // Initialize data structures
        tree = new Tree_cls();

        // Main Window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TableFlipperZ HTML Editor");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));
        getContentPane().setLayout(new BorderLayout());

        // Top panel
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
        pnlMain.add(new JScrollPane(txtHTML));
        pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        //TreeView
        treeView = tree.getComponent();
        treeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        pnlMain.add(new JScrollPane(treeView));

        getContentPane().add(pnlMain, BorderLayout.CENTER);

        // Set ActionListeners
        btnOpen.addActionListener((ActionEvent e) -> {
            File file = FileIO.getFile();
            StringSplitter.split(FileIO.read(file, false), tree.getRoot());
            txtHTML.setText(FileIO.read(file, true));
            updateTreeView();
        });

        btnAdd.addActionListener((ActionEvent e) -> {
            tree.AddNode(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView();
        });

        btnEdit.addActionListener((ActionEvent e) -> {
            tree.EditNode(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView();
        });

        btnDelete.addActionListener((ActionEvent e) -> {
            tree.DeleteTagKeepChildren(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView();
        });

        btnDeleteSubtree.addActionListener((ActionEvent e) -> {
            tree.DeleteTagWithChildren(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView();
        });

        btnSave.addActionListener((ActionEvent e) -> {
            if (tree.getRoot().getChildren() != null || tree.getRoot().getChildren().get(0) != null) {
                FileIO.write(tree.getRoot().getChildren().get(0).toString(0));
            }
        });

        cmbxFontSize.addActionListener((ActionEvent e) -> {
            txtHTML.setFont(new Font("Courier new", Font.PLAIN, cmbxFontSize.getItemAt(cmbxFontSize.getSelectedIndex())));
        });

    }

    private void showWindow() {
        setSize(800, 600);
        setVisible(true);
    }

    private void updateTreeView() {
        treeView = tree.getComponent();
        DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        model.reload(root);
    }

}
