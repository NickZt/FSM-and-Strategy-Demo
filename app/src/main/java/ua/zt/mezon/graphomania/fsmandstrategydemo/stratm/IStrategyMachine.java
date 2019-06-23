package ua.zt.mezon.graphomania.fsmandstrategydemo.stratm;

/**
 * Shortened strategy pattern realisation intended to link to MVP pattern.
 * init must been in Presenter, with Action links to supposed View or Presenter actions
 */

public interface IStrategyMachine {
    boolean setCurrentState(String targetStateDesc);

    void addState(IStrategyState newState);

    boolean removeState(String targetStateDesc);

    IStrategyState getCurrentState();

    IStrategyState getStartState();

    void setStartState(IStrategyState startState);

    IStrategyState getEndState();

    void setEndState(IStrategyState endState);
}
