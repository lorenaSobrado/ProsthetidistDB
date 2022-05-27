package SwingWindows;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class LabelRenderer implements TableCellRenderer {
	
	// to process correctly the image, if not, it would show a String instead of the
	// actual image

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return (Component) value;
	}

}
