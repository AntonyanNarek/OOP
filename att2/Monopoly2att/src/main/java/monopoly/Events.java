package monopoly;

import static monopoly.Main.log;

public class Events {

    public void getRandomEvent(Player player){
        events((int) (Math.random() * 4), player);
    }

    public void events(int x, Player player){
        switch (x) {
            case 0 -> {
                System.out.println("Вы получили вознаграждение в размере 1000");
                log.info("Получение вознаграждения в размере 1000");
                player.setBalance(player.getBalance() + 1000);
            }
            case 1 -> {
                System.out.println("Вы должны заплатить банку 1000");
                if (player.getBalance() > 1000){
                    player.setBalance(player.getBalance() - 1000);
                } else {
                    player.setBalance(0);
                }
                log.info("Оплата штрафа в размере 1000");
            }
            case 2 -> {
                System.out.println("Вы отправляетесь в тюрьму с противоположным направлением движения");
                player.setLocation(10);
                player.setBlockedMoves(2);
                log.info("Перемещение на поле тюрьмы");
            }
            case 3 -> {
                System.out.println("Пропуск следующего хода");
                player.setBlockedMoves(1);
                log.info("Пропуск следующего хода");
            }
            case 4 -> {
                System.out.println("Вы перемещаетесь на поле старта, получая вознаграждение за пройденный круг");
                player.setLocation(0);
                log.info("Перемещение на поле старта с получением 2000 на баланс ");
            }
        }
    }

}
