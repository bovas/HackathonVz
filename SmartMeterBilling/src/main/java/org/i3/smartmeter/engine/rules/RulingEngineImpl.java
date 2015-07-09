package org.i3.smartmeter.engine.rules;

import java.util.LinkedList;
import java.util.List;

public abstract class RulingEngineImpl implements RulingEngine {
	
	protected List<Rule> rules, applicableRules;

	public List<Rule> getApplicableRules() {
		// TODO Auto-generated method stub
		return applicableRules;
	}
	
	public void executeRules() {
		// TODO Auto-generated method stub
		applicableRules = new LinkedList<Rule>();
		for(Rule rule : getRules()){
			if(rule.isSatisfied()){
				applicableRules.add(rule);
			}
		}
	}

	public void setRules(List<Rule> rules) {
		// TODO Auto-generated method stub
		this.rules = rules;
	}

	protected List<Rule> getRules() {
		return rules;
	}

	protected void setApplicableRules(List<Rule> applicableRules) {
		this.applicableRules = applicableRules;
	}
	
}
