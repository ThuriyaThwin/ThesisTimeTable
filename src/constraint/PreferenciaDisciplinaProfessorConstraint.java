package constraint;

import service.csp.Assignment;
import service.csp.Variable;
import service.csp.constraint.Constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreferenciaDisciplinaProfessorConstraint implements Constraint {
	
	private List<Variable> scope;
	private Variable professor;
	private Variable disciplina;
	private Map<String, Integer[]> prefs;
	private String[] disciplinas;
	
	public PreferenciaDisciplinaProfessorConstraint(Variable professor, Variable disciplina, Map<String, Integer[]> prefs, String[] disciplinas) {
		this.scope = new ArrayList<Variable>();
		this.scope.add(professor);
		this.scope.add(disciplina);
		this.professor = professor;
		this.disciplina = disciplina;
		this.prefs = prefs;
		this.disciplinas = disciplinas;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public Map<String, Integer[]> getPrefs() {
		return prefs;
	}
	
	public Integer[] getPreferencias(String key) {
		return prefs.get(key);
	}
	
	public boolean contemDisciplina(String key, String disciplina) {
		
		Integer[] values = prefs.get(key);
		
		if (values != null) {
			
			for (Integer value : values) {
				if (disciplinas[value].equals(disciplina))
					return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		
		String valueDisciplina = (String) assignment.getAssignment(disciplina);
		String valueProfessor = (String) assignment.getAssignment(professor);
		
		if (valueDisciplina != null) {
		
			if (professor.getName().endsWith(disciplina.getName())) {
				
				if (valueProfessor != null) {
					return contemDisciplina(valueProfessor, valueDisciplina);
				}
			}
		}
		
		return true;
	}
}
