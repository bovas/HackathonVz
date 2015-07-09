package org.i3.smartmeter.engine.rules;

import java.util.List;

public interface RulingEngine {
	public List<Rule> getApplicableRules();
	public void setRules(List<Rule> rule);
	public void executeRules();	
}
