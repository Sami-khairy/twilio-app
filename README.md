# Application WhatsApp avec Twilio et Spring Boot

Cette application permet d'envoyer et de recevoir des messages WhatsApp en utilisant l'API Twilio et Spring Boot.

## Prérequis

- Java JDK 17 ou supérieur
- Maven
- Compte Twilio avec accès à WhatsApp Sandbox
- ngrok (pour le développement local)

## Configuration

### 1. Configuration Twilio

1. Créez un compte sur [Twilio](https://www.twilio.com)
2. Accédez à la [Console Twilio](https://www.twilio.com/console)
3. Notez vos identifiants :
   - Account SID
   - Auth Token
   - Numéro WhatsApp Sandbox

### 2. Configuration de l'Application

1. Clonez ce dépôt
2. Ouvrez le fichier `src/main/resources/application.properties`
3. Configurez les variables suivantes :
   ```properties
   twilio.account.sid=votre_account_sid
   twilio.auth.token=votre_auth_token
   twilio.whatsapp.number=votre_numero_whatsapp
   ```

### 3. Configuration de ngrok

1. Téléchargez [ngrok](https://ngrok.com/download)
2. Installez ngrok
3. Démarrez ngrok :
   ```bash
   ngrok http 8080
   ```
4. Copiez l'URL HTTPS générée (ex: https://xxxx-xx-xx-xxx-xx.ngrok-free.app)

### 4. Configuration du Webhook Twilio

1. Allez dans la [Console Twilio](https://www.twilio.com/console)
2. Naviguez vers Messaging > Settings > WhatsApp Sandbox Settings
3. Dans "When a message comes in", entrez :
   `https://votre-url-ngrok/api/whatsapp/webhook`
4. Assurez-vous que la méthode est définie sur "HTTP POST"
5. Sauvegardez les modifications

## Exécution de l'Application

1. Compilez l'application :
   ```bash
   mvn clean install
   ```

2. Lancez l'application :
   ```bash
   mvn spring:boot run
   ```

## Utilisation

### Envoi de Messages

Pour envoyer un message, utilisez l'endpoint suivant :

```bash
curl -X POST http://localhost:8080/api/whatsapp/send \
-H "Content-Type: application/json" \
-d '{
    "to": "whatsapp:+212XXXXXXXXX",
    "message": "Votre message ici"
}'
```

### Réception de Messages

1. Rejoignez votre sandbox WhatsApp Twilio en envoyant le code d'activation
2. Envoyez un message à votre numéro WhatsApp Twilio
3. Les messages reçus seront affichés dans la console de l'application

## Structure du Projet

```
src/
├── main/
│   ├── java/
│   │   └── ma/
│   │       └── khairy/
│   │           └── twilio_app/
│   │               ├── controller/
│   │               │   └── WhatsappController.java
│   │               ├── service/
│   │               │   └── WhatsappService.java
│   │               ├── config/
│   │               │   └── TwilioConfig.java
│   │               └── TwilioAppApplication.java
│   └── resources/
│       └── application.properties
```

## Dépendances

- Spring Boot
- Twilio SDK
- Lombok (optionnel)

## Dépannage

1. **Messages non reçus** :
   - Vérifiez que ngrok est en cours d'exécution
   - Vérifiez l'URL du webhook dans la console Twilio
   - Assurez-vous que l'application est en cours d'exécution

2. **Erreurs d'envoi** :
   - Vérifiez vos identifiants Twilio
   - Assurez-vous que le numéro destinataire est enregistré dans votre sandbox

## Support

Pour toute question ou problème, veuillez ouvrir une issue dans ce dépôt. 
