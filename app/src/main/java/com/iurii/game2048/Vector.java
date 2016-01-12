package com.iurii.game2048;

public class Vector {

    private int[] elements;
    private int score;

    public Vector(int[] elements) {
        this.elements = elements;
    }

    public int[] getElements() {
        return elements;
    }

    public int getScore() {
        return score;
    }

    public void collapse() {
        bubbleZeroes();
        compress();
        bubbleZeroes();
    }

    public void bubbleZeroes() {
        for (int round = 0; round < elements.length; round++) {
            for (int index = 0; index < elements.length - 1; index++) {
                if (elements[index] == 0) {
                    swap(index, index + 1);
                }
            }
        }
    }

    public void getTheNumberOfTheVectorSumOfCollapsing() {
        bubbleZeroes();
        addUpTheNumberOfNeighbors();
    }

    private void compress() {

        for (int j = 0; j < elements.length - 1; j++) {
            if (elements[j] == elements[j + 1]) {
                elements[j] = elements[j] + elements[j + 1];
                elements[j + 1] = 0;
            }
        }
    }

    private void swap(int index1, int index2) {
        int temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    private void addUpTheNumberOfNeighbors() {

        for (int j = 0; j < elements.length - 1; j++) {
            if (elements[j] == elements[j + 1]) {
                elements[j] = elements[j] + elements[j + 1];
                elements[j + 1] = 0;

                score = score + elements[j];
            }
        }
    }
}
