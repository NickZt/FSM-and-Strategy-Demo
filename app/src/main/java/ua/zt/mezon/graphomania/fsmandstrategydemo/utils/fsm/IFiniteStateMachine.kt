package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm

/**
 * Shortened implementation FSM without messages (may be later), with bind to MVP pattern.
 * init must been in Presenter, with Action links to supposed View or Presenter actions
 * Added strange ability to have Action not only on transactions but also on tops, as in the strategy patt
 */
interface IFiniteStateMachine {
    fun setStrategy(targetStateDesc: String): Boolean
    fun addState(startState: IFSMState, newState: IFSMState,
                 fsmAction: FSMAction)

    fun removeState(targetStateDesc: String): Boolean
    val currentState: IFSMState?
    fun getStartState(): IFSMState?
    fun setStartState(startState: IFSMState)
    fun getEndState(): IFSMState?
    fun setEndState(endState: IFSMState)
    fun transit(fsmAction: FSMAction): Boolean
}