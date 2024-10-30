package com.nikitayasiulevich.edazavr.presentation.dishes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.nikitayasiulevich.edazavr.domain.model.Dish
import com.nikitayasiulevich.edazavr.domain.model.Restaurant
import com.nikitayasiulevich.edazavr.ui.theme.Gray

@Composable
fun DishesScreen(
    paddingValues: PaddingValues,
    restaurant: Restaurant,
    onMenuClickListener: () -> Unit,
) {
    val viewModel: DishesViewModel = viewModel(
        DishesViewModel(restaurant = restaurant)
    )
    val screenState = viewModel.screenState.observeAsState(DishesScreenState.Initial)

    val currentState = screenState.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .then(Modifier.padding(paddingValues))
    ) { paddingValues ->
        when (currentState) {
            is HomeScreenState.Restaurants -> {
                Column(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        onMenuClickListener()
                    }) {
                        Text(text = "Menu")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Restaurants(
                        paddingValues = paddingValues,
                        viewModel = viewModel,
                        restaurants = currentState.restaurants,
                        onRestaurantClickListener = { restaurant ->
                            viewModel.loadDishes(restaurant)
                        }
                    )
                }
            }

            is HomeScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.DarkGray)
                }
            }

            is HomeScreenState.Dishes -> {
                Column(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        onMenuClickListener()
                    }) {
                        Text(text = "Menu")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Dishes(
                        paddingValues = paddingValues,
                        viewModel = viewModel,
                        dishes = currentState.dishes,
                        onAddButtonListener = { dish ->
                            viewModel.addDishToShoppingCart(dish)
                        },
                        nextDataIsLoading = currentState.nextDataIsLoading,
                    )
                }
            }

            is HomeScreenState.Initial -> {

            }
        }
    }
}

@Composable
fun Restaurants(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    restaurants: List<Restaurant>,
    onRestaurantClickListener: (Restaurant) -> Unit,
) {

    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(
            count = restaurants.size,
            key = { index ->
                val restaurant = restaurants[index]
                "${restaurant.id}${index}"
            }
        ) {
            val restaurant = restaurants[it]
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(color = Gray)
                    .clip(
                        RoundedCornerShape(25.dp)
                    )
                    .clickable { onRestaurantClickListener(restaurants[it]) },
                contentAlignment = Alignment.Center

            ) {
                AsyncImage(
                    model = restaurant.photoUrl,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentDescription = restaurant.name,
                    contentScale = ContentScale.Crop,
                )
            }
            Text(text = "${restaurants[it].name} - ${restaurants[it].address}")

        }
    }
}

@Composable
fun Dishes(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    dishes: List<Dish>,
    onAddButtonListener: (Dish) -> Unit,
    nextDataIsLoading: Boolean
) {

    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(
            count = dishes.size,
            key = { index ->
                val dishes = dishes[index]
                "${dishes.id}${index}"
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Gray)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .clickable { onAddButtonListener(dishes[it]) },
                contentAlignment = Alignment.Center

            ) {
                Text(text = "${dishes[it].name} - ${dishes[it].description}")
            }
        }
    }
}


