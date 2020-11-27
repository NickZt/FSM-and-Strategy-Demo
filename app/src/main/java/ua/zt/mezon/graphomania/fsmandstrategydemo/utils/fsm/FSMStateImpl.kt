package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm

import com.don11995.log.SimpleLog.e
import java.util.*

/**
 * Created by NickZT on 05.06.2019.
 */
class FSMStateImpl(override val stateDesc: String) : IFSMState {
    private val mMapping = HashMap<String?, IFSMState>()
    private lateinit var mFSMActionForStrategyMode: FSMAction
    override val adjacentStates: Map<String?, IFSMState>
        get() = mMapping

    override fun addTransit(FSMAction: FSMAction, state: IFSMState) {
        mMapping[FSMAction.toString()] = state
    }

    override fun removeTransit(targetStateDesc: String) {
        // get action which directs to target state
        var targetAction: String? = null
        for ((key, state) in mMapping) {
            if (state.stateDesc == targetStateDesc) {
                targetAction = key
            }
        }
        mMapping.remove(targetAction)
    }

    override fun addActionForStrategyMode(fsmAction: FSMAction) {
        mFSMActionForStrategyMode = fsmAction
    }

    override fun executeActionForStrategyMode() {
        if (mFSMActionForStrategyMode != null && mFSMActionForStrategyMode!!.callToExecuteAction != null) {
            try {
                mFSMActionForStrategyMode!!.fireAction()
            } catch (e: Exception) {
                e(e.localizedMessage)
            }
        }
    }
}