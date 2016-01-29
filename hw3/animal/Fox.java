public class Fox extends Animal{
    String name;
    public Fox() {
    }
    public Fox(String s) {
        name = s;
        System.out.println(name);
    }
    public void speak() {
        System.out.println("Ringding");
    }
}