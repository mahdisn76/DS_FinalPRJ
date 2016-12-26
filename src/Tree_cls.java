import java.util.ArrayList;

/**
 * Created by MSN on 12/25/2016.
 */

class TagNode
{
    private TagNode Parent;
    private ArrayList<TagNode> Children;


    private String TagName;
    private String TagAttribute;
    private String TagData;

    public TagNode(TagNode parent, ArrayList<TagNode> children, String tagName, String tagAttribute, String tagData) {
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
        return (TagName==o.TagName);
    }
}

public class Tree_cls {

    private TagNode root;

    void DFS(ArrayList<ArrayList<TagNode>> ans, ArrayList<TagNode> currentpath, TagNode root, TagNode find) {
        currentpath.add(root);
        if (root == find) {
            ans.add(currentpath);
        }
        for (TagNode n :
                root.getChildren()) {
            DFS(ans, currentpath, n, find);
        }
        currentpath.remove(currentpath.size() - 1);
    }



    // it must test
    ArrayList<ArrayList<TagNode>> Search(TagNode node)  //search a tag only by it's tag name and return all of them
    {
        ArrayList<ArrayList<TagNode>> finds = new ArrayList<>();
        DFS(finds, new ArrayList<TagNode>(), root, node);
        return finds;
    }

    void AddNode(TagNode parentnode, TagNode newnode) {
        parentnode.getChildren().add(newnode);
    }

    void DeleteNode(TagNode node) {
        node.getParent().getChildren().remove(node);

    }

    void DeleteTag(String tag)
    {
        // Alireza must complete it using Search function
    }
    void EditNode(TagNode node)
    {

    }

}
