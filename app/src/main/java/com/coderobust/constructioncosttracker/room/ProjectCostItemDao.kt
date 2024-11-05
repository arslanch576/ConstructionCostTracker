package com.coderobust.constructioncosttracker.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.coderobust.constructioncosttracker.data.ProjectCostItem

@Dao
interface ProjectCostItemDao {
    @Insert
    fun insert(project: ProjectCostItem)

    @Delete
    fun delete(project: ProjectCostItem)

    @Query("Select * from ProjectCostItem where projectId=:projectId order by date desc")
    fun getAllProjectCosts(projectId:Int):List<ProjectCostItem>


}