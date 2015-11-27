/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrace.shape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import robotrace.Vector;

/**
 *
 * @author lyuat
 */
public class Shape {

    private ShapeEnum ShapeType;
    private Color shapeColor;
    private float angleOfRotation;
    private Vector vScale;
    private boolean toBeRotated;
    private boolean toBeScaled;
    private Vector shapePos;
    private Vector originPos;
    private List<Shape> ShapeCollection = new ArrayList<Shape>();
    private double radius;
    private double height;

    public Shape(ShapeEnum shapeType, Color shapeColor, Vector pos,Vector opos) {
        this.ShapeType = shapeType;
        this.shapeColor = shapeColor;
        toBeRotated = false;
        toBeScaled = false;
        this.originPos=opos;
        this.shapePos = opos.add(pos);
    }


    public void setColor(Color newColor) {
        this.shapeColor = newColor;
    }

    public Color getColor() {
        return this.shapeColor;
    }

    public void setAngleOfRotation(float newAngle) {
        this.angleOfRotation = newAngle;
    }

    public float getAngleOfRotation() {
        return this.angleOfRotation;
    }

    public void setScale(Vector newVector) {
        this.setvScale(newVector);
    }

    public Vector getScale() {
        return this.getvScale();
    }

    /**
     * @return the shapePos
     */
    public Vector getShapePos() {
        return shapePos;
    }

    /**
     * @param shapePos the shapePos to set
     */
    public void setShapePos(Vector shapePos) {
        this.shapePos = shapePos;
    }

    /**
     * @return the originPos
     */
    public Vector getOriginPos() {
        return originPos;
    }

    /**
     * @param originPos the originPos to set
     */
    public void setOriginPos(Vector originPos) {
        this.originPos = originPos;
    }

    /**
     * @return the vScale
     */
    public Vector getvScale() {
        return vScale;
    }

    /**
     * @param vScale the vScale to set
     */
    public void setvScale(Vector vScale) {
        this.vScale = vScale;
    }

    /**
     * @return the toBeRotated
     */
    public boolean isToBeRotated() {
        return toBeRotated;
    }

    /**
     * @param toBeRotated the toBeRotated to set
     */
    public void setToBeRotated(boolean toBeRotated) {
        this.toBeRotated = toBeRotated;
    }

    /**
     * @return the toBeScaled
     */
    public boolean isToBeScaled() {
        return toBeScaled;
    }

    /**
     * @param toBeScaled the toBeScaled to set
     */
    public void setToBeScaled(boolean toBeScaled) {
        this.toBeScaled = toBeScaled;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the ShapeType
     */
    public ShapeEnum getShapeType() {
        return ShapeType;
    }

    /**
     * @param ShapeType the ShapeType to set
     */
    public void setShapeType(ShapeEnum ShapeType) {
        this.ShapeType = ShapeType;
    }

    /**
     * @return the ShapeCollection
     */
    public List<Shape> getShapeCollection() {
        return ShapeCollection;
    }

    /**
     * @param ShapeCollection the ShapeCollection to set
     */
    public void setShapeCollection(List<Shape> ShapeCollection) {
        this.ShapeCollection = ShapeCollection;
    }

}
