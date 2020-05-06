package tracker;

import org.junit.Assert;
import org.junit.Test;

public class MemTrackerSingleTest {
    /**
     * Первый синглтон
     */
    TrackerSingleFirst first = TrackerSingleFirst.INSTANCE;

    /**
     * Второй сигнлтон
     */
    TrackerSingleSecond second = TrackerSingleSecond.getInstance();

    /**
     * Третий синглтон
     */
    TrackerSingleThird third = TrackerSingleThird.getINSTANCE();

    /**
     * Четвёртый сигнлтон
     */
    TrackerSingleFour four = TrackerSingleFour.getInstance();

    @Test
    public void whenFirstTest() {
        TrackerSingleFirst out = TrackerSingleFirst.INSTANCE;
        boolean condition = first == out;
        Assert.assertTrue(condition);
    }

    @Test
    public void whenSecondTest() {
        TrackerSingleSecond out = TrackerSingleSecond.getInstance();
        boolean condition = second == out;
        Assert.assertTrue(condition);
    }

    @Test
    public void whenThirdTest() {
        TrackerSingleThird out = TrackerSingleThird.getINSTANCE();
        boolean condition = third == out;
        Assert.assertTrue(condition);
    }

    @Test
    public void whenFourTest() {
        TrackerSingleFour out = TrackerSingleFour.getInstance();
        boolean condition = four == out;
        Assert.assertTrue(condition);
    }

}
