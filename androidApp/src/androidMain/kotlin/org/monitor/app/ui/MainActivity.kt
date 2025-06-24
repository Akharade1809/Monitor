package org.monitor.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.monitor.app.App
import org.monitor.app.KMPApplication
import org.monitor.app.ui.navigation.Navigation
import org.monitor.app.ui.theme.AppTheme
import org.monitor.shared.base.executor.IExecutorScope

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        KMPApplication.currentActivity = this

        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background){
                    Navigation()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override val vm: Array<IExecutorScope>
        get() = arrayOf()
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}