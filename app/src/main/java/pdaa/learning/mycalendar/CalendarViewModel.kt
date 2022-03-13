package pdaa.learning.mycalendar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.*

class CalendarViewModel(state: SavedStateHandle) : ViewModel() {

    private val TAG = "CalendarViewModel"

    //Variables tipo de elemento
    private val _recordKind = MutableLiveData<Kind>()
    val recordKind: LiveData<Kind>
        get() = _recordKind

    //Mis variables para el uso del viewModel
    private val _textoPrueba = MutableLiveData<String?>()
    val textoPrueba: LiveData<String?>
        get() = _textoPrueba

    private val _records = MutableLiveData<MutableList<Record>>()
    val records: MutableLiveData<MutableList<Record>>
        get() = _records

    //Starting date variables
    private val _sYear = MutableLiveData<Int>()
    val sYear: LiveData<Int>
        get() = _sYear

    private val _sMonth = MutableLiveData<Int>()
    val sMonth: LiveData<Int>
        get() = _sMonth

    private val _sDay = MutableLiveData<Int>()
    val sDay: LiveData<Int>
        get() = _sDay

    private val _sHour = MutableLiveData<Int>()
    val sHour: LiveData<Int>
        get() = _sHour

    private val _sMinute = MutableLiveData<Int>()
    val sMinute: LiveData<Int>
        get() = _sMinute

    //End date variables
    private val _eYear = MutableLiveData<Int>()
    val eYear: LiveData<Int>
        get() = _eYear

    private val _eMonth = MutableLiveData<Int>()
    val eMonth: LiveData<Int>
        get() = _eMonth

    private val _eDay = MutableLiveData<Int>()
    val eDay: LiveData<Int>
        get() = _eDay

    private val _eHour = MutableLiveData<Int>()
    val eHour: LiveData<Int>
        get() = _eHour

    private val _eMinute = MutableLiveData<Int>()
    val eMinute: LiveData<Int>
        get() = _eMinute

    private val _eventName = MutableLiveData<String>()
    val eventName: LiveData<String>
        get() = _eventName

    private val _eventDescription = MutableLiveData<String>()
    val eventDescription: LiveData<String>
        get() = _eventDescription

    private val _allDay = MutableLiveData<Boolean>()
    val allDay: LiveData<Boolean>
        get() = _allDay

    private val _newRecord = MutableLiveData<Record>()
    val newRecord: LiveData<Record>
        get() = _newRecord

    private val _recordOnDetails = MutableLiveData<Record>()
    val recordOnDetails: LiveData<Record>
        get() = _recordOnDetails


    init {
        _records.value = getAllRecord()
        configureStartingValues()
    }

    private fun getAllRecord(): MutableList<Record>? {
        val calendar = Calendar.getInstance()

        val fechaActualFormateada = calendar.get(Calendar.YEAR).toString() + " - " +
                calendar.get(Calendar.MONTH).toString() + " - " +
                calendar.get(Calendar.DAY_OF_MONTH)

        val horaActualFormateada = calendar.get(Calendar.HOUR_OF_DAY).toString() + ":" +
                calendar.get(Calendar.MINUTE).toString()

        return mutableListOf(
            Record(
                0,
                Kind.ANIVERSARIO,
                "Calificar trabajo de mis estudiantes",
                fechaActualFormateada,
                horaActualFormateada,
                true,
                "Ponerles a todos 10 :)"
            )
        )

    }

    private fun configureStartingValues() {
        val calendar = Calendar.getInstance()
        _sYear.value = calendar.get(Calendar.YEAR)
        _sMonth.value = calendar.get(Calendar.MONTH)
        _sDay.value = calendar.get(Calendar.DAY_OF_MONTH)
        _sHour.value = calendar.get(Calendar.HOUR_OF_DAY)
        _sMinute.value = calendar.get(Calendar.MINUTE)
        _eYear.value = calendar.get(Calendar.YEAR)
        _eMonth.value = calendar.get(Calendar.MONTH)
        _eDay.value = calendar.get(Calendar.DAY_OF_MONTH)
        _eHour.value = calendar.get(Calendar.HOUR_OF_DAY)
        _eMinute.value = calendar.get(Calendar.MINUTE)
        _allDay.value = false
        _newRecord.value =  Record(
            0,
            Kind.CITA,
            "",
            "",
            "",
            false,
            ""
        )
    }

    fun setNewDateRecordValues(year: Int, month: Int, dayOfMonth: Int) {
        _sYear.value = year
        _sMonth.value = month
        _sDay.value = dayOfMonth
    }

    fun setNewHourRecordValues(hour: Int, minute: Int) {
        _sHour.value = hour
        _sMinute.value = minute
    }

    fun setNewEndDateRecordValues(year: Int, month: Int, dayOfMonth: Int) {
        _eYear.value = year
        _eMonth.value = month
        _eDay.value = dayOfMonth
    }

    fun setEndNewHourRecordValues(hourOfDay: Int, minute: Int) {
        _eHour.value = hourOfDay
        _eMinute.value = minute
    }

    fun setKindOfRecord(kind: Kind) {
        _recordKind.value = kind
        Log.i(TAG, "Valor de kind  en setKindOfRecord${kind.toString()}")
    }

    fun generateRegisterToSave(eventName:String, eventDescription:String, allDay: Boolean) {

        val startDateAndHour = _sYear.value.toString() + "/" +
                _sMonth.value.toString() + "/" + _sDay.value.toString() + " - " +
                _sHour.value.toString() + ":"+_sMinute.value.toString()

        val endDateAndHour = _eYear.value.toString() + "/" +
                _eMonth.value.toString() + "/" + _eDay.value.toString() + " - " +
                _eHour.value.toString() + ":"+_eMinute.value.toString()



        val myNewREcord = Record(
            1,
            _recordKind.value,
            eventName,
            startDateAndHour,
            endDateAndHour,
            allDay,
            eventDescription
        )
         _newRecord.value = myNewREcord
    }

    fun saveNewRecord() {
        _newRecord.value?.let { _records.value?.add(it) }
    }

    fun showDetailsOfTheRecord(record: Record) {
        _recordOnDetails.value = record
    }
}