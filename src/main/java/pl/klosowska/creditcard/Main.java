package pl.klosowska.creditcard;

public class Main {
    public static void main(String[] args) {
        String name = "Monika";
        Double digit = 2.2;

        greet(name);
    }

    static void greet(String name) {
        var helloMessage = String.format("Hello %s", name);
        System.out.println(helloMessage);
    }

}