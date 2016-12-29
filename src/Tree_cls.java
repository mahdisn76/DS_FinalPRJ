import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
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
        if (TagName == null)
            return null;

        String ans = "";
        for (int i = 0; i < tabnum; i++) {
            ans += "\t";
        }
        ans += "<" + TagName;
        if (TagAttribute != null)
            ans += " " + TagAttribute;

        if (isSingleTag == true) {
            ans += " />";
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
        ans += "</" + TagName + ">";
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


    // it must test
    ArrayList<ArrayList<TagNode>> Search(TagNode node)  //search a tag only by it's tag name and return all of them
    {
        ArrayList<ArrayList<TagNode>> finds = new ArrayList<>();
        DFS(finds, new ArrayList<TagNode>(), root, node);
        return finds;
    }

    void AddNode(String parentName) // add a new child to it's parent
    {
        TagNode consideredParent = findSameNames(parentName);
        //consideredParent = findSameNames(parentName);
        if (consideredParent == null) {
            return;
        }

        TagNode newNode;

        Scanner reader = new Scanner(System.in);
        System.out.print("Set Tag name :");
        String tagName = reader.nextLine();

        if(!tagName.equals("br"))
        {
        System.out.print("Set Tag attribute :");
        String tagAtt = reader.nextLine();
        System.out.print("Set Tag data :");
        String tagData = reader.nextLine();
         newNode = new TagNode(consideredParent, null, tagName, tagAtt, tagData, false);}
        else
            newNode = new TagNode(consideredParent  , null , tagName , null , null , true);

        if (consideredParent.getChildren() == null) {
            ArrayList<TagNode> children = new ArrayList<>();
            children.add(newNode);
            consideredParent.setChildren(children);
        } else
            consideredParent.getChildren().add(newNode);
        System.out.println("# Tag Added #");

    }

    void DeleteNode(TagNode node)  // delete a node and also delete it from
    {
        node.getParent().getChildren().remove(node);
        System.out.println("# Tag Deleted #");
    }

    void DeleteTagWithChildren(String tag) {
        TagNode considerNode = findSameNames(tag);

        if (considerNode == null)
            return;
        DeleteNode(considerNode);

    }

    void DeleteTagKeepChildren(String tag)
    {
    if(tag.equals("br"))
    {
        System.out.println("this tag does't have any children");
        return;
    }
        TagNode considerNode = findSameNames(tag);
        if (considerNode == null)
            return;


        if(considerNode.getChildren() !=null)
        {
            ArrayList<TagNode> parentChildren = considerNode.getParent().getChildren();

//            for(TagNode n:considerNode.getChildren())
//            parentChildren.add(n);
            for (int i = 0; i < considerNode.getChildren().size(); i++)
                parentChildren.add(considerNode.getChildren().get(i));

            considerNode.getParent().setChildren(parentChildren);
        }
        DeleteNode(considerNode);

    }

    void EditNode(String TagName) {


        TagNode consideredNode = findSameNames(TagName);
        if (consideredNode == null)
            return;

        // TagNode myNode = consideredNode.get(consideredNode.size()-1);

        System.out.println("----------Node Name,Attribute,Data---------");
        System.out.println("Name:" + consideredNode.getTagName() + "\n" + "Attribute:" + consideredNode.getTagAttribute() + "\n" + "Data:" + consideredNode.getTagData());
        System.out.println("-------------------------------------------\n");

        System.out.println("---------------Insert New Information-------------");
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter new tag name: ");
        String tagName = reader.nextLine();
        if(tagName.equals("br"))
        {
            consideredNode.setTagAttribute(null);
            consideredNode.setTagData(null);
            consideredNode.setTagName("br");
            consideredNode.setisSingleTag(true);
            System.out.println("# Tag Edited #");
            return;
        }
        System.out.print("Enter new tag attribute: ");
        String tagAtt = reader.nextLine();
        System.out.print("Enter new tagData: ");
        String tagData = reader.nextLine();
        //--------------------------------------- Get  new name,attribute,data,isSingleTag


          consideredNode.setTagAttribute(tagAtt);
          consideredNode.setTagData(tagData);
          consideredNode.setTagName(tagName);
          System.out.println("# Tag Edited #");

    }

    public String toString(TagNode root) {
        if (root == null)
            return null;
        return root.toString(0);
    }


    TagNode findSameNames(String nodeName)    // it will use directly when we have single tag
    {
        TagNode node = new TagNode(null,null,nodeName,null,null,false);


        ArrayList<ArrayList<TagNode>> SameNameNodes;


        SameNameNodes = Search(node);
        Scanner reader = new Scanner(System.in);

        if (SameNameNodes.size() >= 2) {
            System.out.println("There are some " + '<' + nodeName + '>' + "\nwhich one do you want???");
            for (int i = 0; i < SameNameNodes.size(); i++) {
                System.out.print("\n" + (i + 1));
                for (int j = 1; j < SameNameNodes.get(i).size(); j++)
                    System.out.print("->" + SameNameNodes.get(i).get(j).getTagName());
            }

            System.out.println("\n0.Return to Menu\n");
            int x = reader.nextInt();
            if (x == 0) {
                System.out.println("Operation Failed");
                return null;
            }
            while (x > SameNameNodes.size()) {
                System.out.print("There are just " + (SameNameNodes.size()) + " Tags\n Enter right number: ");
                x = reader.nextInt();
            }

            return SameNameNodes.get(x - 1).get(SameNameNodes.get(x - 1).size() - 1);
        } else if (SameNameNodes.size() == 1) {

            return SameNameNodes.get(0).get(SameNameNodes.get(0).size() - 1);
        } else {
            System.out.println("there is no node with this name");
            return null;
        }

    }

    // Method for drawing the tree on a canvas (Graphics g)
    // By Parham
    public JTree getComponent() {
        DefaultMutableTreeNode root = this.getRoot().getComponent();
        JTree tree = new JTree(root);
        BFS(this.root, root);
        return tree;
    }

    private void BFS(TagNode v, DefaultMutableTreeNode root) {
        for (TagNode child : v.getChildren())
            root.add(child.getComponent());
        for (TagNode child : v.getChildren())
            BFS(child, child.getComponent());
    }

}
