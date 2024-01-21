package ba.edu.ibu.eventport.api.core.service;

import ba.edu.ibu.eventport.api.rest.websockets.MainSocketHandler;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  private final MainSocketHandler mainSocketHandler;

  public NotificationService(MainSocketHandler mainSocketHandler) {
    this.mainSocketHandler = mainSocketHandler;
  }

  public void broadcastMessage(String message) {
    mainSocketHandler.broadcastMessage(message);
  }

  public void sendMessage(String userId, String message) {
    mainSocketHandler.sendMessage(userId, message);
  }
}
