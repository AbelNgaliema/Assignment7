package Repository.CustomerRepository.Implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import Config.DBConstants;
import Domain.Customer;
import Repository.CustomerRepository.CustomerRepository;

/**
 * Created by AbelN on 23/04/2016.
 */
public class CustomerRepositoryImpl extends SQLiteOpenHelper implements CustomerRepository {

    public static final String TABLE_NAME = "customer";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CUSTOMERADDRESS = "customeraddress";
    public static final String COLUMN_PERSONALINFORMATION ="personalInformation";



   /* public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_ISBNNUMBER= "isbnNumber";
    public static final String COLUMN_PRICE = "price";*/

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public void close() {
        this.close();
    }

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_CUSTOMERADDRESS + " NONE NOT NULL, "
            + COLUMN_PERSONALINFORMATION + " NONE NOT NULL , "
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT);";




    public CustomerRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public CustomerRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public Customer findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_PERSONALINFORMATION,
                        COLUMN_CUSTOMERADDRESS
                        ,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Customer customer = new Customer.Builder().id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID))))
                    .build();

            return customer;
        } else {
            return null;
        }

    }

    @Override
    public Customer save(Customer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CUSTOMERADDRESS, String.valueOf(entity.getCustomerAddress()));
        values.put(COLUMN_PERSONALINFORMATION, String.valueOf(entity.getPersonalInformation()));
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Customer insertedEntity = new Customer.Builder()
                .id(new Long(id))
                .copy(entity)
                .build();
        return insertedEntity;
    }



    @Override
    public Customer update(Customer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CUSTOMERADDRESS, String.valueOf(entity.getCustomerAddress()));
        values.put(COLUMN_PERSONALINFORMATION, String.valueOf(entity.getPersonalInformation()));
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Customer delete(Customer entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Customer> findAll() {


        SQLiteDatabase db = this.getReadableDatabase();
        Set<Customer> customers = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Customer customer = new Customer.Builder()
                        .id(cursor.getColumnIndex(COLUMN_ID))
                        .build();
                customers.add(customer);
            } while (cursor.moveToNext());
        }
        return customers;
    }



    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}

