import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserResource {
    @GET("user/")
    fun getAllUsers(): Call<List<User>>
    @GET("user/{id}")
    fun getUser(@Path("id") userId: Long): Call<User>

}