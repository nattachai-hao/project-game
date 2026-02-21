package potion;

import character.Character;

public class EnergyPotion extends Potion{
    public EnergyPotion(int buyCost) {
        super("EnergyPotion");
        setBuyCost(buyCost);
        setSellCost(10);
    }

    @Override
    public boolean buy(character.Character character) {
        if(character.getGold() >= getBuyCost()) {
            character.setGold(character.getGold() - getBuyCost());
            character.getInventory().addPotion(this);
            return true;
        } return false;
    }

    @Override
    public boolean sell(character.Character character) {
        character.getInventory().removePotion(this);
        character.setGold(character.getGold() + getSellCost());
        return true;
    }

    @Override
    public boolean use(Character character) {
        character.setEnergy(character.getEnergy() + 1);
        character.getInventory().removePotion(this);
        return true;
    }

    public String toString() {
        return getName() + " : price = " + getBuyCost() + " use this potion to increase " + 1 + " energy";
    }
}
