package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.Repository
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.MockDataModel
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.ItemData
import kotlin.random.Random
import kotlin.random.nextLong

class MainFragmentViewModel : ViewModel() {
    fun initialize(context: MainFragment) {
        mViewState.value = MainFragmentUiStatesModel.Initial;
        mRepository = MockDataModel(context.requireContext())
        mRepository.iniLoad()
    }

    val mViewState: MutableLiveData<MainFragmentUiStatesModel> = MutableLiveData()

    lateinit var mRepository:  Repository


    fun startLoadData() {
        viewModelScope.launch {
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(10)
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(30)
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(42)
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(50)
            delay()

            if (Random.nextBoolean()) {
                mViewState.value = MainFragmentUiStatesModel.LoadErrorState("The hurricane cut the connection")
                return@launch
            }

            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(70)
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(90)
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(94)
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(99)
            delay()
            mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(100)
            if (Random.nextBoolean()) {
                mViewState.value = MainFragmentUiStatesModel.ListEmptyState
                return@launch
            }
            mViewState.value = MainFragmentUiStatesModel.ListShowState(arrayListOf(ItemData("One"), ItemData("Two")))
        }
    }

    private suspend fun delay() {
        kotlinx.coroutines.delay(Random.nextLong(500L..1200L))
    }

}