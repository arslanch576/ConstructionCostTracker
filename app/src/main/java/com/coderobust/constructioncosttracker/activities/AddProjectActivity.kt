package com.coderobust.constructioncosttracker.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coderobust.constructioncosttracker.room.AppDatabase
import com.coderobust.constructioncosttracker.data.Project
import com.coderobust.constructioncosttracker.databinding.ActivityAddProjectBinding

class AddProjectActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProjectBinding;
    lateinit var project: Project
    var isForEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("id")) {
            isForEdit = true
            project = AppDatabase.getDatabase(this).projectDao()
                .getProjectById(intent.getIntExtra("id", -1))
            binding.title.editText?.setText(project.title)
            binding.desc.editText?.setText(project.desc)
            binding.date.editText?.setText(project.startDate)
            binding.cost.editText?.setText(project.budget.toString())
        }

        binding.save.setOnClickListener {
            if (!isForEdit)
                project = Project()
            project.title = binding.title.editText?.text.toString()
            project.desc = binding.desc.editText?.text.toString()
            project.startDate = binding.date.editText?.text.toString()
            project.budget = binding.cost.editText?.text.toString().toInt()


            if (!isForEdit) {
                AppDatabase.getDatabase(this).projectDao().insert(project)
                Toast.makeText(this, "Project Added", Toast.LENGTH_SHORT).show()
            }else{
                AppDatabase.getDatabase(this).projectDao().update(project)
                Toast.makeText(this, "Project updated", Toast.LENGTH_SHORT).show()
            }
            finish()


        }


    }
}