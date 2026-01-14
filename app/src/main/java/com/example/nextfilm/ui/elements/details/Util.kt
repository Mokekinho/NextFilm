package com.example.nextfilm.ui.elements.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_ORIGINAL

@Composable
fun ErrorMessage(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
        ,
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
            Button(
                onClick = {
                    onRetry()
                }
            ) {
                Text("Retry")
            }
        }
    }

}

@Composable
fun ImageBox(
    backDropUrl: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ){
        Box(
            modifier = modifier
                .fillMaxWidth()
        ){
            AsyncImage(
                model = BASE_IMAGE_URL + IMAGE_FORMAT_ORIGINAL + backDropUrl,
                contentDescription = "Poster of movie: $name",
                //contentScale = ContentScale.Crop, // util pra cortar, mas aqui to usando o tamano original pra qualidade ficar boa, com o W500 tava meio embaçado
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .matchParentSize() // faz com que fiqeu com o mesmo tamanho do pai, muito util esse aqui
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background,
                            )
                            ,
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            // Essa versão aqui de baixo pode ser util pra deixar o gradiente concentrado mais pro final, mas pelos testes ta legal do jeitoq ue ta por enquanto
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(0.4f)
//                    .align(Alignment.BottomCenter)
//                    .background(
//                        Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Transparent,
//                                MaterialTheme.colorScheme.background
//                            )
//                        )
//                    )
//            )
        }



        Text(
            text = name,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .align(
                    Alignment.BottomStart
                )
        )
    }


}
@Composable
fun DefaultVerticalSpacer(){
    Spacer(
        modifier = Modifier
            .height(5.dp)
    )
}

@Composable
fun DefaultHorizontalSpacer(){
    Spacer(
        modifier = Modifier
            .width(5.dp)
    )
}