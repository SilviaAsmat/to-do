package com.example.to_do.feature_to_do.presentation.to_do_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_do.core.presentation.components.ArchiveButton
import com.example.to_do.core.presentation.components.CompleteButton
import com.example.to_do.core.presentation.components.DeleteButton
import com.example.to_do.core.presentation.components.getToDoColors
import com.example.to_do.feature_to_do.domain.model.ToDoItem
import com.example.to_do.ui.theme.ToDoTheme

@Composable
fun ToDoItemCard(
    todo: ToDoItem,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onCompleteClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onCardClick: () -> Unit,
){
    val toDoColors = getToDoColors(todo = todo)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        onClick = onCardClick,
        colors = CardDefaults.cardColors(containerColor =  toDoColors.backgroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            CompleteButton(onCompleteClick,toDoColors.checkColor,todo.completed)
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = Bold,
                color = toDoColors.textColor,
                fontSize = 32.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.Top
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ){
                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = toDoColors.textColor,
                    fontSize = 24.sp,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(end = 4.dp)
            ) {
                ArchiveButton(onArchiveClick, toDoColors.archiveIconColor)
                DeleteButton(onDeleteClick)

            }

        }
    }
}

@Preview
@Composable
fun ToDoItemCardPreview() {
    ToDoTheme {
        ToDoItemCard(
            ToDoItem(
                title = "Clean the apt",
                description = "Wash dishes, laundry, pickup stuff, take out garbage",
                timestamp = 2342345,
                completed = true,
                archived = false,
                id = 0
            ),
            onDeleteClick = {},
            onCardClick = {},
            onArchiveClick = {},
            onCompleteClick = {},
        )
    }
}