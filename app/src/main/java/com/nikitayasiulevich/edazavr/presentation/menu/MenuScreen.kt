package com.nikitayasiulevich.edazavr.presentation.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikitayasiulevich.edazavr.presentation.commons.NavigationItem
import com.nikitayasiulevich.edazavr.ui.theme.LightGray

@Composable
fun MenuScreen(
    userId: String,
    onBackPressed: () -> Unit,
    onItemClickListener: (NavigationItem, String) -> Unit,
    menuItems: List<NavigationItem>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .size(50.dp),
                shape = RoundedCornerShape(15.dp),
                containerColor = LightGray,
                onClick = {
                    onBackPressed()
                }
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back button"
                )
            }
            Row(
                modifier = Modifier
                    .width(300.dp)
                    .height(56.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Menu - $userId",
                    fontSize = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .size(50.dp),
                shape = RoundedCornerShape(15.dp),
                containerColor = LightGray,
                onClick = {
                    onItemClickListener(menuItems[0], userId)
                }
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "profile"
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(count = menuItems.size - 1,
                key = { index ->
                    index
                }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClickListener(menuItems[it + 1], userId)
                        }
                ) {
                    Text(text = stringResource(id = menuItems[it + 1].titleResId))
                }
            }
        }
    }
}