import java.time.LocalDateTime;

public class BankAccount {
// Поля класса
private String ownerName; // Имя владельца
private int balance; // Баланс
private LocalDateTime openingDate; // Дата открытия
private boolean isBlocked; // Флаг, заблокирован ли счет

// Конструктор с параметрами: имя владельца и начальный баланс
public BankAccount(String ownerName, int initialBalance) {
this.ownerName = ownerName;
this.balance = initialBalance;
this.openingDate = LocalDateTime.now(); // Устанавливаем дату на момент создания
this.isBlocked = false; // По умолчанию счет не заблокирован
}

// Конструктор, принимающий только имя владельца
public BankAccount(String ownerName) {
this.ownerName = ownerName;
this.balance = 0; // Начальный баланс 0
this.openingDate = LocalDateTime.now(); // Устанавливаем дату на момент создания
this.isBlocked = false; // По умолчанию счет не заблокирован
}

// Геттер для имени владельца
public String getOwnerName() {
return ownerName;
}

// Геттер для баланса
public int getBalance() {
return balance;
}

// Геттер для даты открытия
public LocalDateTime getOpeningDate() {
return openingDate;
}

// Геттер для статуса блокировки счета
public boolean isBlocked() {
return isBlocked;
}

// Метод для блокировки счета
public void blockAccount() {
isBlocked = true;
}

// Метод для разблокировки счета
public void unblockAccount() {
isBlocked = false;
}

// Метод для пополнения счета
public void deposit(int amount) {
if (!isBlocked) {
balance += amount;
} else {
System.out.println("Счет заблокирован. Пополнение невозможно.");
}
}

// Метод для снятия средств со счета
public void withdraw(int amount) {
if (!isBlocked) {
if (amount <= balance) {
balance -= amount;
} else {
System.out.println("Недостаточно средств.");
}
} else {
System.out.println("Счет заблокирован. Снятие невозможно.");
}
}

@Override
public String toString() {
return "BankAccount{" +
"ownerName='" + ownerName + '\'' +
", balance=" + balance +
", openingDate=" + openingDate +
", isBlocked=" + isBlocked +
'}';
}
}