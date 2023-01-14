package urifia.gaml.architecture.sbdi;

import msi.gama.common.interfaces.IValue;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.IType;
import msi.gaml.types.Types;

@vars ({ @variable (
		name = "name", 
		type = IType.STRING,
		doc = @doc ("The name of this SBDI plan")),
		@variable (
				name = "todo",
				type = IType.STRING,
				doc = @doc("represent the when facet of a plan")),
		@variable (
				name = SbdiPlanStatement.INTENTION,
				type = SMentalStateType.id,
				doc = @doc ("A string representing the current intention of this SBDI plan")),
		@variable (
				name = SbdiArchitecture.FINISHEDWHEN,
				type = IType.STRING,
				doc = @doc("a string representing the finished condition of this plan")),
		@variable (
				name = SbdiArchitecture.INSTANTANEAOUS,
				type = IType.STRING, 
				doc = @doc("indicates if the plan is instantaneous"))
		/*
		 * @var(name = "value", type = IType.NONE),
		 * 
		 * @var(name = "parameters", type = IType.MAP),
		 */
		// @var(name = "values", type = IType.MAP), @var(name = "priority", type
		// = IType.FLOAT),
		// @var(name = "date", type = IType.FLOAT), @var(name = "subintentions",
		// type = IType.LIST),
		// @var(name = "on_hold_until", type = IType.NONE)
})
public class BDIPlan implements IValue {

	private SbdiPlanStatement planstatement;

	@getter ("name")
	public String getName() {
		return this.planstatement.getName();
	}

	@getter ("todo")
	public String getWhen() {
		return this.planstatement._when.serialize(true);
	}

	@getter (SbdiArchitecture.FINISHEDWHEN)
	public String getFinishedWhen() {
		return this.planstatement._executedwhen.serialize(true);
	}

	@getter (SbdiPlanStatement.INTENTION)
	public SPredicate getIntention(final IScope scope) {
		return (SPredicate) this.planstatement._intention.value(scope);
	}

	@getter (SbdiArchitecture.INSTANTANEAOUS)
	public String getInstantaneous() {
		return this.planstatement._instantaneous.serialize(true);
	}

	public SbdiPlanStatement getPlanStatement() {
		return this.planstatement;
	}

	public BDIPlan() {
		super();
	}

	public BDIPlan(final SbdiPlanStatement statement) {
		super();
		this.planstatement = statement;
	}

	public void setSbdiPlanStatement(final SbdiPlanStatement statement) {
		this.planstatement = statement;

	}

	@Override
	public String toString() {
		return serialize(true);
	}

	@Override
	public String serialize(final boolean includingBuiltIn) {
		return "BDIPlan(" + planstatement.getName()
		// +(values == null ? "" : "," + values) +
				+ ")";
	}

	@Override
	public String stringValue(final IScope scope) throws GamaRuntimeException {
		return "BDIPlan(" + planstatement.getName()+")"
		// +(values == null ? "" : "," + values)
		;
	}

	@Override
	public IValue copy(final IScope scope) throws GamaRuntimeException {
		return new BDIPlan(planstatement);
	}

	public boolean isSimilarName(final BDIPlan other) {
		if (this == other) { return true; }
		if (other == null) { return false; }
		if (planstatement == null) {
			if (other.planstatement != null) { return false; }
		} else if (!planstatement.equals(other.planstatement)) { return false; }
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (planstatement == null ? 0 : planstatement.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final BDIPlan other = (BDIPlan) obj;
		if (planstatement == null) {
			if (other.planstatement != null) { return false; }
		} else if (!planstatement.equals(other.planstatement)) { return false; }
		return true;
	}

	/**
	 * Method getType()
	 * 
	 * @see msi.gama.common.interfaces.ITyped#getGamlType()
	 */
	@Override
	public IType<?> getGamlType() {
		return Types.get(BDIPlanType.id);
	}
}
