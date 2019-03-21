package main;


import service.algo.BacktrackingStrategy;
import service.csp.Assignment;
import service.csp.CSP;
import service.csp.CSPStateListener;
import service.csp.Variable;

import java.util.Iterator;

public class Main {
	
	public static void main(String[] args) {
		
		Timetable timetable = new Timetable();
		
		BacktrackingStrategy bts = new BacktrackingStrategy();
		
		bts.addCSPStateListener(new CSPStateListener() {
		   
			@Override
		    public void stateChanged(Assignment assignment, CSP csp) {
		        System.out.println("Assignment evolved : " + assignment);
		    }

		    @Override
		    public void stateChanged(CSP csp) {
		        System.out.println("CSP evolved : " + csp);
		    }
		});
		
		double start = System.currentTimeMillis();
		
		Assignment sol = bts.solve(timetable);
		
		double end = System.currentTimeMillis();
		
		System.out.println();
		
		for (int i = 0; i < sol.getVariables().size(); i++) {
			
			Variable disciplina = new Variable("D" + (i+1));
			
			if (sol.getVariables().contains(disciplina)) {
				
				Iterator<Variable> it = sol.getVariables().iterator();
				
				while (it.hasNext()) {
				
					Variable temp = it.next();
					
					if (temp.getName().endsWith(disciplina.getName())) {
						
						System.out.print(temp + " = " + sol.getAssignment(temp));
						
						if (it.hasNext()) {
							
							System.out.print(", ");
						}
					}
				}
								
			} else {
				
				break;
			}
			
			System.out.println("\n");
		}
		
		System.out.println("\nTime to solve = " + (end - start));
	}
}