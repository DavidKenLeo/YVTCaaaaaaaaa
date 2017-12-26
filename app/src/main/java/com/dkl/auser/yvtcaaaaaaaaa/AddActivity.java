package com.dkl.auser.yvtcaaaaaaaaa;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void add(View v)
    {
        EditText ed = (EditText)findViewById(R.id.na);
        EditText ed2 = (EditText)findViewById(R.id.ph);
        EditText ed3 = (EditText)findViewById(R.id.mail);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("phone", new String[] {"id", "username", "tel","mail"}, "id=?", new String[] {String.valueOf(id)}, null, null, null);

        if (c.moveToFirst())
        {

            ed.setText(c.getString(1));
            ed2.setText(c.getString(2));
            ed3.setText(c.getString(3));
        }

        String username = ed.getText().toString();
        String tel = ed2.getText().toString();
        String mail = ed3.getText().toString();
        String str = "Insert Into phone (username, tel,mail) values ('" + username + "', '" + tel + "', '" + mail + "')";


        //////////////////////////////
        ContentValues cv = new ContentValues();
        cv.put("username", ed.getText().toString());
        cv.put("tel", ed2.getText().toString());
        cv.put("mail",ed3.getText().toString());
        db.update("phone", cv, "id=?", new String[] {String.valueOf(id)});
        db.execSQL(str);
        finish();
    }
    public void delete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("請確認刪除");
        builder.setPositiveButton("刪除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
                db.delete("phone", "id=?", new String[] {String.valueOf(id)});
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
