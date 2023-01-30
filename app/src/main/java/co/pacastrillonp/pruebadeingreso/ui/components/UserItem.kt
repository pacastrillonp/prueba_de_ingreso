package co.pacastrillonp.pruebadeingreso.ui.components

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
import co.pacastrillonp.pruebadeingreso.model.UserPresentable
import co.pacastrillonp.pruebadeingreso.ui.theme.PruebaDeIngresoTheme

@Composable
fun UserItem(userPresentable: UserPresentable, onClick: (Int) -> Unit) {
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
                Text(text = userPresentable.name)
                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                )
                {
                    Icon(Icons.Rounded.Email, contentDescription = "")
                    Text(text = userPresentable.emailAddress)
                }
                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Rounded.Phone, contentDescription = "")
                    Text(text = userPresentable.telephoneNumber)
                }
                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(0.9f))
                    Button(onClick = { onClick(userPresentable.id) }, shape = RectangleShape) {
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
        UserItem(UserPresentable(1, "Pablo Castrillón", "3017209503", "pactres@gmail.com")) { }
    }
}