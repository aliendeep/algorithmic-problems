// http://www.lintcode.com/en/problem/toy-factory/
/**
 * Your object will be instantiated and called as such:
 * ToyFactory tf = new ToyFactory();
 * Toy toy = tf.getToy(type);
 * toy.talk();
 */
interface Toy {
    void talk();
}

class Dog implements Toy {
    // Write your code here
    @Override
    public void talk(){
        System.out.println("Wow");
    }
}

class Cat implements Toy {
    // Write your code here
    @Override
    public void talk(){
        System.out.println("Meow");
    }
}

public class ToyFactory {
    /**
     * @param type a string
     * @return Get object of the type
     */
    public Toy getToy(String type) {
        if(type == null)
            return null;
        // Write your code here
        if(type.equalsIgnoreCase("Dog")){
            return new Dog();
        }
        if(type.equalsIgnoreCase("Cat")){
            return new Cat();
        }
        return null;
    }
}