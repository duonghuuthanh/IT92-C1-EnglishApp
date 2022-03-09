import com.dht.junitdemo.NTTest;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.InputMismatchException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestCase {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After Each");
    }

    @Test
    @DisplayName("Kiem thu cho so chan nguyen to")
    @Tag("critical")
    public void test01() {
        int input = 2;
        boolean expected = true;
        boolean actual = NTTest.isNt(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Kiem thu cho so le nguyen to")
    @Tag("normal")
    public void test02() {
        boolean actual = NTTest.isNt(15);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test so chan khong nguyen to")
    public void test03() {
        boolean actual = NTTest.isNt(4);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Kiem tra ngoai le")
    public void test4() {
        int n = -1;
        Assertions.assertThrows(InputMismatchException.class, () -> {
            NTTest.isNt(n);
        });
    }

    @Test
    @DisplayName("Kiem tra timeout")
    public void test5() {
        Assertions.assertTimeout(Duration.ofNanos(1), () -> {
            NTTest.isNt(100);
        });
    }
    
    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 20, 144, 156})
    public void testEvenNumbers(int n) {
        Assertions.assertFalse(NTTest.isNt(n));
    }
    
    @ParameterizedTest
    @CsvSource({"3,true", "143,false", "71,true"})
    public void testOddNumbers(int n, boolean expected) {
        boolean actual = NTTest.isNt(n);
        Assertions.assertEquals(expected, actual);
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/data/datatest.csv", numLinesToSkip = 1)
    public void testNumbers(int n, boolean expected) {
        boolean actual = NTTest.isNt(n);
        Assertions.assertEquals(expected, actual, String.format("Dang test %d", n));
    }
}
