package inventory;

import potion.Potion;

import java.util.ArrayList;

public class Inventory {
    public static final int maxPotion = 10;
    private ArrayList<Potion> arrayListPotion;

    public Inventory() {
        arrayListPotion = new ArrayList<>();
    }

    public ArrayList<Potion> getArrayListPotion() {
        return arrayListPotion;
    }

    public void addPotion(Potion potion) {
        if(getArrayListPotion().size() < maxPotion) {
            getArrayListPotion().add(potion);
        }
    }

    public void removePotion(Potion potion) {
        for(int i = 0; i < getArrayListPotion().size(); i++) {
            if(potion.equals(arrayListPotion.get(i))) {
                arrayListPotion.remove(potion);
                return;
            }
        }
    }

    public boolean isEmpty() {
        if(getArrayListPotion().isEmpty()) return true;
        return false;
    }
}
