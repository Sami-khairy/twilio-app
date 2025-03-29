package ma.khairy.twilio_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class WhatsappService {

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    public void sendWhatsappMessage(String to, String message) {
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber("whatsapp:" + fromNumber),
                message
        ).create();
    }
}
