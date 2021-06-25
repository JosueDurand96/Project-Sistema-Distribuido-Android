package com.durand.data.entities.advance_vaccination

import java.io.Serializable

data class AdvanceVaccinationMasterResponseEntities(
        val cardResponse: Array<AdvanceVaccinationResponseEntities>? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdvanceVaccinationMasterResponseEntities

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