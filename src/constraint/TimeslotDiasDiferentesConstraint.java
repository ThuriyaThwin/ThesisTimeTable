package constraint;

import service.csp.Assignment;
import service.csp.Variable;
import service.csp.constraint.Constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeslotDiasDiferentesConstraint implements Constraint {
	
	private List<Variable> scope;
	private Variable timeslot1;
	private Variable timeslot2;
	String [] dias;
	
	public TimeslotDiasDiferentesConstraint(Variable timeslot1, Variable timeslot2, String [] dias) {
		
		this.scope = new ArrayList<Variable>();
		
		this.timeslot1 = timeslot1;
		this.timeslot2 = timeslot2;
		
		this.scope.add(timeslot1);
		this.scope.add(timeslot2);
		
		this.dias = dias;
	}
	
	public Variable getTimeslot1() {
		return timeslot1;
	}
	
	public Variable getTimeslot2() {
		return timeslot2;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		if (timeslot1 == null || timeslot2 == null || (timeslot1 == timeslot2))
			return true;
		
		List<String> lista = new ArrayList<String>(Arrays.asList(dias));
		
		String valueTimeslot1 = (String) assignment.getAssignment(timeslot1);
		String valueTimeslot2 = (String) assignment.getAssignment(timeslot2);
		
		if (valueTimeslot1 != null && valueTimeslot2 != null) {
			
			String dia1 = valueTimeslot1.split("_")[0];
			String dia2 = valueTimeslot2.split("_")[0];
			
			int indexDia1 = lista.indexOf(dia1);
			int indexDia2 = lista.indexOf(dia2);
			
			if (dia1.equals(dia2))
				return false;

            return indexDia1 + indexDia2 >= 2;
		}
		
		return true;
	}

}
