package com.apptikar.feature.chat.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.apptikar.designsystem.theme.*
import com.apptikar.feature.chat.domain.model.ChatState
import com.apptikar.feature.chat.domain.model.RecentChats
import com.apptikar.feature.chat.presentation.R


@Composable
fun HomeHeader(modifier: Modifier,image: String? = null,navigate : () -> Unit) {
  Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
      Icon(painter = painterResource(id = R.drawable.search), tint = White, contentDescription = stringResource(R.string.search), modifier =  Modifier.clickable {
          navigate.invoke()
      })
      Text(
          text = stringResource(R.string.home),
          style = MaterialTheme.typography.h5.copy(color = White, fontSize = 25.sp),
      )

      AsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
              .data(image)
              .crossfade(true)
              .build(),
          placeholder = painterResource(R.drawable.placeholder),
          error = painterResource(R.drawable.placeholder),
          contentDescription = stringResource(R.string.profile_image),
          contentScale = ContentScale.Crop,
          modifier = Modifier
              .size(40.dp)
              .clip(CircleShape)
      )

    }
}


@Composable
fun PersonalChatHeader(modifier: Modifier,chatState : RecentChats,imageSize: Int = 60) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = stringResource(R.string.search)
        )
        Spacer(modifier = Modifier.fillMaxWidth(0.05f))

            ProfileImage(image = chatState.image, isOnline = chatState.isOnline , imageSize = imageSize)
            Spacer(modifier = Modifier.fillMaxWidth(0.05f))
            ProfileNameAndLastMessage(name = chatState.name, lastMessage = chatState.lastMessage)
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.05f))
        Row() {
            IconWithCircleBack(
                background = White,
                icon = R.drawable.call_chat,
                contentDescription = R.string.make_voice_call,
                iconTint = Color.Black
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            IconWithCircleBack(
                background = White,
                icon = R.drawable.video,
                contentDescription = R.string.make_video_call,
                iconTint = Color.Black
            )
        }



    }

}




@Composable
fun ChatItem(modifier : Modifier,chatState : ChatState) {
    Row(
        modifier = modifier
           ,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

            Row(modifier = Modifier.wrapContentSize(), horizontalArrangement = Arrangement.Start,   verticalAlignment = Alignment.CenterVertically) {
                ProfileImage(image = chatState.imageUrl, isOnline = chatState.isOnline)
                Spacer(modifier = Modifier.size(15.dp))
                ProfileNameAndLastMessage(name = chatState.numberId, lastMessage = chatState.lastMessage)
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = chatState.lastSeen,
                    style = MaterialTheme.typography.h5.copy(color = GrayOr, fontSize = 15.sp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(15.dp))
                NumberOfNewMessages(
                    modifier = Modifier.size(25.dp),
                    numberOfUnseenMessages = chatState.unseenMessages
                )
            }
        }
        Spacer(modifier = Modifier.size(15.dp))


}




@Composable
fun NumberOfNewMessages(modifier: Modifier, numberOfUnseenMessages: Int) {
if (numberOfUnseenMessages > 0) {
    Box {

        Canvas(modifier = modifier.align(Alignment.Center))
        {
            drawCircle(color = RedError)
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = numberOfUnseenMessages.toString(),
            style = MaterialTheme.typography.h5.copy(color = White, fontSize = 15.sp)
        )
    }
}
}

@Composable
fun IconWithCircleBack(modifier: Modifier = Modifier, background:Color,icon:Int,contentDescription:Int,iconTint: Color= White) {
    Box {

        Canvas(modifier = modifier.align(Alignment.Center))
        {
            drawCircle(color = background)
        }

        Icon(modifier = Modifier.align(Alignment.Center), tint = iconTint , painter = painterResource(id = icon), contentDescription = stringResource(id = contentDescription,))


    }
}

@Composable
fun IsOnlineCircle(modifier : Modifier ,isOnline: Boolean = false) {
    Canvas(modifier = modifier)
    {
        drawCircle(color = if (isOnline) OnlineGreen else OfflineGray)
    }
}



@Composable
fun ProfileImage(image:String? = null,isOnline : Boolean,imageSize:Int = 70) {
    ConstraintLayout(modifier = Modifier.wrapContentSize())
    {
        val (profileImage,isOnlineCircle) = createRefs()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentDescription = stringResource(R.string.profile_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(profileImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(imageSize.dp)
                .clip(CircleShape)
        )

        IsOnlineCircle(modifier = Modifier
            .constrainAs(isOnlineCircle) {
                end.linkTo(profileImage.end)
                bottom.linkTo(profileImage.bottom)
                circular(profileImage, 130f, (imageSize / 2).dp)
            }
            .size(10.dp)
            ,isOnline)
    }
}

@Composable
fun ProfileNameAndLastMessage(name:String,lastMessage: String) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(text = name, style =  MaterialTheme.typography.h4.copy(color = Color.Black, fontSize = 20.sp)
            , maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = lastMessage, style =  MaterialTheme.typography.h5.copy(color = GrayOr, fontSize = 15.sp)
            ,maxLines = 1,overflow = TextOverflow.Ellipsis)


    }
}

@Composable
fun NavigationIcon(name: Int,icon:Int,focused:Boolean = false,) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

      Icon(painter = painterResource(id = icon), contentDescription = stringResource(id = name),
          tint = if (focused) TextGreen else GrayOr
      )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = stringResource(id = name),
            style = MaterialTheme.typography.h5.copy(color = if (focused) TextGreen else GrayOr))

    }
}



@Composable
fun OutcomeMessage(modifier : Modifier ,  content: @Composable () -> Unit) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp))
        .background(Green)
        .padding(vertical = 10.dp, horizontal = 15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
           content.invoke()
    }
}

@Composable
fun IncomeMessage(modifier : Modifier, content: @Composable () -> Unit) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .background(MessageWhite)
        .padding(vertical = 5.dp, horizontal = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        content.invoke()

    }
}




@Preview
@Composable
fun Preview() {
//OutcomeMessage(modifier = Modifier.wrapContentSize()) {
//    Text(
//        text = "message",
//        style = MaterialTheme.typography.h5.copy(color = White, fontSize = 15.sp)
//    )
//}




}

