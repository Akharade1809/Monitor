package org.monitor.app.ui.student_home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.monitor.app.ui.theme.medium_gray
import org.monitor.app.ui.theme.light_gray
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.monitor.app.R
import org.monitor.app.ui.theme.AppTheme
import org.monitor.app.ui.theme.baseDark
import org.monitor.app.ui.theme.dim_gray



@Composable
fun CardItemView(
    @DrawableRes icon: Int,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subTitle: String? = null,
    trailingIcon: Int? = null,
    supportingText: String? = null,
) {
    Card(
        border = BorderStroke(2.dp, color = dim_gray),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = baseDark),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = light_gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    supportingText?.let {
                        Text(
                            text = it,
                            color = medium_gray,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Divider(color = medium_gray.copy(alpha = 0.4f))

            subTitle?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it,
                        color = medium_gray,
                        fontSize = 13.sp
                    )
                    trailingIcon?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            tint = medium_gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun CardItemViewPreview(){
    AppTheme {
        CardItemView(
            icon = R.drawable.ic_launcher_foreground,
            title = "CardItemView",
            onClick = {},
            trailingIcon =  R.drawable.ic_launcher_foreground,
            subTitle = "subtitle description"
        )
    }
}
