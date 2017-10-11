package com.example.administrator.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sqlitemodule.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this,"user_blank",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("name","chenglang");
//        values.put("age",26);
//        values.put("adr","广州");
//        db.insert("person","age",null);

        db.delete("person", "personid=?", new String[]{"2"});

        values.put("name","yilin");
        values.put("age",26);
        values.put("adr","茂名");
        db.update("person", values, "personid=?", new String[]{"1"});


        Cursor cursor = db.query("person", new String[]{"personid,name,age,adr"},"adr like?",new String[]{"广州"},null,null,null,null);
        while (cursor.moveToNext()) {
            int personid = cursor.getInt(0); //获取第一列的值,第一列的索引从0开始
            String name = cursor.getString(1);//获取第二列的值
            int age = cursor.getInt(2);//获取第三列的值
            String adr = cursor.getString(3);
            Log.i("person",personid+" "+name+" "+age+" "+adr);
        }
        cursor.close();
        db.close();
    }
}
