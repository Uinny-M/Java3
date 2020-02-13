package Less_1;

import java.util.ArrayList;

/**
 * Created by mma on 12.02.2020.
 */
public class Box {
    //private float value;
    private float boxWeight;
    ArrayList<Fruit> box = new ArrayList<>();

    public Box() {
        this.boxWeight = boxWeight;
    }

    public float getWeight() {
        return boxWeight;
    }

    public boolean compare(Box box) {
        return this.getWeight() == box.getWeight();
    }

    public void add(Fruit fruit) {
        box.add(fruit);
        boxWeight = fruit.getWeight();
    }

    public void sprinkle(Box newbox) {
        for (int i = 0; i < box.size(); i++) {
            newbox.add(box.get(i));
            box.remove(i);
        }
        boxWeight = 0;
    }
}
