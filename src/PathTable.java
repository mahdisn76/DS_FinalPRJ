/**
 * Created by MSN on 12/30/2016.
 */
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class PathTable extends JDialog{

    /**
     * Created by MSN on 12/30/2016.
     */


    PathTable(ArrayList<ArrayList<TagNode>> paths)
    {
        //headers for the table
        String[] columns = new String[] {
                "Id", "Path"
        };

        String data[][] = new String[paths.size()][2];

        int indx=0;
        for (ArrayList<TagNode> path: paths)
        {
            String tmp="";
            for(int i=1;i<path.size()-1;i++)
            {
                tmp+=path.get(i).getTagName();
                tmp +=" -> ";
            }
            tmp+=path.get(path.size()-1).getTagName();
            data[indx][0]= String.valueOf(indx+1);
            data[indx][1] = tmp;
            indx++;
        }




        String col[] = new String[2];
        col[0]="ID";
        col[1]="Path";

        DefaultTableModel model = new DefaultTableModel(data,col)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);


        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    GlobalVariable.SelectedPath=row;
                    setVisible(false);
                    // your valueChanged overridden method
                }
            }
        });

//        table.addMouseListener((ActionEvent e)->
//        {
//
//        });

        //create table with data
        //JTable table = new JTable(data, columns);

        //add the table to the frame
        this.add(new JScrollPane(table));

        this.setTitle("Table Example");
        this.pack();
    }

}
