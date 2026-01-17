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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nextfilm.data.sources.remote.responses.details.movie.Cast
import com.example.nextfilm.ui.theme.Blue
import com.example.nextfilm.ui.theme.Green
import com.example.nextfilm.ui.theme.Red
import com.example.nextfilm.ui.theme.Yellow
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_ORIGINAL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_W500
import java.util.Locale

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



@Composable
fun CastCard(
    cast: Cast,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxSize()
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()

            ,
            contentAlignment = Alignment.Center
        ){

            AsyncImage(
                model = BASE_IMAGE_URL + IMAGE_FORMAT_W500 + cast.profile_path,
                contentDescription = "Poster of movie: ${cast.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = cast.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = cast.character,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }

    }
}


@Composable
fun VoteAverageBox(
    voteAverage : Float,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    modifier: Modifier = Modifier
) {

    val progress = (voteAverage / 10f).coerceIn(0f, 1f)
    val color = when{
        voteAverage >= 8.5f -> Blue
        voteAverage >= 7f -> Green
        voteAverage >= 5f -> Yellow
        else -> Red
    }

    Box(
        modifier = modifier
            .fillMaxSize()
        ,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            progress = {progress},
            color = color,
            strokeWidth = strokeWidth, // faz ele ficar mas gordinho
            modifier = Modifier.fillMaxSize(),
            trackColor = Color.Transparent,
        )
        Text(
            text = String.format(Locale.getDefault(),"%.1f", voteAverage),
            style = MaterialTheme.typography.bodyLarge
        )

    }

}