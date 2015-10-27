package org.mtforce.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TableColumn {

	SENSOR(0, "Sensor"),NAME(1, "Name"), VALUE(2, "Value");

	private TableColumn(int index, String name) {
		this.index = index;
		this.name = name;
	}

	private int index;
	private String name;

	private static final Map<Integer, TableColumn> COLUMN_INDEX_NAME_MAP = new HashMap<>();
	private static final List<String> NAMES = new ArrayList<>();

	static {
		for (TableColumn c : TableColumn.values()) {
			COLUMN_INDEX_NAME_MAP.put(c.index, c);
			NAMES.add(c.name);
		}
	}

	public static TableColumn fromIndex(int colIndex) {
		TableColumn columnName = COLUMN_INDEX_NAME_MAP.get(colIndex);
		return (columnName != null) ? columnName : null;
	}

	public static String[] getNames() {
		return NAMES.toArray(new String[NAMES.size()]);
	}

}
