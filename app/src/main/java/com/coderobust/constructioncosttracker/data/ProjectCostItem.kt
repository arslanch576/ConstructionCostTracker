package com.coderobust.constructioncosttracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProjectCostItem(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var projectId:Int,
    var date:String,
    var name:String,
    var cost:Int
) {
}