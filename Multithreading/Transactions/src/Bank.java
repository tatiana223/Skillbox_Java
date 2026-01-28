import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Один из счетов не существует");
        }

        Object lock1 = fromAccountNum.compareTo(toAccountNum) < 0 ? fromAccount : toAccount;
        Object lock2 = fromAccountNum.compareTo(toAccountNum) < 0 ? toAccount : fromAccount;
        synchronized (lock1) {
            synchronized (lock2) {
                if (fromAccount.isBlocked() || toAccount.isBlocked()) {
                    throw new IllegalStateException("Один из счетов заблокирован");
                }
                if (fromAccount.getMoney() < amount) {
                    throw new IllegalArgumentException("Недостаточно средст на счете отправителя");
                }

                fromAccount.setMoney(fromAccount.getMoney() - amount);
                toAccount.setMoney(toAccount.getMoney() + amount);

                if (amount > 50_000) {
                    try {
                        boolean isFraudulent = isFraud(fromAccountNum, toAccountNum, amount);
                        if (isFraudulent) {
                            fromAccount.setBlocked(true);
                            toAccount.setBlocked(true);
                            System.out.println("Транзакция признана мошеннической. Счета заблокированы.");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;
        for (Account account : accounts.values()) {
            sum += account.getMoney();
        }
        return sum;
    }


    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }
}
