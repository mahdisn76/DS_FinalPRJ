import java.io.File;

/**
 * Created by MSN on 12/26/2016.
 */
public class main {
    public static void main(String[] args)
    {
        //System.out.println("1.Add a Node \n 2.Delete a Node and keep its child \n 3.Delete a Node with its child \n 4.Edit Tag");
        //readHTML()
        TagNode root = new TagNode(null,null,null,null,null);
        StringSpliter.split(FileReader.readHTML(),root);
        System.out.println("Done");
    }


}
