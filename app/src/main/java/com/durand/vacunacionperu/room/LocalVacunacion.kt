package com.durand.vacunacionperu.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local")
data class LocalVacunacion(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val description: String
) {
    constructor(
        description: String
    ) : this(0, description)
}