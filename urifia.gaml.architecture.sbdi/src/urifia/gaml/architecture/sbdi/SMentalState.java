package urifia.gaml.architecture.sbdi;

import java.util.List;

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

@vars ({ @variable (
		name = "modality",
		type = IType.STRING, 
		doc = @doc ("the modality of the sk_mental state (Spatial by default")),
		@variable ( 
				name = "sk_predicate",
				type = SPredicateType.id,
				doc = @doc ("the sk_predicate about which is the sk_mental state")),
		@variable (
				name = "sk_mental_state",
				type = SMentalStateType.id,
				doc = @doc ("the sk_mental state (name) about which is the sk_mental state")),
		@variable ( 
				name = "spatialknowledge",
				type = SpatialKnowledgeType.id,
				doc = @doc ("the spatial knowledge about which is the sk_mental state")),
		@variable (
				name = "sk_owner",
				type = IType.AGENT,
				doc = @doc ("the sk_owner of the sk_mental state")),
		@variable (
				name = "sk_power",
				type = IType.FLOAT,
				doc = @doc ("the power or sk_power value related to this spatial sk_mental state")),
		@variable (
				name = "sk_lifetime",
				type = IType.INT,
				doc = @doc ("the sk_lifetime of the sk_mental state")) })

public class SMentalState implements IValue{

	String modality;
	SPredicate sk_predicate;
	Double sk_power;
	int sk_lifetime = -1;
	boolean sk_isUpdated = false;
	SMentalState sk_mental;
	SpatialKnowledge sknow;
	IAgent sk_owner;
	List<SMentalState> onHoldUntil;
	List<SMentalState> subintentions;
	SMentalState superIntention;
	
	@getter ("modality")
	public String getModality() {
		return modality;
	}
	
	@getter ("sk_predicate")
	public SPredicate getSk_predicate() {
		return sk_predicate;
	}
	
	@getter ("sk_power")
	public Double getSk_power() {
		return sk_power;
	}
	public int getSk_lifetime() {
		return sk_lifetime;
	}
	
	@getter ("smental_state")
	public SMentalState getSMentalState() {
		return sk_mental;
	}
	
	@getter ("spatial_knowledge")
	public SpatialKnowledge getSknow() {
		return sknow;
	}
	
	@getter ("sk_owner")
	public IAgent getSk_owner() {
		return sk_owner;
	} 
	
	@getter ("subintentions")
	public List<SMentalState> getSubintentions() {
		return subintentions;
	}
	
	@getter ("superIntention")
	public SMentalState getSuperIntention() {
		return superIntention;
	}
	
	//setters
	
	public void setSk_predicate(SPredicate sk_predicate) {
		this.sk_predicate = sk_predicate;
	}

	public void setSk_lifetime(int sk_lifetime) {
		this.sk_lifetime = sk_lifetime;
	}

	public void setSk_isUpdated(boolean sk_isUpdated) {
		this.sk_isUpdated = sk_isUpdated;
	}

	public void setSk_mental(SMentalState sk_mental) {
		this.sk_mental = sk_mental;
	}

	public void setSknow(SpatialKnowledge sknow) {
		this.sknow = sknow;
	}


	public List<SMentalState> getOnHoldUntil() {
		return onHoldUntil;
	}

	public void setModality(final String modality) {
		this.modality = modality;
	}

	public void setPredicate(final SPredicate spred) {
		this.sk_predicate = spred;
	}

	public void setSMentalState(final SMentalState sment) {
		this.sk_mental = sment;
	}

	public void setSpatialknowledge(final SpatialKnowledge sknow) {
		this.sknow = sknow;
	}

	public void setSk_power(final Double po) {
		this.sk_power = po;
	}

	public void setSk_lifeTime(final int lt) {
		this.sk_lifetime = lt;
	}

	public void setSk_owner(final IAgent ag) {
		this.sk_owner = ag;
	}

	public void setSubintentions(final List<SMentalState> subintentions) {
		this.subintentions = subintentions;
	}

	public void setSuperIntention(final SMentalState superSPredicate) {
		this.superIntention = superSPredicate;
	}

	public void setOnHoldUntil(final List<SMentalState> onHoldUntil) {
		this.onHoldUntil = onHoldUntil;
	}

	public void updateSk_lifetime() {
		if (this.sk_lifetime > 0 && !this.sk_isUpdated) {
			this.sk_lifetime = this.sk_lifetime - 1;
			this.sk_isUpdated = true;
		}
	}

	public SMentalState() {
		super();
		this.modality = "";
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_owner = null;
		this.sknow = null;
	}

	public SMentalState(final String mod) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_owner = null;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SPredicate pred) {
		super();
		this.modality = mod;
		this.sk_predicate = pred;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_owner = null;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState ment) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = ment;
		this.sk_power = 1.0;
		this.sk_owner = null;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_owner = null;
		this.sknow = skno;
	}

	public SMentalState(final String mod, final SPredicate pred, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = pred;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState ment, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = ment;
		this.sk_power = 1.0;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_owner = ag;
		this.sknow = skno;
	}

	public SMentalState(final String mod, final SPredicate pred, final Double stre) {
		super();
		this.modality = mod;
		this.sk_predicate = pred;
		this.sk_mental = null;
		this.sk_power = stre;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState ment, final Double stre) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = ment;
		this.sk_power = stre;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno, final Double stre) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = stre;
		this.sknow = skno;
	}

	public SMentalState(final String mod, final SPredicate pred, final int life) {
		super();
		this.modality = mod;
		this.sk_predicate = pred;
		this.sk_mental = null;
		this.sk_lifetime = life;
		this.sk_power = 1.0;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState ment, final int life) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = ment;
		this.sk_lifetime = life;
		this.sk_power = 1.0;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno, final int life) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_lifetime = life;
		this.sk_power = 1.0;
		this.sknow = skno;
	}

	public SMentalState(final String mod, final SPredicate pred, final Double stre, final int life) {
		super();
		this.modality = mod;
		this.sk_predicate = pred;
		this.sk_mental = null;
		this.sk_power = stre;
		this.sk_lifetime = life;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState ment, final Double stre, final int life) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = ment;
		this.sk_power = stre;
		this.sk_lifetime = life;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno, final Double stre, final int life) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = stre;
		this.sk_lifetime = life;
		this.sknow = skno;
	}

	public SMentalState(final String mod, final SPredicate pred, final Double stre, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = pred;
		this.sk_mental = null;
		this.sk_power = stre;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState ment, final Double stre, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = ment;
		this.sk_power = stre;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno, final Double stre, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = stre;
		this.sk_owner = ag;
		this.sknow = skno;
	}

	public SMentalState(final String mod, final SPredicate spred, final int slt, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = spred;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_lifetime = slt;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState ment, final int life, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = ment;
		this.sk_power = 1.0;
		this.sk_lifetime = life;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno, final int life, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = 1.0;
		this.sk_lifetime = life;
		this.sk_owner = ag;
		this.sknow = skno;
	}

	public SMentalState(final String mod, final SPredicate spred, final Double po, final int life, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = spred;
		this.sk_mental = null;
		this.sk_power = po;
		this.sk_lifetime = life;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SMentalState sment, final Double po, final int slt, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = sment;
		this.sk_power = po;
		this.sk_lifetime = slt;
		this.sk_owner = ag;
		this.sknow = null;
	}

	public SMentalState(final String mod, final SpatialKnowledge skno, final Double po, final int slt, final IAgent ag) {
		super();
		this.modality = mod;
		this.sk_predicate = null;
		this.sk_mental = null;
		this.sk_power = po;
		this.sk_lifetime = slt;
		this.sk_owner = ag;
		this.sknow = skno;
	}

	@Override
	public String toString() {
		return serialize(true);
	}

	@Override
	public String serialize(final boolean includingBuiltIn) {
		return modality + "(" + (sk_predicate == null ? "" : sk_predicate) + (sk_mental == null ? "" : sk_mental)
				+ (sknow == null ? "" : sknow) + "," + (sk_owner == null ? "" : sk_owner) + "," + sk_power + "," + sk_lifetime + ")";
	}

	@Override
	public IType<?> getGamlType() {
		return Types.get(SMentalStateType.id);
	}

	@Override
	public String stringValue(final IScope scope) throws GamaRuntimeException {
		return modality + "(" + (sk_predicate == null ? "" : sk_predicate) + (sk_mental == null ? "" : sk_mental)
				+ (sknow == null ? "" : sknow) + "," + (sk_owner == null ? "" : sk_owner) + "," + sk_power + "," + sk_lifetime + ")";
	}

	@Override
	public IValue copy(final IScope scope) throws GamaRuntimeException {
		final SMentalState tempMental = new SMentalState(modality);
		tempMental.setSk_lifeTime(sk_lifetime);
		tempMental.setSk_power(sk_power);
		tempMental.setSk_owner(sk_owner);
		if (sk_predicate != null) {
			tempMental.setSk_predicate(sk_predicate);
			return tempMental;
		} else if (sk_mental != null) {
			tempMental.setSk_mental(sk_mental);
			return tempMental;
		} else if (sknow != null) {
			tempMental.setSknow(sknow);
			return tempMental;
		}
		return tempMental;
	}

	@Override
	public int hashCode() {
		// final int prime = 31;
		final int result = 1;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final SMentalState other = (SMentalState) obj;
		// if(other.getModality()!=this.modality){return false;}
		if (this.sk_predicate == null && other.getSk_predicate() != null) { return false; }
		if (this.sk_predicate != null && other.getSk_predicate() == null) { return false; }
		if (this.sk_predicate != null && other.getSk_predicate() != null) {
			if (!other.getSk_predicate().equals(this.sk_predicate)) { return false; }
		}
		if (this.sk_mental == null && other. getSMentalState() != null) { return false; }
		if (this.sk_mental != null && other.getSMentalState() == null) { return false; }
		if (this.sk_mental != null && other.getSMentalState() != null) {
			if (!other.getSMentalState().equals(this.sk_mental)) { return false; }
		}
		if (this.sknow == null && other.getSknow()!= null) { return false; }
		if (this.sknow != null && other.getSknow() == null) { return false; }
		if (this.sknow != null && other.getSknow() != null) {
			if (!other.getSknow().equals(this.sknow)) { return false; }
		}
		// if (subintentions == null) {
		// if (other.subintentions != null && !other.subintentions.isEmpty()) {
		// return false;
		// }
		// } else if (!subintentions.equals(other.subintentions)) {
		// return false;
		// }
		// if (superIntention == null) {
		// if (other.superIntention != null) {
		// return false;
		// }
		// } else if (superIntention.partialEquality(other.superIntention)) {
		// return false;
		// }
		if (this.sk_owner != null && other.getSk_owner() != null) {
			if (!other.getSk_owner().equals(this.sk_owner)) { return false; }
		}
		// if(other.getStrength()!=this.sk_power){return false;}
		return true;
	}

	
}