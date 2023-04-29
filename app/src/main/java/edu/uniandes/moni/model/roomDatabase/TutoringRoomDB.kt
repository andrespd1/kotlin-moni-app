package edu.uniandes.moni.model.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity()
data class TutoringRoomDB(
    @PrimaryKey
    val id: UUID= UUID.randomUUID(),
    @ColumnInfo(name ="description" )
    val description: String,
    @ColumnInfo(name ="inUniversity" )
    val inUniversity: Boolean,
    @ColumnInfo(name ="price" )
    val price: String,
    @ColumnInfo(name ="title" )
    val title: String,
    @ColumnInfo(name ="topic" )
    val topic: String,
    @ColumnInfo(name ="tutorEmail" )
    val tutorEmail: String?
    )
