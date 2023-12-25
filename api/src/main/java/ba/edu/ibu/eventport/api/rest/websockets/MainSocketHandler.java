package ba.edu.ibu.eventport.api.rest.websockets;

import ba.edu.ibu.eventport.api.exceptions.GeneralException;
import ba.edu.ibu.eventport.api.rest.models.auth.User;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;

@Component
public class MainSocketHandler implements WebSocketHandler {

  public HashMap<Object, WebSocketSession> sessions = new HashMap<>();


  public MainSocketHandler() {
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    User user = new User();
    user.setId("blabla");

    sessions.put(user.getId(), session);
    System.out.println("Session created for the user " + user.getId() + " where the session id is " + session.getId());
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) {
    System.out.println("Error happened " + session.getId() + " with reason ### " + exception.getMessage());
  }


  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
    System.out.println(
      "Connection closed for session " + session.getId() + " with status ### " + closeStatus.getReason());
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
    String messageReceived = (String) message.getPayload();
    System.out.println("Message received: " + messageReceived);
  }

  public void broadcastMessage(String message) {
    sessions.forEach((key, session) -> {
      try {
        if (session.isOpen()) {
          session.sendMessage(new TextMessage(message));
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void sendMessage(String userId, String message) {
    WebSocketSession session = sessions.get(userId);
    if (session == null) {
      return;
    }


    try {
      session.sendMessage(new TextMessage(message));
    } catch (IOException e) {
      throw new GeneralException(e);
    }
  }
}
