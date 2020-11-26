package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm

import android.util.Log
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.FSMAction

/**
 * Created by NickZT on 01.02.2019.
 */
class StrategyStateImpl(override val stateDesc: String) : IStrategyState {
    private var mFSMActionForStrategyMode: FSMAction? = null
    override fun addAction(fsmAction: FSMAction?) {
        mFSMActionForStrategyMode = fsmAction
    }

    override fun executeAction() {
        if (mFSMActionForStrategyMode != null && mFSMActionForStrategyMode!!.callToExecuteAction != null) {
            try {
                mFSMActionForStrategyMode!!.fireAction()
            } catch (e: Exception) {
                Log.e(TAG, e.localizedMessage)
            }
        }
    }

    companion object {
        private const val TAG = "StM"
    }
}