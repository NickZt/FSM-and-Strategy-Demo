package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm

import android.util.Log
import java.util.*

/**
 * Created by NickZT on 02.02.2019.
 */
class FiniteStateMachineWithStatePayloadImpl<E> : IFiniteStateMachine {
    private lateinit var mStartState: IFSMState
    private lateinit var mEndState: IFSMState
    override lateinit var currentState: IFSMState
        private set
    private val mAllStates = ArrayList<IFSMState>()
    private val mMapForAllStates = HashMap<String?, ArrayList<IFSMState>?>()
    private val mPayload: E? = null
    override fun setStrategy(targetStateDesc: String): Boolean {
        if (currentState == null) {
            Log.e(TAG, " FSM " + "Please setup start state")
            return false
        } else {
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

    override fun addState(startState: IFSMState, newState: IFSMState, fsmAction: FSMAction) {
        // validate startState, newState and action
        // update mapping in finite state machine
        mAllStates.add(newState)
        val startStateDesc = startState.stateDesc
        val newStateDesc = newState.stateDesc
        mMapForAllStates[newStateDesc] = ArrayList()
        var adjacentStateList: ArrayList<IFSMState>? = null
        if (mMapForAllStates.containsKey(startStateDesc)) {
            adjacentStateList = mMapForAllStates[startStateDesc]
            adjacentStateList!!.add(newState)
        } else {
            mAllStates.add(startState)
            adjacentStateList = ArrayList()
            adjacentStateList.add(newState)
        }
        mMapForAllStates[startStateDesc] = adjacentStateList

        // update mapping in startState
        for (state in mAllStates) {
            val isStartState = state.stateDesc == startState.stateDesc
            if (isStartState) {
                startState.addTransit(fsmAction, newState)
            }
        }
    }

    override fun removeState(targetStateDesc: String): Boolean {
        // validate state
        if (!mMapForAllStates.containsKey(targetStateDesc)) {
            Log.e(TAG, "FSM Don't have state: $targetStateDesc")
            return false
        } else {
            // remove from mapping
            mMapForAllStates.remove(targetStateDesc)
        }

        // update all state
        var targetState: IFSMState? = null
        for (state in mAllStates) {
            if (state.stateDesc == targetStateDesc) {
                targetState = state
            } else {
                state.removeTransit(targetStateDesc)
            }
        }
        mAllStates.remove(targetState)
        return true
    }

    override fun getStartState(): IFSMState? {
        return mStartState
    }

    override fun setStartState(startState: IFSMState) {
        mStartState = startState
        currentState = startState
        mAllStates.add(startState)
        // todo: might have some value
        mMapForAllStates[startState.stateDesc] = ArrayList()
    }

    override fun getEndState(): IFSMState? {
        return mEndState
    }

    override fun setEndState(endState: IFSMState) {
        mEndState = endState
        mAllStates.add(endState)
        mMapForAllStates[endState.stateDesc] = ArrayList()
    }

    override fun transit(FSMAction: FSMAction): Boolean {
        if (currentState == null) {
            Log.e(TAG, "FSM " + "Please setup start state")
            return false
        }
        val localMapping = currentState!!.adjacentStates
        if (localMapping!!.containsKey(FSMAction.toString())) {
            currentState = localMapping!![FSMAction.toString()]
        } else {
            Log.e(TAG, "FSM " + "No action start from current state")
            return false
        }
        return true
    }

    companion object {
        private const val TAG = "FSM"
    }
}