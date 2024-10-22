package com.coderobust.constructioncosttracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProjectCostItemDao {
    @Insert
    fun insert(project:ProjectCostItem)

    @Delete
    fun delete(project: ProjectCostItem)

    @Query("Select * from ProjectCostItem where projectId=:projectId")
    fun getAllProjectCosts(projectId:Int):List<ProjectCostItem>


}