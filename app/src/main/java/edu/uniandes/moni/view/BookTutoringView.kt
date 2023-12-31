package edu.uniandes.moni.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.monitores.TitleWithButtons
import edu.uniandes.moni.model.dto.TutoringDTO
import edu.uniandes.moni.navigation.AppScreens
import edu.uniandes.moni.view.components.*
import edu.uniandes.moni.view.theme.moniFontFamily
import edu.uniandes.moni.viewmodel.SessionViewModel
import edu.uniandes.moni.viewmodel.TutoringViewModel
import edu.uniandes.moni.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Timestamp

@Composable
fun BookTutoringScreen(
    navController: NavController,
    id: String,
    tutoringTitle: String?,
    description: String?,
    rate: String?,
    tutorEmail: String?,
    tutoringViewModel: TutoringViewModel,
    sessionViewModel: SessionViewModel

) {


    tutoringViewModel.getTutoringById(id)
    val tutoria: TutoringDTO = TutoringViewModel.getOneTutoring()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TitleWithButtons("Book", false, false) },
        bottomBar = { }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .padding(15.dp)
        ) {
            if (tutoringTitle != null && description != null && rate != null && id.isNotBlank()) {
                var commentary = ""
                var date = ""
                var time = ""
                var place = ""

                var pressedButton = false
                val filledCommentary = remember { mutableStateOf(true) }
                val filledDate = remember { mutableStateOf(true) }
                val filledTime = remember { mutableStateOf(true) }
                val filledPlace = remember { mutableStateOf(true) }

                LazyColumn() {
                    item {
                        TutoringDescription(tutoringTitle, description)
                    }

                    item {

                        Column(
                            modifier = Modifier.padding(
                                bottom = 40.dp,
                                start = 10.dp,
                                end = 10.dp
                            )
                        ) {
                            Text(
                                text = "Commentaries for the tutor",
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontFamily = moniFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(20.dp)
                            )
                            InputText("I'd like to learn about...", "", commentary) {
                                commentary = it
                                if (pressedButton)
                                    filledCommentary.value = it.isNotBlank()

                            }
                            if (!filledCommentary.value) {
                                Text(
                                    text = "Please fill the commentary",
                                    style = TextStyle(textDecoration = TextDecoration.Underline),
                                    color = Color.Red,
                                    fontFamily = moniFontFamily,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }
                    }

                    item {

                        Column(
                            modifier = Modifier.padding(
                                bottom = 40.dp,
                                start = 10.dp,
                                end = 10.dp
                            )
                        ) {
                            Text(
                                text = "Hourly rate",
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontFamily = moniFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(20.dp)
                            )
                            Text(
                                text = rate,
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontFamily = moniFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(20.dp)
                            )

                        }
                    }

                    item {

                        Column(
                            modifier = Modifier.padding(
                                bottom = 40.dp,
                                start = 10.dp,
                                end = 10.dp
                            )
                        ) {
                            Text(
                                text = "Date",
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontFamily = moniFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(20.dp)
                            )
                            NewDatePicker(date) {
                                date = it
                                if (pressedButton)
                                    filledDate.value = it.isNotBlank()

                            }
                            if (!filledDate.value) {
                                Text(
                                    text = "Please fill the date",
                                    style = TextStyle(textDecoration = TextDecoration.Underline),
                                    color = Color.Red,
                                    fontFamily = moniFontFamily,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }

                        }
                    }


                    item {

                        Column(
                            modifier = Modifier.padding(
                                bottom = 40.dp,
                                start = 10.dp,
                                end = 10.dp
                            )
                        ) {
                            Text(
                                text = "Time",
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontFamily = moniFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(20.dp)
                            )
                            NewTimePicker(time) {
                                time = it
                                if (pressedButton)
                                    filledTime.value = it.isNotBlank()
                            }
                            if (!filledTime.value) {
                                Text(
                                    text = "Please fill the time",
                                    style = TextStyle(textDecoration = TextDecoration.Underline),
                                    color = Color.Red,
                                    fontFamily = moniFontFamily,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }
                    }

                    item {

                        Column(
                            modifier = Modifier.padding(
                                bottom = 40.dp,
                                start = 10.dp,
                                end = 10.dp
                            )
                        ) {
                            Text(
                                text = "Place",
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontFamily = moniFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(20.dp)
                            )
                            InputText("Describe the place", "", place) {
                                place = it
                                if (pressedButton)
                                    filledPlace.value = it.isNotBlank()
                            }
                            if (!filledPlace.value) {
                                Text(
                                    text = "Please fill the place",
                                    style = TextStyle(textDecoration = TextDecoration.Underline),
                                    color = Color.Red,
                                    fontFamily = moniFontFamily,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }

                        }
                    }

                    if (tutoria.tutorEmail != UserViewModel.getUser().email) {
                        item {
                            var completed = remember { mutableStateOf(100) }
                            MainButton(text = "Confirm") {
                                pressedButton = true

                                if (commentary.isBlank() || date.isBlank() || time.isBlank() || place.isBlank()) {
                                    if (commentary.isBlank()) {
                                        filledCommentary.value = false
                                    }
                                    if (date.isBlank()) {
                                        filledDate.value = false
                                    }
                                    if (time.isBlank()) {
                                        filledTime.value = false
                                    }
                                    if (place.isBlank()) {
                                        filledPlace.value = false
                                    }
                                } else {
                                    if (tutorEmail != null) {
                                        val dateS = date.split("/")
                                        val day = dateS[0].toInt()
                                        val month = dateS[1].toInt()
                                        val year = dateS[2].toInt()

                                        val timeS = time.split(":")
                                        val hour = timeS[0].toInt()
                                        val min = timeS[1].toInt()


                                        val meetingPlace =
                                            Timestamp(year, month, day, hour, min, 0, 0)
                                        Log.d("ASSEr", meetingPlace.toString())
                                        sessionViewModel.addSession(
                                            UserViewModel.getUser().email,
                                            meetingPlace,
                                            place,
                                            tutorEmail,
                                            id
                                        ){
                                            completed.value = it
                                        }
                                    }
                                }

                            }

                            if(completed.value == 0) {
                                CreateDialog("Session", "Your session have been created successfully") {
                                    completed.value = 10000
                                    navController.navigate(route = AppScreens.MarketScreen.route)
                                }
                            }
                            else if(completed.value == 1) {
                                CreateDialog("Something went wrong", "The session couldn't be saved") {
                                    completed.value = 10000
                                }
                            }
                            else if(completed.value == 2) {
                                CreateDialog("Something went wrong", "There is no internet connection, please try it later") {
                                    completed.value = 10000
                                }
                            }
                        }

                    } else {
                        item {
                            MainButton(text = "You can't book your own tutoring") {
                                navController.navigate(route = AppScreens.MarketScreen.route)
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun TutoringDescription(tutoryTitle: String, description: String) {
    Column() {
        Text(
            text = tutoryTitle,
            fontSize = 30.sp,
            color = Color.Black,
            fontFamily = moniFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )

        Text(
            text = "Description",
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = moniFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )

        Text(
            text = description,
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = moniFontFamily,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp)
        )

    }

}