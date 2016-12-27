import java.io.IOException;

/**
 * Created by MSN on 12/26/2016.
 */
public class main {
    public static void main(String[] args) throws IOException {
        //System.out.println("1.Add a Node \n 2.Delete a Node and keep its child \n 3.Delete a Node with its child \n 4.Edit Tag");

        TagNode root = new TagNode(null,null,null,null,null);
        StringSpliter.split(FileIO.read(false),root);
        if(root.getChildren()!=null || root.getChildren().get(0)!=null)
            FileIO.write(root.getChildren().get(0).toString(0));
    }
}
