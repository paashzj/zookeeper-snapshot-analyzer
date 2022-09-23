package zookeeper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ZookeeperKtTest {

    @Test
    fun test() {
        val resource = this.javaClass.getResource("/snapshot.0")
        Assertions.assertNotNull(resource)
        resource?.let { loadSnapshot(it.path) }
    }
}
