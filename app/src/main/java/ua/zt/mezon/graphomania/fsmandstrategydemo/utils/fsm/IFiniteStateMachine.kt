package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm;

/**
 * Shortened implementation FSM without messages (may be later), with bind to MVP pattern.
 * init must been in Presenter, with Action links to supposed View or Presenter actions
 * Added strange ability to have Action not only on transactions but also on tops, as in the strategy patt
 */

public interface IFiniteStateMachine {
    boolean setStrategy(String targetStateDesc);

    void addState(IFSMState startState, IFSMState newState,
                  FSMAction fsmAction);

    boolean removeState(String targetStateDesc);

    IFSMState getCurrentState();

    IFSMState getStartState();

    void setStartState(IFSMState startState);

    IFSMState getEndState();

    void setEndState(IFSMState endState);

    boolean transit(FSMAction fsmAction);
}
