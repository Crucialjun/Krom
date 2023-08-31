package ng.documenti.krom.features.animelist.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AnimeListScreen(){
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "Anime List Screen");
        }
    }

}