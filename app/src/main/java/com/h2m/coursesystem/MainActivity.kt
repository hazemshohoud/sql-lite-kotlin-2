package com.h2m.coursesystem

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btn_add: Button
    lateinit var btn_view: Button
    lateinit var ed_name: EditText
    lateinit var ed_corse: EditText
    lateinit var ed_fee: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btn_add=findViewById(R.id.add)
        btn_view=findViewById(R.id.view)
        ed_name=findViewById(R.id.Name)
        ed_corse=findViewById(R.id.corse)
        ed_fee=findViewById(R.id.fee)
        btn_add.setOnClickListener(
            View.OnClickListener {
                InsertOrCreate()
            }
        )
        btn_view.setOnClickListener(
            View.OnClickListener {
            openView()
        })
    }
    fun openView(){
        var Intent=Intent(this,CorsesActivity::class.java)
        startActivity(Intent)
    }
    fun InsertOrCreate():Unit{
        try {
            var db:SQLiteDatabase=openOrCreateDatabase("dbCourse",Context.MODE_PRIVATE,null);
            db.execSQL(""+
            "CREATE TABLE IF NOT EXISTS RegistrationRecords("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "name VARCHAR,"+
                    "corse VARCHAR,"+
            "fee VARCHAR)")
            val sql ="INSERT INTO RegistrationRecords(name,corse,fee)VALUES(?,?,?)"
            var statement =db.compileStatement(sql)
            statement.bindString(1,ed_name.text.toString())
            statement.bindString(2,ed_corse.text.toString())
            statement.bindString(3,ed_fee.text.toString())
            statement.execute()
            Toast.makeText(this,"Record Added",Toast.LENGTH_LONG).show()
            ed_name.text.clear()
            ed_corse.text.clear()
            ed_fee.text.clear()
        }catch (ex:Exception){
            Toast.makeText(this,"Record Failed ${ex.message}",Toast.LENGTH_LONG).show()
        }
    }

}