package com.achesnovitskiy.empoyees.screens.employees

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.achesnovitskiy.empoyees.R
import com.achesnovitskiy.empoyees.adapters.EmployeeAdapter
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.achesnovitskiy.empoyees.pojo.Employee
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_employee_list.*

class EmployeeListActivity : AppCompatActivity() {
    private val employeeAdapter: EmployeeAdapter by lazy { EmployeeAdapter() }
    private val viewModel: EmployeeViewModel by lazy {
        val vmFactory = EmployeeViewModel.ViewModelFactory(Application())
        ViewModelProvider(this, vmFactory).get(EmployeeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        with(rv_employees) {
            adapter = employeeAdapter
            layoutManager = LinearLayoutManager(this@EmployeeListActivity)
            addItemDecoration(divider)
        }

        viewModel.employees?.observe(this, Observer { employeeAdapter.setEmlpoyees(it) })
        viewModel.loadData()
    }
}
