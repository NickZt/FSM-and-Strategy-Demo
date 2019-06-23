package ua.zt.mezon.graphomania.fsmandstrategydemo;

import android.util.Log;

import java.lang.ref.WeakReference;

import ua.zt.mezon.graphomania.fsmandstrategydemo.stratm.IStrategyMachine;
import ua.zt.mezon.graphomania.fsmandstrategydemo.stratm.IStrategyState;
import ua.zt.mezon.graphomania.fsmandstrategydemo.stratm.StrategyMachineImpl;
import ua.zt.mezon.graphomania.fsmandstrategydemo.stratm.StrategyStateImpl;
import ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm.FSMAction;

/**
 * Created by NickZT on 22.06.2019.
 */
public class MainActivityPresenterImpl  implements MainActivityPresenter {
    private static final String TAG = "PresenterImpl ";
    private final WeakReference<MainActivity> mMainActivity;

    public static final String START = "start";
    public static final String STATE_3 = "state3";
    public static final String STATE_1 = "state1";
    public static final String STATE_2 = "state2";
    private IStrategyMachine mStrategyMachine;
    private IStrategyState mStartStateNormal;
    private IStrategyState mEndStatecharging;
    private IStrategyState mFsmStateLow;
    private IStrategyState mFsmStateCritical;
    private int mPercent;

    public MainActivityPresenterImpl(MainActivity mainActivity) {
        mMainActivity = new WeakReference<MainActivity>(mainActivity);




            mStrategyMachine = new StrategyMachineImpl();
            mStartStateNormal = new StrategyStateImpl(START);
            mEndStatecharging = new StrategyStateImpl(STATE_3);
            mFsmStateLow = new StrategyStateImpl(STATE_1);
            mFsmStateCritical = new StrategyStateImpl(STATE_2);

            mStartStateNormal.addAction(new FSMAction(START, this::stActionStart));
            mEndStatecharging.addAction(new FSMAction(STATE_3, this::stActionState3));
            mFsmStateLow.addAction(new FSMAction(STATE_1, this::stActionState1));
            mFsmStateCritical.addAction(new FSMAction(STATE_2, this::stActionState2));

            mStrategyMachine.setStartState(mStartStateNormal);
            mStrategyMachine.setEndState(mEndStatecharging);
            mStrategyMachine.addState(mFsmStateLow);
            mStrategyMachine.addState(mFsmStateCritical);


        Log.d(TAG, "FSM  MainActivityPresenterImpl"
                    + mStrategyMachine.getCurrentState().getStateDesc());

    }

    private void stActionState2() {

    }

    private void stActionState1() {

    }

    private void stActionState3() {

    }

    private void stActionStart() {

    }
    public void doStActionByPercent(int calculateBtDevBattState) {
        mPercent = calculateBtDevBattState;
        if (!mStrategyMachine.getCurrentState().getStateDesc().contentEquals(START)) {
            if (between(mPercent, 0, 3)) {
               mStrategyMachine.setCurrentState( STATE_1);
                //showBtDevBattLevelPercent(mPercent, true);
            } else if (between(mPercent, 4, 20)) {
                mStrategyMachine.setCurrentState( STATE_2);
            } else {
                mStrategyMachine.setCurrentState( STATE_3);
            }
        }
        mStrategyMachine.getCurrentState().executeAction();
    }
    public  boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        return (i >= minValueInclusive && i <= maxValueInclusive);
    }

}
