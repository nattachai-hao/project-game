package room;

import enemy.Enemy;

import java.util.ArrayList;

public class Enemyroom {
    private ArrayList<Enemy> enemies;

    public Enemyroom() {
        enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy) {
        getEnemies().add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public boolean allEnemyDeath() {
        boolean check = true;
        for (int i = 0; i < getEnemies().size(); i++) {
            if(getEnemies().get(i).getHp() > 0) check = false;
        }
        return check;
    }
}
