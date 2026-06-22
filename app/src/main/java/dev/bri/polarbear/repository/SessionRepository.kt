package dev.bri.polarbear.repository

import dev.bri.polarbear.data.db.WorkoutSessionDao
import dev.bri.polarbear.data.model.HrSample
import dev.bri.polarbear.data.model.SessionPhaseRecord
import dev.bri.polarbear.data.model.WorkoutSession
import dev.bri.polarbear.data.model.WorkoutSessionWithDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import dev.bri.polarbear.data.model.ZoneSnapshot

class SessionRepository(private val dao: WorkoutSessionDao) {
    fun observeAll(): Flow<List<WorkoutSession>> = dao.observeAll()

    fun observeWithDetails(id: Long): Flow<WorkoutSessionWithDetails?> =
        dao.observeWithDetails(id).map { it.firstOrNull() }

    suspend fun save(
        session: WorkoutSession,
        zoneSnapshots: List<ZoneSnapshot>,
        hrSamples: List<HrSample>,
        phaseRecords: List<SessionPhaseRecord>,
    ): Long {
        val sessionId = dao.insertSession(session)
        if (zoneSnapshots.isNotEmpty()) {
            dao.insertZoneSnapshots(zoneSnapshots.map { it.copy(sessionId = sessionId) })
        }
        if (hrSamples.isNotEmpty()) {
            dao.insertHrSamples(hrSamples.map { it.copy(sessionId = sessionId) })
        }
        if (phaseRecords.isNotEmpty()) {
            dao.insertPhaseRecords(phaseRecords.map { it.copy(sessionId = sessionId) })
        }
        return sessionId
    }

    suspend fun delete(id: Long) = dao.deleteById(id)
}
