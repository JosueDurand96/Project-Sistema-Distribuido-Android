package com.durand.data.entities.local_vaccination

import com.durand.data.entities.vaccination.VaccinationResponseEntities
import java.io.Serializable


data class LocalVaccinationMasterResponseEntities (
        val cardResponse: Array<LocalVaccinationResponseEntities>? = null
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocalVaccinationMasterResponseEntities

        if (cardResponse != null) {
            if (other.cardResponse == null) return false
            if (!cardResponse.contentEquals(other.cardResponse)) return false
        } else if (other.cardResponse != null) return false

        return true
    }

    override fun hashCode(): Int {
        return cardResponse?.contentHashCode() ?: 0
    }
}