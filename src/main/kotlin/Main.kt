import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import constant.PixelConst
import module.ZnodeMetric
import module.ZooKeeperSummary
import org.apache.zookeeper.server.DataTree
import widget.ConfigItemInt
import widget.ZooKeeperAnalyze
import zookeeper.loadData

@Composable
@Preview
fun App() {
    var editZkSnapshotPath by remember { mutableStateOf("") }

    MaterialTheme {
        Column {
            Row {
                OutlinedTextField(
                    value = editZkSnapshotPath,
                    onValueChange = {
                        editZkSnapshotPath = it
                    },
                    label = { Text("zookeeper snapshot file path") }
                )
                Button(
                    onClick = {
                        loadData(editZkSnapshotPath)
                    },
                    modifier = Modifier.padding(10.dp),
                    content = {
                        Text("Load")
                    }
                )
            }
            ZooKeeperAnalyze()
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = resources.strings.title,
        state = rememberWindowState(size = PixelConst.appSize)
    ) {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            MaterialTheme {
                App()
            }
        }
    }
}
