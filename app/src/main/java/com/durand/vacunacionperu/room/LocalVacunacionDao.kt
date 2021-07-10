package com.durand.vacunacionperu.room

import androidx.room.*

@Dao
interface LocalVacunacionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocalVacunacion(paso1_1: LocalVacunacion)

    @Update
    fun actualizarLocalVacunacion(paso1_1: LocalVacunacion)

    @Delete()
    fun eliminarLocalVacunacion(paso1_1: LocalVacunacion)

    @Query("SELECT * FROM local")
    fun getLocalVacunacion():List<LocalVacunacion>

    @Query("SELECT * FROM local WHERE id = :id ")
    fun getLocalVacunacion_Id(id: Int?): LocalVacunacion

    @Query("DELETE FROM local WHERE id = :id ")
    fun eliminarLocalVacunacion_1Id(id: Int?)
}