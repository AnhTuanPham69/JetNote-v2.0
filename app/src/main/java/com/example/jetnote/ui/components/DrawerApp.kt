package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import com.example.jetnote.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.jetnote.routing.Screen
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.routing.JetNotesRouter
import com.example.jetnote.theme.JetNotesThemeSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppDrawerHeader(){
    Row(modifier= Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.Filled.Menu, contentDescription = "Drawer Header Icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Trucker",
            modifier=Modifier.align(Alignment.CenterVertically)

        )
    }
}


@Composable
private fun LightDarkTheme(){
    Row(){
        Text(
            text = "Turn on dark theme",
            style = MaterialTheme.typography.body2,
            color= MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
        Switch(
            checked = JetNotesThemeSettings.isDarkThemeEnabled,
            onCheckedChange = {
                JetNotesThemeSettings.isDarkThemeEnabled=it
            },
            modifier= Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}


@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected:Boolean,
    onClick:()->Unit
){
    val colors=MaterialTheme.colors
    val imageAlpha= if(isSelected){
        1f
    }else{
        0.6f
    }

    val textColor = if(isSelected){
        colors.primary
    } else{
        colors.onSurface.copy(alpha=0.6f)
    }

    val backgroundColor= if(isSelected){
        colors.primary.copy(alpha= 0.12f)
    } else {
        colors.surface
    }

    Surface(
        modifier= Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color=backgroundColor,
        shape= MaterialTheme.shapes.small
    ){
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        ){
            Image(
                imageVector=icon,
                contentDescription="Screen Navigation Button",
                colorFilter=ColorFilter.tint(textColor),
                alpha=imageAlpha
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text =label,
                style = MaterialTheme.typography.body2,
                color=textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



@Composable
fun AppDrawer(
    currentScreen: Screen,
    closeDrawerAction:()->Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppDrawerHeader()
        Divider(color= MaterialTheme.colors.onSurface.copy(alpha=.2f))
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Hi, My name is Tuan",
            isSelected = currentScreen=== Screen.Notes,
            onClick = {
                JetNotesRouter.navigateTo(Screen.Notes)
                closeDrawerAction()
            }
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Delete  ,
            label = "Trash",
            isSelected = currentScreen=== Screen.Trash,
            onClick = {
                JetNotesRouter.navigateTo(Screen.Trash)
                closeDrawerAction()
            }
        )
        LightDarkTheme()
    }

}
@Preview(showBackground = true)
@Composable
fun AppPreviewer(){
    AppDrawer(Screen.Notes,{})
}

@Composable
fun TopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    val drawerState = scaffoldState.drawerState
    TopAppBar(
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        Icons.Default.Menu,
                        tint = Color.White,
                        contentDescription = stringResource(id = R.string.app_name)
                    )
                },
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) drawerState.open()
                        else drawerState.close()
                    }
                }
            )
        },
        title = { Text(text = stringResource(id = R.string.app_name), color = Color.White) },
        backgroundColor = colorResource(id = R.color.colorPrimary)
    )
}
