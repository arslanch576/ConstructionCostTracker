package com.coderobust.constructioncosttracker.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coderobust.constructioncosttracker.room.AppDatabase
import com.coderobust.constructioncosttracker.data.Project
import com.coderobust.constructioncosttracker.databinding.ActivityAddProjectBinding

class AddProjectActivity : AppCompatActivity() {

    lateinit var binding:ActivityAddProjectBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {

            val project= Project(
                title = binding.title.editText?.text.toString(),
                desc = binding.desc.editText?.text.toString(),
                startDate = binding.date.editText?.text.toString(),
                budget = binding.cost.editText?.text.toString().toInt()
            )

            AppDatabase.getDatabase(this).projectDao().insert(project)
            Toast.makeText(this,"Project Added",Toast.LENGTH_SHORT).show()
            finish()

        }




    }
}