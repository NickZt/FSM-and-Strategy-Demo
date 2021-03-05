package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.ItemData

interface MainFragmentViewStatesRenderContract {
    fun render(viewState: MainFragmentUiStatesModel) {
        when (viewState) {
            is MainFragmentUiStatesModel.Initial -> {
                showInitState()
            }
            is MainFragmentUiStatesModel.LoadCounterPercentDataState -> {
                showLoadProgress(viewState.percent)
            }
            is MainFragmentUiStatesModel.LoadErrorState -> {
                showError(viewState.errorCode)
            }
            is MainFragmentUiStatesModel.ListEmptyState -> {
                showEmptyState()
            }
            is MainFragmentUiStatesModel.ListShowState -> {
                showList(viewState.listItem)
            }
        }
    }

    fun showInitState()
    fun showLoadProgress(percent: Int)
    fun showError(error: String?)
    fun showEmptyState()
    fun showList(listItems: ArrayList<ItemData>)
}
