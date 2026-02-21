package enemy.boss;

import enemy.basic.Goblin;
import java.util.Random;
import character.Character;

public class GoblinChief extends Goblin {

    private boolean enraged = false;
    private Random random = new Random();

    public GoblinChief() {
        super();

        // Override base goblin stats with boss stats
        setName("Goblin Chief");
        setMaxHp(50);
        setHp(50);
        setAttack(6);
        setDefense(5);
    }

    @Override
    public void performAction(Character target) {

        // Phase 2 mechanic
        if (!enraged && getHp() <= getMaxHp() / 2) {
            enraged = true;
            setAttack(getAttack() + 4);
            System.out.println(getName() + " becomes ENRAGED! Its power surges!");
        }

        // 20% chance to command attack (boss-only move)
        int roll = random.nextInt(100);

        if (roll < 20) {
            commandStrike(target);
        } else {
            super.performAction(target); // use normal goblin behavior
        }
    }

    private void commandStrike(Character target) {
        System.out.println(getName() + " commands a brutal strike!");
        target.takeDamage(getAttack() + 5);
    }
}