package co.pacastrillonp.pruebadeingreso.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.pacastrillonp.pruebadeingreso.ui.components.EmptyPublicationsText
import co.pacastrillonp.pruebadeingreso.ui.components.Loading
import co.pacastrillonp.pruebadeingreso.ui.components.PublicationItem

class UsersPublicationsFragment : Fragment() {

    private val viewModel: UserPublicationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            val user = viewModel.user
            val publications = viewModel.publications

            setContent {
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
                        if (publications.isEmpty()) {
                            EmptyPublicationsText()
                        } else {
                            Text(user.name, fontSize = 30.sp)
                            Spacer(modifier = Modifier.padding(5.dp))
                            Text(user.telephoneNumber, fontSize = 30.sp)
                            Spacer(modifier = Modifier.padding(5.dp))
                            Text(user.emailAddress, fontSize = 30.sp)
                            Spacer(modifier = Modifier.padding(5.dp))
                            LazyColumn(contentPadding = PaddingValues(bottom = 150.dp)) {
                                itemsIndexed(
                                    items = publications
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
}
