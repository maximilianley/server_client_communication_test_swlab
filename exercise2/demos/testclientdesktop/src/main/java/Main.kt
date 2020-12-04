import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8280/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
        val userResource = retrofit.create(UserResource::class.java)
        userResource.getAllUsers().execute().body()?.forEach { u -> println(u.username) }



        val gameSocketClient = GameSocketClient("ws://127.0.0.1:8280/api/v1/ws", "1", "1235")

        //gameSocketClient.sendMessage("Test")



    }
}
