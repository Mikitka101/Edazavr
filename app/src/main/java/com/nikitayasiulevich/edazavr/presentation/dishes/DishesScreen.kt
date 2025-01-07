package com.nikitayasiulevich.edazavr.presentation.dishes

import android.app.Application
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
import com.nikitayasiulevich.edazavr.ui.theme.Gray

@Composable
fun DishesScreen(
    restaurantId: String,
    application: Application,
    onMenuClickListener: () -> Unit,
) {
    val viewModel: DishesViewModel = viewModel(
        factory = DishesViewModelFactory(restaurantId, application)
    )

    val screenState = viewModel.screenState.observeAsState(DishesScreenState.Initial)

    val currentState = screenState.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()

    ) { paddingValues ->
        when (currentState) {
            is DishesScreenState.Dishes -> {
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
                        dishes = currentState.dishes,
                        onDishAddClickListener = { dish ->
                            //viewModel.loadDishes(dish)
                        }
                    )
                }
            }

            is DishesScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.DarkGray)
                }
            }

            is DishesScreenState.Initial -> {

            }
        }
    }
}

@Composable
fun Dishes(
    paddingValues: PaddingValues,
    dishes: List<Dish>,
    onDishAddClickListener: (Dish) -> Unit,
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
                val dish = dishes[index]
                "${dish.id}${index}"
            }
        ) {
            val dish = dishes[it]
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(color = Gray)
                    .clip(
                        RoundedCornerShape(25.dp)
                    )
                    .clickable { onDishAddClickListener(dishes[it]) },
                contentAlignment = Alignment.Center

            ) {
                AsyncImage(
                    model = dish.photoUrl,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentDescription = dish.name,
                    contentScale = ContentScale.Crop,
                )
            }
            Text(text = "${dishes[it].name} - ${dishes[it].description}")

        }
    }
}


