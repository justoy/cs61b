public class TestAnimal{
    public static void main(String[] args){
        Animal a0 = new Animal();   // SuperAnimal
        Fox f0 = new Fox();         // SuperAnimal
        a0.speak();                 // I'm an animal
        f0.speak();                 // Ringding
        ((Animal) f0).speak();      // Ringding
        //((Fox) a0).speak();         // compilation error 

        Animal a1 = new Fox();
        a1.speak();// Ringding
        ((Animal) a1).speak();//Ringding

       // Fox f1 = new Animal();//compilation error 

        Animal a2 = new Animal();// SuperAnimal
        System.out.println(a2.name);//SuperAnimal
        Animal a3 = new Fox("SuperFox");//SuperAnimal + SuperFox
        System.out.println(a3.name);//SuperAnimal
        System.out.println(((Animal) a3).name);//SuperAnimal
    }
}

