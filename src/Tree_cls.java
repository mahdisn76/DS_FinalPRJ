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

    void AddNode(String parentName) // add a new child to it's parent
    {
        TagNode consideredParent = findSameNames(parentName);
        //consideredParent = findSameNames(parentName);
        if(consideredParent == null){
            System.out.println("there is no node with this name");
            return;}
Scanner reader = new Scanner(System.in);
        System.out.print("Set Tag name :");
        String tagName = reader.nextLine();
        System.out.print("Set Tag attribute :");
        String tagAtt = reader.nextLine();
        System.out.print("Set Tag data :");
        String tagData = reader.nextLine();
        TagNode node  =  new TagNode();

        TagNode newNode = new TagNode(consideredParent, null , tagName,tagAtt,tagData);
        if(consideredParent.getChildren() == null)
        {
            ArrayList<TagNode> children = new ArrayList<>();
            children.add(newNode);
            consideredParent.setChildren(children);
        }
        else
        consideredParent.getChildren().add(newNode);
//        ArrayList<TagNode> children = consideredParent.getChildren();
//        children.add(newNode);
//        consideredParent.setChildren(children);
    }

    void DeleteNode(TagNode node)  // delete a node and also delete it from
    {
        node.getParent().getChildren().remove(node);
    }

    void DeleteTagWithChildren(String tag)
    {

        TagNode considerNode = findSameNames(tag);
        if (considerNode == null){
            System.out.println("there is no node with this name");
            return;
        }
        DeleteNode(considerNode);

    }
    void DeleteTagKeepChildren(String tag)
    {

        TagNode considerNode = findSameNames(tag);
        if (considerNode == null){
            System.out.println("there is no node with this name");
            return;
        }

        if(considerNode.getChildren() !=null){
            ArrayList<TagNode> parentChildren = considerNode.getParent().getChildren();

//            for(TagNode n:considerNode.getChildren())
//            parentChildren.add(n);
            for(int i=0;i<considerNode.getChildren().size();i++)
                parentChildren.add(considerNode.getChildren().get(i));
        considerNode.getParent().setChildren(parentChildren);}
        DeleteNode(considerNode);

    }

      void EditNode(String TagName) {

          TagNode consideredNode = findSameNames(TagName);
          //consideredNode = findSameNames(TagName);
          if (consideredNode == null){
              System.out.println("there is no node with this name");
              return;
           }
         // TagNode myNode = consideredNode.get(consideredNode.size()-1);

          System.out.println("----------Node Name,Attribute,Data---------");
          System.out.println("Name:" + consideredNode.getTagName() +"\n" + "Attribute:"+ consideredNode.getTagAttribute() +"\n"+"Data:" +consideredNode.getTagData());
          System.out.println("-------------------------------------------\n");

          System.out.println("---------------Insert New Information-------------");
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter new tag name: ");
          String tagName = reader.nextLine();
        System.out.print("Enter new tag attribute: ");
        String tagAtt = reader.nextLine();
        System.out.print("Enter new tagData: ");
        String tagData = reader.nextLine();
        //--------------------------------------- Get  new name,attribute,data

          consideredNode.setTagAttribute(tagAtt);
          consideredNode.setTagData(tagData);
          consideredNode.setTagName(tagName);
    }

    public String ToString(TagNode root)
    {
        if(root==null)
            return null;
        return root.toString(0);
    }

    TagNode findSameNames(String nodeName)
    {
        TagNode node = new TagNode(null,null,nodeName,null,null);

        ArrayList<ArrayList<TagNode>> SameNameNodes;
        ArrayList<TagNode> myConsideredNode;

        SameNameNodes = Search(node);
        Scanner reader = new Scanner(System.in);

        if(SameNameNodes.size() >= 2) {
            System.out.println("There are some " + '<' + nodeName + '>' + "\nwhich one do you want???");
            for (int i = 0; i < SameNameNodes.size(); i++) {
                System.out.print("\n"+ (i+1));
                for (int j = 1; j < SameNameNodes.get(i).size(); j++)
                    System.out.print("->" + SameNameNodes.get(i).get(j).getTagName());
            }
            System.out.println("\n");
            int x = reader.nextInt();
            myConsideredNode = SameNameNodes.get(x-1);
            //return myConsideredNode.get(myConsideredNode.size()-1);
            return SameNameNodes.get(x-1).get(SameNameNodes.get(x-1).size()-1);
        }
        else if(SameNameNodes.size() == 1)
        {
            //return SameNameNodes.get(0).get();
            return SameNameNodes.get(0).get(SameNameNodes.get(0).size()-1);
        }
        else
            return null;

    }


}
