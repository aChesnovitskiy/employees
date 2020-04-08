package com.achesnovitskiy.empoyees.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.achesnovitskiy.empoyees.converters.Converter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employees")
@TypeConverters(Converter::class)
data class Employee (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("f_name") val f_name : String,
    @SerializedName("l_name") val l_name : String,
    @SerializedName("birthday") val birthday : String? = null,
    @SerializedName("avatr_url") val avatr_url : String? = null,
    @SerializedName("specialty") val specialty : List<Speciality>? = null
)