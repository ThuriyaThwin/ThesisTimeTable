package service.csp;

/**
 * Interface which allows interested clients to register at a solution strategy
 * and follow their progress step by step.
 * 
 * @author Thuriya Thwin
 */
public interface CSPStateListener {
	/** Informs about changed assignments. */
	void stateChanged(Assignment assignment, CSP csp);

	/** Informs about changed domains (inferences). */
	void stateChanged(CSP csp);
}
