package com.ralcala.nekoweather.presentation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {

    }

    // Location city, Country

    // Current weather description
    // sun or rain icon, past 6pm moon icon

    // Temperature deg C

    // sunrise sunset

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
//        topBar = topBar,
//        bottomBar = bottomBar,
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .border(2.dp, Color.Blue)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${viewModel.appState.city}, ${viewModel.appState.country}",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sunrise: ${viewModel.appState.sunriseTime}",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Blue
            )
            Text(
                text = "Sunset: ${viewModel.appState.sunsetTime}",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Blue
            )

            Text(
                text = viewModel.appState.currentTemp,
                textAlign = TextAlign.Center,
                fontSize = 80.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Blue
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
//    MainScreen()
}