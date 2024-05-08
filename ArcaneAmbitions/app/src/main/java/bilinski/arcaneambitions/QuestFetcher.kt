package bilinski.arcaneambitions

import android.content.Context
import android.net.Uri
import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Date

const val TAG = "QuestFetcher"
const val BASE_URL = "https://questapiservermb.azurewebsites.net/data?"

class QuestFetcher(val context: Context, val title: String, val description: String, val time: String){
    interface OnQuestReceivedListener {
        fun onQuestReceived(quest: Quest)
        fun onErrorResponse(error: VolleyError)
    }
    private var requestQueue = Volley.newRequestQueue(context)

    fun fetchQuest(listener: OnQuestReceivedListener) {
        Log.d("fetchQuest","In fetchQuest")
        val url = Uri.parse(BASE_URL).buildUpon()
            .appendQueryParameter("title", title)
            .appendQueryParameter("description", description)
            .appendQueryParameter("time", time.toString())
            .build().toString()

        Log.d(TAG,url)

        val jsObjRequest = JsonObjectRequest(
            Request.Method.GET,url,null, {response ->
//                Thread.sleep(4000) //demo only
                listener.onQuestReceived(parseJsonQuest(response))
            },
            {error ->
                listener.onErrorResponse(error)
            }
        )
        requestQueue.add(jsObjRequest)
    }

    fun parseJsonQuest(jsonObject: JSONObject): Quest {
            var title = jsonObject.getString("fantasyTitle")
            var description = jsonObject.getString(("fantasyDescription"))
            var gold = jsonObject.getInt("goldReward")
            var exp = jsonObject.getInt("expReward")
            return Quest(title,description,time,gold,exp)
}
}
