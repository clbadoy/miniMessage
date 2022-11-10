public class Admin {
    
    private static Admin instance = null;

    // Constructor that revents classes to create another object of the Admin Class.
    private Admin()
    {
        
    }

    public static Admin getInstance() 
    {
        if(instance == null) 
        {
            instance = new Admin();
        }

        return instance;
    }
}
