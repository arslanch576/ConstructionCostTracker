package com.coderobust.constructioncosttracker.adapters

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.coderobust.constructioncosttracker.activities.AddProjectActivity
import com.coderobust.constructioncosttracker.activities.ProjectDetailsActivity
import com.coderobust.constructioncosttracker.data.Project
import com.coderobust.constructioncosttracker.data.ProjectCostItem
import com.coderobust.constructioncosttracker.databinding.ItemLabelBinding
import com.coderobust.constructioncosttracker.databinding.ItemProjectBinding
import com.coderobust.constructioncosttracker.databinding.ItemProjectCostBinding
import com.coderobust.constructioncosttracker.room.AppDatabase
import com.coderobust.constructioncosttracker.viewholders.LabelItemViewHolder
import com.coderobust.constructioncosttracker.viewholders.ProjectCostViewHolder
import com.coderobust.constructioncosttracker.viewholders.ProjectViewHolder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.truncate

class ProjectAdapter(val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val binding =
                ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProjectViewHolder(binding)
        } else if (viewType == 1) {
            val binding =
                ItemProjectCostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProjectCostViewHolder(binding)
        } else {
            val binding =
                ItemLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LabelItemViewHolder(binding)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        if (items.get(position) is Project) return 0
        if (items.get(position) is ProjectCostItem) return 1
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ProjectViewHolder) {
            val project = items.get(position) as Project
            holder.binding.title.text = project.title
            holder.binding.desc.text = project.desc
            holder.binding.budget.text = "Budget: " + project.budget

            holder.itemView.setOnClickListener {
                holder.itemView.context.startActivity(
                    Intent(
                        holder.itemView.context,
                        ProjectDetailsActivity::class.java
                    ).putExtra("id", project.id)
                )
            }

            holder.itemView.setOnLongClickListener {
                val dialogBuilder=MaterialAlertDialogBuilder(holder.itemView.context)
                dialogBuilder.setItems(arrayOf("Edit","Delete"), DialogInterface.OnClickListener { dialog, which ->
                    if (which==0){
                        holder.itemView.context.startActivity(Intent(holder.itemView.context,AddProjectActivity::class.java).putExtra("id",project.id))
                    }else{
                        val warningDialog=MaterialAlertDialogBuilder(holder.itemView.context)
                        warningDialog.setTitle("Sure to delete?")
                        warningDialog.setMessage("The project will be deleted permanently")
                        warningDialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                            AppDatabase.getDatabase(holder.itemView.context).projectDao().delete(project)
                            Toast.makeText(holder.itemView.context,"Deleted",Toast.LENGTH_SHORT).show()
                        })

                        warningDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                            dialog.dismiss()
                        })

                        warningDialog.show()

                    }
                })

                dialogBuilder.show()

                true
            }
        }
        if (holder is ProjectCostViewHolder) {
            val projectCost = items.get(position) as ProjectCostItem
            holder.binding.title.text = projectCost.name
            holder.binding.cost.text = "Cost: " + projectCost.cost
        }
        if (holder is LabelItemViewHolder) {
            val item = items.get(position) as String
            holder.binding.textView2.text = item
        }
    }
}