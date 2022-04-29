package com.suromo.magic.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.ui.components.MagicIcon
import com.suromo.magic.ui.components.NavigationIcon
import com.suromo.magic.ui.theme.MagicTheme

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   :
 */
@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateToMessage: () -> Unit,
    navigateToCollection: () -> Unit,
    navigateToDisclaimer: () -> Unit,
    navigateToSetting: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        MagicLogo(Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = Icons.Filled.Home,
            label = stringResource(id = R.string.home),
            isSelected = currentRoute == MainDestinations.HOME_ROUTE,
            action = {
                navigateToHome()
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Filled.PersonPin,
            label = stringResource(id = R.string.about_me),
            isSelected = currentRoute == MainDestinations.ABOUT_ROUTE,
            action = {
                navigateToAbout()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Filled.Message,
            label = stringResource(id = R.string.leave_me_message),
            isSelected = currentRoute == MainDestinations.ABOUT_ROUTE,
            action = {
                navigateToMessage()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Filled.Collections,
            label = stringResource(id = R.string.collection),
            isSelected = currentRoute == MainDestinations.ABOUT_ROUTE,
            action = {
                navigateToCollection()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Filled.FileCopy,
            label = stringResource(id = R.string.disclaimer),
            isSelected = currentRoute == MainDestinations.DISCLAIMER_ROUTE,
            action = {
                navigateToDisclaimer()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Filled.Settings,
            label = stringResource(id = R.string.setting),
            isSelected = currentRoute == MainDestinations.ABOUT_ROUTE,
            action = {
                navigateToSetting()
                closeDrawer()
            }
        )
    }
}

@Composable
private fun MagicLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        MagicIcon()
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.ic_magic_wordmark),
            contentDescription = stringResource(R.string.app_name),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
        )
    }
}

@Composable
private fun DrawerButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationIcon(
                    icon = icon,
                    isSelected = isSelected,
                    contentDescription = null, // decorative
                    tintColor = textIconColor
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    MagicTheme {
        Surface {
            AppDrawer(
                currentRoute = MainDestinations.HOME_ROUTE,
                navigateToHome = {},
                navigateToAbout = {},
                navigateToMessage = {},
                navigateToCollection = {},
                navigateToDisclaimer = {},
                navigateToSetting = {},
                closeDrawer = { }
            )
        }
    }
}