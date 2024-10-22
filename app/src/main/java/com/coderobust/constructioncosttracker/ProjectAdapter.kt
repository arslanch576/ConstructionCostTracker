package com.coderobust.constructioncosttracker

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.coderobust.constructioncosttracker.databinding.ItemProjectBinding

class ProjectAdapter(val items:List<Project>):RecyclerView.Adapter<ProjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding=ItemProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project=items.get(position)
        holder.binding.title.text=project.title
        holder.binding.desc.text=project.desc
        holder.binding.budget.text="Budget: "+project.budget

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context,ProjectDetailsActivity::class.java).putExtra("id",project.id))
        }
    }
}