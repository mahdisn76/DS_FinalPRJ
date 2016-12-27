import java.io.IOException;
import java.util.Scanner;

/**
 * Created by MSN on 12/26/2016.
 */
public class main {
    public static void main(String[] args) throws IOException {

        Tree_cls MainTree = new Tree_cls();  // this is the main tree of program ... that all of html file is in it

        StringSpliter.split(FileIO.read(false),MainTree.getRoot());

        boolean repeat = true;

while(repeat) {
    System.out.println("\n***********************----WELCOME----***********************");
    System.out.println(" 1.Add a Tag \n 2.Delete a Tag and keep its children \n 3.Delete a Tag with its child \n 4.Edit Tag");
    System.out.println(" 5.Show Tree \n 0.Save and Exit");
    int input;
    Scanner reader = new Scanner(System.in);
    input = reader.nextInt();
    switch (input) {
        case 1:
            System.out.print("set parent: ");
            String parentname = reader.next();
            MainTree.AddNode(parentname);
            break;

        case 2:
            System.out.print("Enter tag name: ");
            String tagname = reader.next();
            MainTree.DeleteTagKeepChildren(tagname);
            break;

        case 3:
            System.out.print("Enter tag name: ");
            String Tagname = reader.next();
            MainTree.DeleteTagWithChildren(Tagname);
            break;

        case 4:
            System.out.print("Enter tag name: ");
            String previosname = reader.next();
            MainTree.EditNode(previosname);
            break;

        case 5:
            break;


        case 0:
            repeat =false;
            break;

    }
}
        if(MainTree.getRoot().getChildren()!=null || MainTree.getRoot().getChildren().get(0)!=null) {
            FileIO.write(MainTree.getRoot().getChildren().get(0).toString(0));
        }

    }
}
