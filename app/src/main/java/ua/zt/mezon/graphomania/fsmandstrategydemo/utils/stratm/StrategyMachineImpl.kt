package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm

import android.util.Log
import java.util.*

/**
 * Created by NickZT on 01.02.2019.
 */
class StrategyMachineImpl : IStrategyMachine {
    private var mStartState: IStrategyState? = null
    private var mEndState: IStrategyState? = null
    override var currentState: IStrategyState? = null
        private set
    private val mAllStates = ArrayList<IStrategyState>()
    override fun setCurrentState(targetStateDesc: String): Boolean {
        if (currentState == null) {
            Log.e(TAG, "FSM " + "Please setup start state")
            return false
        } else {
            if (!currentState!!.stateDesc.contentEquals(targetStateDesc)) {
                for (state in mAllStates) {
                    if (state.stateDesc == targetStateDesc) {
                        currentState = state
                        return true
                    }
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