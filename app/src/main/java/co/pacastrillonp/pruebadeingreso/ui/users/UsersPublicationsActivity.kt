package co.pacastrillonp.pruebadeingreso.ui.users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.pacastrillonp.pruebadeingreso.ui.components.EmptyPublicationsText
import co.pacastrillonp.pruebadeingreso.ui.components.Loading
import co.pacastrillonp.pruebadeingreso.ui.components.PublicationItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersPublicationsActivity : ComponentActivity() {

    private val viewModel by viewModel<UserPublicationsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId: Int = intent.getIntExtra("userId", -1)
        val name: String = intent.getStringExtra("name") ?: ""
        val telephoneNumber: String = intent.getStringExtra("telephoneNumber") ?: ""
        val emailAddress: String = intent.getStringExtra("emailAddress") ?: ""

        viewModel.fetchUserPublications(userId, name, telephoneNumber, emailAddress)
        setContent {

            val publications = viewModel.publications.observeAsState(listOf())
            val fetchingData = viewModel.fetchingData.observeAsState(false)

            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (fetchingData.value) {
                    Loading()
                } else {
                    if (publications.value.isEmpty()) {
                        EmptyPublicationsText()
                    } else {

                        Text(name, fontSize = 30.sp)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(telephoneNumber, fontSize = 30.sp)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(emailAddress, fontSize = 30.sp)
                        Spacer(modifier = Modifier.padding(5.dp))
                        LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {
                            itemsIndexed(
                                items = publications.value
                            ) { _, publication ->
                                PublicationItem(userPublicationsPresentable = publication)
                            }
                        }
                    }
                }
            }
        }
    }
}