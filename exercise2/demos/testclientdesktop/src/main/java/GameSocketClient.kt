import okhttp3.*
import okio.ByteString
import java.util.*
import java.util.concurrent.TimeUnit

class GameSocketClient(url: String, username: String, password: String) : WebSocketListener() {

    private val webSocket: WebSocket

    init {
        val client: OkHttpClient = OkHttpClient
                .Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build()
        val request: Request = Request.Builder()
                .url(url)
                //.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(("$username:$password").toByteArray())) // "MToxMjM0"
                .addHeader("Authorization", "Basic " + "MToxMjM0")
                .build()
        this.webSocket = client.newWebSocket(request, this)

    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Log.i("WS closed")

    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.i("WS closing")

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        if (response?.code == 401) {
            println("auth failed :/")
        }
        Log.i("WS failure ${t.message}")

    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.i("WS message $text")
        this.webSocket.send("message from client")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.i("WS byte message, ignoring")
        //this.webSocket.send("message from client")
        webSocket.send("message from client")
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.i("WS opened")
        webSocket.send("QQQblaSS")
        webSocket.send("dlsdldk")
        webSocket.send("QQoioOI")
    }

    object Log {
        fun i(msg: String) {
            println("Log: $msg")
        }
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }
}