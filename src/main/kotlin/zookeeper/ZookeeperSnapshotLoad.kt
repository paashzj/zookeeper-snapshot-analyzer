package zookeeper

import androidx.compose.runtime.mutableStateOf
import module.ZnodeMetric
import module.ZooKeeperSummary
import org.apache.jute.BinaryInputArchive
import org.apache.jute.InputArchive
import org.apache.zookeeper.server.DataTree
import org.apache.zookeeper.server.persistence.FileSnap
import java.io.File

val dataTree = mutableStateOf(DataTree())
val zookeeperSummary = mutableStateOf(
    ZooKeeperSummary(
        0, 0, 0, 0, 0, emptyList(),
    )
)

fun loadData(path: String) {
    dataTree.value = loadSnapshot(path)
    zookeeperSummary.value = calculateSummary(dataTree.value)
}

fun calculateSummary(dataTree: DataTree): ZooKeeperSummary {
    val znodeCount = dataTree.nodeCount
    val watchCount = dataTree.watchCount
    val znodeMetricList: MutableList<ZnodeMetric> = mutableListOf()
    var bfsList: List<String> = getChildrenFullPath(dataTree, "")
    while (bfsList.isNotEmpty()) {
        val newBfsList: MutableList<String> = mutableListOf()
        for (znodePath in bfsList) {
            try {
                val statNode = dataTree.statNode(znodePath, null)
                znodeMetricList.add(ZnodeMetric(znodePath, statNode.dataLength))
                newBfsList.addAll(getChildrenFullPath(dataTree, znodePath))
            } catch (e: Exception) {
                println("get znode data error: $znodePath")
            }
        }
        bfsList = newBfsList
    }
    var dataSizeSum: Long = 0
    var dataSizeMax: Long = 0
    for (znodeMetric in znodeMetricList) {
        dataSizeSum += znodeMetric.dataSize
        if (znodeMetric.dataSize > dataSizeMax) {
            dataSizeMax = znodeMetric.dataSize.toLong()
        }
    }
    return ZooKeeperSummary(
        znodeCount, dataSizeSum, dataSizeMax, dataSizeSum / znodeCount,
        watchCount, znodeMetricList,
    )
}

fun getChildrenFullPath(dataTree: DataTree, path: String): List<String> {
    val children = dataTree.getChildren(path, null, null)
    return children.map { "$path/$it" }.toList()
}

fun loadSnapshot(filePath: String): DataTree {
    File(filePath).inputStream().use { inputStream ->
        val ia: InputArchive = BinaryInputArchive.getArchive(inputStream)
        val fileSnap = FileSnap(null)
        val dataTree = DataTree()
        val sessionMap = HashMap<Long, Int>()
        fileSnap.deserialize(dataTree, sessionMap, ia)
        return dataTree
    }
}
