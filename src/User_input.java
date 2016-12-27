import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by KINGMAX on 12/26/2016.
 */
public class User_input {

    static void num1(String parentName ,String tagName , String tagAtt , String tagData)
    {
        TagNode parent = new TagNode(null,null,parentName,null,null);
        ArrayList<ArrayList<TagNode>> parents_find;
        parents_find=Tree_cls.Search(parent);

        if(parents_find.size() > 1) {
            System.out.println("There are some " + parentName + "\n which one do you want?");
            for (int i = 0; i < parents_find.size(); i++) {
                System.out.print(i+1);
                for (int j = 0; j < parents_find.get(i).size(); j++)
                    System.out.println("->" + parents_find.get(i).get(j));
            }
            Scanner reader = new Scanner(System.in);
            int x = reader.nextInt();
            TagNode node = new TagNode(parents_find.get(x-1).get(parents_find.get(x-1).size()-1) , null  , tagName , tagAtt , tagData);    //we should get a pointer to our considered node and set it to parent -node
Tree_cls.AddNode(parents_find.get(x-1).get(parents_find.get(x-1).size()-1) , node);
        }
        else if(parents_find.size() == 1)
        {
            TagNode node = new TagNode(parents_find.get(0).get(parents_find.get(0).size()-1) , null  , tagName , tagAtt , tagData);
            Tree_cls.AddNode(parents_find.get(0).get(parents_find.get(0).size()-1) , node);
        }
        else
            System.out.println("There is no parent with this name");

    }



    static void num4(String preTagName )
    {
        TagNode node = new TagNode(null,null,preTagName,null,null);
        ArrayList<ArrayList<TagNode>> node_find = new ArrayList<>();
        node_find = Tree_cls.Search(node);


        if(node_find.size() > 1) {
            System.out.println("There are some " + preTagName + "\n which one do you want?");
            for (int i = 0; i < node_find.size(); i++) {
                System.out.print(i+1);
                for (int j = 0; j < node_find.get(i).size(); j++)
                    System.out.println("->" + node_find.get(i).get(j));
            }
            Scanner reader = new Scanner(System.in);
            int x = reader.nextInt();
            Tree_cls.EditNode(node_find.get(x-1).get(node_find.get(x-1).size()-1));
        }
        else if(node_find.size() == 1)
        {
            Tree_cls.EditNode(node_find.get(0).get(node_find.get(0).size()-1));
        }
        else
        {
            System.out.println("There no tag with this name ");
        }

    }

}
