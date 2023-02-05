package co.pacastrillonp.pruebadeingreso.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import co.pacastrillonp.pruebadeingreso.ui.users.UsersPublicationsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val users = viewModel.userPresentable.observeAsState(listOf())
            val fetchingData = viewModel.fetchingData.observeAsState(false)
            val searchByUserName = remember { mutableStateOf(TextFieldValue("")) }

            PruebaDeIngresoTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (fetchingData.value) {
                            Loading()
                        } else {
                            if (users.value.isEmpty()) {
                                SearchText(state = searchByUserName)
                                viewModel.searchedItems(searchByUserName.value.text)
                                EmptyListText()
                            } else {
                                SearchText(state = searchByUserName)
                                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                                viewModel.searchedItems(searchByUserName.value.text)
                                LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {

                                    itemsIndexed(
                                        items = users.value
                                    ) { _, user ->

                                        UserItem(userPresentable = user, onClick = {
                                            val intent = Intent(
                                                this@MainActivity,
                                                UsersPublicationsActivity::class.java
                                            ).apply {
                                                putExtra("userId", user.id)
                                                putExtra("name", user.name)
                                                putExtra("telephoneNumber", user.telephoneNumber)
                                                putExtra("emailAddress", user.emailAddress)
                                            }
                                            startActivity(intent)
                                        })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        viewModel.users.observe(this) {
            if (it.isEmpty()) {
                viewModel.fetchUserData()
            } else {
                viewModel.updateUserPresentable(it)
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