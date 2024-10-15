package com.coderobust.constructioncosttracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.coderobust.constructioncosttracker.databinding.ActivityAddProjectBinding

class AddProjectActivity : AppCompatActivity() {

    lateinit var binding:ActivityAddProjectBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {

            val project=Project(
                title = binding.title.editText?.text.toString(),
                desc = binding.desc.editText?.text.toString(),
                startDate = binding.date.editText?.text.toString(),
                budget = binding.title.editText?.text.toString().toInt()
            )

            AppDatabase.getDatabase(this).projectDao().insert(project)
            

        }




    }
}