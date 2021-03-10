package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.ItemData

interface MainFragmentViewStatesRenderContract {
    fun render(viewState: MainFragmentUiStatesModel) {
        when (viewState) {
            is MainFragmentUiStatesModel.IniState -> {
                showIni()
            }
            is MainFragmentUiStatesModel.LoadCounterPercentDataState -> {
                showLoadCounterPercentData(viewState.percent)
            }
            is MainFragmentUiStatesModel.LoadErrorState -> {
                showLoadError(viewState.errorCode)
            }
            is MainFragmentUiStatesModel.ListEmptyState -> {
                showListEmpty()
            }
            is MainFragmentUiStatesModel.ListShowState -> {
                showListShow(viewState.listItem)
            }
        }
    }

    fun showIni()
    fun showLoadCounterPercentData(percent: Int)
    fun showLoadError(error: String?)
    fun showListEmpty()
    fun showListShow(listItems: ArrayList<ItemData>)
}
