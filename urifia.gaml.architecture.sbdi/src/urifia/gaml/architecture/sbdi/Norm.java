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
		doc = @doc ("The name of this norm")),
		@variable (
				name = NormStatement.INTENTION,
				type = SPredicateType.id,
				doc = @doc ("A string representing the current intention of this Norm")),
		@variable (
				name = NormStatement.OBLIGATION,
				type = SPredicateType.id,
				doc = @doc ("A string representing the current obligation of this Norm")),
		@variable (
				name = SbdiArchitecture.FINISHEDWHEN,
				type = IType.STRING,
				doc = @doc("represents the condition whena norm is finished")),
		@variable (
				name = SbdiArchitecture.INSTANTANEAOUS,
				type = IType.STRING,
				doc = @doc("indicates if the norm is instantaneous")),
		@variable (
				name = NormStatement.LIFETIME,
				type = IType.INT,
				doc = @doc("the lifetim during which the norm is considered violated when it has been violated"))
})

//Classe qui permet de d�finir les normes comme type, contenant le norm statement, sur l'exemple des plans
public class Norm implements IValue{

	private NormStatement normStatement;
	private Boolean isViolated;
	private Integer lifetimeViolation;
	private Boolean noLifetime;
	private Boolean isApplied;
	private Boolean isSanctioned;
	
	@getter ("name")
	public String getName() {
		return this.normStatement.getName();
	}
	
	@getter (NormStatement.LIFETIME)
	public Integer getLifetime(final IScope scope) {
		return this.lifetimeViolation;
	}
	
	@getter ("when")
	public String getWhen() {
		return this.normStatement._when.serialize(true);
	}
	
	@getter (NormStatement.INTENTION)
	public SPredicate getIntention(final IScope scope) {
		if(this.normStatement!=null && this.normStatement._intention!=null){
			return (SPredicate) this.normStatement._intention.value(scope);
		} else {
			return null;
		}
	}
	
	@getter (NormStatement.OBLIGATION)
	public SPredicate getObligation(final IScope scope) {
		if(this.normStatement!=null && this.normStatement._obligation!=null){
			return (SPredicate) this.normStatement._obligation.value(scope);
		} else {
			return null;
		}
	}
	
	@getter (SbdiArchitecture.FINISHEDWHEN)
	public String getFinishedWhen() {
		if(this.normStatement!=null && this.normStatement._executedwhen!=null){
			return this.normStatement._executedwhen.serialize(true);
		} else {
			return null;
		}
	}
	
	@getter (SbdiArchitecture.INSTANTANEAOUS)
	public String getInstantaneous() {
		if(this.normStatement!=null && this.normStatement._instantaneous!=null){
			return this.normStatement._instantaneous.serialize(true);
		} else {
			return null;
		}
	}
	
	public NormStatement getNormStatement() {
		return this.normStatement;
	}
	
	public Boolean getViolated(){
		return this.isViolated;
	}
	
	public Boolean getApplied(){
		return this.isApplied;
	}
	
	public Boolean getSanctioned(){
		return this.isSanctioned;
	}
	
	public Norm(){
		super();
		this.isViolated = false;
		this.isApplied = false;
		this.lifetimeViolation = -1;
		this.noLifetime = true;
		this.isSanctioned = false;
	}
	
	public Norm(final NormStatement statement) {
		super();
		this.normStatement = statement;
		this.lifetimeViolation = -1;
		this.isViolated = false;
		this.noLifetime = true;
		this.isApplied = false;
		this.isSanctioned = false;
	}
	
	public Norm(final NormStatement statement, final IScope scope) {
		super();
		this.normStatement = statement;
		this.isViolated = false;
		this.isApplied = false;
		this.isSanctioned = false;
		if(statement._lifetime!=null){
			this.lifetimeViolation = (Integer) statement._lifetime.value(scope);
			this.noLifetime = false;
		} else {
			this.lifetimeViolation = -1;
			this.noLifetime = true;
		}
	}
	
	public void setViolation(final Boolean violation){
		this.isViolated = violation;
		this.isApplied = !violation;
	}
	
	public void setSanctioned(final Boolean sanction){
		this.isSanctioned = sanction;
	}
	
	public void sanctioned(){
		this.isSanctioned = true;
	}
	
	public void violated(final IScope scope){
		this.isViolated = true;
		this.isApplied = false;
		if(this.normStatement._lifetime!=null){
			this.lifetimeViolation = (Integer) this.normStatement._lifetime.value(scope);
		} else {
			this.lifetimeViolation = 1;
		}
		this.noLifetime=false;
	}
	
	public void applied(final IScope scope){
		this.isApplied = true;
		this.isViolated = false;
		this.lifetimeViolation=-1;
		this.noLifetime=false;
	}
	
	public void updateLifeime(){
		if(!noLifetime && isViolated){
			this.lifetimeViolation --;
		}
		if(this.lifetimeViolation<0 && !noLifetime){
			isViolated = false;
			isSanctioned = false;
			noLifetime=true;
		}
	}
	
	@Override
	public String serialize(boolean includingBuiltIn) {
		return "Norm(" + normStatement + ")";
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final Norm other = (Norm) obj;
		if (normStatement == null) {
			if (other.normStatement != null) { return false; }
		} else if (!normStatement.equals(other.normStatement)) { return false; }
		return true;
	}
	
	@Override
	public IType<?> getGamlType() {
		return Types.get(NormType.id);
//		return null;
	}

	@Override
	public String stringValue(IScope scope) throws GamaRuntimeException {
		return "Norm(" + normStatement	+ ")";
	}

	@Override
	public IValue copy(IScope scope) throws GamaRuntimeException {
		return new Norm(normStatement);
	}

}