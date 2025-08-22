package fr.hattane.ilias.deseigner.model;

public class Dimensions {
    private int width;
    private int height;
    private double scale;

    public Dimensions(int width, int height, double scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
