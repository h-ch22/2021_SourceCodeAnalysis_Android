package kr.ac.jbnu.moonlader.controller.models

import kotlin.properties.Delegates

object GameStatusChange {
    var listener = ArrayList<refreshGameStatus>()

    var property : String by Delegates.observable(""){property, oldValue, newValue ->
        listener.forEach{
            it.refreshGameStatus()
        }
    }
}