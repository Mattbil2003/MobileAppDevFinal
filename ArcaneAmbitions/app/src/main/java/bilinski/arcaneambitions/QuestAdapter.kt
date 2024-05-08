package bilinski.arcaneambitions

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

class QuestAdapter(private var questList: List<Quest>,
                   private val questStorage: QuestStorage,
                    private val navController: NavController
) : RecyclerView.Adapter<QuestAdapter.QuestViewHolder>() {

    class QuestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dueTimeTextView: TextView = view.findViewById(R.id.questTime)
        val titleTextView: TextView = view.findViewById(R.id.textViewQuestTitle)
        val descriptionTextView: TextView = view.findViewById(R.id.textViewQuestDescription)
        val completeButton: Button = view.findViewById(R.id.completeButton)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quest, parent, false)
        return QuestViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
        val quest = questList[position]
        holder.titleTextView.text = quest.title
        holder.descriptionTextView.text = quest.description
        holder.dueTimeTextView.text = "Due: ${quest.time}"
        holder.completeButton.setOnClickListener{
            questStorage.deleteQuest(quest.title)
            Toast.makeText(holder.itemView.context, "Quest Completed! :)", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.navigation_HomeFragment)
            questStorage.incrementStats(quest.gold_reward,quest.exp_reward)

        }
        holder.deleteButton.setOnClickListener{
            questStorage.deleteQuest(quest.title)
            Toast.makeText(holder.itemView.context, "Quest Failed :(", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.navigation_HomeFragment)


        }
    }

    override fun getItemCount() = questList.size
    }
