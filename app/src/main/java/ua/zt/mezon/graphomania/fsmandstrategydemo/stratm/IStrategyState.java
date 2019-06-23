package ua.zt.mezon.graphomania.fsmandstrategydemo.stratm;


import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.FSMAction;

/**
 * Created by NickZT on 01.02.2019.
 */
public
interface IStrategyState {


    String getStateDesc();

    void addAction(FSMAction fsmAction);

    void executeAction();
}
