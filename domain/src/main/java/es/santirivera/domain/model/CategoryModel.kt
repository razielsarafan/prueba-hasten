package es.santirivera.domain.model

import android.os.Parcel
import android.os.Parcelable
import es.santirivera.data.dto.CategoryDTO
import java.util.*

class CategoryModel : Parcelable {

    var playerList: ArrayList<PlayerModel>? = null
    var title: String? = null
    var type: String? = null

    constructor(categoryDTO: CategoryDTO) {
        playerList = ArrayList()
        for (playerDTO in categoryDTO.players!!) {
            playerList!!.add(PlayerModel(playerDTO))
        }
        this.title = categoryDTO.title
        this.type = categoryDTO.title
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(this.playerList)
        dest.writeString(this.title)
        dest.writeString(this.type)
    }

    protected constructor(`in`: Parcel) {
        this.playerList = ArrayList()
        `in`.readList(this.playerList, CategoryModel::class.java!!.getClassLoader())
        this.title = `in`.readString()
        this.type = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<CategoryModel> = object : Parcelable.Creator<CategoryModel> {
            override fun createFromParcel(source: Parcel): CategoryModel {
                return CategoryModel(source)
            }

            override fun newArray(size: Int): Array<CategoryModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
