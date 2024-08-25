package Stream;

import Stream.model.Trader;
import Stream.model.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> transactions2011 = transactions.stream()
                .filter(t->t.getYear()==2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println("Все транзакции за 2011 год отсортированные по сумме: " + transactions2011.toString());

        List<String> towns = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .toList();
        System.out.println("Список неповторяющихся городов, в которых работают трейдеры: " + towns.toString());

        List<String> namesCambridge = transactions.stream()
                .filter(t -> Objects.equals(t.getTrader().getCity(), "Cambridge"))
                .map(t -> t.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .distinct()
                .toList();
        System.out.println("Список трейдеров из Кембриджа: " + namesCambridge.toString());

        List<String> namesAll = transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted(Comparator.naturalOrder())
                .distinct()
                .toList();
        System.out.println("Список трейдеров: " + namesAll.toString());

        boolean tradersMilan = transactions.stream()
                .anyMatch(t -> Objects.equals(t.getTrader().getCity(), "Milan"));

        System.out.println("Существует ли трейдер из Милана: " + tradersMilan);

        List<Integer> transactionsCambridge = transactions.stream()
                .filter(t -> Objects.equals(t.getTrader().getCity(), "Cambridge"))
                .map(Transaction::getValue)
                .toList();
        System.out.println("Список сумм транзакций трейдеров из Кембриджа: " + transactionsCambridge.toString());

        int maxSumm = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare)
                .get();
        System.out.println("Максимальная сумма: " + maxSumm);

        int minSumm = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compare)
                .get();
        System.out.println("Максимальная сумма: " + minSumm);
    }
}