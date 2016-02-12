import java.util.Map;
import java.util.Random;
import java.util.HashMap;

public class UsernameBank {

    // Instance variables (remember, they should be private!)
    // YOUR CODE HERE

   private Map<String, String> email2Name;
   private Map<String, String>name2Email;
   private Map<String, Integer> badNames;
   private Map<String, Integer> badEmails;
   private static final String validChars = "0123456789abcdefghijklmnopqrstuvwxyz";
   private Random rand;
   private static final int totalNames = 36*36*36+36*36;

    public UsernameBank() {
        email2Name =  new HashMap<String, String>();
        name2Email = new HashMap<String,String>();
        badNames = new HashMap<String, Integer>();
        badEmails = new HashMap<String, Integer>();
        rand = new Random();
    }

    public void generateUsername(String username, String email) {
        if(username==null || email==null){
            throw new NullPointerException("user name or email is null");
        }

        Username name = new Username(username);//illegal name

        if(name2Email.containsKey(username) || email2Name.containsKey(email)){
            throw new IllegalArgumentException("Username or email already exists");
        }

        if(!email.contains("@")){
            throw new IllegalArgumentException("IllegalArgumentException email");
        }

        email2Name.put(email, username);
        name2Email.put(username,email);
    }

    public String getEmail(String username) {
        if(username == null){
            throw new NullPointerException("User name is null");
        }

        if(name2Email.containsKey(username)){
            return name2Email.get(username);
        }
        recordBadUsername(username);
        return null;
    }

    public String getUsername(String userEmail)  {
        if(userEmail==null){
            throw new NullPointerException("User email is null");
        }

        if(email2Name.containsKey(userEmail)){
            return email2Name.get(userEmail);
        }
        recordBadEmail(userEmail);
        return null;
    }

    public Map<String, Integer> getBadEmails() {
        return badEmails;
    }

    public Map<String, Integer> getBadUsernames() {
        return badNames;
    }

    public String suggestUsername() {
        StringBuilder uName = new StringBuilder();
        String sName = null; 
        do{
            int size=Math.random()<0.5 ? 2 : 3;
            for(int i=0;i<size;++i){
                uName.append(validChars.charAt(rand.nextInt(36)));
            }
            sName = uName.toString();
            if(!name2Email.containsKey(sName)){
                return sName;
            }
        }while(name2Email.size()<totalNames/2);
        return null;
    }

    // The answer is somewhere in between 3 and 1000.
    public static final int followUp() {
        // YOUR CODE HERE
        return 0;
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadUsername(String username) {
        if(badNames.containsKey(username)){
            int n=badNames.get(username)+1;
            badNames.put(username, n);
        }
        else{
            badNames.put(username,1);
        }
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadEmail(String email) {

        if(badEmails.containsKey(email)){
            int n=badEmails.get(email) + 1;
            badEmails.put(email, n);
        }
        else{
            badEmails.put(email,1);
        }
    }

    public static void main(String[] args){
        UsernameBank bank = new UsernameBank();
        bank.generateUsername("lsj", "lsj@lsj.com");
       // bank.generateUsername("ls", "lsjlsj.com");
       // bank.generateUsername("lsj", "lsj@lsj.com");
        bank.generateUsername(bank.suggestUsername(), "lsj2@lsj.com");
        System.out.println(bank.getEmail("lsj"));
        System.out.println(bank.getEmail("lsj2"));
        System.out.println(bank.getUsername("lsj2@lsj.com"));
        System.out.println(bank.getUsername("lsj3@lsj.com"));
    }
}