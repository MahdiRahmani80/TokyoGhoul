package mx.ma3r.tokyogoul.model

data class Movie(
    val id:Int,
    val name:String,
    val url:String,
    val chatList:ArrayList<Chat> = ArrayList<Chat>()
)
