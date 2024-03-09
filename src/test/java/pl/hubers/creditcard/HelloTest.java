package pl.hubers.creditcard;

import org.junit.jupiter.api.Test;

public class HelloTest {
    @Test
    void helloWorldTest() {

        ///AAa - arrange
        var a = 2;
        var b = 4;
        ///Act // When
        var result = a + b;
        ///Assert // Then / expected
        assert result == 6;
    }

    @Test
    void itShowsHello() {
        var hello = "Hello Jakub";


    }
}
