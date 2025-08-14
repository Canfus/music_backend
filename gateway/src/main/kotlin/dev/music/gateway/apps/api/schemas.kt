package dev.music.gateway.apps.api

data class ApiResponse<Data>(
  val data: Data? = null,
  val error: Collection<Map<String, String>>? = null
)

data class ListResponse<Data>(
  val items: Collection<Data> = listOf(),
)