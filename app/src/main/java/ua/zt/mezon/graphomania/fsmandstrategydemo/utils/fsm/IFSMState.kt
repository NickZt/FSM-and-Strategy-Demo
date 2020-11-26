package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm

/**
 * Created by NickZT on 05.06.2019.
 */
interface IFSMState {
    // Returns the mapping for which one action will lead to another state
    val adjacentStates: Map<String?, IFSMState>
    val stateDesc: String
    fun addTransit(FSMAction: FSMAction, nextState: IFSMState)
    fun removeTransit(targetStateDesc: String)
    fun addActionForStrategyMode(fsmAction: FSMAction?)
    fun executeActionForStrategyMode()
}