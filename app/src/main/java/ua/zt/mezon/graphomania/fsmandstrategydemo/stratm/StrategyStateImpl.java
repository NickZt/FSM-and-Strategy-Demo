package ua.zt.mezon.graphomania.fsmandstrategydemo.stratm;

import android.util.Log;

import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.FSMAction;

/**
 * Created by NickZT on 01.02.2019.
 */
public class StrategyStateImpl implements IStrategyState {
    private static final String TAG = "StM";
    private String mStateName;
    private FSMAction mFSMActionForStrategyMode = null;

    public StrategyStateImpl(String stateName) {
        mStateName = stateName;
    }


    @Override
    public String getStateDesc() {
        return mStateName;
    }


    @Override
    public void addAction(FSMAction fsmAction) {
        mFSMActionForStrategyMode = fsmAction;
    }

    @Override
    public void executeAction() {
        if (mFSMActionForStrategyMode != null && mFSMActionForStrategyMode.getCallToExecuteAction() != null) {
            try {
                mFSMActionForStrategyMode.fireAction();
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        }
    }


}
