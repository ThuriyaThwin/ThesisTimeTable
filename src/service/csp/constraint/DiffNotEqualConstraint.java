package service.csp.constraint;

import service.csp.Assignment;
import service.csp.Variable;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a binary constraint which forbids equal values.
 * 
 * @author Thuriya Thwin
 */
public class DiffNotEqualConstraint implements Constraint {

	private Variable var1;
	private Variable var2;
	private int diff;
	private List<Variable> scope;

	public DiffNotEqualConstraint(Variable var1, Variable var2, int diff) {
		this.var1 = var1;
		this.var2 = var2;
		this.diff = diff;
		scope = new ArrayList<Variable>(2);
		scope.add(var1);
		scope.add(var2);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		Object value1 = assignment.getAssignment(var1);
		Object value2 = assignment.getAssignment(var2);
		return (value1 == null || value2 == null ||
				value1 instanceof Integer &&
				value2 instanceof Integer &&
				Math.abs((Integer) value1 - (Integer) value2) != diff);
	}
}
