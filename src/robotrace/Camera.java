package robotrace;

/**
 * Implementation of a camera with a position and orientation.
 */
class Camera {

    /**
     * The position of the camera.
     */
    public Vector eye = new Vector(3f, 6f, 5f);

    /**
     * The point to which the camera is looking.
     */
    public Vector center = Vector.O;

    /**
     * The up vector.
     */
    public Vector up = Vector.Z;

    /**
     * Array of robots needed to retrieve positions of a selected one *
     */
    private Robot robots[];

    /**
     * The robot witch is on focus
     */
    public int robotOnFocus = 0;

    /**
     * Updates the camera viewpoint and direction based on the selected camera
     * mode.
     */
    public void update(GlobalState gs, Robot focus) {

        switch (gs.camMode) {

            // Helicopter mode
            case 1:
                setHelicopterMode(gs, focus);
                break;

            // Motor cycle mode    
            case 2:
                setMotorCycleMode(gs, focus);
                break;

            // First person mode    
            case 3:
                setFirstPersonMode(gs, focus);
                break;

            // Auto mode    
            case 4:
                setAutoMode(gs, focus);
                break;

            // Default mode    
            default:
                setDefaultMode(gs);
        }
    }

    /**
     * Computes eye, center, and up, based on the camera's default mode.
     */
    private void setDefaultMode(GlobalState gs) {
        // Set position for camera
        center = gs.cnt;
        up = Vector.Z;
       
        //Calculate Eye x y z
        eye = new Vector(
                gs.vDist * Math.cos(gs.theta) * Math.cos(gs.phi),
                gs.vDist * Math.sin(gs.theta) * Math.cos(gs.phi),
                gs.vDist * Math.sin(gs.phi)+40
        );

        eye.add(center);
    }

    /**
     * Computes eye, center, and up, based on the helicopter mode. The camera
     * should focus on the robot.
     */
    private void setHelicopterMode(GlobalState gs, Robot focus) {

        // The height of the camera above the robot
        final float helicopterCameraheight = 35;

        //The position of the focused robot
        float x_robot = (float) robots[this.robotOnFocus].position.x;
        float y_robot = (float) robots[this.robotOnFocus].position.y;
        float z_robot = (float) (robots[this.robotOnFocus].position.z);

        // Set the angle of the camera
        float cameraAngle = -10;

        // Calculate camera position
        float x = (float) (x_robot + cameraAngle);
        float y = (float) (y_robot + cameraAngle);
        float z = z_robot + helicopterCameraheight;

        // Set the new coordinates
        eye = new Vector(x, y, z);

        // focus the robot
        center = new Vector(x_robot, y_robot, z_robot);
    }

    /**
     * Computes eye, center, and up, based on the motorcycle mode. The camera
     * should focus on the robot.
     */
    private void setMotorCycleMode(GlobalState gs, Robot focus) {

        // center is the robot position 
        center = robots[0].position;
        up = Vector.Z;

        // calculate the eye position 
        eye = robots[0].direction.cross(Vector.Z).normalized().scale(20);
        eye = center.add(eye);
        eye = eye.add(new Vector(0, 0, 1));

    }

    /**
     * Computes eye, center, and up, based on the first person mode. The camera
     * should view from the perspective of the robot.
     */
    private void setFirstPersonMode(GlobalState gs, Robot focus) {

        eye = robots[0].position;
        eye.z = eye.z + 1;
        up = Vector.Z;
        // center is in the direction of the tangent 
        center = robots[0].direction;
        center.normalized().scale(10);
        center = eye.add(center);

    }

    /**
     * Computes eye, center, and up, based on the auto mode. The above modes are
     * alternated.
     */
    float lastTime;
    int currentMode;

    private void setAutoMode(GlobalState gs, Robot focus) {

        if (lastTime + 10 <= Math.round(gs.tAnim)) {
            lastTime = Math.round(gs.tAnim);
            currentMode++;
        }
        if (currentMode > 2) {
            currentMode = 0;
        }

        switch (currentMode) {

            case 0:
                setHelicopterMode(gs, focus);
                break;
            case 1:
                setMotorCycleMode(gs, focus);
                break;
            case 2:
                setFirstPersonMode(gs, focus);
                break;
        }
    }

    public void initRobots(Robot robots[]) {
        this.robots = robots;
    }

}
