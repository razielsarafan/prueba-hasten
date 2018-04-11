package es.santirivera.data.dto

import android.os.Parcel
import android.os.Parcelable

class CategoryDTO : Parcelable {

    var players: List<PlayerDTO>? = null
    var title: String? = null
    var type: String? = null

    constructor(title: String, type: String, players: List<PlayerDTO>) {
        this.title = title
        this.type = type
        this.players = players
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(this.players)
        dest.writeString(this.title)
        dest.writeString(this.type)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.players = `in`.createTypedArrayList(PlayerDTO.CREATOR)
        this.title = `in`.readString()
        this.type = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<CategoryDTO> = object : Parcelable.Creator<CategoryDTO> {
            override fun createFromParcel(source: Parcel): CategoryDTO {
                return CategoryDTO(source)
            }

            override fun newArray(size: Int): Array<CategoryDTO?> {
                return arrayOfNulls(size)
            }
        }
    }
}
