

import com.iurii.game2048.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class VectorCollapseTests {
    private Data collapsed;
    private Data initial;

    public VectorCollapseTests(Data initial, Data collapsed) {
        this.initial = initial;
        this.collapsed = collapsed;
    }

    @Parameterized.Parameters(name = "given vector {0}, when collapsing, then it becomes {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Data(new int[]{0, 0, 16, 16}), new Data(new int[]{32, 0, 0, 0})},
                {new Data(new int[]{16, 16, 0, 0}), new Data(new int[]{32, 0, 0, 0})},
                {new Data(new int[]{2, 2, 16, 16}), new Data(new int[]{4, 32, 0, 0})},
                {new Data(new int[]{16, 2, 16, 2}), new Data(new int[]{16, 2, 16, 2})},
                {new Data(new int[]{2, 0, 2, 0}), new Data(new int[]{4, 0, 0, 0})},
                {new Data(new int[]{0, 2, 0, 2}), new Data(new int[]{4, 0, 0, 0})},
                {new Data(new int[]{2, 0, 0, 2}), new Data(new int[]{4, 0, 0, 0})},
                {new Data(new int[]{0, 4, 2, 2}), new Data(new int[]{4, 4, 0, 0})},
        });
    }

    @Test
    public void givenVector_whenCollapsing_thenCollapsedCorrectly() throws Exception {
        runTest(initial.getArr(), collapsed.getArr());
    }

    private void runTest(int[] initial, int[] collapsed) {
        Vector vector = new Vector(initial);

        vector.collapse();

        int[] actual = vector.getElements();
        assertArrayEquals(collapsed, actual);
    }
}
