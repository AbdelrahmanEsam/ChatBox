import com.apptikar.feature.chat.domain.model.UserResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(val numberId : String, val username : String)


fun UserResponseDto.mapToUserResponse() : UserResponse
{
    return  UserResponse(numberId, username)
}