package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm;


import io.reactivex.functions.Action;

/**
 * Created by NickZT on 05.06.2019.
 */
public class FSMAction {
    private String mActionName;
    private Action mCallToExecuteAction;

    public FSMAction(String actionName) {
        mActionName = actionName;
        mCallToExecuteAction = null;
    }

    public FSMAction(String actionName, Action onExecute) {
        mActionName = actionName;
        mCallToExecuteAction = onExecute;
    }

    public Action getCallToExecuteAction() {
        return mCallToExecuteAction;
    }

    public String getActionName() {
        return mActionName;
    }

    public boolean fireAction() throws Exception {
        if (mCallToExecuteAction != null) {
            mCallToExecuteAction.run();
        }
        return mCallToExecuteAction != null;
    }

    @Override
    public String toString() {
        return mActionName;
    }

}
