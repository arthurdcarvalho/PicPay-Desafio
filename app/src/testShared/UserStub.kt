import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.entity.User
import org.mockito.ArgumentMatchers

object UserStub {
    fun getUserListSuccessResult() = SuccessResult(
        listOf(
            user(),
            user()
        )
    )

    fun getUserListEmptyResult() = EmptyResult<List<User>> (
        error = ArgumentMatchers.anyString(),
        message = ArgumentMatchers.anyString()
    )

    private fun user() = User(
        id = 1,
        name = "Sandrine",
        username = "Tod86",
        img = "https://randomuser.me/api/portraits/men/1.jpg"
    )
}