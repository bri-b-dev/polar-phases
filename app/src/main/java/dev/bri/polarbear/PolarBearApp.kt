package dev.bri.polarbear

import android.app.Application
import dev.bri.polarbear.data.db.AppDatabase
import dev.bri.polarbear.repository.SessionRepository
import dev.bri.polarbear.repository.TemplateRepository
import dev.bri.polarbear.repository.ZoneRepository

class PolarBearApp : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val zoneRepository by lazy { ZoneRepository(database.hrZoneDao()) }
    val templateRepository by lazy { TemplateRepository(database.workoutTemplateDao()) }
    val sessionRepository by lazy { SessionRepository(database.workoutSessionDao()) }
}
