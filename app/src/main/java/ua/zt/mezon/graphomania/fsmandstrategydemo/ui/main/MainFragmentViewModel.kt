package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.MainFragmentRepository
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.MockDataRepository

class MainFragmentViewModel : ViewModel() {
    fun initialize(context: MainFragment) {
        mViewState.value = MainFragmentUiStatesModel.IniState
        mGateway = MockDataRepository(context.requireContext())
        mGateway.iniLoad()
    }

    val mViewState: MutableLiveData<MainFragmentUiStatesModel> = MutableLiveData()

    lateinit var mGateway:  MainFragmentRepository


    fun startLoadData() {

    }
}
