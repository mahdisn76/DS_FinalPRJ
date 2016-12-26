import java.util.ArrayList;

/**
 * Created by MSN on 12/26/2016.
 */

/*
    this class
    splits the string that read from the file
    and pass it to the Tree ...
*/

public class StringSpliter {
    public static void split(String str,TagNode parent)
    {
        str.trim();
        if(str.isEmpty())
            return ;
        if(!str.contains("<"))
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
            if(str.contains("<"))
            {
                if(parent.getTagData()==null)
                    parent.setTagData(str.substring(0, str.indexOf('<')));
                else
                    parent.setTagData(parent.getTagData() + (str.substring(0, str.indexOf('<'))));
                str = str.substring(str.indexOf('<'));
            }
            else
            {
                if(parent.getTagData()==null)
                    parent.setTagData(str.substring(0));
                else
                    parent.setTagData(parent.getTagData() + (str.substring(0)));
                str="";
            }
        }
        str.trim();
        if(str.isEmpty())
        {
            return;
        }

        int space_index=str.indexOf(" ");
        int tag_index=str.indexOf(">");

        if(tag_index==-1)
            return;

        String tagname="",tagattributes="";

        if(tag_index<space_index || space_index==-1)
        {
            tagname = str.substring(1,tag_index);
        }
        else
        {
            tagname = str.substring(1, space_index);
            tagattributes = str.substring(space_index + 1, tag_index);
        }

        if(str.indexOf("</"+tagname+">")==-1)
            return;

        String txt_btw_tg = str.substring(str.indexOf(">")+1,str.indexOf("</"+tagname+">")); // text between tags

        txt_btw_tg.trim();
        tagname.trim();
        tagattributes.trim();

        TagNode newtag = new TagNode(parent,null,tagname,tagattributes,null);

        if(parent.getChildren()==null)
            parent.setChildren(new ArrayList<>());

        parent.getChildren().add(newtag);

        if(!txt_btw_tg.isEmpty())
            split(txt_btw_tg,newtag);

        if(str.indexOf("</"+tagname+">")==-1)
            return;

        str = str.substring(str.indexOf("</"+tagname+">")+("</"+tagname+">").length());
        str.trim();

        if(!str.isEmpty())
            split(str,parent);

        return;
    }

    public static void TestSpliter(String[] args) {
        String test =
                "<html attribute is here>" +
                "tag data is here" +
                "<p1 attp1>" +
                "tag inside another one is here" +
                "</p1> " +
                "<H1 ATTh1>THIS is a HEADER</H1>"+
                "another data can be here " +
                        "<h2></h2>"+
                "</html>";
        TagNode root = new TagNode(null,null,null,null,null);
        split(test,root);

    }
}
