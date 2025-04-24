package com.example.fetchrewards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetchrewards.ui.theme.FetchOrange
import com.example.fetchrewards.ui.theme.FetchOrangeLight
import com.example.fetchrewards.ui.theme.FetchPurple
import com.example.fetchrewards.ui.theme.FetchPurpleLight
import com.example.fetchrewards.ui.theme.FetchRewardsTheme
import kotlin.random.Random

@Preview(apiLevel = 34)
@Composable
fun MainScreenPreview(){
    FetchRewardsTheme{
        Surface {
            MainScreen(
                fetchTestData.toList(),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    fetchItems: List<Pair<Int, List<FetchItem>>>?,
    modifier: Modifier
){
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(FetchPurple)
                .fillMaxWidth()
        ){
            Spacer(Modifier.width(5.dp))
            Image(
                painter = painterResource(R.drawable.fetch_logo_outline),
                contentDescription = "",
                modifier = Modifier.padding(5.dp)
            )

            Text(
                text = "Fetch Rewards",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            if(fetchItems != null){
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ){
                    fetchItems.forEach { pair ->
                        stickyHeader(key = pair.first){
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(0.dp),
                                colors = CardDefaults.cardColors(containerColor = FetchOrange)
                            ) {
                                Spacer(Modifier.height(10.dp))
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Spacer(Modifier.width(20.dp))
                                    Text(
                                        text = "List Id ${pair.first}",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = FetchPurple
                                    )
                                }
                                Spacer(Modifier.height(10.dp))
                            }
                        }

                        item{
                            LazyRow(
                                modifier = Modifier.padding(start = 10.dp),
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                items(
                                    items = pair.second,
                                ){ fetchItem ->
                                    GroupItem(fetchItem)
                                }
                            }
                        }
                    }
                }
            }

            else{
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun GroupItem(fetchItem: FetchItem){
    Card(
        modifier = Modifier
            .width(
                width = 250.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row {
                Image(
                    painter = painterResource(R.drawable.shop),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(FetchOrange)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Card(
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = textContainerColor(isSystemInDarkTheme())
                        )
                    ) {
                        Text(
                            text = "${fetchItem.name}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor(isSystemInDarkTheme()),
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Card(
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = textContainerColor(isSystemInDarkTheme())
                        )
                    ) {
                        Text(
                            text = "${fetchItem.id}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor(isSystemInDarkTheme()),
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
            }

            Icon(
                painter = painterResource(R.drawable.unsaved),
                contentDescription = "",
                tint = FetchOrange
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(dividerColor(isSystemInDarkTheme()))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(R.drawable.points),
                modifier = Modifier.size(20.dp),
                contentDescription = "",
                tint = Color.Yellow
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "${Random.nextInt(0, 3000)}"
            )
        }
    }
}

private fun dividerColor(darkTheme : Boolean) : Color =
    if(darkTheme)
        FetchOrange
    else
        FetchPurple

private fun textColor(darkTheme : Boolean) : Color =
    if(darkTheme)
        FetchOrange
    else
        FetchPurple

private fun textContainerColor(darkTheme : Boolean) : Color =
    if(darkTheme)
        FetchOrangeLight
    else
        FetchPurpleLight

