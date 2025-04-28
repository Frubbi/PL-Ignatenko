import java.util.*;

class Main {
	  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ввод количества игроков и номера стартового игрока
        System.out.print("Введите количество игроков: ");
        int numPlayers = scanner.nextInt();
        
        System.out.print("Введите номер игрока, с которого начинается игра: ");
        int startPlayer = scanner.nextInt();
        
        // Вызываем функцию для игры
        playGame(numPlayers, startPlayer);
    }
    
    public static void playGame(int numPlayers, int startPlayer) {
        // Список игроков
        List<Integer> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(i);
        }

        // Номер игрока, с которого начинается игра (сначала корректируем на 0, так как индексация в списках с 0)
        int currentIndex = startPlayer - 1;
        
        List<Integer> eliminatedPlayers = new ArrayList<>();
        
        // Пока в игре более одного игрока
        while (players.size() > 1) {
            // Находим индекс третьего игрока для выбывания
            currentIndex = (currentIndex + 2) % players.size();
            int eliminatedPlayer = players.remove(currentIndex); // Убираем игрока из игры
            eliminatedPlayers.add(eliminatedPlayer); // Добавляем в список выбывших
        }

        // Последний оставшийся игрок - победитель
        eliminatedPlayers.add(players.get(0));

        // Выводим результат
        System.out.println("Выбывшие игроки (включая победителя): " + eliminatedPlayers);
    }
}