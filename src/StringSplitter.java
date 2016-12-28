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

        if(str.substring(0,1)=="</" )  //if it was a single tag --------------
        {
            int spaceindx = str.indexOf(" ");
            int tagindx = str.indexOf(">");

            String tgName;
            String tgAtt;
            if(spaceindx<tagindx && spaceindx!=-1) // has attributes
            {
                tgName = str.substring(2,spaceindx);
                tgAtt = str.substring(spaceindx,tagindx);
            }
            else
            {
                tgName = str.substring(2,tagindx);
                tgAtt="";
            }


            TagNode tg = new TagNode(parent,null,tgName,tgAtt,null,true);
            str=str.substring(str.indexOf(">"));
            split(str,parent);
            return;
        }

        //---------------------------------------------------------------------

        int space_index=str.indexOf(" ");
        int tag_index=str.indexOf(">");

        if(tag_index==-1)
            return;

        String tagname,tagattributes = null;

        if(tag_index<space_index || space_index==-1)
        {
            tagname = str.substring(1,tag_index);
        }
        else
        {
            tagname = str.substring(1, space_index);
            tagattributes = str.substring(space_index + 1, tag_index);
        }

        if(!str.contains("</"+tagname+">"))
            return;

        String txt_btw_tg = str.substring(str.indexOf(">")+1,str.indexOf("</"+tagname+">")); // text between tags

        txt_btw_tg=txt_btw_tg.trim();
        tagname=tagname.trim();
        tagattributes=tagattributes.trim();

        TagNode newtag = new TagNode(parent,null,tagname,tagattributes,null,false);

        if(parent.getChildren()==null)
            parent.setChildren(new ArrayList<>());

        parent.getChildren().add(newtag);

        if(!txt_btw_tg.isEmpty())
            split(txt_btw_tg,newtag);

        if(!str.contains("</"+tagname+">"))
            return;

        str = str.substring(str.indexOf("</"+tagname+">")+("</"+tagname+">").length());
        str=str.trim();

        if(!str.isEmpty())
            split(str,parent);

    }

    public static void TestSpliter() {
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

        Tree_cls testtree = new Tree_cls();
        split(test,testtree.getRoot());
    }
}
