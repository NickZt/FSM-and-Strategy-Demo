package ua.zt.mezon.graphomania.fsmandstrategydemo

import android.util.Log
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.FSMAction
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm.IStrategyMachine
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm.IStrategyState
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm.StrategyMachineImpl
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.stratm.StrategyStateImpl
import java.lang.ref.WeakReference

/**
 * Created by NickZT on 22.06.2019.
 */
class MainActivityPresenterImpl(mainActivity: MainActivity) : MainActivityPresenter {
    private val mMainActivity: WeakReference<MainActivity>
    private val mStrategyMachine: IStrategyMachine
    private val mStartStateNormal: IStrategyState
    private val mEndStatecharging: IStrategyState
    private val mFsmStateLow: IStrategyState
    private val mFsmStateCritical: IStrategyState
    private var mPercent = 0
    private fun stActionStart() {}
    private fun stActionState3() {}
    private fun stActionState1() {}
    private fun stActionState2() {}
    fun doStActionByPercent(calculateBtDevBattState: Int) {
        mPercent = calculateBtDevBattState
        if (!mStrategyMachine.currentState!!.stateDesc.contentEquals(START)) {
            if (between(mPercent, 0, 3)) {
                mStrategyMachine.setCurrentState(STATE_1)
                //showBtDevBattLevelPercent(mPercent, true);
            } else if (between(mPercent, 4, 20)) {
                mStrategyMachine.setCurrentState(STATE_2)
            } else {
                mStrategyMachine.setCurrentState(STATE_3)
            }
        }
        mStrategyMachine.currentState!!.executeAction()
    }

    fun between(i: Int, minValueInclusive: Int, maxValueInclusive: Int): Boolean {
        return i >= minValueInclusive && i <= maxValueInclusive
    }

    companion object {
        const val START = "start"
        const val STATE_3 = "state3"
        const val STATE_1 = "state1"
        const val STATE_2 = "state2"
        private const val TAG = "PresenterImpl "
    }

    init {
        var tmplink = this::stActionStart
        // todo Create persistent store
        // them init from store or reinit obj from
        //ideology  before push action save them to storage ( with saves context data)
        // state order user story usid checked fulfilled submitted cancelled payed shipped
        mMainActivity = WeakReference(mainActivity)
        mStrategyMachine = StrategyMachineImpl()
        mStartStateNormal = StrategyStateImpl(START)
        mEndStatecharging = StrategyStateImpl(STATE_3)
        mFsmStateLow = StrategyStateImpl(STATE_1)
        mFsmStateCritical = StrategyStateImpl(STATE_2)
        mStartStateNormal.addAction(FSMAction(START, this::stActionStart))
        mEndStatecharging.addAction(FSMAction(STATE_3, this::stActionState3))
        mFsmStateLow.addAction(FSMAction(STATE_1, this::stActionState1))
        mFsmStateCritical.addAction(FSMAction(STATE_2, this::stActionState2))
        mStrategyMachine.setStartState(mStartStateNormal)
        mStrategyMachine.setEndState(mEndStatecharging)
        mStrategyMachine.addState(mFsmStateLow)
        mStrategyMachine.addState(mFsmStateCritical)
        Log.d(TAG, "FSM  MainActivityPresenterImpl"
                + mStrategyMachine.currentState!!.stateDesc)
    }

}