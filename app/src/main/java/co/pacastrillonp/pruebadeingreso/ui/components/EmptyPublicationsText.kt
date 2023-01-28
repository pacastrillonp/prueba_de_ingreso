package co.pacastrillonp.pruebadeingreso.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import co.pacastrillonp.pruebadeingreso.ui.theme.PruebaDeIngresoTheme

@Composable
fun EmptyPublicationsText() {
    Text("The user doesn't have publications", fontSize = 30.sp)
}

@Preview(showBackground = true)
@Composable
fun EmptyPublicationsTextPreview() {
    PruebaDeIngresoTheme {
        EmptyPublicationsText()
    }
}