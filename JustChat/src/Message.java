import java.io.*;
import java.time.LocalDateTime;

public class Message implements Serializable{
    public User sender;
    public int roomId;
    public String content;
    public LocalDateTime now;

    Message(User sender, int roomId, String content){
        this.sender = sender;
        this.roomId = roomId;
        this.content = content;

        now = LocalDateTime.now();
    }
}