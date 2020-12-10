package de.hsheilbronn.it.ai.swlab.t00.ex2.backend.game;

import de.hsheilbronn.it.ai.swlab.t00.ex2.backend.game.gameinstance.SingleGameInstance
import io.quarkus.security.Authenticated
import io.vertx.axle.core.eventbus.Message
import java.util.concurrent.CopyOnWriteArrayList
import java.util.function.Consumer
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*
import javax.websocket.server.ServerEndpoint

// gibt es einmal in der Anwendung
// sonst gibt es z.B. RequestScoped
@ServerEndpoint("/api/v1/ws")
@ApplicationScoped
/**
 * Verwaltet die verschiedenen Spiele und sorgt dafür,
 * dass Nachrichten von Endgeräten in die richtigen Spiele kommen.
 */
class GameManagerService {
    var sessions = CopyOnWriteArrayList<Session>()
    var gameInstances = CopyOnWriteArrayList<SingleGameInstance>()

    @OnOpen
    fun onOpen(session: Session) {
        if (session.userPrincipal == null) {
            session.close(CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY, "AUTH_FAILED"))
        }
        sessions.add(session)
        //session.basicRemote.sendText("you opened a connection with the server")
        println("Added session: "+session.toString())
        println("EEEEEEEDDDDRRRRRR")
        //session.basicRemote.sendText("you opened a connection with the server")
    }

    @OnClose
    fun onClose(session: Session) {
        sessions.remove(session)
    }

    @OnError
    fun onError(session: Session, throwable: Throwable?) {
        sessions.remove(session)
    }

    @OnMessage
    fun onMessage(msgString: String, session: Session) {
        //broadcast(message)
        // todo session.userPrincipal
        print("received message: "+msgString+" from: "+session.toString())
        println("session in list: KKKKK")
        println("session in list: "+sessions.contains(session).toString())
        val message = MessageConverter.convertMessage(msgString)
        if (isInGameInstance(session)) {
            // send message to room
        } else {
            // allow other actions
        }
        //session.asyncRemote.sendText("message from server")
        /*session.asyncRemote.sendObject("message from server"){ result: SendResult ->
            if (result.exception != null) {
                println("Unable to send message: " + result.exception)
            }
        }
        */
        //session.basicRemote.sendText("message from server")
        sessions[0].basicRemote.sendText("message from server")
        println("{\"from\":\""+sessions[0].id+"\",\"content\":\""+"message from server"+"\"}")
        //sessions[0].basicRemote.sendText("{\"from\":\""+sessions[0].id+"\",\"content\":\""+"message from server"+"\"}")
        broadcast("wkjqwlkj")
        for (s in sessions){
            if (s.isOpen){
                session.basicRemote.sendText("yo sent you this")
            }else println("KJSLKSDJLSKDJ")
        }
        //println("session in list: "+sessions.contains(session).toString())
    }

    private fun isInGameInstance(session: Session): Boolean {
        return session.userProperties.containsKey("instanceId");
    }

    fun sendMessageToSession(recipient: String, message: String) {
        // korrekten Empfänger finden
        // Nachricht umwandeln
        // -> MessageConverter entsprechend anpassen

    }

    private fun broadcast(message: String) {
        sessions.forEach(Consumer { s: Session ->
            s.asyncRemote.sendObject("broadcast message from server") { result: SendResult ->
                if (result.exception != null) {
                    println("Unable to send message: " + result.exception)
                }
            }
        })
    }

}
