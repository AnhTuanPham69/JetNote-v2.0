package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnote.domain.model.ColorModel
import com.example.jetnote.util.fromHex


@Composable
fun NoteColor(
    modifier: Modifier = Modifier,
    color: Color,
    size: Dp,
    border: Dp
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color, CircleShape)
            .border(
                BorderStroke(border, SolidColor(Color.Black)),
                CircleShape
            )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteColor() {
    NoteColor(
        color = Color.Black,
        size = 40.dp,
        border = 2.dp
    )
}


@Composable
fun ColorItem(
    color: ColorModel,
    onColorSelect: (ColorModel) -> Unit
){
    Row(
        modifier = Modifier
//            .fillMaxWidth()
            .clickable(onClick = { onColorSelect(color) })
    ){
        NoteColor(modifier=Modifier.padding(10.dp),color = Color.fromHex(color.hex), size = 40.dp, border =2.dp )
    }
}



@Composable
fun ColorPicker(
    colors: List<ColorModel>,
    onColorSelect: (ColorModel) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Color Picker",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        LazyColumn(modifier=Modifier.fillMaxWidth()){
            items(colors.size){
                itemIndex -> val color = colors[itemIndex]
                ColorItem(
                    color= color,
                    onColorSelect=onColorSelect
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PriViewColorPicker(){
    ColorItem(
        color=ColorModel.DEFAULT
    ){}
}