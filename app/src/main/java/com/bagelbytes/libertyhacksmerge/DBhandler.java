package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/16/2017.
 */
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.sql.Date;
        import java.util.ArrayList;

        import static java.security.AccessController.getContext;

public class DBhandler extends SQLiteOpenHelper {
    //all constants as they are static and final(Db=Database)
    //Db Version
    private static final int Db_Version=13;

    //Db Name
    private static final String Db_Name="ourDB";

    //table name
    private static final String Table_Name="user";
    private static final String Payment_Table_Name="payment";
    private static final String Register_Table_Name="register";
    private static final String Payment_Method_Table_Name="paymentMethod";


    //Creating mycontacts Columns
    private static final String User_id="id";
    private static final String User_name="name";
    private static final String User_password="password";

    //Creating Payment Columns
    private static final String Payment_id="id";
    private static final String Payment_name="name";
    private static final String Payment_service="service"; //provider
    private static final String Payment_date="date";
    private static final String Payment_pay="pay";
    private static final String Payment_auto="auto";
    private static final String Payment_Account_Number= "accountNumber";
    private static final String Payment_Account_Holder= "accountHolder";
    private static final String Payment_Zip= "zip";
    private static final String Payment_Payment_Method= "paymentMethodID";

    //Creating Register Columns
    private static final String Register_id="id";
    private static final String Register_fullname="fullname";
    private static final String Register_name="name";
    private static final String Register_password="password";
    private static final String Register_zip="zip";

    //Creating PaymentMethod Columns
    private static final String Payment_Method_id="id";
    private static final String Payment_Method_type="type";
    private static final String Payment_Method_paypalEmail="paypalEmail";
    private static final String Payment_Method_paypalPassword="paypalPassword";
    private static final String Payment_Method_bankAccountName="bankAccountName";
    private static final String Payment_Method_bankRoutingNumber="bankRoutingNumber";
    private static final String Payment_Method_bankAccountNumber="bankAccountNumber";
    private static final String Payment_Method_creditcardNumber="creditcardNumber";
    private static final String Payment_Method_creditcardExpirationDate="creditcardExpirationDate";
    private static final String Payment_Method_creditcardSecurityCode="creditcardSecurityCode";

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

        String Create_Payment_Table="CREATE TABLE " + Payment_Table_Name
                + "(" + Payment_id + " INTEGER PRIMARY KEY,"
                + Payment_name + " TEXT,"
                + Payment_service + " TEXT,"
                + Payment_date + " NUMERIC,"
                + Payment_pay + " REAL,"
                + Payment_Account_Holder + " TEXT,"
                + Payment_Account_Number + " TEXT,"
                + Payment_Payment_Method + " INTEGER,"
                + Payment_Zip + " INTEGER" + ")";        // Auto (BOOL) is recorded in SQLite as 0 for false, and 1 for true
        db.execSQL(Create_Payment_Table);

        String Create_Register_Table="CREATE TABLE " + Register_Table_Name + "(" + Register_id + " INTEGER PRIMARY KEY,"
                + Register_fullname + " TEXT," + Register_name + " TEXT," + Register_password + " TEXT," + Register_zip
                + "NUMERIC" + ")";
        db.execSQL(Create_Register_Table);

        String Create_Payment_Method_Table="CREATE TABLE " + Payment_Method_Table_Name
                + "(" + Payment_Method_id + " INTEGER PRIMARY KEY, "
                + Payment_Method_type + " TEXT, "
                + Payment_Method_paypalEmail + " TEXT, "
                + Payment_Method_paypalPassword + " TEXT, "
                + Payment_Method_bankAccountName + " TEXT, "
                + Payment_Method_bankRoutingNumber+ " INTEGER, "
                + Payment_Method_bankAccountNumber + " INTEGER, "
                + Payment_Method_creditcardNumber+ " INTEGER, "
                + Payment_Method_creditcardExpirationDate+ " INTEGER, "
                + Payment_Method_creditcardSecurityCode+ " INTEGER" + ")";
        db.execSQL(Create_Payment_Method_Table);

    }
    //Upgrading the Db
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table if exists
        Log.d("Database: ", "on upgrade");
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + Payment_Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + Register_Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + Payment_Method_Table_Name);

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
        cv.put(Payment_name,payment.getName());
        cv.put(Payment_service,payment.getService());
        cv.put(Payment_date,payment.getDate());
        cv.put(Payment_pay,payment.getPay());
        cv.put(Payment_Account_Holder, payment.getAccountHolder());
        cv.put(Payment_Account_Number, payment.getAccountNumber());
        cv.put(Payment_Zip, payment.getZip());
        cv.put(Payment_Payment_Method, payment.getPaymentMethod());
        //cv.put(Payment_auto,payment.getAuto());
        // insert row
        db.insert(Payment_Table_Name, null, cv);
        db.close();
    }

    public void updatePayment(Payment payment){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Payment_name,payment.getName());
        cv.put(Payment_service,payment.getService());
        cv.put(Payment_date,payment.getDate());
        cv.put(Payment_pay,payment.getPay());
        cv.put(Payment_Account_Holder, payment.getAccountHolder());
        cv.put(Payment_Account_Number, payment.getAccountNumber());
        cv.put(Payment_Zip, payment.getZip());
        cv.put(Payment_Payment_Method, payment.getPaymentMethod());

        int id = generatePaymentID(payment);

        db.update(Payment_Table_Name, cv, "rowid=" + Integer.toString(id), null);
    }

    public int generatePaymentID(Payment p) {
        // getting db instance for writing the payment
        int id = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid FROM " + Payment_Table_Name +
                    " WHERE " + Payment_Account_Number + "=? AND " + Payment_service + "=?", new String[]{p.getAccountNumber(), p.getService()});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id = cursor.getInt(0);
                cursor.close();
            }
        return id;

    }


    public void addPaymentMethod(PaymentMethod paymentMethod){
        // getting db instance for writing the payment
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(Payment_id,payment.getId());
        cv.put(Payment_Method_type,paymentMethod.getType());
        cv.put(Payment_Method_paypalEmail,paymentMethod.getPaypalEmail());
        cv.put(Payment_Method_paypalPassword,paymentMethod.getPaypalPassword());
        cv.put(Payment_Method_bankAccountName,paymentMethod.getBankAccountName());
        cv.put(Payment_Method_bankRoutingNumber,paymentMethod.getBankRoutingNumber());
        cv.put(Payment_Method_bankAccountNumber,paymentMethod.getBankAccountNumber());
        cv.put(Payment_Method_creditcardNumber,paymentMethod.getCreditcardNumber());
        cv.put(Payment_Method_creditcardExpirationDate,paymentMethod.getCreditcardExpirationDate());
        cv.put(Payment_Method_creditcardSecurityCode,paymentMethod.getCreditcardSecurityCode());
        //cv.put(Payment_auto,payment.getAuto());
        // insert row
        db.insert(Payment_Method_Table_Name, null, cv);
        db.close();
    }

    public int generatePaymentMethodID(PaymentMethod pm) {
        // getting db instance for writing the payment
        int id = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        if (pm.getType().equals("PayPal")) {
            Cursor cursor = db.rawQuery("SELECT rowid FROM " + Payment_Method_Table_Name +
                    " WHERE " + Payment_Method_paypalEmail + "=?", new String[]{pm.getPaypalEmail()});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id = cursor.getInt(0);
                cursor.close();
            }
        }else if(pm.getType().equals("Credit Card")){
            Cursor cursor = db.rawQuery("SELECT rowid FROM " + Payment_Method_Table_Name +
                    " WHERE " + Payment_Method_creditcardNumber + "=? AND " + Payment_Method_creditcardSecurityCode + "=?", new String[]{pm.getCreditcardNumber(), pm.getCreditcardSecurityCode()});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id = cursor.getInt(0);
                cursor.close();
            }
        }else if(pm.getType().equals("Bank Account")){
            Cursor cursor = db.rawQuery("SELECT rowid FROM " + Payment_Method_Table_Name +
                    " WHERE " + Payment_Method_bankAccountNumber + "=? AND " + Payment_Method_bankRoutingNumber + "=?", new String[]{pm.getBankAccountNumber(), pm.getBankRoutingNumber()});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id = cursor.getInt(0);
                cursor.close();
            }
        }
        return id;

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
                payment.setAccountHolder(cursor.getString(5));
                payment.setAccountNumber(cursor.getString(6));
                payment.setPaymentMethod(cursor.getInt(7));
                payment.setZip(cursor.getString(8));

                paymentList.add(payment);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return paymentList;
    }


    // Return PaymentMethod used
    public PaymentMethod getPaymentMethodByID(Integer id)
    {
        PaymentMethod pm = null;
        PaymentMethod pmFailed = new PaymentMethod();
        boolean success = false;
        String selectQuery = "SELECT * FROM " + Payment_Method_Table_Name + "WHERE id=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()){
            //Integer id = Integer.parseInt(cursor.getString(0));
            String type = cursor.getString(1);
            String paypalEmail = cursor.getString(2);
            String paypalPassword = cursor.getString(3);
            String bankAccountName = cursor.getString(4);
            String bankRoutingNumber = cursor.getString(5);
            String bankAccountNumber = cursor.getString(6);
            String creditcardNumber = cursor.getString(7);
            String creditcardExpirationDate = cursor.getString(8);
            String creditcardSecurityCode = cursor.getString(9);
            pm = new PaymentMethod(type, paypalEmail, paypalPassword, bankAccountName, bankRoutingNumber, bankAccountNumber, creditcardNumber, creditcardExpirationDate, creditcardSecurityCode);
        }
        db.close();
        cursor.close();

        if (pm != null) {
            return pm;
        }else{
            return pmFailed;
        }
    }


    public void registerUser(Register user) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(Register_id,user.getId());
        cv.put(Register_fullname,user.getName());
        cv.put(Register_name,user.getName());
        cv.put(Register_password,user.getPassword());
        cv.put(Register_zip,user.getZip());
        db.insert(Register_Table_Name, null, cv);
        db.close();
    }



}
