package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm

import android.util.Log
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.FSMAction

/**
 * Created by NickZT on 01.02.2019.
 */
class StrategyStateImpl(override val stateDesc: String) : IStrategyState {
    private lateinit var mFSMActionForStrategyMode: FSMAction

    override fun addAction(fsmAction: FSMAction) {
        mFSMActionForStrategyMode = fsmAction
    }

    override fun executeAction() {
        try {
            mFSMActionForStrategyMode!!.fireAction()
        } catch (e: Exception) {
            Log.e(TAG, e.localizedMessage)
        }
    }

    companion object {
        private const val TAG = "StM"
    }
}