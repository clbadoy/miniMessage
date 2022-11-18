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
 *  The runner class starts up the GUI Frame for the Mini Twitter Program.
 */
public class Main {
    public static void main(String[] args) {
        
        // Create Singleton instance of Admin. Prevents duplicates.
        Admin.getInstance();
        System.out.println("Mini Twitter Loaded!");
    }
    
}
