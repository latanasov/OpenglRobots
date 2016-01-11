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
     * Array of robots needed to retrieve position/direction of a selected one *
     */
    private Robot robots[];

    /**
     * The robot witch is on focus
     */
    public int robotOnFocus = 0;
    /**
     * The current camera mode
     */
    int currentMode;

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
                gs.vDist * Math.sin(gs.phi) + 40
        );

        eye.add(center);
    }

    /**
     * Computes eye, center, and up, based on the helicopter mode. The camera
     * should focus on the robot.
     */
    //Time passed since the method has been first called
    int lTimeHel = 0;

    private void setHelicopterMode(GlobalState gs, Robot focus) {
        // The distance of the camera above the robot
        final float helicopterCameraheight = 35;

        float x_pos = (float) robots[this.robotOnFocus].position.x;
        float y_pos = (float) robots[this.robotOnFocus].position.y;
        float z_pos = (float) (robots[this.robotOnFocus].position.z);

        // Set the angle of the camera
        float cameraAngle = -10;

        // Calculate camera position
        float x = (float) (x_pos + cameraAngle);
        float y = (float) (y_pos + cameraAngle);
        float z = z_pos + helicopterCameraheight;

        // Set the new coordinates
        eye = new Vector(x, y, z);

        // focus the robot
        center = new Vector(x_pos, y_pos, z_pos);
        //Focus  the next robot every few seconds
        if (lTimeHel + 10 <= Math.round(gs.tAnim)) {
            lTimeHel = Math.round(gs.tAnim);
            robotOnFocus++;
        }
        if (this.robotOnFocus > 3) {
            robotOnFocus = 0;
        }
    }

    /**
     * Computes eye, center, and up, based on the motorcycle mode. The camera
     * should focus on the robot.
     */
    private void setMotorCycleMode(GlobalState gs, Robot focus) {

        //Set center to the focused robot position
        center = robots[this.robotOnFocus].position;
        up = Vector.Z;
        // Calculate the position of the eye
        eye = robots[robotOnFocus].direction.cross(Vector.Z).normalized().scale(20);
        eye = center.add(eye);
        eye = eye.add(up);

    }

    /**
     * Computes eye, center, and up, based on the first person mode. The camera
     * should view from the perspective of the robot.
     */
    //Time passed since the method has been first called
    int lTimeFPM;

    private void setFirstPersonMode(GlobalState gs, Robot focus) {

        //Eye position equals the robot position
        eye = robots[robotOnFocus].position;
        //Move up from the robot
        eye.z = eye.z + 1;
        up = Vector.Z;
        // Look towards the direction of the tangent
        center = robots[robotOnFocus].direction;
        center.normalized().scale(8);
        center = eye.add(center);
        //Focus  the next robot every few seconds
        if (lTimeFPM + 10 <= Math.round(gs.tAnim)) {
            lTimeFPM = Math.round(gs.tAnim);
            robotOnFocus++;
        }
        if (this.robotOnFocus > 3) {
            robotOnFocus = 0;
        }
    }

    /**
     * Computes eye, center, and up, based on the auto mode. The above modes are
     * alternated.
     */
    //Time passed since method has been called;
    float lastTimeAutoMode;

    private void setAutoMode(GlobalState gs, Robot focus) {
        //Change to the next camera mode every few seconds

        if (lastTimeAutoMode + 10 <= Math.round(gs.tAnim)) {
            lastTimeAutoMode = Math.round(gs.tAnim);
            currentMode++;
        }
        if (currentMode > 2) {
            currentMode = 0;
        }

        //Select the appropriate mode
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

    /**
     *
     * @param robots array of robots used to select the focused robot and
     * retrieve its position/direction
     */
    public void initRobots(Robot robots[]) {
        this.robots = robots;
    }

}
