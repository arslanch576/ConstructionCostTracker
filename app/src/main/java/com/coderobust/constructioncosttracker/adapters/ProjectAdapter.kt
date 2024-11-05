package com.coderobust.constructioncosttracker.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coderobust.constructioncosttracker.activities.ProjectDetailsActivity
import com.coderobust.constructioncosttracker.data.Project
import com.coderobust.constructioncosttracker.data.ProjectCostItem
import com.coderobust.constructioncosttracker.databinding.ItemLabelBinding
import com.coderobust.constructioncosttracker.databinding.ItemProjectBinding
import com.coderobust.constructioncosttracker.databinding.ItemProjectCostBinding
import com.coderobust.constructioncosttracker.viewholders.LabelItemViewHolder
import com.coderobust.constructioncosttracker.viewholders.ProjectCostViewHolder
import com.coderobust.constructioncosttracker.viewholders.ProjectViewHolder

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