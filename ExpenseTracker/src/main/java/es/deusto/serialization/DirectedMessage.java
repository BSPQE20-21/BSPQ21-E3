package es.deusto.serialization;

// the relationship between the user and the message or in our case user and expenses

public class DirectedMessage {

    private UserData userData;
    private MessageData messageData;

    public DirectedMessage() {

    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setMessageData(MessageData messageData) {
        this.messageData = messageData;
    }

    public MessageData getMessageData() {
        return this.messageData;
    }
}