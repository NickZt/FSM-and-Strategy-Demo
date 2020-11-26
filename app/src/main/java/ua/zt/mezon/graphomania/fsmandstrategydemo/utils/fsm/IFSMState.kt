package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm;

import java.util.Map;

/**
 * Created by NickZT on 05.06.2019.
 */
public
interface IFSMState {
    // Returns the mapping for which one action will lead to another state
    Map<String, IFSMState> getAdjacentStates();

    String getStateDesc();

    void addTransit(FSMAction FSMAction, IFSMState nextState);

    void removeTransit(String targetStateDesc);

    void addActionForStrategyMode(FSMAction fsmAction);

    void executeActionForStrategyMode();
}
