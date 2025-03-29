package ma.khairy.twilio_app.controller;

import ma.khairy.twilio_app.service.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsappController {

    private static final Logger logger = LoggerFactory.getLogger(WhatsappController.class);

    @Autowired
    private WhatsappService whatsAppService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {
        try {
            whatsAppService.sendWhatsappMessage(request.getTo(), request.getMessage());
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send message: " + e.getMessage());
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<Map<String, String>> receiveMessage(@RequestParam("Body") String body,
                                                            @RequestParam("From") String from,
                                                            @RequestParam("MessageSid") String messageSid) {
        try {
            logger.info("Webhook reçu - Début du traitement");
            logger.info("Message reçu de: {}", from);
            logger.info("Contenu: {}", body);
            logger.info("MessageSid: {}", messageSid);
            
            // Créer la réponse JSON
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Message reçu avec succès");
            response.put("from", from);
            response.put("body", body);
            response.put("messageSid", messageSid);
            
            logger.info("Webhook traité avec succès");
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Erreur lors de la réception du message: {}", e.getMessage(), e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Erreur lors du traitement du message");

            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    public static class MessageRequest {
        private String to;
        private String message;

        // Getters and setters
        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}