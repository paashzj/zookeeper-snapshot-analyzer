package widget

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun ConfigItemBase(
    inputState: MutableState<String>,
    desc: String,
    verify: (String) -> String,
    errorState: MutableState<String>,
    singleLine: Boolean = false,
) {
    OutlinedTextField(
        value = inputState.value,
        onValueChange = {
            inputState.value = it
            errorState.value = verify(it)
        },
        isError = errorState.value != "",
        label = { Text(if (errorState.value != "") errorState.value else desc) },
        singleLine = singleLine,
    )
}
