package es.santirivera.data.dto

import android.os.Parcel
import android.os.Parcelable

class PlayerDTO : Parcelable {

    var name: String? = null
    var surname: String? = null
    var image: String? = null

    constructor(name: String, surname: String, image: String) {
        this.name = name
        this.surname = surname
        this.image = image
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.surname)
        dest.writeString(this.image)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.name = `in`.readString()
        this.surname = `in`.readString()
        this.image = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<PlayerDTO> = object : Parcelable.Creator<PlayerDTO> {
            override fun createFromParcel(source: Parcel): PlayerDTO {
                return PlayerDTO(source)
            }

            override fun newArray(size: Int): Array<PlayerDTO?> {
                return arrayOfNulls(size)
            }
        }
    }
}
