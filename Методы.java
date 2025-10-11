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

// Геттеры
public String getOwnerName() {
return ownerName;
}

public int getBalance() {
return balance;
}

public LocalDateTime getOpeningDate() {
return openingDate;
}

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
public boolean deposit(int amount) {
if (amount > 0 && !isBlocked) {
balance += amount;
return true; // Операция успешна
} else {
System.out.println("Ошибочное значение суммы или счет заблокирован. Пополнение невозможно.");
return false; // Операция неуспешна
}
}

// Метод для снятия средств со счета
public boolean withdraw(int amount) {
if (amount > 0 && amount <= balance && !isBlocked) {
balance -= amount;
return true; // Операция успешна
} else {
System.out.println("Недостаточно средств или счет заблокирован. Снятие невозможно.");
return false; // Операция неуспешна
}
}

// Метод для перевода средств на другой счет
public boolean transfer(BankAccount otherAccount, int amount) {
if (otherAccount != null && withdraw(amount)) {
return otherAccount.deposit(amount); // Пытаемся пополнить другой счет
}
System.out.println("Перевод невозможен.");
return false; // Операция неуспешна
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

