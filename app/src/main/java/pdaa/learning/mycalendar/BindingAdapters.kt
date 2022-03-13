package pdaa.learning.mycalendar

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pdaa.learning.mycalendar.mainScreen.RecordGridAdapter
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Record>?) {
    val adapter = recyclerView.adapter as RecordGridAdapter
    adapter.submitList(data)
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("recordStartDate")
fun bindStartDateToString(textView: TextView, date: Calendar) {
    //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    val textToShow = date.get(1).toString() + " - " + date.get(2) + " - " + date.get(3)
    textView.text = textToShow
}

@BindingAdapter(value = ["year", "month", "day"], requireAll = true)
fun bindIntDateToString(textView: TextView, year: Int, month:Int, day:Int){
    val dateInString = "$year - $month - $day"
    textView.text = dateInString
}

@BindingAdapter(value = ["hour", "minute"], requireAll = true)
fun bindIntHourToString(textView: TextView, hour:Int, minute:Int){
    val dateInString = "$hour:$minute"
    textView.text = dateInString
}

@BindingAdapter("bindIsTheEventAllDay")
fun bindIntDateToString(textView: TextView, eventAllDay:Boolean){

    if (eventAllDay){
        textView.text = "SI"
    }else{
        textView.text = "NO"
    }


}
