package co.pacastrillonp.pruebadeingreso.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.pacastrillonp.pruebadeingreso.model.User
import co.pacastrillonp.pruebadeingreso.ui.theme.PruebaDeIngresoTheme

@Composable
fun UserItem(user: User, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Surface() {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            )
            {
                Text(text = user.name)
                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                )
                {
                    Icon(Icons.Rounded.Email, contentDescription = "")
                    Text(text = user.emailAddress)
                }
                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Rounded.Phone, contentDescription = "")
                    Text(text = user.telephoneNumber)
                }
                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(0.9f))
                    Button(onClick = { onClick(user.id) }, shape = RectangleShape) {
                        Text(text = "Get Information")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PruebaDeIngresoTheme {
        UserItem(User(1, "Pablo Castrillón", "3017209503", "pactres@gmail.com")) { }
    }
}