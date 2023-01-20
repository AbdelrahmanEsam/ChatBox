package com.apptikar.feature.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.apptikar.designsystem.theme.*
import com.apptikar.feature.auth.presentation.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun OrDividerRow(dividerColor:Color , textColor: Color) {



ConstraintLayout(modifier = Modifier
    .fillMaxWidth()
    .wrapContentHeight()) {

    val (orText, leftDivider,rightDivider) = createRefs()

    Divider(modifier = Modifier.constrainAs(leftDivider){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(orText.start)
            bottom.linkTo(parent.bottom)
        width = Dimension.fillToConstraints

    },color = dividerColor, thickness = 1.dp)
    Text(modifier = Modifier
        .padding(horizontal = 10.dp)
        .constrainAs(orText) {
            start.linkTo(leftDivider.end)
            top.linkTo(parent.top)
            end.linkTo(rightDivider.start)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
        },
        text = stringResource(id = R.string.or),
        style = MaterialTheme.typography.h5.copy(color = textColor, fontSize = 15.sp),
    )
    Divider(modifier = Modifier.constrainAs(rightDivider){
        start.linkTo(orText.end)
        top.linkTo(parent.top)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
        width = Dimension.fillToConstraints
    },color = dividerColor, thickness = 1.dp)

}

}

@Composable
fun ButtonsSection(googleIcon:Int, facebookIcon:Int, appleIcon:Int
                   , googleAction:() -> Unit = {},facebookAction :  () -> Unit = {}, appleAction : () -> Unit= {}) {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center) {
        Image(modifier = Modifier.clickable {
          facebookAction()
        }
            ,painter = painterResource(id = facebookIcon), contentDescription = "facebook" )
        Spacer(modifier = Modifier.size(30.dp))

        Image(modifier = Modifier.clickable {
           googleAction()
        }
            ,painter = painterResource(id = googleIcon), contentDescription = "google", )
        Spacer(modifier = Modifier.size(30.dp))

        Image(modifier = Modifier.clickable {
           appleAction()
        }
            ,painter = painterResource(id = appleIcon), contentDescription = "apple" )

    }
}

@Composable
fun InputField(
    label: Int,
    isError: Boolean = false,
    errorMessage: Int? = null,
    value: String = "",
    inputType : KeyboardType = KeyboardType.Text,
    action: (String) -> Unit = {},
) {
    Surface {

        Column {
        TextField(
            value = value,
            onValueChange =
            {
                action(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textStyle = MaterialTheme.typography.h5.copy(color = BlackText, fontSize = 15.sp),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = White,
                focusedIndicatorColor = if (!isError) TextGreen else RedError,
                unfocusedIndicatorColor = if (!isError) Color.Black else RedError
            ),
            keyboardOptions = KeyboardOptions(keyboardType = inputType),
        )


        if (isError) {
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                modifier = Modifier.align(Alignment.End),
                text = stringResource(id = errorMessage!!),
                style = MaterialTheme.typography.h5.copy(color = RedError, fontSize = 15.sp),
                maxLines = 1
            )
        }
}

        Text(
            text = stringResource(id = label),
            style = MaterialTheme.typography.h5.copy(
                color = if (!isError) TextGreen else RedError,
                fontSize = 13.sp
            )
        )
    }
}

@Composable
fun LoginInText(navigate: () -> Unit) {
    Row(
        Modifier
            .fillMaxSize()
            .clickable {
                navigate.invoke()
            }, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = R.string.existing_account),
            style = MaterialTheme.typography.h5.copy(color = GrayText, fontSize = 15.sp),
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.h5.copy(color = White, fontSize = 18.sp),
        )
    }
}

@Composable
fun RoundedCornerButton(text: String, canLogin: Boolean = false, signUp: () -> Unit) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .clip(RoundedCornerShape(15.dp)), colors = ButtonDefaults.buttonColors(backgroundColor = if (!canLogin) White else TextGreen),onClick =  signUp)

    {

        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(color = if (!canLogin) BlackText else White),
        )
    }
}