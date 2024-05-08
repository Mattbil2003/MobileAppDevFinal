package bilinski.arcaneambitions

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class QuestStorage(private val context: Context) {
    private val fileName = "quests.json"
    private val statsFileName = "game_stats.json"
    private val gson = Gson()

    data class GameStats(
        var gold: Int = 0,
        var experience: Int = 0
    )


    fun loadQuests(): List<Quest> {
        return try {
            context.openFileInput(fileName).use { inputStream ->
                val jsonString = inputStream.bufferedReader().readText()
                val type = object : TypeToken<List<Quest>>() {}.type
                gson.fromJson(jsonString, type) ?: emptyList()
            }
        } catch (e: IOException) {
            emptyList()
        }
    }

    private fun saveQuests(quests: List<Quest>) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
            val jsonString = gson.toJson(quests)
            outputStream.write(jsonString.toByteArray())
        }
    }

    fun addQuest(newQuest: Quest) {
        val quests = loadQuests().toMutableList()
        quests.add(newQuest)
        saveQuests(quests)
    }


    fun deleteQuest(title: String) {
        val quests = loadQuests().toMutableList()
        quests.removeIf { it.title == title }
        saveQuests(quests)
    }


    fun deleteAllQuests() {
        saveQuests(emptyList())
    }


    fun loadGameStats(): GameStats {
        return try {
            context.openFileInput(statsFileName).use { inputStream ->
                val jsonString = inputStream.bufferedReader().readText()
                gson.fromJson(jsonString, GameStats::class.java) ?: GameStats()
            }
        } catch (e: IOException) {
            GameStats()
        }
    }

    fun saveGameStats(stats: GameStats) {
        context.openFileOutput(statsFileName, Context.MODE_PRIVATE).use { outputStream ->
            val jsonString = gson.toJson(stats)
            outputStream.write(jsonString.toByteArray())
        }
    }

    fun incrementStats(goldIncrease: Int, expIncrease: Int) {
        val stats = loadGameStats()
        stats.gold += goldIncrease
        stats.experience += expIncrease
        saveGameStats(stats)
    }
}


