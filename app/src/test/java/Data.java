public class Data {
    int[] arr;

    public Data(int[] arr) {
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }

    @Override
    public String toString() {
        String result = "[ ";

        for (int i = 0; i < arr.length; i++) {
            result += arr[i] + " ";
        }
        result += "]";

        return result;
    }
}
