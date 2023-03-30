package edu.uniandes.moni.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.uniandes.moni.R
import edu.uniandes.moni.navigation.AppScreens
import edu.uniandes.moni.viewmodel.logUser


@Composable
fun LogInScreen(navController: NavController) {


    val texts: List<String> = listOf(
        stringResource(R.string.email_text_field),
        stringResource(R.string.password_text_field)
    )
    val images: List<Painter> = listOf(
        painterResource(id = R.drawable.mail),
        painterResource(id = R.drawable.no_see)
    )
    var columns: List<String>

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(contentPadding)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            columns = ColumnWithTextFieldAndTitle("Log In", texts, images)


            Button(
                onClick = {
                    val email: String = columns[0]
                    val password: String = columns[1]
                    logUser(email = email, password = password, navController = navController)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(23, 48, 102)),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .size(300.dp, 40.dp)
            )

            {
                Text(
                    text = "Log In",
                    color = Color.White
                )

            }
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.SignUpScreen.route)

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .size(300.dp, 40.dp)
            )

            {
                Text(
                    text = "Register now",
                    color = Color.White
                )

            }
        }

    }
}





