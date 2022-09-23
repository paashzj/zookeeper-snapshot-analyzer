package constant

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize

class PixelConst {

    companion object {
        val widthDp = Dp(1400f)
        val heightDp = Dp(900f)
        val appSize = DpSize(widthDp, heightDp)
        val configDialogSize = DpSize(widthDp * 2 / 3, heightDp * 3 / 4)
        val configErrorDialogSize = DpSize(widthDp / 3, heightDp / 5)
    }
}
