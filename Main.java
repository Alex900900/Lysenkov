import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== №2.1 Массивы (Работа с парком машин) =====");
        task2_1();

        System.out.println("\n===== №2.2 Коллекции (Управление моделями) =====");
        task2_2();

        System.out.println("\n===== №2.3 Equals/hashCode (Сравнение автомобилей) =====");
        task2_3();

        System.out.println("\n===== №2.4 Stream API (Анализ автопарка) =====");
        task2_4();
    }

    // ---------- №2.1 ----------
    public static void task2_1() {
        Random random = new Random();
        int[] years = new int[50];

        for (int i = 0; i < years.length; i++) {
            years[i] = 2000 + random.nextInt(26); // от 2000 до 2025
        }

        System.out.println("Машины после 2015 года:");
        for (int year : years) {
            if (year > 2015) System.out.print(year + " ");
        }
        System.out.println();

        int currentYear = 2025;
        double avgAge = Arrays.stream(years)
                .map(y -> currentYear - y)
                .average()
                .orElse(0);
        System.out.printf("Средний возраст авто: %.2f лет\n", avgAge);
    }

    // ---------- №2.2 ----------
    public static void task2_2() {
        List<String> models = new ArrayList<>(Arrays.asList(
                "Toyota Camry", "BMW X5", "Audi A4", "Tesla Model S", "Tesla Model 3",
                "BMW X5", "Kia Rio", "Audi A4", "Toyota Corolla", "Tesla Model X"
        ));

        // Замена Tesla → ELECTRO_CAR
        models.replaceAll(m -> m.contains("Tesla") ? "ELECTRO_CAR" : m);

        // Удаляем дубликаты и сортируем в обратном алфавитном порядке
        Set<String> uniqueModels = new TreeSet<>(Collections.reverseOrder());
        uniqueModels.addAll(models);

        System.out.println("Отсортированные модели без дубликатов:");
        uniqueModels.forEach(System.out::println);

        System.out.println("\nИтоговый Set:");
        System.out.println(uniqueModels);
    }

    // ---------- №2.3 ----------
    public static void task2_3() {
        Set<Car> cars = new HashSet<>();

        cars.add(new Car("VIN001", "Camry", "Toyota", 2020, 40000, 18000));
        cars.add(new Car("VIN002", "Model S", "Tesla", 2022, 10000, 70000));
        cars.add(new Car("VIN001", "Camry", "Toyota", 2020, 40000, 18000)); // дубликат
        cars.add(new Car("VIN003", "X5", "BMW", 2019, 60000, 35000));

        System.out.println("Машины без дубликатов VIN:");
        cars.forEach(System.out::println);

        List<Car> sorted = new ArrayList<>(cars);
        Collections.sort(sorted);
        System.out.println("\nОтсортированные по году (новые → старые):");
        sorted.forEach(System.out::println);
    }

    // ---------- №2.4 ----------
    public static void task2_4() {
        List<Car> cars = List.of(
                new Car("VIN001", "Camry", "Toyota", 2020, 40000, 18000),
                new Car("VIN002", "Model S", "Tesla", 2022, 10000, 70000),
                new Car("VIN003", "X5", "BMW", 2019, 60000, 35000),
                new Car("VIN004", "A4", "Audi", 2021, 30000, 28000),
                new Car("VIN005", "Rio", "Kia", 2018, 80000, 15000)
        );

        // 1️⃣ фильтрация по пробегу < 50_000
        List<Car> lowMileage = cars.stream()
                .filter(c -> c.getMileage() < 50000)
                .collect(Collectors.toList());
        System.out.println("Машины с пробегом < 50 000 км:");
        lowMileage.forEach(System.out::println);

        // 2️⃣ сортировка по цене (по убыванию)
        List<Car> sortedByPrice = cars.stream()
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .collect(Collectors.toList());
        System.out.println("\nТоп-3 самых дорогих машин:");
        sortedByPrice.stream().limit(3).forEach(System.out::println);

        // 3️⃣ средний пробег
        double avgMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.printf("\nСредний пробег всех машин: %.2f км\n", avgMileage);

        // 4️⃣ группировка по производителю
        Map<String, List<Car>> grouped = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        System.out.println("\nГруппировка по производителю:");
        grouped.forEach((manufacturer, list) -> {
            System.out.println(manufacturer + ":");
            list.forEach(c -> System.out.println("  " + c));
        });
    }
}

// ===== Класс Car =====
class Car implements Comparable<Car> {
    private String vin;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;
    private double price;

    public Car(String vin, String model, String manufacturer, int year, int mileage, double price) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public String getVin() { return vin; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year);
    }

    @Override
    public String toString() {
        return manufacturer + " " + model + " (" + year + ", VIN: " + vin + ", Пробег: " + mileage + " км, Цена: " + price + "$)";
    }
}
