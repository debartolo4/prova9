package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * The Class showClusterReports.
 * @author G5 lab group
 */
public class showClusterReports extends JFrame {

    /** The scroll pane. */
//    private JScrollPane scrollPane;
    
    /** The table. */
//    private JTable table;
    
    /** The list. */
    List<Object> list;


    
    /**
     * Instantiates a new show cluster reports.
     *
     * @param list the list
     */
    public showClusterReports(List<Object> list) {
    	this.list=list;
    	super.setTitle("Monthly Report");
    	initComponents();
    	setVisible(true);
    	
    }

  
    
    /**
     * Inits the components.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	
    	JScrollPane scrollPane;
    	JTable table;
    	
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new javax.swing.JTable() {

            @Override
            public Component prepareRenderer(
                TableCellRenderer renderer, int row, int col) {
                if (col == 0) {
                    return this.getTableHeader().getDefaultRenderer()
                        .getTableCellRendererComponent(this, this.getValueAt(
                            row, col), false, false, row, col);
                } else {
                    return super.prepareRenderer(renderer, row, col);
                }
            }
        };
        table.setAutoCreateRowSorter(false);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        final JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table));
        table.setToolTipText("<html>Process time - time since set date and schedule date <br>Waiting time - Time spent in waiting room</html>");
     
      
      String []tryStr = new String[]{ "Clinic name", "Month", "Week number in month", "Number of trited patients", "Avg. process time", "Avg. time in waiting room", " Number no-show patients ","Clients left company" };
      DefaultTableModel model = new DefaultTableModel(tryStr,0);
      table.setModel(model);
      
      for( Object rowData: list ){
    	  model.addRow((String[])rowData);
      }
      
        scrollPane = new JScrollPane(table);
        this.add(scrollPane,BorderLayout.NORTH);
        pack();
    }

   
    
    /**
     * The Class HeaderRenderer.
     */
    private static class HeaderRenderer implements TableCellRenderer {

        /** The renderer. */
        TableCellRenderer renderer;

        /**
         * Instantiates a new header renderer.
         *
         * @param table the table
         */
        public HeaderRenderer(JTable table) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
        }
    }
    

    
}




