package com.example.weatherapp.features.weather.presentation.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.core.wrapper.UIState
import com.example.weatherapp.features.weather.presentation.viewmodel.WeatherViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherLandingScreen() {
    val cityWeatherViewModel = hiltViewModel<WeatherViewModel>()

    // Collect the UI state from the view model
    val uiState by cityWeatherViewModel.uiState.collectAsState()

    //search query
    var citySearchQuery by remember { mutableStateOf("") }


    Scaffold {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.app_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Black overlay with opacity
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        // Your content goes here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start

        ) {
            //30 height space
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Hello, there!",
                color = Color.White,
                fontSize = 32.sp
            )
            Text(
                text = "Check the weather by the city",
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                singleLine = true,
                value = citySearchQuery,
                trailingIcon = {
                    IconButton(onClick = {
                        cityWeatherViewModel.fetchData(citySearchQuery)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "searchIcon",
                            tint = Color.White
                        )
                    }


                },
                onValueChange = {
                    citySearchQuery = it
                },
                label = {
                    Text(
                        text = "Search City",
                        color = Color.White,
                    )
                },
                shape = MaterialTheme.shapes.extraLarge,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                when (val currentState = uiState) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is UIState.Success -> {
                        WeatherInfoDetails(currentState.data)
                    }

                    is UIState.Failure -> {
                        Text(
                            "Error: ${currentState.failure.message}",
                            color = Color.White,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}
