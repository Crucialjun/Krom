package ng.documenti.krom.features.uploads.presentation.ui

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ng.documenti.krom.common.utils.AppUtils
import ng.documenti.krom.features.uploads.presentation.viewmodels.UploadViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun UploadsScreen() {
    val viewModel: UploadViewModel = hiltViewModel()
    val context = LocalContext.current
    val searchState = viewModel.searchWithImageState
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                viewModel.updateImageToSearch(uri);
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Button(onClick = {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }) {
                Text(
                    "Select an image to search",
                    style = TextStyle(fontSize = 20.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        color = Color.White))
            }
            AnimatedVisibility(visible = viewModel.imageToSearch != Uri.EMPTY ) {
                Column {
                    AsyncImage(
                        viewModel.imageToSearch,
                        contentDescription = "Selected image",
                        modifier = Modifier.height(150.dp)
                    )

                    Button(onClick = {
                        val bytes = AppUtils.readBytes(context,viewModel.imageToSearch)
                        viewModel.searchWithImage(bytes!!)
                    }) {
                        Text(text = "Search", style = TextStyle(fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = Color.White))
                    }
                }
            }

            Text("Results ", style = TextStyle(fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
            Spacer(modifier =Modifier.height(16.dp))

            when {
                searchState.value.isLoading -> {
                    Text(text = "Loading", style = TextStyle(fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
                }
                searchState.value.error.isNotBlank() -> {
                    Text(text = searchState.value.error, style = TextStyle(fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
                }

                searchState.value.searchWithImage?.isNotEmpty() == true -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            items(searchState.value.searchWithImage?.size ?: 0) { index ->
                                val item = searchState.value.searchWithImage?.get(index)
                                Card(
                                    colors = CardDefaults.cardColors(
                                    ),
                                    ){
                                    Text(text = item?.anilist?.title.toString() ?: "No title",style = TextStyle(fontSize = 14.sp) , modifier = Modifier.padding(8.dp))
                                }
                            }
                        })
                }
            }


        }
    }

}