package com.achesnovitskiy.empoyees.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achesnovitskiy.empoyees.R
import com.achesnovitskiy.empoyees.pojo.Employee
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.employee_item.*

class EmployeeAdapter() : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employees: List<Employee> = listOf()

    fun setEmlpoyees(employees: List<Employee>) {
        this.employees = employees
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EmployeeViewHolder(inflater.inflate(R.layout.employee_item, parent, false))
    }

    override fun getItemCount(): Int = employees.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) = holder.bind(employees[position])

    inner class EmployeeViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(item: Employee) {
            tv_first_name.text = item.f_name
            tv_last_name.text = item.l_name
        }
    }
}