package com.coderobust.constructioncosttracker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coderobust.constructioncosttracker.databinding.ActivityAddProjectBinding
import com.coderobust.constructioncosttracker.databinding.ActivityAddProjectCostBinding

class AddProjectCostActivity : AppCompatActivity() {

    lateinit var binding:ActivityAddProjectCostBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddProjectCostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id=intent.getIntExtra("id",-1)

        binding.save.setOnClickListener {

            val project=ProjectCostItem(
                projectId = id,
                name = binding.title.editText?.text.toString(),
                date = binding.date.editText?.text.toString(),
                cost = binding.cost.editText?.text.toString().toInt()
            )

            AppDatabase.getDatabase(this).projectCostItemDao().insert(project)
            Toast.makeText(this,"Project cost Added",Toast.LENGTH_SHORT).show()
            finish()

        }




    }
}