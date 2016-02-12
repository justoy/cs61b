import java.util.Random;

public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97

    // Instance Variables (remember, they should be private!)
    // YOUR CODE HERE
    private int[] userName = new int[3];
    private int size=0;
    private static final String possibleChars = "0123456789abcdefghijklmnopqrstuvwxyz";

    public Username() {
        Random rand = new Random();
        size = Math.random()<0.5 ? 2 : 3;
        for(int i=0;i<size;++i){
            userName[i]=possibleChars.charAt(rand.nextInt(36));        
        }
    }

    public Username(String reqName) {
        size = reqName.length();
        int c;

        if(reqName==null){
            throw new NullPointerException("Required username is null");
        }

        if(size>3||size<2){
            throw new IllegalArgumentException("Illegal requred name");          
        }

        for(int i=0;i<size;++i){
            c=reqName.charAt(i);
            if(c>='A'&&c<='Z') c+=32;
            if(possibleChars.indexOf(c)<0){
                throw new IllegalArgumentException("Illegal requred name");
            }
            userName[i]=c;
        }
    }

    @Override
    public boolean equals(Object o) {
        Username temp = (Username)o;
        return hashCode()==temp.hashCode();
    }

    @Override
    public int hashCode() { 
        return userName[0]*1000000+userName[1]*1000+userName[2];
    }

    private void printName(){
        for(int i=0;i<size;++i){
            System.out.printf("%c",(char)userName[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Username().printName();
        new Username().printName();
        Username lsj = new Username("lsj");
        Username lsj2 = new Username("lsj");
        Username lsj3 = new Username("LSJ");
        Username xm = new Username("xm");
        //Username xmls = new Username("xmls");
        //Username xml = new Username("xm@");
        System.out.println(lsj.hashCode());
        System.out.println(lsj2.hashCode());
        System.out.println(lsj3.hashCode());
        System.out.println(xm.hashCode());

    }
}