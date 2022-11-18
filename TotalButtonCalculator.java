public class TotalButtonCalculator implements AdminVisitor {

    @Override
    public int visit(User user) {
        return user.getNewsFeed().getFeedList().size();
    }

    
}
