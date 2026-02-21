package enemy.basic;

import character.Character;
import enemy.Enemy;

public class Slime extends Enemy {

    public Slime() {
        super("Slime");
        setMaxHp(14);
        setHp(14);
        setAttack(2);
        setDefense(0);
    }

    @Override
    public void performAction(Character target) {
        System.out.println(getName() + " hardens and gains 3 shield!");
        addShield(3);

        normalAttack(target);
    }

    @Override
    public String toString() {
        return getName() + ": Hp = " + getHp();
    }
}
