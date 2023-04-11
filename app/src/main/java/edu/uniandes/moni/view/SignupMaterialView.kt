package edu.uniandes.moni.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uniandes.moni.navigation.AppScreens
import edu.uniandes.moni.view.components.*
import edu.uniandes.moni.view.theme.main
import edu.uniandes.moni.view.theme.moniFontFamily
import edu.uniandes.moni.viewmodel.UserViewModel

@Composable
fun SignupMaterialView(navController: NavController) {
    val userViewModel: UserViewModel = UserViewModel()
    var name: String = ""
    var email: String = ""
    var password: String = ""
    var passwordRepeat: String = ""


    Scaffold() { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                textAlign = TextAlign.Center,
                fontFamily = moniFontFamily,
                fontWeight = FontWeight.Bold,
                color = main,
                fontSize = 38.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            Row(modifier = Modifier.padding(top = 25.dp, bottom = 15.dp)) {
                NameInput() {
                    name = it.text
                }
            }
            Row(modifier = Modifier.padding(bottom = 15.dp)) {
                EmailInput() {
                    email = it.text
                }
            }
            Row(modifier = Modifier.padding(bottom = 15.dp)) {
                PasswordInput("Password") {
                    password = it
                }
            }
            Row(modifier = Modifier.padding(bottom = 25.dp)) {
                PasswordInput("Repeat your password") {
                    passwordRepeat = it
                }
            }
            Row(
                modifier = Modifier.padding(bottom = 25.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Interests: ",
                    textAlign = TextAlign.Start,
                    fontFamily = moniFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0, 0, 0),
                    fontSize = 18.sp,
                )
            }
            Row(modifier = Modifier.padding(bottom = 15.dp)) {
                Select(label = "Select interest 1", optionList = mutableListOf("Calculus", "Physics", "Dancing", "Fitness"))
            }
            Row(modifier = Modifier.padding(bottom = 55.dp)) {
                Select(label = "Select interest 2", optionList = mutableListOf("Calculus", "Physics", "Dancing", "Fitness"))
            }
            Row(modifier = Modifier.padding(bottom = 15.dp)) {
                MainButton(text = "Sign Up") {
                    if (password == passwordRepeat) {
                        userViewModel.registerUser(name, email, password, "", "")
                    }
                }
            }
            SecondaryButton(text = "Log In") {
                navController.navigate(route = AppScreens.LoginScreen.route)
            }
        }
    }
}