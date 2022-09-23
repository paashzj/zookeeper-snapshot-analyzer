package widget

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import module.AnalyzeEnum
import widget.component.RowPaddingButton
import zookeeper.ZooKeeperDetail
import zookeeper.ZooKeeperSummary

val idx = mutableStateOf(AnalyzeEnum.Summary)

@Composable
fun ZooKeeperAnalyze() {
    Head(idx)
    when (idx.value) {
        AnalyzeEnum.Summary -> {
            ZooKeeperSummary()
        }
        AnalyzeEnum.Detail -> {
            ZooKeeperDetail()
        }
    }
}

@Composable
fun Head(idx: MutableState<AnalyzeEnum>) {
    Row {
        RowPaddingButton(
            onClick = {
                idx.value = AnalyzeEnum.Summary
            },
        ) {
            Text(text = "summary")
        }
        RowPaddingButton(
            onClick = {
                idx.value = AnalyzeEnum.Detail
            },
        ) {
            Text(text = "detail")
        }
    }
}
