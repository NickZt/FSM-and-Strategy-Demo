package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm

import io.reactivex.functions.Action

/**
 * Created by NickZT on 05.06.2019.
 */
class FSMAction {
    var actionName: String
        private set
    var callToExecuteAction: Action?
        private set

    constructor(actionName: String) {
        this.actionName = actionName
        callToExecuteAction = null
    }

    constructor(actionName: String, onExecute: Action?) {
        this.actionName = actionName
        callToExecuteAction = onExecute
    }

    @Throws(Exception::class)
    fun fireAction(): Boolean {
        if (callToExecuteAction != null) {
            callToExecuteAction!!.run()
        }
        return callToExecuteAction != null
    }

    override fun toString(): String {
        return actionName
    }
}