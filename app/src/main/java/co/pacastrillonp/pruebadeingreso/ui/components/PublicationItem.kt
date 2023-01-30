package co.pacastrillonp.pruebadeingreso.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.pacastrillonp.pruebadeingreso.model.UserPublicationsPresentable
import co.pacastrillonp.pruebadeingreso.ui.theme.PruebaDeIngresoTheme

@Composable
fun PublicationItem(userPublicationsPresentable: UserPublicationsPresentable) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            )
            {
                Text(text = userPublicationsPresentable.publication)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PublicationItemPreview() {
    PruebaDeIngresoTheme {
        PublicationItem(
            UserPublicationsPresentable(
                "PabloCastrillon",
                "3017209503",
                "pactres@gmail.com",
                "Al seleccionar el botón “Ver publicaciones” se debe mostrar el nombre, email, teléfono y publicaciones"
            )
        )
    }
}
