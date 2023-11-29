package com.nabilbdev.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nabilbdev.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LemonadeTheme {
                    LemonadeApp()
                }
            }
        }
    }
}


@Composable
fun LemonadeApp() {

    var result by remember { mutableStateOf(1) }
    var squeeze by remember { mutableStateOf(0) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {

        when (result) {
            1 -> {
                LemonadeWithImageAndTxt(
                    imageResource = R.drawable.lemon_tree,
                    stringResource = R.string.tree_msg,
                    contentDescription = R.string.tree_cd,
                    onImageClick = {

                        result = 2
                        squeeze = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonadeWithImageAndTxt(
                    imageResource = R.drawable.lemon_squeeze,
                    stringResource = R.string.lemon_msg,
                    contentDescription = R.string.lemon_cd,
                    onImageClick = {

                        squeeze--
                        if (squeeze == 0) {
                            result = 3
                        }
                    }
                )
            }

            3 -> {
                LemonadeWithImageAndTxt(
                    imageResource = R.drawable.lemon_drink,
                    stringResource = R.string.full_glass_msg,
                    contentDescription = R.string.full_glass_cd,
                    onImageClick = {

                        result = 4
                    }
                )
            }

            4 -> {
                LemonadeWithImageAndTxt(
                    imageResource = R.drawable.lemon_restart,
                    stringResource = R.string.empty_glass_msg,
                    contentDescription = R.string.empty_glass_cd,
                    onImageClick = {

                        result = 1
                    }
                )
            }
        }
    }
}


@Composable
fun LemonadeWithImageAndTxt(
    imageResource: Int,
    stringResource: Int,
    contentDescription: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = stringResource), fontSize = 18.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

