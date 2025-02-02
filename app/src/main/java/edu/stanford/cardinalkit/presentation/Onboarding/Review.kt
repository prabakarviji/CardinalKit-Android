package edu.stanford.cardinalkit.presentation.Onboarding

import android.widget.ScrollView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import edu.stanford.cardinalkit.presentation.navigation.Screens
import kotlinx.coroutines.CoroutineScope

@Composable
fun Review(
    navController: NavHostController,
)
{

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                navigationIcon={
                    IconButton(onClick={
                        navController.navigate(Screens.OnboardingScreen.route)
                    }){
                        Icon(Icons.Filled.ArrowBack, "back Icon")
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp
            )
        },
        content = {
            Column(modifier= Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp)
            ){
                Text(
                    text="Review",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Light
                )
                Column(modifier= Modifier
                    .fillMaxSize()){
                    DisplayList(
                        items = data)
                    Box(modifier= Modifier
                        .fillMaxSize()
                        .fillMaxHeight(0.67f)){
                        GetStartedButton(
                            modifier = Modifier.align(Alignment.Center),
                        ) {
                            navController.navigate(Screens.LoginScreen.route)
                        }
                    }


                }
            }
        }
    )
}

@Composable
fun ReviewScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            fontSize=16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.description,
            fontSize=16.sp,
            textAlign = TextAlign.Left
        )
    }

}
@Composable
fun GetStartedButton(
    modifier: Modifier,
    onClick: () -> Unit
){
    Row(
        modifier = modifier.padding(horizontal = 50.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
    ) {
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Gray,
                    backgroundColor = Color.LightGray
                )
            ) {
                Text(
                    text = "Continue to Sign In",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 30.dp)
                )

        }
    }

}

private val data= listOf(
    OnBoardingPage.First,
    OnBoardingPage.Second,
    OnBoardingPage.Third,
    OnBoardingPage.Fourth
)
@Composable
fun DisplayList(items: List<OnBoardingPage>) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier,
        state = listState) {
        items(items){
            item -> ReviewScreen(onBoardingPage = item)
        }


    }


}
