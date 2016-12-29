import sun.tracing.dtrace.DTraceProviderFactory;

import java.util.ArrayList;

/**
 * Created by MSN on 12/26/2016.
 */

/*
    this class
    splits the string that read from the file
    and pass it to the Tree ...
*/

public class StringSplitter {
    public static void split(String str,TagNode parent)
    {
        str = str.trim();
        if(str.isEmpty())
            return ;
        if(!str.contains("<"))  // set tag data ...
        {
            if(parent==null)
                return;
            else
                if(parent.getTagData()==null)
                    parent.setTagData(str);
                else
                    parent.setTagData(parent.getTagData()+str);
            return;
        }

        if(str.charAt(0)!='<') // for settig the tagdata
        {
            if(parent.getTagData()==null)
                parent.setTagData(str.substring(0, str.indexOf('<')));
            else
                parent.setTagData(parent.getTagData() + (str.substring(0, str.indexOf('<'))));

            str = str.substring(str.indexOf('<'));
        }

        str = str.trim();
        if(str.isEmpty())
        {
            return;
        }

        // ---------------------------------------- to this line is only for tagdata

        if(!str.contains(">"))
            return;

        //single tag ---------------------------------------------------------



        TagNode tg;
        int tagindx = str.indexOf(">");
        int spcindx = str.indexOf(" ");

        boolean att;      //hasAttribute
        boolean isSingle; //is a single tag

        if(str.charAt(tagindx-1)=='/')
            isSingle=true;
        else
            isSingle=false;

        if(str.contains(" ") && spcindx<tagindx)
            att=true;
        else
            att=false;


        if(att==true)  //has attributes
            tg = new TagNode(parent,null,str.substring(1,spcindx).trim(),str.substring(spcindx,tagindx-1).trim(),null,isSingle);
        else
            tg = new TagNode(parent,null,str.substring(1,tagindx).trim(),null,null,isSingle);

        if(parent.getChildren()==null)
            parent.setChildren(new ArrayList<>());
        parent.getChildren().add(tg);

        if(isSingle==true)  //it is a single tag
        {
            str=str.substring(str.indexOf(">")+1).trim();
            split(str,parent);
            return;
            /* this tag removed from str
                and
                split will call for new str ...
                ans after that return
             */
        }

        // from here to the end will done if it is a double tag

        int endindx=-1;  // for text between tag
        int startindx = str.indexOf(">")+1;

        int i=startindx;
        int currenttg=1;
        while(i<str.length())
        {
            if(str.substring(i).indexOf("<"+tg.getTagName())==0 && str.substring(i).charAt(str.substring(i).indexOf(">")-1)!='/')
                currenttg++;

            if(str.substring(i).indexOf("</"+tg.getTagName()+">")==0)
            {
                currenttg--;

                if(currenttg==0)
                {
                    endindx=i-1; // -1?!
                    break;
                }
            }
            i++;
        }
        if(endindx==-1)
            return;
        endindx+= 1;

        String txt_btw_tg = str.substring(startindx,endindx).trim(); // text between tags

        if(!txt_btw_tg.isEmpty())
            split(txt_btw_tg,tg);

        str = str.substring(endindx+("</"+tg.getTagName()+">").length()).trim();

        if(!str.isEmpty())
            split(str,parent);
    }


    public static void Test(String[] args) {

        Tree_cls MainTree = new Tree_cls();  // this is the main tree of program ... that all of html file is in it
        System.out.println("\n***********************----WELCOME----***********************");

        String str = "<html>\n" +
                "    <head>\n" +
                "        <title>PRJ3</title>\n" +
                "\t\t<link type=\"text/css\" rel=\"stylesheet\" href=\"temp.css\"/>\n" +
                "    </head>\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <body>\n" +
                "        <header style=\"background-color:palegoldenrod;height:50px ; margin:5px 5px 5px 5px ; text-align:center ;color:red\"  >  MSN WebSite </header>\n" +
                "        <br />\n" +
                "        <footer style=\"background-color:lightblue\">\n" +
                "            <ul style=\"\">\n" +
                "\n" +
                "                <li id=\"d2\">\n" +
                "                    news\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >sport</li>\n" +
                "                        <li id=\"d1\" >industry</li>\n" +
                "                        <li id=\"d1\" >social</li>\n" +
                "                        <li id=\"d1\" >technology</li>\n" +
                "                        <li id=\"d1\" >political</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "               \n" +
                "                <li id=\"d2\" >\n" +
                "                    gallery\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >photos</li>\n" +
                "                        <li id=\"d1\" >clips</li>\n" +
                "                        <li id=\"d1\" >musics</li>\n" +
                "                        <li id=\"d1\" >docs</li>\n" +
                "                        <li id=\"d1\" >others</li>\n" +
                "                    </ul>                \n" +
                "                </li>\n" +
                "               \n" +
                "                <li id=\"d2\">\n" +
                "                    users\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >sign in</li>\n" +
                "                        <li id=\"d1\" >sign up</li>\n" +
                "                        <li id=\"d1\" >feed back</li>\n" +
                "                        <li id=\"d1\" >RSS</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "                \n" +
                "                <li id=\"d2\">\n" +
                "                    about us\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >head</li>\n" +
                "                        <li id=\"d1\" >developer team</li>\n" +
                "                        <li id=\"d1\" >news part management</li>\n" +
                "                        <li id=\"d1\" >IT department</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "\n" +
                "\n" +
                "\n" +
                "            </ul>\n" +
                "            <br /><br /><br /><br /><br /><br /><br />\n" +
                "        </footer>\n" +
                "        <br />\n" +
                "\n" +
                "        <div style=\"float:left ; height:300px ; width:100px ; background-color:darkgray ; \">\n" +
                "        advertisement\n" +
                "        </div>\n" +
                "        <div style=\"float:left;width:69%;height:400px ;background-color:lime\">\n" +
                "            <div id=\"d3\" >\n" +
                "                    <img src=\"a.png\" style=\"margin:47%\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div style=\"float:right;background-color:mediumspringgreen;width:100px;height:300px\" >\n" +
                "        linked sites :<br />\n" +
                "            <a href=\"http://www.google.com\">google.com</a>\n" +
                "            <a href=\"http://www.yahoo.com\">yahoo.com</a>\n" +
                "            <a href=\"http://www.stackoverflow.com\">stackoverflow</a>\n" +
                "            <a href=\"http://www.cnet.com\">cnet.com</a>\n" +
                "        \n" +
                "        </div>\n" +
                "    </body>\n" +
                "\n" +
                "</html>";


        String str2 = "<link type=\"text/css\" rel=\"stylesheet\" href\"temp.css\"/>" ;

        String str3 ="<ul style=\"\">\n" +
                "\n" +
                "                <li id=\"d2\">\n" +
                "                    news\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >sport</li>\n" +
                "                        <li id=\"d1\" >industry</li>\n" +
                "                        <li id=\"d1\" >social</li>\n" +
                "                        <li id=\"d1\" >technology</li>\n" +
                "                        <li id=\"d1\" >political</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "               \n" +
                "                <li id=\"d2\" >\n" +
                "                    gallery\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >photos</li>\n" +
                "                        <li id=\"d1\" >clips</li>\n" +
                "                        <li id=\"d1\" >musics</li>\n" +
                "                        <li id=\"d1\" >docs</li>\n" +
                "                        <li id=\"d1\" >others</li>\n" +
                "                    </ul>                \n" +
                "                </li>\n" +
                "               \n" +
                "                <li id=\"d2\">\n" +
                "                    users\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >sign in</li>\n" +
                "                        <li id=\"d1\" >sign up</li>\n" +
                "                        <li id=\"d1\" >feed back</li>\n" +
                "                        <li id=\"d1\" >RSS</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "                \n" +
                "                <li id=\"d2\">\n" +
                "                    about us\n" +
                "                    <ul>\n" +
                "                        <li id=\"d1\" >head</li>\n" +
                "                        <li id=\"d1\" >developer team</li>\n" +
                "                        <li id=\"d1\" >news part management</li>\n" +
                "                        <li id=\"d1\" >IT department</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "\n" +
                "\n" +
                "\n" +
                "            </ul>";

        StringSplitter.split(str,MainTree.getRoot());

        System.out.println(MainTree.getRoot().getChildren().get(0).toString(0));

    }
}
