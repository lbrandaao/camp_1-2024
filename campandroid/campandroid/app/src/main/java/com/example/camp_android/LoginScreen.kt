package com.example.camp_android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camp_android.ui.theme.PinkIoasys
import com.example.camp_android.ui.theme.Poppins


@Composable
fun LogoImage() {
    val image = painterResource(id = R.drawable.logo_ioasys_1)
    
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun LoginScreen(onLoginClick: (String, String) -> Unit) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = PinkIoasys),
        verticalArrangement = Arrangement.SpaceAround
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            LogoImage()
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 200.dp)
        ) {
            Text(text = "Seja bem vind@!", color = Color.White, fontSize = 30.sp,
                fontFamily = Poppins, fontWeight = FontWeight.Normal)
            Text(
                text = "Lista de tarefas",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp),
                fontFamily = Poppins, fontWeight = FontWeight.Normal
            )
            // OutlinedTextField
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                value = email, onValueChange = { value ->
                    email = value
                },
                shape = RoundedCornerShape(100.dp),
                label = {
                    Text(text = "Usuário", fontFamily = Poppins)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                value = password, onValueChange = {
                    password = it
                },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(100.dp),
                label = {
                    Text(text = "Senha", fontFamily = Poppins)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                )
            )
            Button(
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                onClick = { onLoginClick.invoke(email, password) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.Gray
                ),
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text(text = "Entrar", fontSize = 16.sp, fontFamily = Poppins,
                    modifier = Modifier.padding(5.dp))
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginClick = {_,_ ->})
}


