// Интерфейс для обслуживания комнат
interface RoomService<T extends Room> {
    void clean(T room);
    void reserve(T room);
    void free(T room);
}

// Кастомное исключение — если комната уже забронирована
class RoomAlreadyReservedException extends RuntimeException {
    public RoomAlreadyReservedException(String message) {
        super(message);
    }
}

// Абстрактный базовый класс Room
abstract class Room {
    protected int number;
    protected int maxPeople;
    protected int pricePerNight;
    protected boolean isReserved;

    public Room(int number, int maxPeople, int pricePerNight) {
        this.number = number;
        this.maxPeople = maxPeople;
        this.pricePerNight = pricePerNight;
        this.isReserved = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " №" + number +
                " (макс. " + maxPeople + " чел, " +
                "цена: " + pricePerNight + ", " +
                (isReserved ? "занята" : "свободна") + ")";
    }
}

// --- Дочерние классы ---

class EconomyRoom extends Room {
    public EconomyRoom(int number, int pricePerNight) {
        super(number, (int)(Math.random() * 2 + 1), pricePerNight); // 1-2 чел
    }
}

class StandardRoom extends EconomyRoom {
    public StandardRoom(int number, int pricePerNight) {
        super(number, pricePerNight);
    }
}

abstract class ProRoom extends Room {
    public ProRoom(int number, int maxPeople, int pricePerNight) {
        super(number, maxPeople, pricePerNight);
    }
}

class LuxRoom extends ProRoom {
    public LuxRoom(int number, int pricePerNight) {
        super(number, (int)(Math.random() * 3 + 2), pricePerNight); // 2-4 чел
    }
}

class UltraLuxRoom extends LuxRoom {
    public UltraLuxRoom(int number, int pricePerNight) {
        super(number, pricePerNight);
    }
}

// --- Реализация интерфейса RoomService ---
class RoomServiceImpl implements RoomService<Room> {

    @Override
    public void clean(Room room) {
        System.out.println("Комната " + room.getNumber() + " убрана.");
    }

    @Override
    public void reserve(Room room) {
        if (room.isReserved())
            throw new RoomAlreadyReservedException("Комната №" + room.getNumber() + " уже забронирована!");
        room.setReserved(true);
        System.out.println("Комната №" + room.getNumber() + " успешно забронирована!");
    }

    @Override
    public void free(Room room) {
        room.setReserved(false);
        System.out.println("Комната №" + room.getNumber() + " теперь свободна.");
    }
}

// --- Тест ---
public class Main {
    public static void main(String[] args) {
        RoomService<Room> service = new RoomServiceImpl();

        EconomyRoom economy = new EconomyRoom(101, 1500);
        StandardRoom standard = new StandardRoom(102, 2000);
        LuxRoom lux = new LuxRoom(201, 5000);
        UltraLuxRoom ultra = new UltraLuxRoom(301, 10000);

        System.out.println(economy);
        System.out.println(lux);
        System.out.println(ultra);

        // Протестируем методы
        service.clean(lux);
        service.reserve(lux);
        System.out.println(lux);

        try {
            service.reserve(lux); // Ошибка
        } catch (RoomAlreadyReservedException e) {
            System.out.println("❌ " + e.getMessage());
        }

        service.free(lux);
        System.out.println(lux);
    }
}
