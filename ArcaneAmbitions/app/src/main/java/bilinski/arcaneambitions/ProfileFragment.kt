package bilinski.arcaneambitions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class ProfileFragment : Fragment() {
    private lateinit var questStorage: QuestStorage // Declare the variable as a class-level property

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val questStorage = QuestStorage(requireContext())

        val levelCountTextView: TextView = view.findViewById(R.id.level)
        val expProgressBar: ProgressBar = view.findViewById(R.id.xpProgressBar)
        val goldCountTextView: TextView = view.findViewById(R.id.goldCount)
        val resetQuestsButton: Button = view.findViewById(R.id.resetQuestsButton)

        resetQuestsButton.setOnClickListener {
            questStorage.deleteAllQuests()
        }


        val gameStats = questStorage.loadGameStats()
        val currentLevel = gameStats.experience / 100

        levelCountTextView.text = "Level: $currentLevel"
        goldCountTextView.text = "Gold: ${gameStats.gold}"

        val expWithinLevel = gameStats.experience % 100
        expProgressBar.max = 100
        expProgressBar.progress = expWithinLevel

        return view
    }
}
