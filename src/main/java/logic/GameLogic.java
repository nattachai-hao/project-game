package logic;

import character.Character;
import character.Player;
import potion.Potion;
import room.Enemyroom;

import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
    public static boolean isGameEnd(Character character) {
        if(character.getHp() <= 0) return true;
        return false;
    }

    public static boolean fight(Player player, Enemyroom enemyroom) {
        int turn = 0;
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("your enemy");
            for(int i = 0; i < enemyroom.getEnemies().size(); i++) {
                System.out.println((i+1) + ": " + enemyroom.getEnemies().get(i).toString());
            }
            System.out.println(player.toString());
            System.out.println("your turn");
            System.out.println("select your action");
            System.out.println("1: Attack");
            System.out.println("2: Block");
            System.out.println("3: Item");
            System.out.println("4: Skill");
            System.out.println("5: Focus");
            int choose = in.nextInt();
            while (true) {
                if(choose == 1) {
                    attack(player,enemyroom);
                    break;
                }else if(choose == 2) {
                    player.block();
                    break;
                }else if(choose == 3) {
                    if(player.getInventory().isEmpty()) {
                        System.out.println("your inventory is empty");
                        System.out.println("select 1 to 5 to do action");
                        choose = in.nextInt();
                    }else {
                        useItem(player);
                        break;
                    }
                }else if(choose == 4) {
                    skill(player,enemyroom);
                    break;
                }else if(choose == 5) {
                    player.focus();
                    break;
                }else {
                    System.out.println("select 1 to 5 to do action");
                    choose = in.nextInt();
                }
            }
            if(enemyroom.allEnemyDeath()) {
                System.out.println("you clear this room");
                //ชนะ
                return false;
            }
            for(int i = 0; i < enemyroom.getEnemies().size(); i++) {
                if(enemyroom.getEnemies().get(i).getHp() > 0) {
                    enemyroom.getEnemies().get(i).performAction(player);
                }
            }
            if(isGameEnd(player)) {
                System.out.println("you lose");
                //เเพ้
                return true;
            }
        }
    }

    public static void useItem(Player player) {
        Scanner in = new Scanner(System.in);
        ArrayList<Potion> bag = player.getInventory().getArrayListPotion();
        while(true) {
            for(int i = 0; i < bag.size(); i++) {
                System.out.println((i+1) + ": " + bag.get(i).toString());
            }
            System.out.println("select potion which you want to use");
            int select = in.nextInt();
            if(select > bag.size() || select <= 0) {
                System.out.println("select again");
            }else {
                bag.get(select-1).use(player);
                player.getInventory().removePotion(bag.get(select-1));
                break;
            }
        }
    }

    public static void attack(Player player, Enemyroom enemyroom) {
        Scanner in = new Scanner(System.in);
        while(true) {
            for(int i = 0; i < enemyroom.getEnemies().size(); i++) {
                System.out.println((i+1) + ": " + enemyroom.getEnemies().get(i).toString());
            }
            System.out.println("select enemy which you want to attack");
            int select = in.nextInt();
            if(select > enemyroom.getEnemies().size() || select <= 0) {
                System.out.println("select again");
            }else{
                player.normalAttack(enemyroom.getEnemies().get(select-1));
                break;
            }
        }
    }

    public static void skill(Player player, Enemyroom enemyroom) {
        Scanner in = new Scanner(System.in);
        System.out.println("1: skill1");
        System.out.println("2: skill2");
        System.out.println("3: skill3");
        System.out.println("select your skill");
        int select = in.nextInt();
        while (true) {
            if(select == 1) {
                System.out.println("you use skill 1");
                break;
            }else if(select == 2) {
                System.out.println("you use skill 2");
                break;
            }else if(select == 3) {
                System.out.println("you use skill 3");
                break;
            }else {
                System.out.println("please select 1 to 3");
                select = in.nextInt();
            }
        }
        while(true) {
            for(int i = 0; i < enemyroom.getEnemies().size(); i++) {
                System.out.println((i+1) + ": " + enemyroom.getEnemies().get(i).toString());
            }
            System.out.println("select enemy which you want to attack");
            int selectEnemy = in.nextInt();
            if(selectEnemy > enemyroom.getEnemies().size() || selectEnemy <= 0) {
                System.out.println("select again");
            }else{
                if(select == 1) {
                    player.skill1(enemyroom.getEnemies().get(selectEnemy-1));
                }else if(select == 2) {
                    player.skill2(enemyroom.getEnemies().get(selectEnemy-1));
                }else if(select == 3) {
                    player.skill3(enemyroom.getEnemies().get(selectEnemy-1));
                }
                break;
            }
        }
    }
}
