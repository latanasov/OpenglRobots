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
                gs.vDist * Math.sin(gs.phi)
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

        // The distance from the robot
        final int distance = 15;

        // Get the position of the robot
        float x_robot = (float) robots[0].position.x;
        float y_robot = (float) robots[0].position.y;
        float z_robot = (float) (robots[0].position.z + 0.5 * 4);

        // Get the angle of the robot
        float angle = robots[0].robotAngle;

        // Calculate camera position
        float x = (float) (x_robot + distance * Math.cos(angle + 5));
        float y = (float) (y_robot + distance * Math.sin(angle + 5));
        float z = z_robot;

        // Set the new coordinates
        eye = new Vector(x, y, z);

        // Look towards the robot, which is the center
        center = new Vector(0, 0, 0);
    }

    /**
     * Computes eye, center, and up, based on the first person mode. The camera
     * should view from the perspective of the robot.
     */
    private void setFirstPersonMode(GlobalState gs, Robot focus) {
// Get the position of the last robot

//TODO change to look from last robot
        float x_robot = (float) robots[0].position.x;
        float y_robot = (float) robots[0].position.y;
        float z_robot = (float) (robots[0].position.z + 4);

        // Get the angle of the robot
        float angle = (float) (robots[0].robotAngle * Math.PI);

        // Calculate camera viewing point
        float x = (float) (x_robot + Math.cos(angle));
        float y = (float) (y_robot + Math.sin(angle));
        float z = z_robot;

        // Set the new coordinates
        eye = new Vector(x_robot, y_robot, z_robot);

        // Look towards the robot
        center = new Vector(x, y, z);
    }

    /**
     * Computes eye, center, and up, based on the auto mode. The above modes are
     * alternated.
     */
    private void setAutoMode(GlobalState gs, Robot focus) {
        // code goes here ...
    }

    public void initRobots(Robot robots[]) {
        this.robots = robots;
    }

}
