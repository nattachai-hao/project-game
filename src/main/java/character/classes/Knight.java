package character.classes;

import character.Player;
import character.Character;

public class Knight extends Player {

    public Knight(String name) {
        super(name);
        setMaxHp(25);
        setHp(25);
        setAttack(4);
        setDefense(2);
        setEnergy(0);
    }

    // Shield Strike
    @Override
    public void skill1(Character target) {
        if (getEnergy() >= 1) {
            int damage = getAttack() + 2;
            target.takeDamage(damage);
            addShield(2);
            setEnergy(getEnergy() - 1);
        }
    }

    // Fortify
    @Override
    public void skill2(Character target) {
        if (getEnergy() >= 1) {
            addShield(5);
            setEnergy(getEnergy() - 1);
        }
    }

    // Shield Slam
    @Override
    public void skill3(Character target) {
        if (getEnergy() >= 2) {
            int damage = getAttack() + getShield();
            target.takeDamage(damage);
            resetShield();
            setEnergy(getEnergy() - 2);
        }
    }

    public String toString() {
        return getName() + " -> Hp = " + getHp() + " atk = " + getAttack() + " def = " + getDefense() + " energy = " + getEnergy() + " block = " + getBlock();
    }

    public void upgrade() {
        setMaxHp(getMaxHp() + 5);
        setHp(getHp() + 5);
        setDefense(getDefense() + 1);
    }
}
