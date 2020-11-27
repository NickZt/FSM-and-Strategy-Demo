package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm

/**
 * Shortened strategy pattern realisation intended to link to MVP pattern.
 * init must been in Presenter, with Action links to supposed View or Presenter actions
 */
interface IStrategyMachine {
    fun setCurrentState(targetStateDesc: String): Boolean
    fun addState(newState: IStrategyState)
    fun removeState(targetStateDesc: String): Boolean
    val currentState: IStrategyState
    fun getStartState(): IStrategyState?
    fun setStartState(startState: IStrategyState)
    fun getEndState(): IStrategyState?
    fun setEndState(endState: IStrategyState)
}