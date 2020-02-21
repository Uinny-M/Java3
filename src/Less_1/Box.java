package Less_1;

import java.util.ArrayList;

/**
 * Created by mma on 12.02.2020.
 */
public class Box<T extends Fruit> {
    private float boxWeight;
    ArrayList<T> box = new ArrayList();

    public Box() {
        this.boxWeight = boxWeight;
    }

    public float getWeight() {
        return boxWeight;
    }

    public boolean compare(Box box) {
        return this.getWeight() == box.getWeight();
    }

    public void add(T fruit) {
        if ((box.isEmpty()) || (fruit.getClass().equals(box.get(0).getClass()))) {
            box.add((T) fruit);
            boxWeight = fruit.getWeight();
        } else System.out.println("Отставить мультифрукт!");
    }

    public void sprinkle(Box newBox) {
        for (int i = 0; i < box.size(); i++) {
            newBox.add(box.get(i));
            box.remove(i);
        }
        boxWeight = 0;
    }
}
