/*

import com.iurii.game2048.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class VectorAmountTests {

    private Data initial;
    private int expected;


    public VectorAmountTests(Data initial, int expected) {
        this.initial = initial;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "given vector {0}, when collapsing, then it becomes {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Data(new int[]{0, 0, 16, 16}), new Data(new int[]{32})},

        });
    }

    @Test
    public void givenVector_WhenWeAddTheSameNumberOfNeighbors_thenAdditionCorrectly() throws Exception {
        runTest(initial.getArr() );
    }

    private void runTest(int[] initial) {
        Vector vector = new Vector(initial);

        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        assertEquals(expected, actual);
    }
}*/