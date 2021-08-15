package fr.piotrfleury.youcompose.data.models

/*
{
  "kind": "youtube#videoListResponse",
  "etag": etag,
  "nextPageToken": string,
  "prevPageToken": string,
  "pageInfo": {
    "totalResults": integer,
    "resultsPerPage": integer
  },
  "items": [
    video Resource
  ]
}
 */
data class YoutubeApiResponse(
    var kind: String?,
    var etag: String?,
    var nextPageToken: String?,
    var prevPageToken: String?,
    var pageInfo: PageInfo?,
    var items: List<Video>?
)

data class PageInfo(
    var totalResults: Int?,
    var resultPerPage: Int?,
)