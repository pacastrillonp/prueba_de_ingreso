package co.pacastrillonp.pruebadeingreso.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.pacastrillonp.pruebadeingreso.ui.theme.PruebaDeIngresoTheme

@Composable
fun SearchText(state: MutableState<TextFieldValue>) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = Color.Black
            )
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = state.value,
            onValueChange = { state.value = it },
            modifier = Modifier
                .background(Color.White, CircleShape)
                .height(40.dp)
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.value == TextFieldValue("")) Text(
                            "Search user"
                        )
                        innerTextField()
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextPreview() {
    PruebaDeIngresoTheme {
        SearchText(remember { mutableStateOf(TextFieldValue("")) })
    }
}