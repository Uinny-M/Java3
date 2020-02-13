package Less_1;

import java.util.ArrayList;

/**
 * Created by mma on 12.02.2020.
 */
public class HW_1 {

    public static void main(String[] args) {
        //задание 1
        String[] str = {new L1Generik<String>("000").getValue(), new L1Generik<String>("111").getValue()};
        L1Generik.printMass(str);
        L1Generik.massChange(str, 0, 1);
        L1Generik.printMass(str);
        ArrayList list = L1Generik.arrFromMass(str);
        L1Generik.printArr(list);

        //задание 2
        Box box_apple = new Box();
        Box box_apple1 = new Box();
        box_apple.add(new Apple());
        Box box_orange = new Box();
        box_orange.add(new Orange());
        System.out.println(box_apple.compare(box_orange));
        System.out.println(box_apple.compare(box_apple));

        box_apple.sprinkle(box_apple1);
        System.out.println(box_apple.getWeight());
        System.out.println(box_apple1.getWeight());

    }

    static class L1Generik<T> {
        private T value;

        public L1Generik(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public static <T> T[] massChange(T[] mass, int index1, int index2) {
            T temp = mass[index1];
            mass[index1] = mass[index2];
            mass[index2] = temp;
            return mass;
        }

        public static <T> ArrayList<T> arrFromMass(T[] mass) {
            ArrayList<T> arr = new ArrayList<T>();
            for (int i = 0; i < mass.length; i++) {
                arr.add(i, mass[i]);
            }
            return arr;
        }

        public static <T> void printMass(T[] mass) {
            for (int i = 0; i < mass.length; i++) {
                System.out.print(mass[i] + " ");
            }
            System.out.println();
        }

        public static <T> void printArr(ArrayList<T> arr) {
            for (int i = 0; i < arr.size(); i++) {
                System.out.print("" + i + " - " + arr.get(i) + " ");
            }
            System.out.println();
        }
    }
}
