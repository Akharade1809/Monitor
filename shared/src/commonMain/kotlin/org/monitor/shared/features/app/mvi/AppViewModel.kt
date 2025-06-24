package org.monitor.shared.features.app.mvi

import org.monitor.shared.base.mvi.BaseViewModel
import org.monitor.shared.domain.entities.AppViewModelInfo
import org.monitor.shared.features.AppContract

open class AppViewModel : BaseViewModel<AppContract.Event, AppContract.State, AppContract.Effect>() {


    override fun createInitialState(): AppContract.State {

        println("AppViewModel : creating init state ")
        return AppContract.State(AppViewModelInfo())
    }

    override fun handleEvent(event: AppContract.Event) {
        //TODO
    }

}