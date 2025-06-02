package com.example.musicdownloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        setContent {
            MaterialTheme {
                DownloadScreen()
            }
        }
    }
}

@Composable
fun DownloadScreen() {
    var query by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Attendi...") }
    var history by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Inserisci titolo o URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            Button(onClick = {
                val py = Python.getInstance()
                val module = py.getModule("downloader")
                val result = module.callAttr("download_music", query).toString()
                status = result
            }) {
                Text("Scarica")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {
                val py = Python.getInstance()
                val module = py.getModule("downloader")
                val result = module.callAttr("get_history")
                history = result.asList().map { it.toString() }
            }) {
                Text("Cronologia")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(status)

        if (history.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text("Download recenti:", style = MaterialTheme.typography.titleMedium)
            history.reversed().take(10).forEach {
                Text("• $it")
            }
        }
    }
}