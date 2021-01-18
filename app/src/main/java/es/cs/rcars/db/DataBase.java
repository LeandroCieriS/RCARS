package es.cs.rcars.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import es.cs.rcars.models.User;

public class DataBase extends SQLiteOpenHelper {

    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_USER_USUARIO = "USER_USUARIO";
    public static final String COLUMN_USER_NOMBRE = "USER_NOMBRE";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PASS = "USER_PASS";
    public static final String COLUMN_USER_ADMIN = "USER_ADMIN";
    public static final String COLUMN_ID = "ID";

    public DataBase(@Nullable Context context) {
        super(context, "socialjam.db", null, 1);
    }

    //Se crea la primera vez que la bbdd es accedida, tiene que haber codigo para crear la nueva bbdd
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement =
                "CREATE TABLE " + USERS_TABLE + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_USER_USUARIO + " TEXT, "
                        + COLUMN_USER_NOMBRE + " TEXT, "
                        + COLUMN_USER_EMAIL + " TEXT, "
                        + COLUMN_USER_PASS + " TEXT, "
                        + COLUMN_USER_ADMIN + " BOOL)";

        db.execSQL(createTableStatement);
    }

    //Se llama si el num version de la bbdd cambia, previene la rotura de la bbdd si los usuarios anteriores cambian
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor checkLogin(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM " + USERS_TABLE
                        + " WHERE " + COLUMN_USER_USUARIO + " = '" + user.toLowerCase() +"' AND "
                        + COLUMN_USER_PASS + " = '" + pass +"'",
                null
        );

        //return c.getCount() > 0;
        return c;
    }


    public List<User> getEveryone() {
        List<User> returnList = new ArrayList<>();

        //obtener los usuarios
        String queryString = "SELECT * FROM " + USERS_TABLE;

        //Solo leer para q no se bloquee la bbdd (cuando escribes se bloquea)
        SQLiteDatabase db = this.getReadableDatabase();

        //tmb se puede usar db.execSQL(); void
        //cursor rawQuery
        Cursor cursor = db.rawQuery(queryString, null);

        //controlar que se ejecuto la orden query
        if (cursor.moveToFirst()) {
            //crea objetos de usuario en la lista mientras haya
            do {
                int userID = cursor.getInt(0);
                String userUsuario = cursor.getString(1);
                String userNombre = cursor.getString(2);
                String userEmail = cursor.getString(3);
                String userPass = cursor.getString(4);
                boolean userAdmin = cursor.getInt(5) == 1;

                User newUser = new User();
                returnList.add(newUser);

            } while (cursor.moveToNext());
        }  //Fallo no pone nada


        //Cerramos conexiones a la bbdd
        cursor.close();
        db.close();

        return returnList;
    }


}