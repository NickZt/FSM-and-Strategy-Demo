package ua.zt.mezon.graphomania.fsmandstrategydemo.stratm;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by NickZT on 01.02.2019.
 */
public class StrategyMachineImpl implements IStrategyMachine {
    private static final String TAG = "StM";
    private IStrategyState mStartState;
    private IStrategyState mEndState;
    private IStrategyState mCurrentState;
    private ArrayList<IStrategyState> mAllStates = new ArrayList<>();


    public StrategyMachineImpl() {
    }

    @Override
    public boolean setCurrentState(String targetStateDesc) {

        if (mCurrentState == null) {
            Log.e(TAG, "FSM " + "Please setup start state");
            return false;
        } else {
            if (!getCurrentState().getStateDesc().contentEquals(targetStateDesc)) {
                for (IStrategyState state : mAllStates) {
                    if (state.getStateDesc().equals(targetStateDesc)) {
                        mCurrentState = state;
                        return true;
                    }
                }
            }
        }
        Log.e(TAG, "FSM " + "No such strategy in current fsm states");
        return false;
    }

    @Override
    public void addState(IStrategyState newState) {
        // validate startState, newState and action
        // update mapping in finite state machine
        mAllStates.add(newState);

    }

    @Override
    public boolean removeState(String targetStateDesc) {
        // update all state
        IStrategyState targetState = null;
        for (IStrategyState state : mAllStates) {
            if (state.getStateDesc().equals(targetStateDesc)) {
                targetState = state;
            }
        }

        if (targetState != null) {
            mAllStates.remove(targetState);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public IStrategyState getCurrentState() {
        return mCurrentState;
    }

    @Override
    public IStrategyState getStartState() {
        return mStartState;
    }

    @Override
    public void setStartState(IStrategyState startState) {
        mStartState = startState;
        mCurrentState = startState;
        mAllStates.add(startState);

    }

    @Override
    public IStrategyState getEndState() {
        return mEndState;
    }

    @Override
    public void setEndState(IStrategyState endState) {
        mEndState = endState;
        mAllStates.add(endState);

    }
}
