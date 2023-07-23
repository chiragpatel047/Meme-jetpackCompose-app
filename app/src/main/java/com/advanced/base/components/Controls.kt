package com.advanced.base.components

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.drawable.Icon
import android.graphics.drawable.PaintDrawable
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberImagePainter
import com.advanced.base.R
import com.advanced.base.models.MemeModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun Controls(title: String, url: String, loadNewMeme: () -> Unit, context: Context) {

    Column() {
        val localContext = LocalContext.current
        Image(
            painter = rememberImagePainter(url),
            contentDescription = "",
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(15.dp)
                .wrapContentHeight()
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Row() {
                Column(

                    modifier = Modifier
                        .weight(1f)
                        .shadow(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable(true) {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, url)
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, "Share via")
                            localContext.startActivity(shareIntent)
                        }
                        .padding(10.dp)

                ) {
                    Image(
                        painter = painterResource(R.drawable.share),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(3.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Share",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(0.dp, 5.dp, 0.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable(true) { downlaodMeme(url, context) }
                        .padding(10.dp)

                ) {
                    Image(
                        painter = painterResource(R.drawable.download_icon),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(3.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Download",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(0.dp, 5.dp, 0.dp, 0.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable(true) { loadNewMeme.invoke() }
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.next),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                            .rotate(270f)
                    )
                    Text(
                        text = "Next",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(0.dp, 5.dp, 0.dp, 0.dp)
                    )
                }
            }
        }
    }
}

fun downlaodMeme(url: String, context: Context) {
    val request = DownloadManager.Request(Uri.parse(url))
        .setNotificationVisibility(
            DownloadManager.Request.VISIBILITY_VISIBLE
                    or DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
        )
        .setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_MOBILE
                    or DownloadManager.Request.NETWORK_WIFI
        )
        .setTitle("Meme")
        .setDescription("Donwloading meme")
        .setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            System.currentTimeMillis().toString() + ".jpg"
        )

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)


}


fun share(url: String, context: Context) {

}