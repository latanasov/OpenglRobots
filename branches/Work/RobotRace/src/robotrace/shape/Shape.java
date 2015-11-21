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
public ShapeEnum ShapeType;
private Color shapeColor;
private float angleOfRotation;
private Vector vScale;
public boolean toBeRotated;
public boolean toBeScaled;
public Vector shapePos;
public List<Shape> ShapeCollection=new ArrayList<Shape>();
public double radius;
public double height;


public Shape(ShapeEnum shapeType,Color shapeColor,Vector pos)
{
   this.ShapeType=shapeType;
   this.shapeColor=shapeColor;
   toBeRotated=false;
   toBeScaled=false;
  this.shapePos=pos;
}
public Shape(ShapeEnum shapeType,Color shapeColor,float angleOfRotation,Vector pos)
{
   this.ShapeType=shapeType;
   this.angleOfRotation=angleOfRotation;
   toBeRotated=true;
   toBeScaled=false;
   this.shapePos=pos;

}

public Shape(ShapeEnum shapeType,Color shapeColor,Vector vScale,Vector pos)
{
   this.ShapeType=shapeType;
   this.vScale=vScale;
   toBeRotated=false;
   toBeScaled=true;
     this.shapePos=pos;

}
public Shape(ShapeEnum shapeType,Color shapeColor,float angleOfRotation,Vector vScale,Vector pos)
{
   this.angleOfRotation=angleOfRotation;
   this.ShapeType=shapeType;
   this.vScale=vScale;
   toBeRotated=true;
   toBeScaled=true;
     this.shapePos=pos;

}

public void setColor(Color newColor)
{
    this.shapeColor=newColor;
}

public Color getColor()
{
    return this.shapeColor;
}

public void setAngleOfRotation(float newAngle)
{
    this.angleOfRotation=newAngle;
}

public float getAngleOfRotation()
{
    return this.angleOfRotation;
}

public void setScale(Vector newVector)
{
    this.vScale=newVector;
}

public Vector getScale()
{
    return this.vScale;
}

}
