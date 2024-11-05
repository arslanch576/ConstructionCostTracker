package com.coderobust.constructioncosttracker.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderobust.constructioncosttracker.room.AppDatabase
import com.coderobust.constructioncosttracker.data.Project
import com.coderobust.constructioncosttracker.adapters.ProjectAdapter
import com.coderobust.constructioncosttracker.data.ProjectCostItem
import com.coderobust.constructioncosttracker.databinding.ActivityProjectDetailsBinding

class ProjectDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProjectDetailsBinding
    lateinit var project: Project
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", -1)
        project = AppDatabase.getDatabase(this).projectDao().getProjectById(id)

        binding.title.text = project.title
        binding.description.text = project.desc
        binding.date.text = "Start date: " + project.startDate
        binding.budget.text = "Budget: " + project.budget

        binding.floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, AddProjectCostActivity::class.java).putExtra("id", id))
        }

    }

    override fun onResume() {
        super.onResume()
        val adapter = ProjectAdapter(
            filter(AppDatabase.getDatabase(this).projectCostItemDao().getAllProjectCosts(project.id))
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun filter(items: List<ProjectCostItem>): List<Any> {
        val result = ArrayList<Any>()
        var previous: String? = null
        for (item in items) {
            if (previous == null || !item.date.equals(previous)) {
                result.add(item.date)
                previous = item.date
            }
            result.add(item)
        }

        return result
    }
}