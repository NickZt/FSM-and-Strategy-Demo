package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.don11995.log.SimpleLog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.LoadItemData
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.MainFragmentRepository
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.MockDataRepository

class MainFragmentViewModel : ViewModel() {
    fun initialize(context: MainFragment) {
        mViewState.value = MainFragmentUiStatesModel.IniState
        mGateway = MockDataRepository(context.requireContext())
        mGateway.iniLoad()
        viewModelScope.launch { startLoadData() }
    }

    val mViewState: MutableLiveData<MainFragmentUiStatesModel> = MutableLiveData()

    lateinit var mGateway: MainFragmentRepository


    private suspend fun startLoadData() {
        mGateway.loadData().collect { either: Either<String, LoadItemData> ->
            when (either) {
                is Either.Left -> {
                    mViewState.value = MainFragmentUiStatesModel.LoadErrorState(either.a)
                }
                is Either.Right -> when (val itemData = either.b) {
                    is LoadItemData.ListShow -> {
                        if (itemData.listItem.isEmpty()) mViewState.value = MainFragmentUiStatesModel.ListEmptyState
                        else mViewState.value = MainFragmentUiStatesModel.ListShowState(itemData.listItem)
                    }
                    is LoadItemData.LoadCounterPercentData -> {
                        mViewState.value = MainFragmentUiStatesModel.LoadCounterPercentDataState(itemData.percent)
                    }
                }
            }

        }
    }

}
