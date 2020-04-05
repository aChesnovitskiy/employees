package com.achesnovitskiy.empoyees.screens.employees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

class EmployeeListActivity : AppCompatActivity(), EmployeeListView {
    private val employeeAdapter: EmployeeAdapter by lazy { EmployeeAdapter() }
    private val presenter: EmployeeListPresenter by lazy { EmployeeListPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        with(rv_employees) {
            adapter = employeeAdapter
            layoutManager = LinearLayoutManager(this@EmployeeListActivity)
            addItemDecoration(divider)
        }

        presenter.loadData()
    }

    override fun onDestroy() {
        presenter.disposeDisposable()
        super.onDestroy()
    }

    override fun showData(data: List<Employee>) {
        employeeAdapter.setEmlpoyees(data)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(
            this,
            "Ошибка получения данных:\n${error.printStackTrace()}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
