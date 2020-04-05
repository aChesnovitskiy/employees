package com.achesnovitskiy.empoyees.screens.employees

import com.achesnovitskiy.empoyees.pojo.Employee

interface EmployeeListView {
    fun showData(data: List<Employee>)

    fun showError(error: Throwable)
}