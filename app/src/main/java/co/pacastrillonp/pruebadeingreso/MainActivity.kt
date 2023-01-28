package co.pacastrillonp.pruebadeingreso

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.pacastrillonp.pruebadeingreso.ui.components.EmptyListText
import co.pacastrillonp.pruebadeingreso.ui.components.Loading
import co.pacastrillonp.pruebadeingreso.ui.components.SearchText
import co.pacastrillonp.pruebadeingreso.ui.components.UserItem
import co.pacastrillonp.pruebadeingreso.ui.theme.PruebaDeIngresoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainActivityViewModel by viewModels()

        setContent {

            PruebaDeIngresoTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val users = viewModel.users

                    val searchByUserName = remember { mutableStateOf(TextFieldValue("")) }
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (viewModel.fetchingData) {
                            Loading()

                        } else {
                            if (users.isEmpty()) {
                                EmptyListText()
                            } else {
                                SearchText(state = searchByUserName)
                                LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {
                                    itemsIndexed(
                                        items = users
                                    ) { index, user ->
                                        UserItem(user = user, onClick = {

                                            Log.d("🐱‍🚀", "index: $index")
                                            Log.d("🐱‍🚀", "id: $it")
                                            Log.d("🐱‍🚀", "user: $user")

                                        })
                                    }
                                }
                            }
                        }
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
    }
}