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
/**
 * Class representation of a shape.A shape is defined by the following
 * properties: ShapeType- defines the type of the shape(cube,sphere etc)
 * angleOfRotation-the rotation angle of the shape vScale- scale vector which
 * determines the scale factor toBeRotated-true/false based on whether the
 * shapes needs to be rotated toBeScaled-true/false based on whether the shapes
 * needs to be scaled shapePos- the (relative) position of the shape
 * originPos-the origin from which the shapePos is determined ShapeCollection-in
 * case the shape is of type ComplexShape, it consists of multiple shapes
 * radius-in case the shape is of type sphere or cylinder radius is required
 * height- in case the shape is of type cylinder height is required
 *
 * @author lyuat
 */
public class Shape {

    private ShapeEnum ShapeType;
    private Color shapeColor;
    private float angleOfRotation = 0;
    private Vector vScale;
    private boolean toBeRotated;
    private boolean toBeScaled;
    private boolean toBeAnimated;
    private boolean toBeTextured;
    private Vector shapePos;
    private Vector originPos;
    private List<Shape> ShapeCollection = new ArrayList<Shape>();
    private double radius;
    private double height;

    /**
     * Constructor for shape, sets the shapetype,color,shape position, and
     * originin position*
     */
    public Shape(ShapeEnum shapeType, Color shapeColor, Vector pos, Vector opos) {
        this.ShapeType = shapeType;
        this.shapeColor = shapeColor;
        toBeRotated = false;
        toBeScaled = false;
        this.originPos = opos;
        this.shapePos = opos.add(pos);
    }

    /**
     * Set color method
     */
    public void setColor(Color newColor) {
        this.shapeColor = newColor;
    }

    /**
     * get color method
     */
    public Color getColor() {
        return this.shapeColor;
    }

    /**
     * Set angle of rotation method
     */
    public void setAngleOfRotation(float newAngle) {
        this.angleOfRotation = newAngle;
    }

    /**
     * get angle of rotation method
     */
    public float getAngleOfRotation() {
        return this.angleOfRotation;
    }

    /**
     * Set scale method
     */
    public void setScale(Vector newVector) {
        this.setvScale(newVector);
    }

    /**
     * get scale method
     */
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

    public void updateShapePos() {
        this.shapePos.add(this.originPos);
    }

    /**
     * @return the toBeAnimated
     */
    public boolean isToBeAnimated() {
        return toBeAnimated;
    }

    /**
     * @param toBeAnimated the toBeAnimated to set
     */
    public void setToBeAnimated(boolean toBeAnimated) {
        this.toBeAnimated = toBeAnimated;
    }

    /**
     * @return the toBeTextured
     */
    public boolean isToBeTextured() {
        return toBeTextured;
    }

    /**
     * @param toBeTextured the toBeTextured to set
     */
    public void setToBeTextured(boolean toBeTextured) {
        this.toBeTextured = toBeTextured;
    }
}
