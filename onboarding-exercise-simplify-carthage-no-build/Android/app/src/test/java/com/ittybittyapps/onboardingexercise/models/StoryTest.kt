package com.ittybittyapps.onboardingexercise.models

import com.ittybittyapps.onboardingexercise.data.model.Item
import com.ittybittyapps.onboardingexercise.data.networking.adapter.ItemAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test

class StoryTest {

    private val moshi = Moshi.Builder()
        .add(ItemAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdapter = moshi.adapter(Item::class.java)
    private val storyJson = """
      {
          "by": "maxmcd",
          "descendants": 24,
          "id": 18434588,
          "kids": [
            18435065,
            18437831,
            18434793,
            18435260,
            18438874,
            18436484,
            18437169,
            18434809
          ],
          "score": 125,
          "time": 1542048024,
          "title": "Show HN: WebRTTY – Share a terminal session over WebRTC",
          "type": "story",
          "url": "https://github.com/maxmcd/webrtty"
    }
    """.trimIndent()

    @Test
    fun testStoryFromJson() {
        val item = jsonAdapter.fromJson(storyJson)
        assert(item is Item.Story)
    }
}
