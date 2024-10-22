package com.coderobust.constructioncosttracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProjectDao {
    @Insert
    fun insert(project: Project)

    @Delete
    fun delete(project: Project)

    @Query("Select * from Project")
    fun getAllProjects(): List<Project>

    @Query("Select * from Project where id=:id")
    fun getProjectById(id: Int): Project


}