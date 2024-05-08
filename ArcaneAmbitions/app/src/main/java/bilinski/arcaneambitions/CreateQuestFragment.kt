package bilinski.arcaneambitions

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.VolleyError
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateQuestFragment : Fragment() {
    private lateinit var questStorage: QuestStorage
    private lateinit var timeTextView: TextView
    private lateinit var questNotificationTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_quest, container, false)
        val titleInput = view.findViewById<EditText>(R.id.titleInput)
        val descriptionInput = view.findViewById<EditText>(R.id.descriptionInput)
        timeTextView = view.findViewById<TextView>(R.id.timeTextView)
        questNotificationTextView = view.findViewById<TextView>(R.id.questNotificationTextView)

        val createTaskButton = view.findViewById<Button>(R.id.createTaskButton)
        questStorage = QuestStorage(requireContext())

        timeTextView.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(context, { _, hourOfDay, minute ->
                val timeString = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
                timeTextView.text = timeString
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        createTaskButton.setOnClickListener {
            val title = titleInput.text.toString()
            val description = descriptionInput.text.toString()
            val timeString = timeTextView.text.toString()
            questNotificationTextView.visibility = View.VISIBLE
            (activity as? MainActivity)?.setNavigationEnabled(false)

            val questFetcher = QuestFetcher(requireContext(), title, description, timeString)

            questFetcher.fetchQuest(object : QuestFetcher.OnQuestReceivedListener {
                override fun onQuestReceived(quest: Quest) {
                    if (isAdded) {
                        questStorage.addQuest(quest)

                        Toast.makeText(requireContext(), "Quest added successfully!", Toast.LENGTH_SHORT).show()
                        Log.e("QuestFetcher", "It worked")

                        (activity as? MainActivity)?.setNavigationEnabled(true)
                    }
                }

                override fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(requireContext(), "Error fetching quest: ${error.message}", Toast.LENGTH_LONG).show()
                    Log.e("QuestFetcher", "Error fetching quest: ${error.message}")
                    (activity as? MainActivity)?.setNavigationEnabled(true)
                }
            })
        }
        return view
}
}


