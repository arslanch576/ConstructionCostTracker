package com.coderobust.constructioncosttracker.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderobust.constructioncosttracker.adapters.ProjectAdapter
import com.coderobust.constructioncosttracker.databinding.ActivityMainBinding
import com.coderobust.constructioncosttracker.room.AppDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddProjectActivity::class.java))
        }



    }

    override fun onResume() {
        super.onResume()
        val adapter= ProjectAdapter(AppDatabase.getDatabase(this).projectDao().getAllProjects())
        binding.recyclerview.adapter=adapter
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
    }
}