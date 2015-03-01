package com.project.monica.snobsinenobilitate.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public abstract class DatabaseHelper {

	public static class DatabaseHelperColumns implements BaseColumns {

		public static final String COLUMN_ID = "_id";
		public static final String TYPE_TEXT = " TEXT";
		public static final String TYPE_BOOLEAN = " BOOLEAN";
		public static final String TYPE_INTEGER = " INTEGER";
		public static final String COMMA_SEP = ",";
	}

	private SSNDatabase database;

	public abstract void upgradeTables(SQLiteDatabase db, int oldVersion, int newVersion);

	public abstract void updateTables(SQLiteDatabase db);

	public abstract String getTableName();

	public abstract String getCreateTableSql();

	public SSNDatabase getDatabase() {
		return database;
	}

	public DatabaseHelper(SSNDatabase database) {
		this.database = database;
	}
}
