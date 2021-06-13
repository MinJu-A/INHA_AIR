package customer.book.ticketing;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;

public class ForcedListSelectionModel extends DefaultColorSelectionModel {
	public void ForcedListSelectionModel (JTable jt) {
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}

