import java.util.function.Predicate;

public class Main {

    interface Printable {
        void print();
    }

    public static void main(String[] args) {

        Printable printable = () -> System.out.println("Hello, this is Printable!");
        printable.print();

        Predicate<String> isNotNull = str -> str != null;
        Predicate<String> isNotEmpty = str -> !str.isEmpty();
        Predicate<String> isValidString = isNotNull.and(isNotEmpty);

        String test1 = null;
        String test2 = "";
        String test3 = "Hello";

        System.out.println("test1 valid: " + isValidString.test(test1));
        System.out.println("test2 valid: " + isValidString.test(test2));
        System.out.println("test3 valid: " + isValidString.test(test3));

        Predicate<String> startsWithJorN = str -> str.startsWith("J") || str.startsWith("N");
        Predicate<String> endsWithA = str -> str.endsWith("A");

        Predicate<String> complexCheck = isValidString
                .and(startsWithJorN)
                .and(endsWithA);

        String test4 = "JAVA";
        String test5 = "NOVA";
        String test6 = "HELLO";

        System.out.println("test4: " + complexCheck.test(test4));
        System.out.println("test5: " + complexCheck.test(test5));
        System.out.println("test6: " + complexCheck.test(test6));
    }
}
