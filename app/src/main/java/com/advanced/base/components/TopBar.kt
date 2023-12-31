package com.advanced.base.components

import android.graphics.drawable.ShapeDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.advanced.base.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun topBar() {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clip(RoundedCornerShape(0.dp,0.dp,50.dp,50.dp))
            .padding(15.dp)
            .wrapContentHeight()
            .fillMaxWidth()

    ) {
        Text(
            text = "Memes",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )
    }
}