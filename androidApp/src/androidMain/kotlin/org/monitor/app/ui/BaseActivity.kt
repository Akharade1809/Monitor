package org.monitor.app.ui

import androidx.appcompat.app.AppCompatActivity
import org.monitor.shared.base.executor.IExecutorScope

abstract class BaseActivity : AppCompatActivity(){
    protected abstract val vm : Array<IExecutorScope>

    protected override fun onDestroy() {
        vm.forEach { it.cancel() }
        super.onDestroy()
    }
}