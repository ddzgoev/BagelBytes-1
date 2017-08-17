package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/16/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

public class DBhandler extends SQLiteOpenHelper {
    //all constants as they are static and final(Db=Database)
    //Db Version
    private static final int Db_Version=1;

    //Db Name
    private static final String Db_Name="ourDB";

    //table name
    private static final String Table_Name="user";
    private static final String Payment_Table_Name="payment";

    //Creating mycontacts Columns
    private static final String User_id="id";
    private static final String User_name="name";
    private static final String User_password="password";

    //Creating Payment Columns
    private static final String Payment_id="id";
    private static final String Payment_name="name";
    private static final String Payment_service="service";
    private static final String Payment_date="date";
    private static final String Payment_pay="pay";
    private static final String Payment_auto="auto";

    //constructor here
    public DBhandler(Context context)
    {
        super(context,Db_Name,null,Db_Version);
    }

    //creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // writing command for sqlite to create table with required columns
        String Create_Table="CREATE TABLE " + Table_Name + "(" + User_id
                + " INTEGER PRIMARY KEY," + User_name + " TEXT," + User_password + " TEXT" + ")";
        db.execSQL(Create_Table);

        String Create_Payment_Table="CREATE TABLE " + Payment_Table_Name + "(" + Payment_id + " INTEGER PRIMARY KEY,"
                + Payment_name + " TEXT," + Payment_service + " TEXT," + Payment_date + " NUMERIC," + Payment_pay + " REAL"
                + Payment_auto + " NUMERIC" + ")";        // Auto (BOOL) is recorded in SQLite as 0 for false, and 1 for true
        db.execSQL(Create_Payment_Table);

    }
    //Upgrading the Db
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + Payment_Table_Name);
        //create the table again
        onCreate(db);
    }
    //Add new User by calling this method
    public void addUser(User usr)
    {
        // getting db instance for writing the user
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        // cv.put(User_id,usr.getId());
        cv.put(User_name,usr.getName());
        cv.put(User_password,usr.getPassword());
        //inserting row
        db.insert(Table_Name, null, cv);
        //close the database to avoid any leak
        db.close();
    }
    public int checkUser(User us)
    {
        int id=-1;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id FROM user WHERE name=? AND password=?",new String[]{us.getName(),us.getPassword()});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id=cursor.getInt(0);
            cursor.close();
        }
        return id;
    }

    // Add a new payment
    public void addPayment(Payment payment){
        // getting db instance for writing the payment
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(Payment_id,payment.getId());
        cv.put(Payment_name,payment.getName());
        cv.put(Payment_service,payment.getService());
        cv.put(Payment_date,payment.getDate());
        cv.put(Payment_pay,payment.getPay());
        //cv.put(Payment_auto,payment.getAuto());
        // insert row
        db.insert(Payment_Table_Name, null, cv);
        db.close();
    }

    // Return all Payment objects as an ArrayList
    public ArrayList<Payment> getAllPayments()
    {
        ArrayList<Payment> paymentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Payment_Table_Name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Payment payment = new Payment();

                payment.setId(Integer.parseInt(cursor.getString(0)));
                payment.setName(cursor.getString(1));
                payment.setService(cursor.getString(2));
                payment.setDate(cursor.getString(3));
                payment.setPay(Double.parseDouble(cursor.getString(4)));
//                payment.setAuto(Integer.parseInt(cursor.getString(5)));

                paymentList.add(payment);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return paymentList;
    }
}