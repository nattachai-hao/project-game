package character.classes;

import character.Player;
import character.Character;

public class Mage extends Player {

    public Mage(String name) {
        super(name);
        setMaxHp(20);
        setHp(20);
        setAttack(5);
        setDefense(1);
        setEnergy(1);
    }

    // Firebolt
    @Override
    public void skill1(Character target) {
        if (getEnergy() >= 1) {
            int damage = getAttack() + 3;
            target.takeDamage(damage);
            setEnergy(getEnergy() - 1);
        }
    }

    // Mana Barrier
    @Override
    public void skill2(Character target) {
        if (getEnergy() >= 1) {
            addShield(3);
            setEnergy(getEnergy() - 1);
        }
    }

    // Arcane Nova
    @Override
    public void skill3(Character target) {
        if (getEnergy() >= 3) {
            int damage = getAttack() * 2 + 6;
            target.takeDamage(damage);
            setEnergy(getEnergy() - 3);
        }
    }

    public String toString() {
        return getName() + " -> Hp = " + getHp() + " atk = " + getAttack() + " def = " + getDefense() + " energy = " + getEnergy() + " block = " + getBlock();
    }

    public void upgrade() {
        setAttack(getAttack() + 6);
    }
}
