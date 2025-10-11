public class Main {
public static void main(String[] args) {
// Создаем банковские счета
BankAccount account1 = new BankAccount("Иван Иванович", 1000);
BankAccount account2 = new BankAccount("Петр Петрович", 500);

// Проверка начальных балансов
System.out.println("Счет 1: " + account1);
System.out.println("Счет 2: " + account2);

// Пополнение счета
boolean depositSuccess = account1.deposit(200);
System.out.println("Пополнение счета 1 на 200: " + depositSuccess);
System.out.println("Счет 1 после пополнения: " + account1);

// Снятие средств
boolean withdrawSuccess = account1.withdraw(300);
System.out.println("Снятие со счета 1 на 300: " + withdrawSuccess);
System.out.println("Счет 1 после снятия: " + account1);

// Попытка снятия суммы превышающей баланс
withdrawSuccess = account1.withdraw(1000);
System.out.println("Снятие со счета 1 на 1000 (превышение баланса): " + withdrawSuccess);

// Перевод средств между счетами
boolean transferSuccess = account1.transfer(account2, 500);
System.out.println("Перевод 500 со счета 1 на счет 2: " + transferSuccess);
System.out.println("Счет 1 после перевода: " + account1);
System.out.println("Счет 2 после перевода: " + account2);

// Попытка перевода суммы превышающей баланс
transferSuccess = account1.transfer(account2, 800);
System.out.println("Перевод 800 со счета 1 на счет 2 (превышение баланса): " + transferSuccess);

// Проверка блокировки счета
account1.blockAccount();
withdrawSuccess = account1.withdraw(200);
System.out.println("Снятие со счета 1 на 200 (счет заблокирован): " + withdrawSuccess);

// Разблокировка счета и повторное снятие
account1.unblockAccount();
withdrawSuccess = account1.withdraw(100);
System.out.println("Снятие со счета 1 на 100 (после разблокировки): " + withdrawSuccess);
System.out.println("Счет 1 после снятия: " + account1);
}
}
`