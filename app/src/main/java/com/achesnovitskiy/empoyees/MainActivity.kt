package com.achesnovitskiy.empoyees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.achesnovitskiy.empoyees.adapters.EmployeeAdapter
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.achesnovitskiy.empoyees.pojo.Employee
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeAdapter = EmployeeAdapter()

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        with(rv_employees) {
            adapter = employeeAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(divider)
        }

        val apiFactory = ApiFactory
        val apiService = apiFactory.getApiService()

        disposable = apiService.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> employeeAdapter.setEmlpoyees(result.employees) },
                { error ->
                    Toast.makeText(
                        this,
                        "Ошибка получения данных:\n${error.printStackTrace()}",
                        Toast.LENGTH_SHORT
                    ).show()
                })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
