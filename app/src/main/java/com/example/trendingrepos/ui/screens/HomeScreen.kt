@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.trendingrepos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.trendingrepos.R
import com.example.trendingrepos.model.TrendingRepos
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

private const val TAG = "HomeScreen"

@Composable

fun RepoCard(trendingRepos: TrendingRepos, modifier: Modifier = Modifier) {
    val searchText = remember { mutableStateOf("") }

    LazyColumn(modifier = modifier) {
        item {
            // Search bar
            TextField(
                value = searchText.value,
                onValueChange = { newText ->
                    searchText.value=newText
                },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        val filteredRepos = trendingRepos.items.filter { repo ->
            // Filter the items based on the search query
            repo.fullName.contains(searchText.value, ignoreCase = true)
        }

        items(filteredRepos) { repo ->
            // Existing code to display each repo
            val isSelected=remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {
                       isSelected.value=!isSelected.value
                    }
                    .background(if (isSelected.value) Color.Gray else Color.Transparent),

                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display the full name and description on the left
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = repo.fullName, fontWeight = FontWeight.Bold)
                    Text(text = repo.description.orEmpty())
                }

                // Display the avatar image on the right
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(repo.owner?.avatarUrl)
                        .crossfade(true)
                        .build(), contentDescription = stringResource(R.string.avatar_photo),

                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    modifier = Modifier.size(72.dp) // You can adjust the size as needed
                )
            }
        }
    }
}

//fun RepoCard(trendingRepos: TrendingRepos, modifier: Modifier = Modifier) {
//    LazyColumn(modifier = modifier) {
//        items(trendingRepos.items) { repo ->
//            Row(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                // Display the full name and description on the left
//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Text(text = repo.fullName, fontWeight = FontWeight.Bold)
//                    Text(text = repo.description.orEmpty())
//                }
//
//                // Display the avatar image on the right
//                AsyncImage(
//                    model = ImageRequest.Builder(context = LocalContext.current)
//                        .data(repo.owner?.avatarUrl)
//                        .crossfade(true)
//                        .build(), contentDescription = stringResource(R.string.avatar_photo),
//
//                    error = painterResource(R.drawable.ic_broken_image),
//                    placeholder = painterResource(R.drawable.loading_img),
//                    modifier = Modifier.size(72.dp) // You can adjust the size as needed
//                )
//            }
//        }
//    }
//}

//fun RepoCard(photo: String?, modifier: Modifier = Modifier) {
//    AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
//        .data(photo)
//        .crossfade(true)
//        .build()
//
//        , contentDescription = stringResource(R.string.avatar_photo),
//
//        error = painterResource(R.drawable.ic_broken_image),
//        placeholder = painterResource(R.drawable.loading_img),
//        modifier= Modifier.fillMaxWidth()
//    )
//}

@Composable
fun HomeScreen(
    repoUiState: RepoUiState, modifier: Modifier = Modifier
) {
    when (repoUiState) {
        is RepoUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
//        is RepoUiState.Success-> RepoCard(
//            repoUiState.TrendingRepos,modifier=modifier.fillMaxWidth()
//        )
        is RepoUiState.Success -> {
            // Extract the list of Repos from the TrendingRepos
            RepoCard(repoUiState.TrendingRepos)

        }

        is RepoUiState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize()
        )
    }

}

@Composable
fun ErrorScreen(modifier: Modifier) {
    // Columns is used to position welements vertically
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}


@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ResultScreenPreview() {
//    MarsPhotosTheme {
//        ResultScreen(stringResource(R.string.placeholder_result))
//    }
//}
