import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Editor extends JFrame {

    private Tree_cls tree;
    private JTree treeView;
    private JTextArea txtHTML;
    private JScrollPane treeScrollPane;
    private JPanel pnlMainRight;
    private JEditorPane htmlPageView;

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
        JButton btnUpdateTree = new JButton("Update tree");
        JButton btnSave = new JButton("Save");
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
        pnlTop.add(btnUpdateTree);
        pnlTop.add(btnSave);
        pnlTop.add(cmbxFontSize);
        getContentPane().add(pnlTop, BorderLayout.NORTH);

        // Text Editor
        JPanel pnlMain = new JPanel(new GridLayout(1, 2));
        txtHTML = new JTextArea();
        txtHTML.setTabSize(4);
        txtHTML.setFont(new Font("Courier new", Font.PLAIN, 15));
        pnlMain.add(new JScrollPane(txtHTML));
        pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnlMainRight = new JPanel(new GridLayout(2, 1));

        // HTML Viewer
        htmlPageView = new JEditorPane();
        htmlPageView.setEditorKit(new HTMLEditorKit());
        htmlPageView.setEditable(false);
        pnlMainRight.add(new JScrollPane(htmlPageView));

        // TreeView
        treeView = tree.getComponent();
        treeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeView.setFont(new Font("Courier new", Font.PLAIN, 15));
        treeScrollPane = new JScrollPane(treeView);
        pnlMainRight.add(treeScrollPane);

        pnlMain.add(pnlMainRight);
        getContentPane().add(pnlMain, BorderLayout.CENTER);
        // Set ActionListeners
        btnOpen.addActionListener((ActionEvent e) -> {
            File file = FileIO.getFile();
            tree = new Tree_cls();
            StringSplitter.split(FileIO.read(file, false), tree.getRoot());
            updateTreeView(true);
        });

        btnAdd.addActionListener((ActionEvent e) -> {
            tree.AddNode(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView(true);
        });

        btnEdit.addActionListener((ActionEvent e) -> {
            tree.EditNode(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView(true);
        });

        btnDelete.addActionListener((ActionEvent e) -> {
            tree.DeleteTagKeepChildren(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView(true);
        });

        btnDeleteSubtree.addActionListener((ActionEvent e) -> {
            tree.DeleteTagWithChildren(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView(true);
        });

        btnUpdateTree.addActionListener((ActionEvent e) -> {
            tree = new Tree_cls();
            StringSplitter.split(txtHTML.getText(), tree.getRoot());
            txtHTML.setText(tree.toString());
            updateTreeView(true);
        });

        btnSave.addActionListener((ActionEvent e) -> {
            if (tree.getRoot().getChildren() != null || tree.getRoot().getChildren().get(0) != null) {
                FileIO.write(tree.getRoot().getChildren().get(0).toString(0));
            }
        });

        cmbxFontSize.addActionListener((ActionEvent e) -> {
            txtHTML.setFont(new Font("Courier new", Font.PLAIN, cmbxFontSize.getItemAt(cmbxFontSize.getSelectedIndex())));
            treeView.setFont(new Font("Courier new", Font.PLAIN, cmbxFontSize.getItemAt(cmbxFontSize.getSelectedIndex())));
        });

        txtHTML.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                //}
            }
            @Override
            public void keyReleased(KeyEvent e) {
                tree = new Tree_cls();
                StringSplitter.split(txtHTML.getText(), tree.getRoot());
                updateTreeView(false);
            }
        });

    }

    private void showWindow() {
        setSize(800, 600);
        setVisible(true);
    }

    private void updateTreeView(boolean updateText) {
        pnlMainRight.remove(treeScrollPane);
        treeView = tree.getComponent();
        treeScrollPane = new JScrollPane(treeView);
        pnlMainRight.add(treeScrollPane);
        htmlPageView.setText(txtHTML.getText());
        if (updateText && (tree.getRoot().getChildren() != null || tree.getRoot().getChildren().get(0) != null))
            txtHTML.setText(tree.getRoot().getChildren().get(0).toString(0));
        pnlMainRight.revalidate();
    }

}
