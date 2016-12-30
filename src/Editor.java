import sun.security.krb5.internal.ccache.Tag;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

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
        JButton btnRefresh = new JButton("Refresh");
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
        pnlTop.add(btnRefresh);
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
            TagNode parentnode = new TagNode();
            parentnode.setTagName(JOptionPane.showInputDialog(this, "Enter parent tag name:", "add node", JOptionPane.QUESTION_MESSAGE));
            ArrayList<ArrayList<TagNode>> paths = new ArrayList<>();
            paths = tree.Search(parentnode);

            PathTable pt = new PathTable(paths);
            pt.setModal(true);
            pt.setVisible(true);
            if(GlobalVariable.SelectedPath != -1)  // this variable's default value is -1
            {
                parentnode = paths.get(GlobalVariable.SelectedPath).get(paths.get(GlobalVariable.SelectedPath).size()-1);
                AddNodefrm frm = new AddNodefrm();
                frm.setModal(true);
                frm.setVisible(true);

                if(GlobalVariable.tgData!=null)
                {
                    TagNode newnode = new TagNode(parentnode,null,GlobalVariable.tgName,GlobalVariable.tgAtt,GlobalVariable.tgData,GlobalVariable.tgIsSingle);
                    tree.AddNode(parentnode,newnode);

                    GlobalVariable.tgData=null;
                    GlobalVariable.tgAtt=null;
                    GlobalVariable.tgName=null;
                }
                GlobalVariable.SelectedPath = -1;  // again set it to the default
            }





            updateTreeView(true);
        });

        btnEdit.addActionListener((ActionEvent e) -> {
            tree.EditNode(JOptionPane.showInputDialog(this, "Enter parent tag name:", "Add tag", JOptionPane.QUESTION_MESSAGE));
            updateTreeView(true);
        });

        btnDelete.addActionListener((ActionEvent e) -> { //delete without deleting it's children
            TagNode tg = new TagNode();
            tg.setTagName(JOptionPane.showInputDialog(this, "Enter tag name:", "delete node", JOptionPane.QUESTION_MESSAGE));
            ArrayList<ArrayList<TagNode>> paths = new ArrayList<>();
            paths = tree.Search(tg);

            PathTable pt = new PathTable(paths);
            pt.setModal(true);
            pt.setVisible(true);
            tg = paths.get(GlobalVariable.SelectedPath).get(paths.get(GlobalVariable.SelectedPath).size()-1);
            if(GlobalVariable.SelectedPath != -1)  // this variable's default value is -1
            {
                tree.DeleteNodeKeepChildren(tg);
                GlobalVariable.SelectedPath = -1;  // again set it to the default
            }
            updateTreeView(true);
        });

        btnDeleteSubtree.addActionListener((ActionEvent e) -> {
            TagNode tg = new TagNode();
            tg.setTagName(JOptionPane.showInputDialog(this, "Enter tag name:", "delete sub tree", JOptionPane.QUESTION_MESSAGE));
            ArrayList<ArrayList<TagNode>> paths = new ArrayList<>();
            paths = tree.Search(tg);
            PathTable pt = new PathTable(paths);
            pt.setModal(true);
            pt.setVisible(true);
            if(GlobalVariable.SelectedPath != -1)  // this variable's default value is -1
            {
                tree.DeleteNode(paths.get(GlobalVariable.SelectedPath).get(paths.get(GlobalVariable.SelectedPath).size()-1));
                GlobalVariable.SelectedPath = -1;  // again set it to the default
            }
            updateTreeView(true);

        });

        btnRefresh.addActionListener((ActionEvent e) -> {
            tree = new Tree_cls();
            StringSplitter.split(txtHTML.getText(), tree.getRoot());
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
        if (updateText && (tree.getRoot().getChildren() != null || tree.getRoot().getChildren().get(0) != null))
            txtHTML.setText(tree.getRoot().getChildren().get(0).toString(0));
        htmlPageView.setText(txtHTML.getText());
        pnlMainRight.revalidate();
    }

}
