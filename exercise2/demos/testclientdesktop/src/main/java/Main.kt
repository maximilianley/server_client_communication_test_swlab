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

        val userList = userResource.getAllUsers().execute().body()
        if (userList != null) {
            for (user in userList) {
                println("user ${user.username} with id ${user.id} is in the list")
            }
        } else {
            println("userlist is null")
        }

        /*
        val user = userResource.getUser(5).execute().body()
        if (user != null) {
            println("username is ${user.username}")
        } else {
            println("user was null")
        }
         */


        /*
        val gameSocketClient = GameSocketClient("ws://127.0.0.1:8280/api/v1/ws")
        gameSocketClient.sendMessage("Test")
         */


    }
}