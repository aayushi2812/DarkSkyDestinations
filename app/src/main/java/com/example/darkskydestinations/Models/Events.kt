package com.example.darkskydestinations.Models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Event(val date: String,
                  val title: String,
                  val description: String)

data class EventsContainer(
    @SerializedName("astronomyEvents")
    val events: List<Event>
)

class EventsService{
    val json =
        """
            {
              "astronomyEvents": [
                {
                  "date": "January 1",
                  "title": "Mercury at Greatest Eastern Elongation",
                  "description": "The planet Mercury reaches greatest eastern elongation of 19.4 degrees from the Sun. Best time to view Mercury in the evening sky after sunset."
                },
                {
                  "date": "January 3-4",
                  "title": "Quadrantids Meteor Shower",
                  "description": "The Quadrantids is an above-average meteor shower, peaking with up to 40 meteors per hour. Best viewed after midnight in a dark location."
                },
                {
                  "date": "January 25",
                  "title": "Full Moon",
                  "description": "This phase occurs at 17:54 UTC."
                },
                {
                  "date": "February 9",
                  "title": "Mercury at Greatest Western Elongation",
                  "description": "Mercury reaches greatest western elongation of 25.3 degrees from the Sun. Best time to view Mercury in the morning sky just before sunrise."
                },
                {
                  "date": "February 24",
                  "title": "Full Moon",
                  "description": "This phase occurs at 00:30 UTC."
                },
                {
                  "date": "March 10",
                  "title": "Saturn at Solar Conjunction",
                  "description": "Saturn will be directly behind the Sun and will not be visible."
                },
                {
                  "date": "March 25",
                  "title": "Full Moon",
                  "description": "This phase occurs at 10:00 UTC."
                },
                {
                  "date": "April 8",
                  "title": "Total Solar Eclipse",
                  "description": "A total solar eclipse, visible in parts of the Pacific, northern Mexico, and the United States."
                },
                {
                  "date": "April 23-24",
                  "title": "Lyrids Meteor Shower",
                  "description": "The Lyrids produce about 20 meteors per hour at their peak. Best viewed after midnight in a dark location."
                },
                {
                  "date": "April 23",
                  "title": "Full Moon",
                  "description": "This phase occurs at 19:24 UTC."
                },
                {
                  "date": "May 6-7",
                  "title": "Eta Aquarids Meteor Shower",
                  "description": "The Eta Aquarids produce about 60 meteors per hour at their peak. Best viewed after midnight."
                },
                {
                  "date": "May 23",
                  "title": "Full Moon",
                  "description": "This phase occurs at 17:53 UTC."
                },
                {
                  "date": "June 3",
                  "title": "Venus at Greatest Eastern Elongation",
                  "description": "Venus reaches greatest eastern elongation of 45.4 degrees from the Sun. Best time to view Venus in the evening sky after sunset."
                },
                {
                  "date": "June 21",
                  "title": "June Solstice",
                  "description": "The solstice occurs at 14:51 UTC, marking the first day of summer in the Northern Hemisphere."
                },
                {
                  "date": "June 22",
                  "title": "Full Moon",
                  "description": "This phase occurs at 03:08 UTC."
                },
                {
                  "date": "July 6",
                  "title": "Earth at Aphelion",
                  "description": "Earth will be at its farthest point from the Sun at a distance of 152,093,250 km."
                },
                {
                  "date": "July 21-22",
                  "title": "Delta Aquarids Meteor Shower",
                  "description": "The Delta Aquarids produce about 20 meteors per hour at their peak. Best viewed after midnight in a dark location."
                },
                {
                  "date": "July 22",
                  "title": "Full Moon",
                  "description": "This phase occurs at 19:17 UTC."
                },
                {
                  "date": "August 12-13",
                  "title": "Perseids Meteor Shower",
                  "description": "The Perseids produce up to 60 meteors per hour at their peak. Best viewed after midnight."
                },
                {
                  "date": "August 21",
                  "title": "Full Moon",
                  "description": "This phase occurs at 08:02 UTC."
                },
                {
                  "date": "September 22",
                  "title": "September Equinox",
                  "description": "The equinox occurs at 06:19 UTC, marking the first day of autumn in the Northern Hemisphere."
                },
                {
                  "date": "September 20-21",
                  "title": "Draconids Meteor Shower",
                  "description": "The Draconids are a minor meteor shower producing about 10 meteors per hour at their peak. Best viewed early evening."
                },
                {
                  "date": "September 22",
                  "title": "Full Moon",
                  "description": "This phase occurs at 23:44 UTC."
                },
                {
                  "date": "October 7-8",
                  "title": "Draconids Meteor Shower",
                  "description": "Another viewing of the Draconids meteor shower."
                },
                {
                  "date": "October 21-22",
                  "title": "Orionids Meteor Shower",
                  "description": "The Orionids produce about 20 meteors per hour at their peak."
                },
                {
                  "date": "October 20",
                  "title": "Full Moon",
                  "description": "This phase occurs at 14:33 UTC."
                }
              ]
            }

        """.trimIndent()
    val gson = Gson()

    val parsedData = gson.fromJson(json, EventsContainer::class.java);
    var list = ArrayList<Event>()

    fun initList(): ArrayList<Event> {
        for (astronomyEvent in parsedData.events) {
            list.add(
                Event(
                    date = astronomyEvent.date,
                    title = astronomyEvent.title,
                    description = astronomyEvent.description
                )
            )
        }
        return list;
    }
}