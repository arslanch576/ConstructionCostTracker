package com.coderobust.constructioncosttracker.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.coderobust.constructioncosttracker.data.Project

@Dao
interface ProjectDao {
    @Insert
    fun insert(project: Project)
    @Update
    fun update(project: Project)

    @Delete
    fun delete(project: Project)

    @Query("Delete from project")
    fun deleteAll()

    @Query("Select * from Project")
    fun getAllProjects(): List<Project>

    @Query("Select * from Project")
    fun getAllProjectsLive(): LiveData<List<Project>>

    @Query("Select * from Project where id=:id")
    fun getProjectById(id: Int): Project


}