package ng.documenti.krom.features.animelist.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
            Text(text = state.animeList.toString());
        }
    }

}