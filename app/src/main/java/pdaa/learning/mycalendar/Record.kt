package pdaa.learning.mycalendar

data class Record(
    val id: Int,
    val kind: Kind?,
    val title: String?,
    val startDate: String?,
    val endDate:String?,
    val fullDay:Boolean?,
    val description: String?
)

enum class Kind{
    CITA,
    ANIVERSARIO,
    CUENTA_ATRAS
}