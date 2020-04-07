package com.achesnovitskiy.empoyees.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.achesnovitskiy.empoyees.pojo.Employee

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val employeeDao: EmployeeDao

    companion object {
        private const val DB_NAME = "employees.db"
        private val LOCK = Any() // Block key

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                }
            }
            return instance
        }
    }
}