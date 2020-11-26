package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm;

import com.don11995.log.SimpleLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NickZT on 05.06.2019.
 */
public class FSMStateImpl implements IFSMState {
    private HashMap<String, IFSMState> mMapping = new HashMap<>();
    private String mStateName;
    private FSMAction mFSMActionForStrategyMode = null;

    public FSMStateImpl(String stateName) {
        mStateName = stateName;
    }

    @Override
    public Map<String, IFSMState> getAdjacentStates() {
        return mMapping;
    }

    @Override
    public String getStateDesc() {
        return mStateName;
    }

    @Override
    public void addTransit(FSMAction FSMAction, IFSMState state) {
        mMapping.put(FSMAction.toString(), state);
    }

    @Override
    public void removeTransit(String targetStateDesc) {
        // get action which directs to target state
        String targetAction = null;
        for (Map.Entry<String, IFSMState> entry : mMapping.entrySet()) {
            IFSMState state = entry.getValue();
            if (state.getStateDesc().equals(targetStateDesc)) {
                targetAction = entry.getKey();
            }
        }
        mMapping.remove(targetAction);
    }

    @Override
    public void addActionForStrategyMode(FSMAction fsmAction) {
        mFSMActionForStrategyMode = fsmAction;
    }

    @Override
    public void executeActionForStrategyMode() {
        if (mFSMActionForStrategyMode != null && mFSMActionForStrategyMode.getCallToExecuteAction() != null) {
            try {
                mFSMActionForStrategyMode.fireAction();
            } catch (Exception e) {
                SimpleLog.e(e.getLocalizedMessage());
            }
        }
    }


}
