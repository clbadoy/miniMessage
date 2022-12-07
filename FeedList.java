/*
 *  Christian Badoy
 *  CS3560
 *  Profsessor Sun
 *  17 November 2022
 * 
 *  The purpose of this project is to create a functioning mini Twitter GUI program using
 *  the design patterns of Singleton, Composite, Visitor, and Observer. It also makes
 *  us learn the basics of Java Swing.
 * 
 *  The FeedList contains methods involving live feed data, including sending, receiving, and viewing messages
 *  from other users.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedList{
    
    private static final List<String> GOOD_WORDS = Arrays.asList("good", "nice", "great");
    private List<Message> messages;
    private User user;

    private int positiveMessageCount;
    private int messageCount;

    public FeedList(User person) {
        messages = new ArrayList<>();
        positiveMessageCount = 0;
        messageCount = 0;
        user = person;
    }

    public List<Message> getFeedList() {
        return messages;
    }

    public int getPositiveCount() {
        return positiveMessageCount;
    }

    public int getMessageCount() {
        return messageCount;
    }
    public Message getLatestMessage() {
        return messages.get(0);
    }

    public void addToFeed(Message otherMessage)
    {
        messages.add(0, otherMessage);
    }

    /*
     * Tabulates message if it is positive while records total.
     * Calls observers (followers) to receive the new message.
     */
    public void sendMessage(Message newText) {
        addToFeed(newText);

        user.refreshLastUpdateTime();
        System.out.println(user.printUserUpdateToConsole());
        
        positiveMessageCount += isPositiveMessage(newText.getMessage());
        messageCount++;
        user.notifyAllObservers();
    }

    /*
     * Private method to determine whether or not a message contains positive words.
     */
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


}
