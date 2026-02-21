package logic;

import character.Character;
import character.Player;
import potion.Potion;
import room.Enemyroom;
import room.Shoproom;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    public static boolean isGameEnd(Character character) {
        return character.getHp() <= 0;
    }

    public static boolean fight(Player player, Enemyroom enemyroom, Scanner in) {

        int turn = 1;

        while (true) {

            System.out.println("\n===== TURN " + turn + " =====");

            showEnemies(enemyroom);
            showPlayer(player);

            boolean actionDone = false;

            while (!actionDone) {

                System.out.println("\n1: Attack");
                System.out.println("2: Block");
                System.out.println("3: Item");
                System.out.println("4: Skill");
                System.out.println("5: Focus");
                System.out.print("Select action: ");

                int choose = getIntInput(in);

                switch (choose) {
                    case 1 -> actionDone = attack(player, enemyroom, in);
                    case 2 -> {
                        player.block();
                        actionDone = true;
                    }
                    case 3 -> {
                        if (player.getInventory().isEmpty()) {
                            System.out.println("Inventory is empty.");
                        } else {
                            actionDone = useItem(player, in);
                        }
                    }
                    case 4 -> actionDone = skill(player, enemyroom, in);
                    case 5 -> {
                        player.focus();
                        actionDone = true;
                    }
                    default -> System.out.println("Select 1 - 5 only.");
                }
            }

            player.endTurn();

            if (isGameEnd(player)) {
                System.out.println("You Lose");
                return true;
            }

            if (enemyroom.allEnemyDeath()) {
                System.out.println("You Clear This Room");
                return false;
            }

            // ===== Enemy Turn =====
            for (int i = 0; i < enemyroom.getEnemies().size(); i++) {
                if (enemyroom.getEnemies().get(i).getHp() > 0) {
                    enemyroom.getEnemies().get(i).performAction(player);
                }
            }

            if (isGameEnd(player)) {
                System.out.println("You Lose");
                return true;
            }

            turn++;
        }
    }

    // ==============================
    // Utility Methods
    // ==============================

    private static int getIntInput(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.println("Please enter a number.");
            in.next(); // clear invalid input
        }
        return in.nextInt();
    }

    private static void showEnemies(Enemyroom enemyroom) {
        System.out.println("===== ENEMIES =====");
        for (int i = 0; i < enemyroom.getEnemies().size(); i++) {
            System.out.println((i + 1) + ": " + enemyroom.getEnemies().get(i));
        }
    }

    private static void showPlayer(Player player) {
        System.out.println("===== PLAYER =====");
        System.out.println(player);
        System.out.println(player.eff());
    }

    // ==============================
    // Actions
    // ==============================

    public static boolean useItem(Player player, Scanner in) {

        ArrayList<Potion> bag = player.getInventory().getArrayListPotion();

        while (true) {

            for (int i = 0; i < bag.size(); i++) {
                System.out.println((i + 1) + ": " + bag.get(i));
            }

            System.out.println((bag.size() + 1) + ": Return");
            System.out.print("Select potion: ");

            int select = getIntInput(in);

            if (select <= 0 || select > bag.size() + 1) {
                System.out.println("Select again.");
            } else if (select == bag.size() + 1) {
                return false;
            } else {
                Potion potion = bag.get(select - 1);
                potion.use(player);
                player.getInventory().removePotion(potion);
                return true;
            }
        }
    }

    public static boolean attack(Player player, Enemyroom enemyroom, Scanner in) {

        while (true) {

            showEnemies(enemyroom);

            System.out.println((enemyroom.getEnemies().size() + 1) + ": Return");
            System.out.print("Select enemy: ");

            int select = getIntInput(in);

            if (select <= 0 || select > enemyroom.getEnemies().size() + 1) {
                System.out.println("Select again.");
            } else if (select == enemyroom.getEnemies().size() + 1) {
                return false;
            } else {
                player.normalAttack(enemyroom.getEnemies().get(select - 1));
                return true;
            }
        }
    }

    public static boolean skill(Player player, Enemyroom enemyroom, Scanner in) {

        int select;

        while (true) {

            System.out.println("1: Skill1");
            System.out.println("2: Skill2");
            System.out.println("3: Skill3");
            System.out.println("4: Return");
            System.out.print("Select skill: ");

            select = getIntInput(in);

            if (select == 4) return false;
            if (select >= 1 && select <= 3) break;

            System.out.println("Select 1 - 4");
        }

        while (true) {

            showEnemies(enemyroom);
            System.out.print("Select enemy: ");

            int selectEnemy = getIntInput(in);

            if (selectEnemy <= 0 || selectEnemy > enemyroom.getEnemies().size()) {
                System.out.println("Select again.");
            } else {

                switch (select) {
                    case 1 -> player.skill1(enemyroom.getEnemies().get(selectEnemy - 1));
                    case 2 -> player.skill2(enemyroom.getEnemies().get(selectEnemy - 1));
                    case 3 -> player.skill3(enemyroom.getEnemies().get(selectEnemy - 1));
                }

                return true;
            }
        }
    }

    public static void store(Player player, Scanner in) {
        Shoproom shoproom = new Shoproom();
        System.out.println("You have entered the Shop Room");
        int select = getIntInput(in);
        while (true) {
            for(int i = 0; i < shoproom.getStore().size(); i++) {
                System.out.println((i+1) + ": " + shoproom.getStore().get(i));
            }
            System.out.println(shoproom.getStore().size()+1 + ": exit");
            System.out.println("Choose an item to buy");
            System.out.println("your gold is " + player.getGold());
            if(select <= 0 || select > shoproom.getStore().size()+1) {
                System.out.println("please select in range");
                select = getIntInput(in);
            }else if(select == shoproom.getStore().size()+1){
                System.out.println("Exit the shop");
                break;
            }else {
                if(shoproom.getStore().get(select-1).buy(player)) {
                    System.out.println("you buy a potion");
                }else {
                    System.out.println("You don't have enough money");
                }
                select = getIntInput(in);
            }
        }
    }

    public static void eventRoom(Player player, Scanner in) {
        Random random = new Random();
        int number = random.nextInt(100) + 1;

        if(number <= 10) {
            System.out.println("You met an angel. The angel blessed you and then departed.");
            System.out.println("your hp + 3, your atk + 2, your def + 1");
            player.setMaxHp(player.getMaxHp() + 3);
            player.setHp(player.getHp() + 3);
            player.setAttack(player.getAttack() + 2);
            player.setDefense(player.getDefense() + 1);
        }else if(number <= 40) {
            System.out.println("You found a chest. You rushed to open the chest without being careful..");
            System.out.println("It is a mimic");
            System.out.println("your lose 5 hp");
            player.setHp(player.getHp() - 5);
        }else if(number <= 70) {
            System.out.println("You found a chest. Open the box carefully.");
            System.out.println("You found 30 gold");
            player.setGold(player.getGold() + 30);
        }else if(number <= 75) {
            System.out.println("You found a demon lord");
            System.out.println("You die instantly");
            player.setHp(0);
        }else if(number <= 85){
            System.out.println("You found a secret book");
            System.out.println("your atk + 5, your def + 2");
            player.setAttack(player.getAttack() + 5);
            player.setDefense(player.getDefense() + 2);
        }else {
            System.out.println("you found an empty room");
            System.out.println("ToT ToT ToT ToT");
        }
    }
}
