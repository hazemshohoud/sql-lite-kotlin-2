package com.h2m.coursesystem

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.h2m.coursesystem.Classes.StudentCorse

class CorsesActivity : AppCompatActivity() {
    private lateinit var CourseList:ListView
    private  var AdapterStrings :ArrayList<String> = ArrayList<String>()
    private lateinit var ArrayAdapter : ArrayAdapter<String>
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_corses)
        CourseList=findViewById(R.id.id_list)
        var db: SQLiteDatabase =openOrCreateDatabase("dbCourse", Context.MODE_PRIVATE,null)
        val C : Cursor =db.rawQuery("Select * From RegistrationRecords",null)
        val C_Index_ID:Int =C.getColumnIndex("id")
        val C_Index_Name:Int =C.getColumnIndex("name")
        val C_Index_Course:Int =C.getColumnIndex("corse")
        val C_Index_Fee:Int =C.getColumnIndex("fee")
        ArrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,AdapterStrings)
        CourseList.adapter=ArrayAdapter
        val Students=ArrayList<StudentCorse>()
        if (C.moveToFirst()){
            do{
                    val temp=StudentCorse().apply {
                    this.id=C.getString(C_Index_ID).toInt()
                    this.name=C.getString(C_Index_Name)
                    this.corse=C.getString(C_Index_Course)
                    this.fee=C.getString(C_Index_Fee)
                }
                Students.add(temp)
                AdapterStrings.add(temp.toString())
            }while (C.moveToNext())
            ArrayAdapter.notifyDataSetChanged()
            CourseList.invalidateViews()
        }
        CourseList.setOnItemClickListener { parent, view, position, id ->
            run{
            val studentInd=Students[position]
            val studemtText=AdapterStrings[position].toString()
            var Intent=Intent(this,EditActivity::class.java).apply{
                putExtra("ID",studentInd.id.toString())
                putExtra("name",studentInd.name)
                putExtra("corse",studentInd.corse)
                putExtra("fee",studentInd.fee)


            }
            startActivity(Intent)
        }  }
    }
}