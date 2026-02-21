package main;

import character.Player;
import character.classes.Knight;
import character.classes.Mage;
import character.classes.Rogue;
import enemy.basic.Goblin;
import enemy.basic.Rat;
import enemy.basic.Slime;
import logic.GameLogic;
import room.Enemyroom;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("===== Welcome to the Dungeon Game =====");

        // ===== Get Player Name =====
        System.out.print("Enter your name -> ");
        String name = input.nextLine();

        // ===== Select Class =====
        Player player = null;

        while (player == null) {
            System.out.println("\nSelect your class:");
            System.out.println("1: Knight");
            System.out.println("2: Mage");
            System.out.println("3: Rogue");

            int choose = input.nextInt();

            switch (choose) {
                case 1 -> player = new Knight(name);
                case 2 -> player = new Mage(name);
                case 3 -> player = new Rogue(name);
                default -> System.out.println("Please select 1 - 3");
            }
        }

        System.out.println("Now you are a " + player.getClass().getSimpleName());

        // ===== Select Level =====
        int roomRemaining = 0;

        while (roomRemaining == 0) {
            System.out.println("\nSelect game level:");
            System.out.println("1: 5 Rooms");
            System.out.println("2: 7 Rooms");

            int level = input.nextInt();

            switch (level) {
                case 1 -> roomRemaining = 5;
                case 2 -> roomRemaining = 7;
                default -> System.out.println("Please select 1 - 2");
            }
        }

        // ===== Game Loop =====
        int start = 1;
        boolean safe = false;
        while (roomRemaining > 0) {

            System.out.println("\n===== RoomRemaining " + roomRemaining + " =====");

            if(start == 1) {
                Enemyroom room = new Enemyroom();
                room.addEnemy(new Rat());
                boolean gameOver = GameLogic.fight(player, room, input);

                if (gameOver) {
                    System.out.println("GAME OVER");
                    input.close();
                    return;
                }

                player.upgrade();
                player.clearEff();
                player.setGold(player.getGold() + 15);
                safe = true;
                System.out.println("Room clear !!!");
            }else if(start == 5) {
                //boss event
                System.out.println("===== BOSS ROOM =====");
            }else if(start == 7) {
                //boss event
                System.out.println("===== BOSS ROOM =====");
            }else {
                System.out.println("Now you have to decide what to do.");
                System.out.println("1: Enemyroom");
                System.out.println("2: Restroom");
                System.out.println("3: Eventroom");
                System.out.println("4: Shoproom");
                int select = input.nextInt();
                while (true) {
                    if(select == 1 || select == 3) {
                        break;
                    }else if((select == 2 || select == 4) && safe) {
                        break;
                    }else if((select == 2 || select == 4) && !safe) {
                        System.out.println("this time you have to go Enemyroom or Eventroom");
                        select = input.nextInt();
                    }else {
                        System.out.println("please select again 1 to 4");
                        select = input.nextInt();
                    }
                }
                if(select == 1) {
                    Enemyroom room = new Enemyroom();
                    if(start == 2) {
                        room.addEnemy(new Rat());
                        room.addEnemy(new Rat());
                    }else if(start == 3) {
                        room.addEnemy(new Rat());
                        room.addEnemy(new Rat());
                        room.addEnemy(new Goblin());
                    }else if(start == 4) {
                        room.addEnemy(new Rat());
                        room.addEnemy(new Goblin());
                        room.addEnemy(new Goblin());
                    }else  if(start == 6) {
                        room.addEnemy(new Slime());
                        room.addEnemy(new Slime());
                        room.addEnemy(new Goblin());
                    }

                    boolean gameOver = GameLogic.fight(player, room, input);

                    if (gameOver) {
                        System.out.println("GAME OVER");
                        input.close();
                        return;
                    }

                    player.upgrade();
                    player.clearEff();
                    player.setGold(player.getGold() + 15);

                    safe = true;
                    System.out.println("Room clear !!!");
                }else if(select == 2) {
                    System.out.println("you go to Restroom");
                    System.out.println("you have to decide");
                    System.out.println("1: rest");
                    System.out.println("2: training");
                    int choose = input.nextInt();
                    while (true) {
                        if(choose >= 1 && choose <= 2) {
                            break;
                        }else {
                            System.out.println("please select again 1 to 2");
                            choose = input.nextInt();
                        }
                    }
                    if(choose == 1) {
                        player.setHp(player.getMaxHp());
                    }else {
                        player.setAttack(player.getAttack() + 2);
                    }
                    safe = false;
                }else if(select == 3) {
                    GameLogic.eventRoom(player,input);
                    if (player.getHp() <= 0) {
                        System.out.println("GAME OVER");
                        input.close();
                        return;
                    }
                    safe = true;
                }else {
                    GameLogic.store(player,input);
                    safe = false;
                }
            }
            roomRemaining--;
            start++;
        }

        System.out.println("\n===== YOU WIN THE GAME =====");
        input.close();
    }
}
