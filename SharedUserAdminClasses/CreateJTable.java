package SharedUserAdminClasses;

import Client.ClientWorker;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;

public class CreateJTable {
    String whatTable;
    JScrollPane scrollPane;
    ClientWorker clientSocket;
    JTable table;

    public int getTableRowCount(JTable table){
        return table.getRowCount();
    }
    public CreateJTable(ClientWorker clientSocket){
        this.clientSocket=clientSocket;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTable getTable(){
        return table;
    }

    public void setWhatTable(String whatTable) {
        this.whatTable = whatTable;
    }

    public String getWhatTable() {
        return whatTable;
    }

    public JScrollPane createTable(String whatTable){
        try {
            setWhatTable(whatTable);
            this.clientSocket.giveTable(getWhatTable());
            table = clientSocket.getTable();
            table.setBorder(null);
            //
            scrollPane = new JScrollPane(table);
            scrollPane.setBackground(new Color(31, 36, 42));

            table.setBackground(new Color(31, 36, 42));
            table.setForeground(new Color(255, 117, 0));
            table.setRowHeight(30);
            table.setBorder(null);
            scrollPane.setBorder(null);

            //nazwy tabel
            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false);
            header.setResizingAllowed(false);
            header.setBackground(new Color(21, 25, 28));
            header.setForeground(new Color(255, 117, 0));
            header.setPreferredSize(new Dimension(10, 30));
            header.setBorder(new LineBorder(new Color(21, 25, 28), 2));
            //Sluzy do usuwania obramowan z komorek
            final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setBorder(null);
            header.setDefaultRenderer(renderer);
            table.setFillsViewportHeight(true);

            setTable(table);
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
        return scrollPane;
    }
}
