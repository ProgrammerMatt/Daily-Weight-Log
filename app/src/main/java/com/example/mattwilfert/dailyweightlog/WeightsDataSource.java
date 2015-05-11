package com.example.mattwilfert.dailyweightlog;

/**
 * Created by MattWilfert on 4/3/15.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class WeightsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_WEIGHT, MySQLiteHelper.COLUMN_DATE};

    public WeightsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Weight createWeight(double weight, String date) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weight);
        values.put(MySQLiteHelper.COLUMN_DATE, ""+ 2);
        long insertId = database.insert(MySQLiteHelper.TABLE_WEIGHTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_WEIGHTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Weight newWeight = cursorToWeight(cursor);
        cursor.close();
        return newWeight;
    }

    public void deleteWeight(Weight Weight) {
        long id = Weight.getId();
        System.out.println("Weight deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_WEIGHTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Weight> getAllWeights() {
        List<Weight> Weights = new ArrayList<Weight>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_WEIGHTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Weight Weight = cursorToWeight(cursor);
            Weights.add(Weight);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Weights;
    }

    public ArrayList<Double> getAllWeightsValues() {
        ArrayList<Double> Weights = new ArrayList<Double>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_WEIGHTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Weight Weight = cursorToWeight(cursor);
            Weights.add(Weight.getWeight());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Weights;
    }

    private Weight cursorToWeight(Cursor cursor) {
        Weight Weight = new Weight();
        Weight.setId(cursor.getLong(0));
        Weight.setWeight(cursor.getDouble(1));
        return Weight;
    }
}

