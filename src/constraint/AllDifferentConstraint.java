package constraint;

import service.csp.Assignment;
import service.csp.Variable;
import service.csp.constraint.Constraint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllDifferentConstraint implements Constraint {
	
	private List<Variable> scope;
	
	public AllDifferentConstraint(List<Variable> vars) {
		this.scope = vars;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		if (scope.size() > 0) {
			
			List<String> list = new ArrayList<String>();
			
			for (Variable variable : scope) {
				
				String value = (String) assignment.getAssignment(variable);
				
				if (value != null) {
					list.add(value);
				}
			}
			
			Set<String> set = new HashSet<String>(list);

            return set.size() >= list.size();
		}
		
		return true;
	}
}