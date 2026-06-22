package dev.bri.polarbear.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dev.bri.polarbear.data.model.HrSample
import dev.bri.polarbear.data.model.SessionPhaseRecord
import dev.bri.polarbear.data.model.WorkoutSession
import dev.bri.polarbear.data.model.WorkoutSessionWithDetails
import dev.bri.polarbear.data.model.ZoneSnapshot
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSessionDao {
    @Query("SELECT * FROM workout_sessions ORDER BY startedAt DESC")
    fun observeAll(): Flow<List<WorkoutSession>>

    @Transaction
    @Query("SELECT * FROM workout_sessions WHERE id = :id")
    fun observeWithDetails(id: Long): Flow<List<WorkoutSessionWithDetails>>

    @Insert
    suspend fun insertSession(session: WorkoutSession): Long

    @Insert
    suspend fun insertZoneSnapshots(snapshots: List<ZoneSnapshot>)

    @Insert
    suspend fun insertHrSamples(samples: List<HrSample>)

    @Insert
    suspend fun insertPhaseRecords(records: List<SessionPhaseRecord>)

    @Query("DELETE FROM workout_sessions WHERE id = :id")
    suspend fun deleteById(id: Long)
}
