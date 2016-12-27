import java.io.IOException;
import java.util.Scanner;

/**
 * Created by MSN on 12/26/2016.
 */
public class main {
    public static void main(String[] args) throws IOException {
        //System.out.println("1.Add a Node \n 2.Delete a Node and keep its child \n 3.Delete a Node with its child \n 4.Edit Tag");

        Tree_cls MainTree = new Tree_cls();  // this is the main tree of program ... that all of html file is in it

        StringSpliter.split(FileIO.read(false),MainTree.getRoot());
        if(MainTree.getRoot().getChildren()!=null || MainTree.getRoot().getChildren().get(0)!=null) {
            FileIO.write(MainTree.getRoot().getChildren().get(0).toString(0));
        }



        System.out.println(" 1.Add a Tag \n 2.Delete a Tag and keep its children \n 3.Delete a Tag with its child \n 4.Edit Tag \n 5.Add a attribute to Tag");
        System.out.println(" 6.Delete Tag attribute \n 7.Edit Tag attribute \n 8.Show Tree");
        int input;
        Scanner reader = new Scanner(System.in);
        input = reader.nextInt();
        switch (input)
        {
            case 1:
                System.out.print("set parent :");
                String parentname = reader.next();
                System.out.print("Set Tag name :");
                String tagname = reader.next();
                System.out.print("Set Tag attribute :");
                String tagatt = reader.next();
                System.out.print("Set Tag data :");
                String tagdata = reader.next();

                User_input.num1(parentname , tagname , tagatt, tagdata);
                break;

            case 2:
                // I will write a function that delete a tag and keep its children
            break;

            case 3:
                System.out.print("Enter tag name:");
                String Tagname = reader.next();
                MainTree.DeleteTag(Tagname);
                break;

            case 4:
                System.out.println("Enter tag name: ");
                String previosname = reader.next();
                User_input.num4(previosname);





        }

    }
}
