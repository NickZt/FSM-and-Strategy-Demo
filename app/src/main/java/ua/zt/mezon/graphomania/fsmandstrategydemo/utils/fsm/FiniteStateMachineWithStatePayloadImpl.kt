package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NickZT on 02.02.2019.
 */
public class FiniteStateMachineWithStatePayloadImpl<E> implements IFiniteStateMachine {
    private static final String TAG = "FSM";
    private IFSMState mStartState;
    private IFSMState mEndState;
    private IFSMState mCurrentState;
    private ArrayList<IFSMState> mAllStates = new ArrayList<>();
    private HashMap<String, ArrayList<IFSMState>> mMapForAllStates = new HashMap<>();
    private E mPayload;

    public FiniteStateMachineWithStatePayloadImpl() {
    }

    @Override
    public boolean setStrategy(String targetStateDesc) {
        if (mCurrentState == null) {
            Log.e(TAG, " FSM " + "Please setup start state");
            return false;
        } else {
            for (IFSMState state : mAllStates) {
                if (state.getStateDesc().equals(targetStateDesc)) {
                    mCurrentState = state;
                    return true;
                }
            }
        }
        Log.e(TAG, "FSM " + "No such strategy in current fsm states");
        return false;
    }

    @Override
    public void addState(IFSMState startState, IFSMState newState, FSMAction fsmAction) {
        // validate startState, newState and action
        // update mapping in finite state machine
        mAllStates.add(newState);
        final String startStateDesc = startState.getStateDesc();
        final String newStateDesc = newState.getStateDesc();
        mMapForAllStates.put(newStateDesc, new ArrayList<IFSMState>());
        ArrayList<IFSMState> adjacentStateList = null;
        if (mMapForAllStates.containsKey(startStateDesc)) {
            adjacentStateList = mMapForAllStates.get(startStateDesc);
            adjacentStateList.add(newState);
        } else {
            mAllStates.add(startState);
            adjacentStateList = new ArrayList<>();
            adjacentStateList.add(newState);
        }
        mMapForAllStates.put(startStateDesc, adjacentStateList);

        // update mapping in startState
        for (IFSMState state : mAllStates) {
            boolean isStartState = state.getStateDesc().equals(startState.getStateDesc());
            if (isStartState) {
                startState.addTransit(fsmAction, newState);
            }
        }
    }

    @Override
    public boolean removeState(String targetStateDesc) {
        // validate state
        if (!mMapForAllStates.containsKey(targetStateDesc)) {
            Log.e(TAG, "FSM " + "Don't have state: " + targetStateDesc);
            return false;
        } else {
            // remove from mapping
            mMapForAllStates.remove(targetStateDesc);
        }

        // update all state
        IFSMState targetState = null;
        for (IFSMState state : mAllStates) {
            if (state.getStateDesc().equals(targetStateDesc)) {
                targetState = state;
            } else {
                state.removeTransit(targetStateDesc);
            }
        }

        mAllStates.remove(targetState);
        return true;
    }

    @Override
    public IFSMState getCurrentState() {
        return mCurrentState;
    }

    @Override
    public IFSMState getStartState() {
        return mStartState;
    }

    @Override
    public void setStartState(IFSMState startState) {
        mStartState = startState;
        mCurrentState = startState;
        mAllStates.add(startState);
        // todo: might have some value
        mMapForAllStates.put(startState.getStateDesc(), new ArrayList<IFSMState>());
    }

    @Override
    public IFSMState getEndState() {
        return mEndState;
    }

    @Override
    public void setEndState(IFSMState endState) {
        mEndState = endState;
        mAllStates.add(endState);
        mMapForAllStates.put(endState.getStateDesc(), new ArrayList<IFSMState>());
    }

    @Override
    public boolean transit(FSMAction FSMAction) {
        if (mCurrentState == null) {
            Log.e(TAG, "FSM " + "Please setup start state");
            return false;
        }
        Map<String, IFSMState> localMapping = mCurrentState.getAdjacentStates();
        if (localMapping.containsKey(FSMAction.toString())) {
            mCurrentState = localMapping.get(FSMAction.toString());
        } else {
            Log.e(TAG, "FSM " + "No action start from current state");
            return false;
        }
        return true;
    }
}
