package com.ittybittyapps.onboardingexercise.data.networking.adapter

import com.ittybittyapps.onboardingexercise.data.model.Item
import com.ittybittyapps.onboardingexercise.data.model.ItemJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

// TODO: Replace with Moshi's PolymorphicTypeAdapter.
class ItemAdapter {

    @FromJson
    fun fromJson(itemJson: ItemJson): Item {
        val type = Item.Type.valueOf(itemJson.type.toUpperCase())
        return when (type) {
            Item.Type.STORY -> {
                Item.Story(
                    by = itemJson.by,
                    descendants = itemJson.descendants,
                    id = itemJson.id,
                    kids = itemJson.kids,
                    score = itemJson.score,
                    time = itemJson.time,
                    title = itemJson.title,
                    type = type,
                    url = itemJson.url
                )
            }
            Item.Type.JOB -> {
                Item.Job(
                    by = itemJson.by,
                    id = itemJson.id,
                    score = itemJson.score,
                    text = itemJson.text,
                    time = itemJson.time,
                    title = itemJson.title,
                    type = type,
                    url = itemJson.url
                )
            }
        }
    }

    @ToJson
    fun toJson(item: Item): ItemJson {
        return when (item) {
            is Item.Story -> {
                val story: Item.Story = item
                ItemJson(
                    id = story.id,
                    deleted = false,
                    type = story.type.value,
                    by = story.by,
                    time = story.time,
                    text = "",
                    dead = false,
                    kids = story.kids,
                    url = story.url,
                    score = story.score,
                    title = story.title,
                    descendants = story.descendants
                )
            }
            is Item.Job -> {
                val job: Item.Job = item
                ItemJson(
                    id = job.id,
                    deleted = false,
                    type = job.type.value,
                    by = job.by,
                    time = job.time,
                    text = job.text,
                    dead = false,
                    kids = listOf(),
                    url = job.url,
                    score = job.score,
                    title = job.title,
                    descendants = 0
                )
            }
        }
    }
}
