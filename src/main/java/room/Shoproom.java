package room;

import potion.*;

import java.util.ArrayList;

public class Shoproom {
    private ArrayList<Potion> store;

    public Shoproom() {
        store = new ArrayList<>();
        store.add(new HealingPotion(5));
        store.add(new EnergyPotion(5));
        store.add(new DefPotion(7));
        store.add(new AtkPotion(7));
    }

    public ArrayList<Potion> getStore() {
        return store;
    }

    public void setStore(ArrayList<Potion> store) {
        this.store = store;
    }
}
