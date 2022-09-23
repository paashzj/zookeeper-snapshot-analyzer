package zookeeper

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import module.ZnodeMetric
import widget.ConfigItemInt

val editZnodeSizeTopNum = mutableStateOf("10")
val topSizeZnodeList = mutableStateOf(emptyList<ZnodeMetric>())

@Composable
fun ZooKeeperSummary() {
    Row {
        Text("znode count: ${zookeeperSummary.value.znodeCount}")
    }
    Row {
        Text("data size sum: ${zookeeperSummary.value.dataSizeSum}")
    }
    Row {
        Text("data size max: ${zookeeperSummary.value.dataSizeMax}")
    }
    Row {
        Text("data size avg: ${zookeeperSummary.value.dataSizeAvg}")
    }
    Row {
        Text("watch count: ${zookeeperSummary.value.watchCount}")
    }
    Row {
        Text("show top size znodes", fontSize = 30.sp)
        ConfigItemInt(
            editZnodeSizeTopNum,
            "",
            mutableStateOf(""),
        )
        Button(
            onClick = {
                val topNum = editZnodeSizeTopNum.value.toInt()
                zookeeperSummary.value.znodeMetricList
                    .sortedByDescending { it.dataSize }
                    .take(topNum)
                    .let {
                        topSizeZnodeList.value = it
                    }
            },
            modifier = Modifier.padding(10.dp),
            content = {
                Text("show")
            }
        )
    }
    repeat(topSizeZnodeList.value.size) { index ->
        val znodeMetric = topSizeZnodeList.value[index]
        Row {
            Text("${znodeMetric.path} ${znodeMetric.dataSize}")
        }
    }
}
