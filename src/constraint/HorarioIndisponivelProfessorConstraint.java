package constraint;

import service.csp.Assignment;
import service.csp.Timeslot;
import service.csp.Variable;
import service.csp.constraint.Constraint;

import java.util.ArrayList;
import java.util.List;
public class HorarioIndisponivelProfessorConstraint implements Constraint {
	
	private List<Variable> scope;
	private List<Timeslot> timeslots;
	private Variable professor;
	private String horario;
	
	public HorarioIndisponivelProfessorConstraint(Variable professor, String horario, List<Timeslot> timeslots) {
		
		this.scope = new ArrayList<Variable>();
		this.professor = professor;
		this.horario = horario;
		this.timeslots = timeslots;
		
		this.scope.add(professor);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public Variable getProfessor() {
		return professor;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public List<Timeslot> getTimeslots() {
		return timeslots;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		String valueProfessor = (String) assignment.getAssignment(professor);
		
		if (valueProfessor != null) {
			
			TimeslotProfessorConstraint constraint = new TimeslotProfessorConstraint(timeslots, valueProfessor);
			
			List<Variable> horarios = constraint.getHorariosByProfessor(valueProfessor, assignment);
			
			for (Variable horario : horarios) {
				
				String valueHorario = (String) assignment.getAssignment(horario);
				
				if (valueHorario != null) {
					
					if (valueHorario.equals(getHorario())) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}