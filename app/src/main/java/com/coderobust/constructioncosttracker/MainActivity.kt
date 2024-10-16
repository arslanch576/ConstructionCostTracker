package com.coderobust.constructioncosttracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderobust.constructioncosttracker.databinding.ActivityAddProjectBinding
import com.coderobust.constructioncosttracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this,AddProjectActivity::class.java))
        }



    }

    override fun onResume() {
        super.onResume()
        val adapter=ProjectAdapter(AppDatabase.getDatabase(this).projectDao().getAllProjects())
        binding.recyclerview.adapter=adapter
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
    }
}