package org.i3.smartmeter.engine.rules;

import java.util.List;

public class RulingEngineFactory {

	private RulingEngine rulingEngine;
	private List<Rule> rules;

	public static RulingEngineFactory getInstance() {
		return new RulingEngineFactory();
	}
	
	public List<Rule> performRuling(){
		List<Rule> applicableRules = null;
		if(rulingEngine!=null && rules!=null && !rules.isEmpty()){
			rulingEngine.setRules(rules);
			rulingEngine.executeRules();
			applicableRules = rulingEngine.getApplicableRules();
		}
		return applicableRules;
	}
	
	public RulingEngine getRulingEngine() {
		return rulingEngine;
	}
	
	public void setRulingEngine(RulingEngine rulingEngine) {
		this.rulingEngine = rulingEngine;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
}
