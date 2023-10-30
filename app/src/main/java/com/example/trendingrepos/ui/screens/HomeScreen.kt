package com.example.trendingrepos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.trendingrepos.R
import com.example.trendingrepos.model.TrendingRepos


@Composable
fun RepoCard(photo: TrendingRepos, modifier: Modifier = Modifier) {
    AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
        .data(photo.items.firstOrNull()?.owner?.avatarUrl)
        .crossfade(true)
        .build()

        , contentDescription = stringResource(R.string.avatar_photo),

        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        modifier= Modifier.fillMaxWidth()
    )
}

@Composable
fun HomeScreen(
    repoUiState:RepoUiState, modifier: Modifier = Modifier
) {
    when(repoUiState) {
        is RepoUiState.Loading-> LoadingScreen(modifier=modifier.fillMaxSize())
        is RepoUiState.Success-> RepoCard(
            repoUiState.TrendingRepos,modifier=modifier.fillMaxWidth()
        )
        is RepoUiState.Error-> ErrorScreen(modifier=modifier.fillMaxSize()
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
