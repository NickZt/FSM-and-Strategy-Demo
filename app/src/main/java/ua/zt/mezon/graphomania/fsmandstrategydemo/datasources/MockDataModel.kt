package ua.zt.mezon.graphomania.fsmandstrategydemo.datasources

import android.content.Context

class MockDataModel(context: Context) : Repository {
    override fun iniLoad() {
        percent = 0;
        errorState = ""
    }

    companion object {
        var loading = false
        var percent = 0
        var errorState = ""
    }


}
