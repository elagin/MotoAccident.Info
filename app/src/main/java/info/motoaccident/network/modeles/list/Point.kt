package info.motoaccident.network.modeles.list

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import info.motoaccident.dictionaries.AccidentDamage
import info.motoaccident.dictionaries.AccidentStatus
import info.motoaccident.dictionaries.AccidentType
import java.util.*

class Point {
    val id: Int = 0
    val time: Long = 0
    @SerializedName("a") val address: String = ""
    @SerializedName("d") val description: String = ""
    @SerializedName("s") val status: AccidentStatus = AccidentStatus.ACTIVE
    @SerializedName("o") val owner: String = ""
    @SerializedName("oid") val ownerId: Int = 0
    val lat: Float = 55.752295f
    val lon: Float = 37.622735f
    @SerializedName("t") val type: AccidentType = AccidentType.OTHER
    @SerializedName("med") val damage: AccidentDamage = AccidentDamage.UNKNOWN
    @SerializedName("m") var messages: List<Message> = ArrayList()
    @SerializedName("v") var volunteers: List<Volunteer> = ArrayList()
    @SerializedName("h") var history: List<History> = ArrayList()

    var location: LatLng = LatLng(lat.toDouble(), lon.toDouble())
        get() = LatLng(lat.toDouble(), lon.toDouble())
        private set

    fun isAccident() = type in AccidentType.SOLO..AccidentType.MOTO_MAN
}
