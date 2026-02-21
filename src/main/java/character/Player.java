package character;

public abstract class Player extends Character {

    public Player(String name) {
        super(name);
    }

    public abstract void skill1(Character target);
    public abstract void skill2(Character target);
    public abstract void skill3(Character target);

    public abstract void upgrade();
    public void clearEff() {
        vulnerableTurns = 0;
        poisonTurns = 0;
        evadeStacks = 0;
        setEnergy(0);
    }
}
