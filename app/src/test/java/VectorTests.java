
import com.iurii.game2048.Vector;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class VectorTests {

    @Test
    public void canCreateBoardVector() throws Exception {
        new Vector(null);
    }

    @Test
     public void givenVector_whenExtractingElements_thenTheyReflectVectorState() throws Exception {
        int[] elements = {0, 4, 0, 4};
        Vector vector = new Vector(elements);

        int[] actual = vector.getElements();

        int[] expected = {0, 4, 0, 4};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void givenVector_WhenWeAddTheSameNumber_thenAdditionCorrectly1() throws Exception {
        int[] elements = {0, 4, 0, 4};
        Vector vector = new Vector(elements);
        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        int expected = 8;

        assertEquals(actual, expected);

    }

    @Test
    public void givenVector_WhenWeAddTheSameNumber_thenAdditionCorrectly2() throws Exception {
        int[] elements = {0, 0, 16, 16};
        Vector vector = new Vector(elements);
        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        int expected = 32;

        assertEquals(actual, expected);

    }

    @Test
    public void givenVector_WhenWeAddTheSameNumber_thenAdditionCorrectly3() throws Exception {
        int[] elements = {2, 2, 16, 16};
        Vector vector = new Vector(elements);
        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        int expected = 36;

        assertEquals(actual, expected);

    }

    @Test
    public void givenVector_WhenWeAddTheSameNumber_thenAdditionCorrectly4() throws Exception {
        int[] elements = {16, 2, 16, 2};
        Vector vector = new Vector(elements);
        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        int expected = 0;

        assertEquals(actual, expected);

    }

    @Test
    public void givenVector_WhenWeAddTheSameNumber_thenAdditionCorrectly5() throws Exception {
        int[] elements = {2, 0, 0, 2};
        Vector vector = new Vector(elements);
        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        int expected = 4;

        assertEquals(actual, expected);

    }

    @Test
    public void givenVector_WhenWeAddTheSameNumber_thenAdditionCorrectly6() throws Exception {
        int[] elements = {0, 4, 2, 2};
        Vector vector = new Vector(elements);
        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        int expected = 4;

        assertEquals(actual, expected);

    }

    @Test
    public void givenVector_WhenWeAddTheSameNumber_thenAdditionCorrectly7() throws Exception {
        int[] elements = {128, 0, 128, 2};
        Vector vector = new Vector(elements);
        vector.getTheNumberOfTheVectorSumOfCollapsing();

        int actual = vector.getScore();
        int expected = 256;

        assertEquals(actual, expected);

    }
}
