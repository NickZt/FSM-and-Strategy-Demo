package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.ItemData

interface MainFragmentViewStatesRenderContract {
    fun render(viewState: MainFragmentUiStatesModel) {
    }

    fun showIni()
    fun showLoadCounterPercentData(percent: Int)
    fun showLoadError(error: String?)
    fun showListEmpty()
    fun showListShow(listItems: ArrayList<ItemData>)
}
