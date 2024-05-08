package bilinski.arcaneambitions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ThingListAdapter(private val thingList : MutableList<String>) :
    RecyclerView.Adapter<ThingListAdapter.ThingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ThingViewHolder(layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) {
        val thing = thingList[position]
        holder.itemView.tag = position
        holder.bind(thing)
    }

    override fun getItemCount(): Int {
        return thingList.size
    }

    inner class ThingViewHolder(inflater: LayoutInflater, parent : ViewGroup?):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.thing_list_item, parent, false)),
        View.OnClickListener{
        private val thingItemView: TextView

        init{
            thingItemView = itemView.findViewById(R.id.thing_textview)
            itemView.setOnClickListener(this)
        }

        fun bind(thing: String){
            thingItemView.text = thing
        }

        override fun onClick(v: View?) {
            val selectedThing = itemView.tag as Int
            thingList[selectedThing] = "Clicked! Thing $selectedThing"
            notifyItemChanged(selectedThing)

        }
    }
}