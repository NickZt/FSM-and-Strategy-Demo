package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm

import android.util.Log
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.IFSMState
import java.util.*

/**
 * Created by NickZT on 01.02.2019.
 */
class StrategyMachineImpl : IStrategyMachine {
    private lateinit var mStartState: IStrategyState
    private lateinit var mEndState: IStrategyState
    override lateinit var currentState: IStrategyState
        private set
    private val mAllStates = ArrayList<IStrategyState>()
    override fun setCurrentState(targetStateDesc: String): Boolean {
        if (!currentState.stateDesc.contentEquals(targetStateDesc)) {
            for (state in mAllStates) {
                if (state.stateDesc == targetStateDesc) {
                    currentState = state
                    return true
                }
            }
        }
        Log.e(TAG, "FSM " + "No such strategy in current fsm states")
        return false
    }

    override fun addState(newState: IStrategyState) {
        // validate startState, newState and action
        // update mapping in finite state machine
        mAllStates.add(newState)
    }

    override fun removeState(targetStateDesc: String): Boolean {
        // update all state
        var targetState: IStrategyState? = null
        for (state in mAllStates) {
            if (state.stateDesc == targetStateDesc) {
                targetState = state
            }
        }
        return if (targetState != null) {
            mAllStates.remove(targetState)
            true
        } else {
            false
        }
    }

    override fun getStartState(): IStrategyState? {
        return mStartState
    }

    override fun setStartState(startState: IStrategyState) {
        mStartState = startState
        currentState = startState
        mAllStates.add(startState)
    }

    override fun getEndState(): IStrategyState? {
        return mEndState
    }

    override fun setEndState(endState: IStrategyState) {
        mEndState = endState
        mAllStates.add(endState)
    }

    companion object {
        private const val TAG = "StM"
    }
}