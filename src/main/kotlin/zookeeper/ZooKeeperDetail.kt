package zookeeper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.apache.zookeeper.data.Stat

val editDetailPath = mutableStateOf("/")
val detailData = mutableStateOf("")
val detailChildren = mutableStateOf(emptyList<String>())

@Composable
fun ZooKeeperDetail() {
    Column {
        Row {
            OutlinedTextField(
                value = editDetailPath.value,
                onValueChange = {
                    editDetailPath.value = it
                },
                label = { Text("zookeeper detail path") }
            )
            Button(
                onClick = {
                    detailChildren.value = dataTree.value.getChildren(editDetailPath.value, null, null)
                    try {
                        detailData.value = String(dataTree.value.getData(editDetailPath.value, Stat(), null))
                    } catch (e: Exception) {
                        println("get data error")
                    }
                },
                modifier = Modifier.padding(10.dp),
                content = {
                    Text("confirm")
                }
            )
        }
        Column {
            Text("data", fontSize = 30.sp)
            Text(detailData.value)
        }
        Text("children", fontSize = 30.sp)
        repeat(detailChildren.value.size) {
            Text(detailChildren.value[it])
        }
    }
}
