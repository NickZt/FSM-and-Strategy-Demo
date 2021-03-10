package ua.zt.mezon.graphomania.fsmandstrategydemo.datasources


sealed class LoadItemData {
    data class LoadCounterPercentData(val percent:Int) : LoadItemData()
    data class ListShow(val listItem: ArrayList<ItemData>) : LoadItemData()
}

class ItemData(val title: String) {
}
