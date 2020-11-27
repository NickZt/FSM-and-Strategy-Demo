package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm

import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.FSMAction

/**
 * Created by NickZT on 01.02.2019.
 */
interface IStrategyState {
    val stateDesc: String
    fun addAction(fsmAction: FSMAction)
    fun executeAction()
}