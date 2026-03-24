@DeprecatedEx(message = "Используйте NewTestClass")
public class TestClass {

    @DeprecatedEx(message = "Используйте newMethod()")
    public void oldMethod() {
        System.out.println("Old method");
    }

    public void newMethod() {
        System.out.println("New method");
    }
}
