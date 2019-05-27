package controller.Chat;

import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint {

    private Session client;

    @OnOpen
    public void connect(Session client){
        this.client = client;
        System.out.println("client" + client);
    }

    //Get the message and sends it back to each session client.
    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        System.out.println("message: " + message);
        for (Session peer : client.getOpenSessions()) {
            peer.getAsyncRemote().sendText(message);
        }
    }

    @OnClose
    public void close(){
        this.client = null;
        System.out.println("closed");
    }
}
