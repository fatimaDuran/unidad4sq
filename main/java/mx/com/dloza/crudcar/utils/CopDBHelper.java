package mx.com.dloza.crudcar.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import mx.com.dloza.crudcar.model.Cop;

/**
 * Created by Ronsoft on 9/16/2017.
 */

public class CopDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cop.db";
    private static final int DATABASE_VERSION = 3 ;
    public static final String TABLE_NAME = "Cop";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COP_NAME = "name";
    public static final String COLUMN_COP_MODEL = "model";
    public static final String COLUMN_COP_MARK = "mark";
    public static final String COLUMN_COP_PROCESADOR = "procesador";
    public static final String COLUMN_COP_MEMORIA = "memoria";
    public static final String COLUMN_COP_YEAR = "year";
    public static final String COLUMN_COP_IMAGE = "image";


    public CopDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COP_NAME + " TEXT NOT NULL, " +
                COLUMN_COP_MODEL + " TEXT NOT NULL, " +
                COLUMN_COP_MARK + " TEXT NOT NULL, " +
                COLUMN_COP_PROCESADOR + " TEXT NOT NULL, " +
                COLUMN_COP_MEMORIA + " TEXT NOT NULL, " +
                COLUMN_COP_YEAR + " NUMBER NOT NULL," +
                COLUMN_COP_IMAGE + " BLOB NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    /**create record**/
    public void saveNewCar(Cop cop) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COP_NAME, cop.getName());
        values.put(COLUMN_COP_MODEL, cop.getModel());
        values.put(COLUMN_COP_MARK, cop.getMark());
        values.put(COLUMN_COP_PROCESADOR, cop.getProcesador());
        values.put(COLUMN_COP_MEMORIA, cop.getMemoria());
        values.put(COLUMN_COP_YEAR, cop.getYear());
        values.put(COLUMN_COP_IMAGE, cop.getImage());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    /**Query records, give options to filter results**/
    public List<Cop> copList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<Cop> copLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Cop cop;

        if (cursor.moveToFirst()) {
            do {
                cop = new Cop();

                cop.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                cop.setName(cursor.getString(cursor.getColumnIndex(COLUMN_COP_NAME)));
                cop.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_COP_MODEL)));
                cop.setMark(cursor.getString(cursor.getColumnIndex(COLUMN_COP_MARK)));
                cop.setProcesador(cursor.getString(cursor.getColumnIndex(COLUMN_COP_PROCESADOR)));
                cop.setMemoria(cursor.getString(cursor.getColumnIndex(COLUMN_COP_MEMORIA)));
                cop.setYear(cursor.getString(cursor.getColumnIndex(COLUMN_COP_YEAR)));
                cop.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_COP_IMAGE)));
                copLinkedList.add(cop);
            } while (cursor.moveToNext());
        }


        return copLinkedList;
    }

    /**Query only 1 record**/
    public Cop getCop(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Cop receivedCop = new Cop();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedCop.setName(cursor.getString(cursor.getColumnIndex(COLUMN_COP_NAME)));
            receivedCop.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_COP_MODEL)));
            receivedCop.setMark(cursor.getString(cursor.getColumnIndex(COLUMN_COP_MARK)));
            receivedCop.setProcesador(cursor.getString(cursor.getColumnIndex(COLUMN_COP_PROCESADOR)));
            receivedCop.setMemoria(cursor.getString(cursor.getColumnIndex(COLUMN_COP_MEMORIA)));
            receivedCop.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_COP_IMAGE)));
        }

        return receivedCop;

    }


    /**delete record**/
    public void deleteCarRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }

    /**update record**/
    public void updateCopRecord(long copId, Context context, Cop updatedcar) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLE_NAME+" SET name ='"+ updatedcar.getName() + "', model ='" + updatedcar.getModel()
                + "', mark ='"+ updatedcar.getMark() +"', transmission ='"+ updatedcar.getProcesador() +
                "', combustible ='"+ updatedcar.getMemoria() /*+"', image ='"+ updatedcar.getImage()*/ +
                "'  WHERE _id='" + copId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();


    }



}
