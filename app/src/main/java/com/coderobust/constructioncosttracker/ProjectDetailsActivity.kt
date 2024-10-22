package com.coderobust.constructioncosttracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderobust.constructioncosttracker.databinding.ActivityProjectDetailsBinding

class ProjectDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityProjectDetailsBinding
    lateinit var project:Project
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id=intent.getIntExtra("id",-1)
        project=AppDatabase.getDatabase(this).projectDao().getProjectById(id)

        binding.title.text=project.title
        binding.description.text=project.desc
        binding.date.text="Start date: "+project.startDate
        binding.budget.text="Budget: "+project.budget

        binding.floatingActionButton2.setOnClickListener {
            startActivity(Intent(this,AddProjectCostActivity::class.java).putExtra("id",id))
        }


    }

    override fun onResume() {
        super.onResume()
        val adapter=ProjectCostAdapter(AppDatabase.getDatabase(this).projectCostItemDao().getAllProjectCosts(project.id))
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
    }
}