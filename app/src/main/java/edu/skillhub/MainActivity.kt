package edu.skillhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.skillhub.ui.ChatScreen
import edu.skillhub.ui.theme.SkillhubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillhubTheme {
                ChatScreen()
            }
        }
    }
}
