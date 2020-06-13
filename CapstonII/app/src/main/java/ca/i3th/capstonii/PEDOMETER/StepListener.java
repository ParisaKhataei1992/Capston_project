
package ca.i3th.capstonii.PEDOMETER;

/**
 * Interface implemented by classes that can handle notifications about steps.
 * These classes can be passed to StepDetector.
 */
public interface StepListener {
    public void onStep();
    public void passValue();
}

