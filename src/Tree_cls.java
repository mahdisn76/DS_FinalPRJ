import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by MSN on 12/25/2016.
 */

class TagNode
{
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

    public TagNode()
    {
        Parent       = null;
        Children     = null;
        TagName      = null;
        TagAttribute = null;
        TagData      = null;
    }

    public TagNode(TagNode parent, ArrayList<TagNode> children, String tagName, String tagAttribute, String tagData)
    {
        Parent = parent;
        Children = children;
        TagName = tagName;
        TagAttribute = tagAttribute;
        TagData = tagData;

    }


    public void setParent(TagNode parent) {
        Parent = parent;
    }

    public TagNode getParent()
    {
        return Parent;
    }

    public void setChildren(ArrayList<TagNode> children) {
        Children = children;
    }

    public ArrayList<TagNode> getChildren()
    {
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

    public boolean equals(TagNode o)
    {
        return (TagName.equals(o.getTagName()));
    }

    String toString (int tabnum)
    {
        if(TagName==null)
            return null;

        String ans="";
        for (int i = 0; i < tabnum; i++) {
            ans+="\t";
        }
        ans+="<"+TagName;
        if(TagAttribute!=null)
            ans+=" " + TagAttribute;
        ans+=">\n";

        if(TagData!=null)
        {
            for (int i = 0; i < tabnum+1; i++) {
                ans+="\t";
            }
            ans+=TagData+"\n";
        }

        if(Children!=null)
            for (TagNode t :
                    Children)
            {
                ans+=t.toString(tabnum+1)+"\n";
            }

        for (int i = 0; i < tabnum; i++)
        {
            ans+="\t";
        }
        ans+="</"+TagName+">";
        return ans;
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

    Tree_cls()
    {
        root=new TagNode();
    }



    void DFS(ArrayList<ArrayList<TagNode>> ans, ArrayList<TagNode> currentpath, TagNode root, TagNode find)
    {
        // DFS on the tree
        currentpath.add(root);

        if(root.getTagName()!=null)
            if (root.equals(find))
            {
                ans.add(new ArrayList<TagNode>(currentpath));
            }

        if(root.getChildren()!=null)
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

    void AddNode(TagNode parentnode, TagNode newnode) // add a new child to it's parent
    {
        parentnode.getChildren().add(newnode);
    }

    void DeleteNode(TagNode node)  // delete a node and also delete it from
    {
        node.getParent().getChildren().remove(node);
    }

    void DeleteTag(String tag)
    {
        // Alireza must complete it using Search function

        TagNode node = new TagNode(null,null,tag,null,null);

        ArrayList<ArrayList<TagNode>> finds = new ArrayList<>();
        finds = Search(node);

        if(finds.size() > 1) {
            System.out.println("There are some " + tag + "\n which one do you want?");
            for (int i = 0; i < finds.size(); i++) {
                System.out.print(i+1);
                for (int j = 0; j < finds.get(i).size(); j++)
                    System.out.println("->" + finds.get(i).get(j));
            }
            Scanner reader = new Scanner(System.in);
            int x = reader.nextInt();
            DeleteNode(finds.get(x-1).get(finds.get(x-1).size()-1));
        }
        else if(finds.size() == 1)
        {
            DeleteNode(finds.get(0).get(finds.get(0).size()-1));
        }
        else
        {
            System.out.println("There no tag with this name ");
        }

    }

    public static void EditNode(TagNode node)
    {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter new tag name:");
        String tagName = reader.next();
        System.out.println("Enter new tag attribute: ");
        String tagAtt = reader.next();
        System.out.println("Enter new tagData :");
        String tagData = reader.next();
        //--------------------------------------- Get  new name,attribute,data
        node.setTagAttribute(tagAtt);
        node.setTagData(tagData);
        node.setTagName(tagName);
    }

    public String ToString(TagNode root)
    {
        if(root==null)
            return null;
        return root.toString(0);
    }

}
