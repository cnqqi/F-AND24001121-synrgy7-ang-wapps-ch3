package com.synrgy.chapter3



import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class DetailAdapter(private val countryId: String, private val context: Context) :
    RecyclerView.Adapter<DetailAdapter.CountryViewHolder>() {

    private val filteredWords: List<String>

    init {
        val country = context.resources.getStringArray(R.array.countryss).toList()
        filteredWords = country
            .filter { it.startsWith(countryId, ignoreCase = true) }
            .take(2)
            .sorted()
    }

    class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.btnItem)
    }

    override fun getItemCount(): Int = filteredWords.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return CountryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = filteredWords[position]
        holder.button.text = item

        holder.button.setOnClickListener {
            val url: Uri = Uri.parse("${DetailFragment.SEARCH}${item}")
            val intent = Intent(Intent.ACTION_VIEW, url)
            context.startActivity(intent)
        }
    }
}
