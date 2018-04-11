package es.santirivera.domain.model

import android.os.Parcel
import android.os.Parcelable

import es.santirivera.data.dto.PlayerDTO

class PlayerModel : Parcelable {

    var name: String? = null
    var surname: String? = null

    var imageUrl: String? = null

    constructor(dto: PlayerDTO) {
        this.name = dto.name
        this.surname = dto.surname
        this.imageUrl = dto.image
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.surname)
        dest.writeString(this.imageUrl)
    }

    protected constructor(`in`: Parcel) {
        this.name = `in`.readString()
        this.surname = `in`.readString()
        this.imageUrl = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<PlayerModel> = object : Parcelable.Creator<PlayerModel> {
            override fun createFromParcel(source: Parcel): PlayerModel {
                return PlayerModel(source)
            }

            override fun newArray(size: Int): Array<PlayerModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
