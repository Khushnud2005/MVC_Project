package uz.example.mvcproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import uz.example.mvcproject.R

class DetailsActivity : AppCompatActivity() {

    lateinit var tv_title:TextView
    lateinit var tv_body:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViews()
    }

    private fun initViews() {
        tv_title = findViewById(R.id.tv_title_detail)
        tv_body = findViewById(R.id.tv_body_detail)
        val extras = intent.extras
        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            tv_title.setText(extras.getString("title")!!.uppercase())
            tv_body.setText(extras.getString("body"))
        }
    }
}