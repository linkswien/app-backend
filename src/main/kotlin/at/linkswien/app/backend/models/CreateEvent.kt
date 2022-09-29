package at.linkswien.app.backend.models

data class CreateEvent(
    val title: String,
    val headerImg: String,
    val description: String,
    val typeData: String
)