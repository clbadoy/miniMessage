import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedList{
    
    private static final List<String> GOOD_WORDS = Arrays.asList("good", "nice", "great");
    private ArrayList<Message> messages;

    private int positiveMessageCount;

    public FeedList() {
        messages = new ArrayList<>();
        positiveMessageCount = 0;
    }

    public ArrayList<Message> getFeedList() {
        return messages;
    }


    public void sendMessage(Message newText) {
        messages.add(newText);
        
        for(int i = 0; i < GOOD_WORDS.size(); i++) {
            if(newText.getMessage().contains(GOOD_WORDS.get(i))) {
                
            }
        }
    }
}
