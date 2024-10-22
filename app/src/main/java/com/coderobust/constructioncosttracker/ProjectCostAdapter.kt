package com.coderobust.constructioncosttracker

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coderobust.constructioncosttracker.databinding.ItemProjectCostBinding

class ProjectCostAdapter(val items:List<ProjectCostItem>):RecyclerView.Adapter<ProjectCostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectCostViewHolder {
        val binding= ItemProjectCostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProjectCostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProjectCostViewHolder, position: Int) {
        val projectCost=items.get(position)
        holder.binding.title.text=projectCost.name
        holder.binding.date.text=projectCost.date
        holder.binding.cost.text="Cost: "+projectCost.cost

    }
}