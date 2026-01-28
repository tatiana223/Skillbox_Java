import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Account  acc1 = new Account();
        acc1.setAccNumber("1");
        acc1.setMoney(100000);

        Account acc2 = new Account();
        acc2.setAccNumber("2");
        acc2.setMoney(5000);

        Map<String, Account> accounts = new HashMap<>();

        accounts.put("1", acc1);
        accounts.put("2", acc2);

        bank.setAccounts(accounts);

        bank.transfer("1", "2", 40000);

        System.out.println("Баланс acc1: " + bank.getBalance("1"));
        System.out.println("Баланс acc2: " + bank.getBalance("2"));

        System.out.println("Баланс всего банка " + bank.getSumAllAccounts());

    }
}
