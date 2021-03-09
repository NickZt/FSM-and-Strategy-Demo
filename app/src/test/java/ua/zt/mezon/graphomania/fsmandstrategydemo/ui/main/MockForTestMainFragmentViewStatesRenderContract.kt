package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.ItemData

open class MockForTestMainFragmentViewStatesRenderContract : MainFragmentViewStatesRenderContract {
    override fun showIni() {
    }

    override fun showLoadCounterPercentData(percent: Int) {
    }

    override fun showLoadError(error: String?) {
    }

    override fun showListEmpty() {
    }

    override fun showListShow(listItems: ArrayList<ItemData>) {
    }
}