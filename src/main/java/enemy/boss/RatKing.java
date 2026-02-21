package enemy.boss;

import character.Character;
import enemy.basic.Rat;
import java.util.Random;

public class RatKing extends Rat {

    private Random random = new Random();
    private boolean frenzy = false;

    public RatKing() {
        super();

        setName("Rat King");
        setMaxHp(30);
        setHp(30);
        setAttack(5);
        setDefense(2);
    }

    @Override
    public void performAction(Character target) {

        // Frenzy phase (under 50% HP)
        if (!frenzy && getHp() <= getMaxHp() / 2) {
            frenzy = true;
            setAttack(getAttack() + 3);
            System.out.println(getName() + " enters a BLOOD FRENZY!");
        }

        System.out.println(getName() + " commands the swarm!");

        // Triple attack boss move
        int attacks = frenzy ? 3 : 2;

        for (int i = 0; i < attacks; i++) {
            System.out.println(getName() + " viciously bites!");
            normalAttack(target);
        }

        if (random.nextInt(100) < 20) {
            System.out.println(getName() + " inflicts poison!");
            target.addPoison(2);
        }
    }
}