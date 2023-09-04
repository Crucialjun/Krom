package ng.documenti.krom.features.animelist.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch
import ng.documenti.krom.R
import ng.documenti.krom.features.animelist.presentation.viewmodels.AnimeListViewModel
import ng.documenti.krom.ui.theme.featuredContainer
import ng.documenti.krom.ui.theme.featuredTextColor
import ng.documenti.krom.ui.theme.unFocusedBorder
import ng.documenti.krom.ui.theme.unFocusedContent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun AnimeListScreen(){

    val viewModel : AnimeListViewModel = hiltViewModel()
    val animeListState = viewModel.animeListState.value
    val featuredAnimeState = viewModel.featuredAnimeState.value
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            Button(onClick = {
                coroutineScope.launch {
                    viewModel.refreshList()
                }
            }) {
                Text(text = "Refresh")
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Hi Susan",
                            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        )
                        Text("Welcome back")
                    }
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "profile",
                        modifier = Modifier.size(48.dp)
                    )


                }
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Search Animes...",
                            style = TextStyle(fontSize = 16.sp, color = unFocusedContent)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "search",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = unFocusedBorder,
                        unfocusedLeadingIconColor = unFocusedContent,
                        unfocusedLabelColor = unFocusedContent,
                        focusedLabelColor = unFocusedContent,


                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .background(color = featuredContainer)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Text(
                        "#1 - Featured Anime",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = featuredTextColor
                        )
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = featuredAnimeState.featuredAnime?.title ?: "No Featured Anime",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                )
                Spacer(Modifier.height(4.dp))
                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = featuredContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    GlideImage(
                        model = featuredAnimeState.featuredAnime?.images?.webp?.large_image_url,
                        contentDescription = "featured anime",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Top Rated Animes",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(4.dp))
                if(animeListState.isLoading){
                    Text(text = "Loading...")
                }else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        content = {

                            items(animeListState.animeList.size) { index ->
                                Card(modifier = Modifier
                                    .width(180.dp)
                                    .height(250.dp)) {
                                    BoxWithConstraints {
                                        GlideImage(
                                            model = animeListState.animeList[index].images.webp.large_image_url,
                                            contentDescription = "featured anime",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier

                                                .clip(RoundedCornerShape(16.dp))


                                        )
                                        Card(
                                            shape = RoundedCornerShape(8.dp),
                                            elevation = CardDefaults.cardElevation(
                                                defaultElevation = 8.dp
                                            ),
                                            colors = CardDefaults.cardColors(
                                                containerColor = featuredContainer
                                            ),
                                            modifier = Modifier
                                                .align(Alignment.BottomCenter)
                                                .padding(16.dp)) {
                                            Column(
                                                modifier = Modifier.padding(8.dp)
                                            ) {
                                                Text(
                                                    text = animeListState.animeList[index].title,
                                                    style = TextStyle(
                                                        color = featuredTextColor,
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.Bold
                                                    ),

                                                    )
                                                Text(
                                                    text = animeListState.animeList[index].year.toString(),
                                                    style = TextStyle(
                                                        color = featuredTextColor,
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.Bold
                                                    ),

                                                    )
                                            }
                                        }
                                    }
                                }
                            }

                        })
                }

                


            }
        }
    }

}