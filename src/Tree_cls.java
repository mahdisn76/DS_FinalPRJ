import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by MSN on 12/25/2016.
 */

class TagNode {
    /*
         each node is for a tag ..
         it contains pointers to parent and its children
         also
         its name , attributes , data (string that comes between tags)
    */
    private TagNode Parent;
    private ArrayList<TagNode> Children;
    private String TagName;
    private String TagAttribute;
    private String TagData;
    private boolean isSingleTag;
    private DefaultMutableTreeNode component;

    public TagNode() {
        Parent = null;
        Children = new ArrayList<>();
        TagName = null;
        TagAttribute = null;
        TagData = null;
        isSingleTag = false;
        component = new DefaultMutableTreeNode(getTagName());
    }

    public TagNode(TagNode parent, ArrayList<TagNode> children, String tagName, String tagAttribute, String tagData, boolean issingletag) {
        Parent = parent;
        Children = children;
        if (children == null)
            this.Children = new ArrayList<>();
        TagName = tagName;
        TagAttribute = tagAttribute;
        TagData = tagData;
        isSingleTag = issingletag;
        component = new DefaultMutableTreeNode(getTagName());
    }

    public boolean isSingleTag() {
        return isSingleTag;
    }

    public void setisSingleTag(boolean singleTag) {
        isSingleTag = singleTag;
    }

    public void setParent(TagNode parent) {
        Parent = parent;
    }

    public TagNode getParent() {
        return Parent;
    }

    public void setChildren(ArrayList<TagNode> children) {
        Children = children;
    }

    public ArrayList<TagNode> getChildren() {
        return Children;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getTagAttribute() {
        return TagAttribute;
    }

    public void setTagAttribute(String tagAttribute) {
        TagAttribute = tagAttribute;
    }

    public String getTagData() {
        return TagData;
    }

    public void setTagData(String tagData) {
        TagData = tagData;
    }

    public boolean equals(TagNode o) {
        return (TagName.equals(o.getTagName()));
    }

    public String toString(int tabnum) {
        if (TagName.trim() == null)
            return null;

        String ans = "";
        for (int i = 0; i < tabnum; i++) {
            ans += "\t";
        }
        ans += "<" + TagName.trim();
        if (TagAttribute != null)
            ans += " " + TagAttribute.trim();

        if (isSingleTag == true) {
            ans += "/>";
            System.out.println(ans);
            return ans;
        }

        ans += ">\n";

        if (TagData != null) {
            for (int i = 0; i < tabnum + 1; i++) {
                ans += "\t";
            }
            ans += TagData + "\n";
        }

        if (Children != null)
            for (TagNode t :
                    Children) {
                ans += t.toString(tabnum + 1) + "\n";
            }

        for (int i = 0; i < tabnum; i++) {
            ans += "\t";
        }
        ans += "</" + TagName.trim() + ">";
        return ans;
    }

    // Object for adding TreeNode to JTree
    DefaultMutableTreeNode getComponent() {
        return component;
    }

}

public class Tree_cls {

    /*
        Tree class has
            a root
            and some functions
     */

    private TagNode root;

    public TagNode getRoot() {
        return root;
    }

    public void setRoot(TagNode root) {
        this.root = root;
    }

    Tree_cls() {
        root = new TagNode();
    }


    public void DFS(ArrayList<ArrayList<TagNode>> ans, ArrayList<TagNode> currentpath, TagNode root, TagNode find) {
        // DFS on the tree
        currentpath.add(root);

        if (root.getTagName() != null)
            if (root.equals(find)) {
                ans.add(new ArrayList<TagNode>(currentpath));
            }

        if (root.getChildren() != null)
            for (TagNode n : root.getChildren())
                DFS(ans, currentpath, n, find);

        currentpath.remove(currentpath.size() - 1);
    }


    // it must Path_table
    ArrayList<ArrayList<TagNode>> Search(TagNode node)  //search a tag only by it's tag name and return all of them
    {
        ArrayList<ArrayList<TagNode>> finds = new ArrayList<>();
        DFS(finds, new ArrayList<>(), root, node);
        return finds;
    }

    void AddNode(TagNode parent , TagNode tg) // add a new child to it's parent
    {
        parent.getChildren().add(tg);
        tg.setParent(parent);
    }

    void DeleteNode(TagNode node)  // delete a node and also delete it from
    {
        node.getParent().getChildren().remove(node);
        System.out.println("# Tag Deleted #");
    }

    void DeleteNodeKeepChildren(TagNode tg)
    {
        for (TagNode ch :
                tg.getChildren()) {
            tg.getParent().getChildren().add(ch);
        }
        tg.getParent().getChildren().remove(tg);
    }


    public String toString(TagNode root) {
        if (root == null)
            return null;
        return root.toString(0);
    }


    // Method for drawing the tree on a canvas (Graphics g)
    // By Parham
    public JTree getComponent() {
        DefaultMutableTreeNode root = this.getRoot().getComponent();
        JTree tree = new JTree(root);
        BFS(this.root, root);
        expandAllNodes(tree, 0, tree.getRowCount());
        return tree;
    }

    private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
        for(int i=startingIndex;i<rowCount;++i){
            tree.expandRow(i);
        }

        if(tree.getRowCount()!=rowCount){
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }

    private void BFS(TagNode v, DefaultMutableTreeNode root) {
        for (TagNode child : v.getChildren())
            root.add(child.getComponent());
        for (TagNode child : v.getChildren())
            BFS(child, child.getComponent());
    }

}
