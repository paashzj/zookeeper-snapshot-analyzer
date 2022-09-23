package module

class ZooKeeperSummary(
    val znodeCount: Int,
    val dataSizeSum: Long,
    val dataSizeMax: Long,
    val dataSizeAvg: Long,
    val watchCount: Int,
    val znodeMetricList: List<ZnodeMetric>,
)
