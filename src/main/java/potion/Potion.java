package potion;

import character.Character;

public abstract class Potion implements Tradable, Useable{
    protected String name;
    protected int buyCost;
    protected int sellCost;

    public Potion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public abstract boolean buy(Character character);

    @Override
    public abstract boolean sell(Character character);

    @Override
    public abstract boolean use(Character character);

    public abstract String toString();

    public int getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(int buyCost) {
        this.buyCost = buyCost;
    }

    public int getSellCost() {
        return sellCost;
    }

    public void setSellCost(int sellCost) {
        this.sellCost = sellCost;
    }
}
