package main;

import character.Character;
import character.Player;
import character.classes.Knight;
import character.classes.Mage;
import character.classes.Rogue;
import enemy.basic.Rat;
import logic.GameLogic;
import room.Enemyroom;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to ...");
        System.out.println("Enter your name ->");
        String name = input.nextLine();
        System.out.println("Hi " + name + " select your class");
        System.out.println("1: Knight");
        System.out.println("2: Mage");
        System.out.println("3. Rogue");
        int choose = input.nextInt();
        Player player = null;
        while (true) {
            if(choose == 1) {
                player = new Knight(name);
                break;
            }else if(choose == 2) {
                player = new Mage(name);
                break;
            }else if(choose == 3) {
                player = new Rogue(name);
                break;
            }else {
                System.out.println("please select 1 to 3 to get class");
                choose = input.nextInt();
            }
        }
        System.out.println("now you are " + player.getClass() + " Now select your game level");
        System.out.println("1: 5 Room");
        System.out.println("2: 7 Room");
        int level = input.nextInt();
        int roomRemaining;
        while (true) {
            if(level == 1) {
                roomRemaining = 5;
                break;
            }else if(level == 2) {
                roomRemaining = 7;
                break;
            }else {
                System.out.println("please select 1 to 2 to select level");
                level = input.nextInt();
            }
        }
        System.out.println("roomRemaining = " + roomRemaining);
        Enemyroom enemyroom1 = new Enemyroom();
        enemyroom1.addEnemy(new Rat());
        if(GameLogic.fight(player,enemyroom1)) return;
        roomRemaining--;
        System.out.println("roomRemaining = " + roomRemaining);
    }
}
