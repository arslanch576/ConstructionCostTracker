package com.coderobust.constructioncosttracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Project(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title:String,
    var desc:String,
    var startDate:String,
    var isCompleted:Boolean=false,
    var budget:Int,
    ) {
}