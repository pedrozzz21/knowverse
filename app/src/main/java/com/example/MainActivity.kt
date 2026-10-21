package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.auth.AuthManager
import com.example.data.local.UserProgressRepository
import com.example.data.python.CodeEvaluator
import com.example.data.python.PythonEngine
import com.example.ui.screens.MainAppScreen
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.KnowverseTheme

import com.example.data.local.room.KnowverseDatabase
import com.example.data.local.room.TrailCacheRepository
import com.example.notification.StreakReminderManager

class MainActivity : ComponentActivity() {

    private lateinit var pythonEngine: PythonEngine
    private lateinit var progressRepository: UserProgressRepository
    private lateinit var evaluator: CodeEvaluator
    private lateinit var authManager: AuthManager
    private lateinit var trailCacheRepository: TrailCacheRepository
    private lateinit var streakReminderManager: StreakReminderManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = KnowverseDatabase.getInstance(applicationContext)
        trailCacheRepository = TrailCacheRepository(db.trailCacheDao(), applicationContext)
        streakReminderManager = StreakReminderManager(applicationContext)

        pythonEngine = PythonEngine(applicationContext)
        progressRepository = UserProgressRepository(applicationContext)
        evaluator = CodeEvaluator(pythonEngine)
        authManager = AuthManager(applicationContext)

        setContent {
            KnowverseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DeepNavy
                ) {
                    MainAppScreen(
                        progressRepository = progressRepository,
                        pythonEngine = pythonEngine,
                        evaluator = evaluator,
                        authManager = authManager,
                        trailCacheRepository = trailCacheRepository,
                        streakReminderManager = streakReminderManager
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pythonEngine.destroy()
    }
}
