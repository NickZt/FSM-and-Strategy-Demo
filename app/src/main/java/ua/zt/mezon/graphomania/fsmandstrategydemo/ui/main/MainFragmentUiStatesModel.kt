package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.ItemData

sealed class MainFragmentUiStatesModel {
    object Initial : MainFragmentUiStatesModel()
    data class LoadCounterPercentDataState(val percent: Int) : MainFragmentUiStatesModel()
    data class LoadErrorState(val errorCode: String) : MainFragmentUiStatesModel()
    object ListEmptyState : MainFragmentUiStatesModel()
    data class ListShowState(val listItem: ArrayList<ItemData>) : MainFragmentUiStatesModel()
//    object NoInDataStateState : MainFragmentUiStates()
}
