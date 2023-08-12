package mx.ma3r.tokyogoul.model

data class Chapter(
    val id:Int,
    val name: Int,
    val episodes:List<Movie>,
    val poster:String?=null,
    val trailer:List<Trailer>?=null,
)

data class Trailer(
    val url:String
)
