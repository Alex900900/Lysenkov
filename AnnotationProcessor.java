import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void process(Class<?> clazz) {

        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx annotation = clazz.getAnnotation(DeprecatedEx.class);
            System.out.println("! класс '" + clazz.getSimpleName() +
                    "' устарел – альтернатива: '" + annotation.message() + "'");
        }

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx annotation = method.getAnnotation(DeprecatedEx.class);
                System.out.println("! метод '" + method.getName() +
                        "' устарел – альтернатива: '" + annotation.message() + "'");
            }
        }
    }
}
