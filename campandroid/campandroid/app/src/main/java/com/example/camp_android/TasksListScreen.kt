package com.example.camp_android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camp_android.ui.theme.PinkButton
import com.example.camp_android.ui.theme.PinkIoasys
import com.example.camp_android.ui.theme.Poppins
import com.example.camp_android.ui.theme.TaskText


@Composable
fun TaskItem(taskName: String) {
    var checkedState by remember {
        mutableStateOf(false)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box(modifier = Modifier
            .background(color = PinkIoasys, shape = CircleShape)
            .size(19.dp),
            contentAlignment = Alignment.Center){
            Text(text = "!", color = Color.White,
                fontFamily = Poppins, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center )
        }

        Text(text = taskName, color = TaskText,
            fontFamily = Poppins, fontSize = 14.sp)

        Checkbox(checked = checkedState, onCheckedChange = { checkedState = it  },
            colors = CheckboxDefaults.colors(
                uncheckedColor = PinkIoasys,
                checkedColor = PinkIoasys,
                checkmarkColor = Color.White
            ),
            modifier = Modifier.size(20.dp))
    }
}

@Composable
fun TasksListScreen() {
    var newTask by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = PinkIoasys)
                .padding(20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Lista de Tarefas", color = Color.White,
                fontSize = 14.sp, fontFamily = Poppins,
                modifier = Modifier
                    .padding(end = 75.dp))

            val image = painterResource(id = R.drawable.logo_home)
            Image(painter = image, contentDescription = "Ícone clicável para voltar para home",
                Modifier.size(40.dp))
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(value = newTask,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
                onValueChange = {
                                newTask = it
                },
                label = {
                    Text(text = "Nova tarefa", fontSize = 12.sp,
                        color = PinkIoasys)
                })
            Button(
                onClick = {  },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PinkButton,
                ),
                modifier = Modifier.shadow(elevation = 5.dp)
                ) {
                Text(text = "ADD", modifier = Modifier.fillMaxWidth(),
                    fontSize = 12.sp, textAlign = TextAlign.Center)
            }
        }

        TaskItem(taskName = "Aprender Dart")
        TaskItem(taskName = "Aprender Flutter")
        TaskItem(taskName = "Criar Apps incríveis")

    }

}


@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    TasksListScreen()
}
