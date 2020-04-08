package com.achesnovitskiy.empoyees.screens.employees

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.*
import com.achesnovitskiy.empoyees.App
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.achesnovitskiy.empoyees.data.AppDatabase
import com.achesnovitskiy.empoyees.pojo.Employee
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(App.applicationContext())
    val employees: LiveData<List<Employee>>? = database?.employeeDao?.getAllEmployees()
    private var errors: MutableLiveData<Throwable>? = null

    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    fun loadData() {
        val apiFactory = ApiFactory
        val apiService = apiFactory.getApiService()

        disposable = apiService.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    deleteAllEmployees()
                    insertEmployees(result.employees)
                },
                { error ->
                    errors?.value = error
                }
            )

        compositeDisposable.add(disposable)
    }

    fun getErrors() = errors

    fun clearErrors() {
        errors = null
    }

    private fun insertEmployees(employees: List<Employee>) {
        InsertEmployeesTask(database).execute(employees)
    }

    class InsertEmployeesTask(private val database: AppDatabase?) :
        AsyncTask<List<Employee>, Unit, Unit>() {
        override fun doInBackground(vararg params: List<Employee>?): Unit {
            if (!params.isNullOrEmpty()) {
                database?.employeeDao?.insertEmployees(params[0])
            }
        }
    }

    private fun deleteAllEmployees() {
        DeleteEmployeesTask(database).execute()
    }

    class DeleteEmployeesTask(private val database: AppDatabase?) :
        AsyncTask<List<Unit>, Unit, Unit>() {
        override fun doInBackground(vararg params: List<Unit>?) {
            database?.employeeDao?.deleteAllEmployees()
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
                return EmployeeViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}