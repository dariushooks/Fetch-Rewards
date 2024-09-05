package com.example.fetchrewards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetchrewards.ui.theme.FetchRewardsTheme

@Preview
@Composable
fun MainScreenPreview(){
    FetchRewardsTheme {
        Surface {
            MainScreen(
                fetchTestData,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(map: Map<Int, List<FetchItem>>, modifier: Modifier){
    val fetchItems = map.toList()
    Column(modifier = modifier) {
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Fetch Rewards",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(Modifier.height(15.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){

            fetchItems.forEach { pair ->
                stickyHeader(key = pair.first){
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    ) {
                        Spacer(Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(Modifier.width(20.dp))
                            Text(
                                text = "List Id ${pair.first}",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }

                items(
                    items = pair.second,
                ){ fetchItem ->
                    val last = pair.second.last()
                    GroupItem(fetchItem, last)
                }
            }
        }
    }
}

@Composable
fun GroupItem(fetchItem: FetchItem, last : FetchItem){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp)
    ){
        Text(
            text = "Name: ${fetchItem.name}",
            fontSize = 18.sp
        )
        Spacer(Modifier.width(20.dp))
        Text(
            text = "Id: ${fetchItem.id}",
            fontSize = 18.sp
        )
    }


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 50.dp,
            end = 15.dp
        )
        .background(Color.DarkGray)
        .height(1.dp)
    )
}