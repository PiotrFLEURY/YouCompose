package fr.piotrfleury.youcompose.data.models

import java.util.*

/*
{
  "kind": "youtube#video",
  "id": string,
  "snippet": {
    "publishedAt": datetime,
    "channelId": string,
    "title": string,
    "description": string,
    "thumbnails": {
      (key): {
        "url": string,
        "width": unsigned integer,
        "height": unsigned integer
      }
    },
    "channelTitle": string
  }
}
 */
data class Video(
    var kind: String? = null,
    var id: String? = null,
    var snippet: Snippet? = null,
)

data class Snippet(
    var publishedAt: Date? = null,
    var channelId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnails: Thumbnails? = null,
    var channelTitle: String? = null,
)

data class Thumbnails(
    var default: Thumbnail? = null,
    var medium: Thumbnail? = null,
    var high: Thumbnail? = null,
    var standard: Thumbnail? = null,
    var maxres: Thumbnail? = null,
)

data class Thumbnail(
    var url: String? = null,
    var width: Int? = null,
    var height: Int? = null,
)