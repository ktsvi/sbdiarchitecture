package urifia.gaml.architecture.sbdi;
import msi.gama.common.interfaces.IValue;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
import urifia.gaml.architecture.sbdi.SPredicateType;

@vars ({ @variable (
		name = "sk_name",
		type = IType.STRING,
		doc = @doc ("the sk name of the spatial knowledge")),
		@variable (
				name = "about_sk_predicate",
				type = SPredicateType.id,
				doc = @doc ("the SK predicate related this S knowledge")),
		@variable (
				name = "sk_power",
				type = IType.FLOAT,
				doc = @doc ("the  power related to S knowledge")),
		@variable (
				name = "sk_decay_value",
				type = IType.FLOAT,
				doc = @doc ("the sk_decay_value value associated at the S knowledge")),
		@variable (
				name = "sk_owner",
				type = IType.AGENT,
				doc = @doc ("the agent owning the S knowledge")),
		@variable (
				name = "sk_lifetime",
				type = IType.INT,
				doc = @doc ("the predicate about_sk_predicate which is the S knowledge")),
       })

public class SpatialKnowledge implements IValue {
	String sk_name;
	SPredicate about_sk_predicate;
	Double sk_power = 1.0;
	Double sk_decay_value = 0.0; 
	Double sk_lifetime = 0.0;
	IAgent sk_owner;
	private boolean noAgentCause = true;
	private boolean noSk_power = true;
	private boolean noAbout = true;

	@getter ("sk_name")
	public String getSk_name() {
		return sk_name;
	} 

	@getter ("sk_power")
	public Double getSk_power() {
		return sk_power;
	}

	@getter ("about_sk_predicate")
	public SPredicate getAbout_sk_predicate() {
		return about_sk_predicate;
	}

	@getter ("sk_decay_value")
	public Double getSk_decay_value() {
		return sk_decay_value;
	}

	@getter ("sk_owner")
	public IAgent getSk_owner() {
		return sk_owner;
	}

	public boolean getNoSk_power() {
		return this.noSk_power;
	}

	public void setSk_name(final String sk_name) {
		this.sk_name = sk_name;
	}

	public void setSk_power(final Double po) {
		this.sk_power = po;
		this.noSk_power = false;
	}

	public void setAbout(final SPredicate ab) {
		this.about_sk_predicate = ab;
		this.noAbout = false;
	}

	public void setSk_decay_value(final Double de) {
		this.sk_decay_value = de;
	}

	public void setSk_owner(final IAgent own) {
		this.sk_owner = own;
	} 

	public SpatialKnowledge() {
		this.sk_name = "No SK";
		this.about_sk_predicate = null;
		this.sk_power = null;
		this.sk_owner = null;
		this.sk_lifetime = 0.0;
	}

	public SpatialKnowledge(final String skname) {
		this.sk_name = skname;
		this.about_sk_predicate = null;
		this.sk_power = null;
		this.sk_owner = null;
		this.sk_lifetime = 0.0;
	}

	public SpatialKnowledge(final String skname, final Double sk_po) {
		this.sk_name = skname;
		this.about_sk_predicate = null;
		this.sk_power = sk_po;
		this.sk_owner = null;
		this.sk_lifetime = 0.0;
		this.noSk_power = false;
	}

	public SpatialKnowledge(final String skname, final SPredicate ab) {
		this.sk_name = skname;
		this.about_sk_predicate = ab;
		this.sk_owner = null;
		this.sk_lifetime = 0.0;
		this.noSk_power = false;
		this.noAbout = false;
	}

	public SpatialKnowledge(final String skname, final IAgent ag) {
		this.sk_name = skname;
		this.sk_owner = ag;
	}

	public SpatialKnowledge(final String skname, final Double skpo, final Double decv) {
		this.sk_name = skname;
		this.about_sk_predicate = null;
		this.sk_owner = null;
		this.sk_power = skpo;
		this.noAbout = false;
		this.sk_decay_value = decv;
		this.noSk_power = false;
	}

	public SpatialKnowledge(final String skname, final Double skpo, final SPredicate ab) {
		this.sk_name = skname;
		this.sk_power = skpo;
		this.about_sk_predicate = ab;
		this.sk_owner = null;
		this.noSk_power = false;
		this.noAbout = false;
	}

	public SpatialKnowledge(final String sk_name, final SPredicate ab, final IAgent ag) {
		this.sk_name = sk_name;
		this.about_sk_predicate = ab;
		this.sk_owner = ag;
		this.noAbout = false;
		this.noAgentCause = ag == null;
	}

	public SpatialKnowledge(final String sk_name, final Double intens, final IAgent ag) {
		this.sk_name = sk_name;
		this.sk_power = intens;
		this.about_sk_predicate = null;
		this.sk_owner = ag;
		this.noSk_power = false;
		this.noAgentCause = ag == null;
	}

	public SpatialKnowledge(final String sk_name, final Double intens, final SPredicate ab, final Double de) {
		this.sk_name = sk_name;
		this.sk_power = intens;
		this.about_sk_predicate = ab;
		this.sk_owner = null;
		this.sk_owner = null;
		this.sk_decay_value = de;
		this.noSk_power = false;
		this.noAbout = false;
	}

	public SpatialKnowledge(final String sk_name, final Double intens, final Double de, final IAgent ag) {
		this.sk_name = sk_name;
		this.sk_power = intens;
		this.about_sk_predicate = null;
		this.sk_owner = ag;
		this.sk_owner = null;
		this.sk_decay_value = de;
		this.noSk_power = false;
		this.noAgentCause = ag == null;
	}

	public SpatialKnowledge(final String sk_name, final Double intens, final SPredicate ab, final IAgent ag) {
		this.sk_name = sk_name;
		this.sk_power = intens;
		this.about_sk_predicate = ab;
		this.sk_owner = ag;
		this.sk_owner = null;
		this.noSk_power = false;
		this.noAgentCause = ag == null;
		this.noAbout = false;
	}

	public SpatialKnowledge(final String sk_name, final Double intens, final SPredicate ab, final Double de, final IAgent ag) {
		this.sk_name = sk_name;
		this.sk_power = intens;
		this.about_sk_predicate = ab;
		this.sk_owner = ag;
		this.sk_owner = null;
		this.sk_decay_value = de;
		this.noSk_power = false;
		this.noAbout = false;
		this.noAgentCause = ag == null;
	}

	public void decayIntensity() {
		this.sk_power = this.sk_power - this.sk_decay_value;
	}

	@Override
	public String toString() {
		return serialize(true);
	}

	@Override
	public String serialize(final boolean includingBuiltIn) {
		if (sk_power > 0) {
			return "spatial_knowledge(" + sk_name + "," + sk_power + "," + about_sk_predicate + "," + sk_decay_value + "," + sk_owner + "," + sk_owner
					+ ")";
		}
		return "spatial_knowledge(" + sk_name + "," + about_sk_predicate + "," + sk_decay_value + "," + sk_owner + "," + sk_owner + ")";
	}

	@Override
	public String stringValue(final IScope scope) throws GamaRuntimeException {
		return sk_name;
	}

	@Override
	public IValue copy(final IScope scope) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, about_sk_predicate, sk_decay_value, sk_owner);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final SpatialKnowledge other = (SpatialKnowledge) obj;
		if (sk_name == null) {
			if (other.sk_name != null) { return false; }
		} else if (!sk_name.equals(other.sk_name)) { return false; }
		if (noAbout && noAgentCause || other.noAbout && other.noAgentCause) { return true; }
		/*
		 * if(about_sk_predicate==null){ if(other.about_sk_predicate!=null){return false;} }else
		 */if (about_sk_predicate != null && other.about_sk_predicate != null && !about_sk_predicate.equalsSpatialKnowledges(other.about_sk_predicate)) { return false; }
		/*
		 * if(agentCause==null){ if(other.agentCause!=null){return false;} }else
		 */
		/*
		 * if (agentCause != null && other.agentCause != null && !agentCause.equals(other.agentCause)) { return false; }
		 */
		if (sk_owner != null && other.sk_owner != null && !sk_owner.equals(other.sk_owner)) { return false; }
		return true;
	}

	@Override
	public IType<?> getGamlType() {
		return Types.get(SpatialKnowledgeType.id);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
