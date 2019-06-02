package controller.Chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{auto_id}")
public class ChatEndpoint {

    private Session client;
    private static ConcurrentHashMap<Session, Integer> sessions = new ConcurrentHashMap<>();
    private static final Logger LOGGER = Logger.getLogger( ChatEndpoint.class.getName() );

    @OnOpen
    public void connect(Session client, @PathParam("auto_id") int autoId){


        sessions.put(client, autoId);
        this.client = client;
        LOGGER.log(Level.SEVERE,"client" + client);
        client.getAsyncRemote().sendText("You are connected");


    }

    //Get the message and sends it back to each session client.
    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        int autoid = sessions.get(client);
        for (Map.Entry<Session, Integer> entry : sessions.entrySet()) {
            if(autoid == (entry.getValue())){
                entry.getKey().getAsyncRemote().sendText(message);

            }
        }
    }

    @OnClose
    public void close(Session client){
        this.client = null;
        LOGGER.log(Level.INFO, "closed");
        sessions.remove(client);
    }
}
