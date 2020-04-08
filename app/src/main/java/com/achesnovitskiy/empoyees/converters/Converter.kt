package com.achesnovitskiy.empoyees.converters

import androidx.room.TypeConverter
import com.achesnovitskiy.empoyees.pojo.Speciality
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun listSpecialityToString(specialities: List<Speciality>): String = Gson().toJson(specialities)

    @TypeConverter
    fun stringToListSpeciality(specialitiesAsString: String): List<Speciality> {
        val gson = Gson()
        val objects = gson.fromJson(specialitiesAsString, ArrayList::class.java)
        val specialities = arrayListOf<Speciality>()
        for (o in objects) {
            specialities.add(gson.fromJson(o.toString(), Speciality::class.java))
        }
        return specialities
    }
}