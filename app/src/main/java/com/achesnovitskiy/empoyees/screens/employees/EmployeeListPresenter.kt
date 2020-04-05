package com.achesnovitskiy.empoyees.screens.employees

import com.achesnovitskiy.empoyees.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EmployeeListPresenter(val view: EmployeeListView) {
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    fun loadData() {
        val apiFactory = ApiFactory
        val apiService = apiFactory.getApiService()

        disposable = apiService.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> view.showData(result.employees) },
                { error -> view.showError(error) }
            )

        compositeDisposable.add(disposable)
    }

    fun disposeDisposable() {
        compositeDisposable.dispose()
    }
}