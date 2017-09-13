package Editor.EditorTables;

public class MainListTableModel extends MainEditorTableModel
{
	private String[] columnNames = {"ID", "NAME", "GROUP"};

	public boolean isCellEditable(int row, int col)
	{
		return false;
	}
}
