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
 * The Class ShowWeeklyReports.
 * @author G5 lab group
 */
public class ShowWeeklyReports extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The scroll pane. */
    private JScrollPane scrollPane;
    
    /** The scroll pane2. */
//    private JScrollPane scrollPane2;
    
    /** The table2. */
//    private JTable table2;
    
    /** The list. */
    List<Object> list;

   
    
    /**
     * Instantiates a new show weekly reports.
     *
     * @param list the list
     */
    public ShowWeeklyReports(List<Object> list) {
    	this.list=list;
    	super.setTitle("Weekly Report");
    	initComponents();
    	setVisible(true);
    	
    }

 
    
    /**
     * Inits the components.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	
    	JScrollPane scrollPane2;
    	JTable table2;
    	
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table2 = new javax.swing.JTable() {

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
        table2.setAutoCreateRowSorter(false);
        table2.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        final JTableHeader header = table2.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table2));
        table2.setToolTipText("<html>Process time - time since set date and schedule date <br>Waiting time - Time spent in waiting room</html>");
     
        
      String []tryStr = new String[]{ "", "Process times", "Wait time since Ap date", "# of treated patients"};
      DefaultTableModel model = new DefaultTableModel(tryStr,0);
      table2.setModel(model);
      
      for( Object rowData: list ){
    	  model.addRow((String[])rowData);
      }
      
        scrollPane2 = new JScrollPane(table2);
        this.add(scrollPane2,BorderLayout.NORTH);
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

