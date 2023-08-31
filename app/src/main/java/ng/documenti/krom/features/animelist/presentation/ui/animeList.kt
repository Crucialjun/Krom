package ng.documenti.krom.features.animelist.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ng.documenti.krom.R
import ng.documenti.krom.features.animelist.presentation.viewmodels.AnimeListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AnimeListScreen(){

    val viewModel : AnimeListViewModel = hiltViewModel()
    val state = viewModel.state.value
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Row{
                Column {
                    Text(text = "Hi Susan")
                    Text("Welcome back")
                }
                Image(painter = painterResource(id = R.drawable.avatar), contentDescription = "profile", modifier = Modifier.size(24.dp))


            }
            Text(text = state.animeList.toString());
            Box {
                Row {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                    Text("Search Animes")
                }
            }
        }
    }

}