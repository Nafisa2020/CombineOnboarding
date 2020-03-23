package com.ittybittyapps.onboardingexercise.home

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ittybittyapps.onboardingexercise.R
import com.ittybittyapps.onboardingexercise.data.model.Item
import com.ittybittyapps.onboardingexercise.util.inflate

class ListItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Item> = emptyList()

    fun set(items: List<Item>) {
        if (items != this.items) {
            this.items = items
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is Item.Story -> R.layout.item_story
        is Item.Job -> R.layout.item_job
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = parent.inflate(viewType)

        return when (viewType) {
            R.layout.item_story -> StoryViewHolder(view)
            R.layout.item_job -> JobViewHolder(view)
            else -> throw IllegalArgumentException("Unknown viewType.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoryViewHolder -> holder.bind(items[position] as Item.Story)
            is JobViewHolder -> holder.bind(items[position] as Item.Job)
        }
    }

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleView: TextView = view.findViewById(R.id.story_title)
        private val authorView: TextView = view.findViewById(R.id.story_author)
        private val commentCountView: TextView = view.findViewById(R.id.story_comments_count)

        fun bind(story: Item.Story) {
            titleView.text = story.title
            authorView.text = story.by
            commentCountView.text = story.descendants.toString()
        }
    }

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleView: TextView = view.findViewById(R.id.job_title)
        private val authorView: TextView = view.findViewById(R.id.job_author)

        fun bind(job: Item.Job) {
            titleView.text = job.title
            authorView.text = job.by
        }
    }
}
