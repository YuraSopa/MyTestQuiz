package com.example.mytestquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytestquiz.presentation.CustomDialog
import com.example.mytestquiz.presentation.QuizScreen
import com.example.mytestquiz.presentation.QuizViewModel
import com.example.mytestquiz.ui.theme.MyTestQuizTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<QuizViewModel>()
                    //val openDialog by remember { viewModel.openDialog }

                    QuizScreen()
                    CustomDialog(openDialog = viewModel.openDialog)
                }
            }
        }
    }
}
