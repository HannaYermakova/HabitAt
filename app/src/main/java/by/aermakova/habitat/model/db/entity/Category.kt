package by.aermakova.habitat.model.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
open class Category : Parcelable {
    @kotlin.jvm.JvmField
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var title: String? = null
    var count = 0
    var color = 0

    @Ignore
    constructor() {
    }

    constructor(title: String?, count: Int, color: Int) {
        this.title = title!!.trim { it <= ' ' }.replace(" ", "_")
        this.count = count
        this.color = color
    }

    @Ignore
    protected constructor(`in`: Parcel) {
        id = `in`.readLong()
        title = `in`.readString()
        count = `in`.readInt()
        color = `in`.readInt()
    }

    @Ignore
    override fun describeContents(): Int {
        return 0
    }

    @Ignore
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(title)
        dest.writeInt(count)
        dest.writeInt(color)
    }

    companion object {
        @Ignore
        val CREATOR: Parcelable.Creator<Category?> = object : Parcelable.Creator<Category?> {
            override fun createFromParcel(`in`: Parcel): Category? {
                return Category(`in`)
            }

            override fun newArray(size: Int): Array<Category?> {
                return arrayOfNulls(size)
            }
        }
    }
}