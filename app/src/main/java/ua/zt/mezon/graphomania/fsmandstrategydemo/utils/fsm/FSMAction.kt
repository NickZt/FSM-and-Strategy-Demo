package ua.zt.mezon.graphomania.fsmandstrategydemo.utils.fsm


/**
 * Created by NickZT on 05.06.2019.
 */
class FSMAction {
    var actionName: String
        private set
    var callToExecuteAction: () -> Unit
        private set

    constructor(actionName: String) {
        this.actionName = actionName
        callToExecuteAction = { }
    }

    constructor(actionName: String, onExecute: () -> Unit) {
        this.actionName = actionName
        callToExecuteAction = onExecute
    }

    @Throws(Exception::class)
    fun fireAction(): Boolean {

            callToExecuteAction.invoke()

        return true
    }

    override fun toString(): String {
        return actionName
    }
}