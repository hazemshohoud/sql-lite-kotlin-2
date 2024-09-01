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

class EditActivity : AppCompatActivity() {
    lateinit var btn_Edit: Button
    lateinit var btn_Delete: Button
    lateinit var id:EditText
    lateinit var ed_name: EditText
    lateinit var ed_corse: EditText
    lateinit var ed_fee: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)
        btn_Edit=findViewById(R.id.edite)
        btn_Delete=findViewById(R.id.delet)
        id=findViewById(R.id.id)
        ed_name=findViewById(R.id.Name)
        ed_corse=findViewById(R.id.corse)
        ed_fee=findViewById(R.id.fee)
        val inten=intent
        val t1=inten.getStringExtra("ID").orEmpty()
        val t2=inten.getStringExtra("name").orEmpty()
        val t3=inten.getStringExtra("corse").orEmpty()
        val t4=inten.getStringExtra("fee").orEmpty()
        id.setText(t1)
        ed_name.setText(t2)
        ed_corse.setText(t3)
        ed_fee.setText(t4)
        btn_Edit.setOnClickListener(View.OnClickListener {
            EditeRecord()
        })
        btn_Delete.setOnClickListener(View.OnClickListener {
            DeletRecord()
        })
    }
    private  fun EditeRecord(){
        try {
            val id=id.text.toString()
            val name=ed_name.text.toString()
            val corse=ed_corse.text.toString()
            val fee=ed_fee.text.toString()
            var db: SQLiteDatabase =openOrCreateDatabase("dbCourse", Context.MODE_PRIVATE,null);
            val sql="UPDATE RegistrationRecords SET name = ?, corse=?,fee=? WHERE id=?"
            val statement= db.compileStatement(sql)
            statement.bindString(1,name)
            statement.bindString(2,corse)
            statement.bindString(3,fee)
            statement.bindString(4,id)
            statement.execute()
            Toast.makeText(this,"Record update ", Toast.LENGTH_LONG).show()

            val Intent =Intent(this,CorsesActivity::class.java)
            startActivity(Intent)

        }catch (ex:Exception){
            Toast.makeText(this,"Record Failed ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun DeletRecord(){
        try {
            val id=id.text.toString()
            var db: SQLiteDatabase =openOrCreateDatabase("dbCourse", Context.MODE_PRIVATE,null);

            val sql="DELETE FROM RegistrationRecords WHERE id=?"
            val statement= db.compileStatement(sql)
            statement.bindString(1,id)
            statement.execute()
            Toast.makeText(this,"Record Delete ", Toast.LENGTH_LONG).show()
            val Intent =Intent(this,CorsesActivity::class.java)
            startActivity(Intent)

        }catch (ex:Exception){
            Toast.makeText(this,"Record Failed ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }
}