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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camp_android.ui.theme.BackgroundDeleteColor
import com.example.camp_android.ui.theme.PinkButton
import com.example.camp_android.ui.theme.PinkIoasys
import com.example.camp_android.ui.theme.Poppins
import com.example.camp_android.ui.theme.TaskText


@Composable
fun TaskItem(taskName: String) {
    var checkedState by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (checkedState) {
            Icon(
                imageVector = Icons.Default.CheckCircle, contentDescription = "Ícone de check",
                tint = PinkIoasys
            )
        } else {
            Icon(
                imageVector = Icons.Default.Info, contentDescription = "Ícone de informação",
                tint = PinkIoasys
            )
        }

        Text(
            text = taskName, color = TaskText,
            fontFamily = Poppins, fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = it },
            colors = CheckboxDefaults.colors(
                uncheckedColor = PinkIoasys,
                checkedColor = PinkIoasys,
                checkmarkColor = Color.White
            ),
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun TasksListScreen() {
    var newTask by remember {
        mutableStateOf("")
    }

    val list = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = PinkIoasys)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(modifier = Modifier.size(40.dp)) {}

            Text(
                text = "Lista de Tarefas", color = Color.White,
                fontSize = 14.sp, fontFamily = Poppins, textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(0.dp)
            )

            val image = painterResource(id = R.drawable.logo_home)
            Image(
                painter = image, contentDescription = "Ícone clicável para voltar para home",
                Modifier.size(40.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = newTask,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
                onValueChange = {
                    newTask = it
                },
                label = {
                    Text(
                        text = "Nova tarefa", fontSize = 12.sp,
                        color = PinkIoasys
                    )
                })
            Button(
                onClick = {
                    list.add(newTask)
                    newTask = ""
                          },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PinkButton,
                ),
                modifier = Modifier.shadow(elevation = 5.dp)
            ) {
                Text(
                    text = "ADD", modifier = Modifier.fillMaxWidth(),
                    fontSize = 12.sp, textAlign = TextAlign.Center
                )
            }
        }


        LazyColumn {
            items(items = list, key = { it }) {task ->
                DismissContainer(onDelete = { list -= task }) {
                    TaskItem(taskName = task)
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DismissContainer(
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    val state = rememberDismissState(
        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToEnd) {
                onDelete()
                true
            } else {
                false
            }
        }
    )
    
    SwipeToDismiss(
        state = state,
        background = { BackgroudDelete() },
        dismissContent = { content() },
        directions = setOf(DismissDirection.StartToEnd)
    )
}

@Composable
fun BackgroudDelete() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp)
        .background(color = BackgroundDeleteColor),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(imageVector = Icons.Default.Delete,
            contentDescription = "Ícone de lixeira",
            tint = Color.White,
            modifier = Modifier.padding(start = 26.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    TasksListScreen()
}
