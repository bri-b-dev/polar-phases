package dev.bri.polarbear.util

data class HrmaxZone(
    val name: String,
    val colorArgb: Int,
    val bpmMin: Int,
    val bpmMax: Int,
)

fun defaultZones(maxHr: Int = 179): List<HrmaxZone> {
    fun bpm(pct: Double) = (maxHr * pct).toInt()
    return listOf(
        HrmaxZone("Recovery",  0xFF9E9E9E.toInt(), bpm(0.50), bpm(0.60)),
        HrmaxZone("Endurance", 0xFF42A5F5.toInt(), bpm(0.60) + 1, bpm(0.70)),
        HrmaxZone("Tempo",     0xFF66BB6A.toInt(), bpm(0.70) + 1, bpm(0.80)),
        HrmaxZone("Threshold", 0xFFFFEE58.toInt(), bpm(0.80) + 1, bpm(0.90)),
        HrmaxZone("Peak",      0xFFEF5350.toInt(), bpm(0.90) + 1, maxHr),
    )
}
