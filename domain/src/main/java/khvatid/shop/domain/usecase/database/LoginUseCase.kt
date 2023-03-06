package khvatid.shop.domain.usecase.database

import android.util.Log
import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.repository.UserRepository

class LoginUseCase(private val repository: UserRepository) {

    suspend fun execute(shortUserModel: ShortUserModel, result: (Boolean) -> Unit) {
        val throwable = repository.getUser(shortUserModel, result = {
            Log.i("LOGIN", "$it is find model")
            if (it != null) {
                repository.insertShortUser(shortUserModel)
                result(true)
            } else {
                result(false)
            }
        })
    }
}