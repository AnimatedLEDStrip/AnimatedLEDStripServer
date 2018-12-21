import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

object CommandLineSocket {
    private val serverSocket = ServerSocket(6)
    private var disconnected = true
    private var clientSocket: Socket? = null

    fun isDisconnected() = !disconnected
    fun getClientIP() = clientSocket!!.remoteSocketAddress

    fun openSocket() {
        while (!quit) {
            clientSocket = serverSocket.accept()
            val socIn = BufferedReader(InputStreamReader(clientSocket!!.getInputStream()))
            disconnected = false
            println("Command Line Connection Established")
            var input: String?
            while (!disconnected) {
                input = socIn.readLine()
                if (animationQueue.checkForNullAndAdd(input)) disconnected = true
            }
            println("Command Line Connection Lost")
        }
    }
}
