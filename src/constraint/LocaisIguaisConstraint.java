package constraint;

import service.csp.Assignment;
import service.csp.Variable;
import service.csp.constraint.Constraint;

import java.util.ArrayList;
import java.util.List;

public class LocaisIguaisConstraint implements Constraint {
	
	private List<Variable> scope;
	
	Variable local1;
	Variable local2;
	
	public LocaisIguaisConstraint(Variable local1, Variable local2) {
		
		this.scope = new ArrayList<Variable>();
		
		this.local1 = local1;
		this.local2 = local2;
		
		this.scope.add(local1);
		this.scope.add(local2);
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		String valueLocal1 = (String) assignment.getAssignment(local1);
		String valueLocal2 = (String) assignment.getAssignment(local2);
		
		if (valueLocal1 != null && valueLocal2 != null) {

            return valueLocal1.equals(valueLocal2);
		}
		
		return true;
	}
	
}
