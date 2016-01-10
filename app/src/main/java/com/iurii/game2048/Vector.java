package com.iurii.game2048;

public class Vector {
    private int[] elements;
    public int getScore() {
        return score;
    }

    private int score = 0;
    public Vector(int[] elements) {
        this.elements = elements;
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

    public int[] getElements() {
        return elements;
    }
    public void getTheNumberOfTheVectorSumOfCollapsing(){
        bubbleZeroes();
        addUpTheNumberOfNeighbors();
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
