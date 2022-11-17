import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedList extends Observer {
    
    private static final List<String> GOOD_WORDS = Arrays.asList("good", "nice", "great");
    private List<Message> messages;
    private User user;

    private int positiveMessageCount;

    public FeedList(User person) {
        messages = new ArrayList<>();
        positiveMessageCount = 0;
        user = person;
    }

    public List<Message> getFeedList() {
        return messages;
    }

    public int getPositiveCount() {
        return positiveMessageCount;
    }

    public void sendMessage(Message newText) {
        messages.add(0, newText);
        
        positiveMessageCount += isPositiveMessage(newText.getMessage());

        //user.notifyAllObservers();
    }

    private int isPositiveMessage(String text) {
        int test = 0;
        String lowText = text.toLowerCase();
        for(int i = 0; i < GOOD_WORDS.size(); i++) {
            if(lowText.contains(GOOD_WORDS.get(i))) {
                test = 1;
                break;
            }
            else {
                test = 0;
            }
        }
        return test;
    }

    private void addToFeed(Message otherMessage)
    {
        messages.add(0, otherMessage);
    }

    @Override
    public void update(Subject subject) {
        addToFeed((Message) subject);
    }
}
