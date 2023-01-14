package urifia.gaml.architecture.sbdi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		name = "sp_name",
		type = IType.STRING,
		doc = @doc ("the sp_name of spatial predicate")),
		@variable (
				name = "sp_is_true",
				type = IType.BOOL,
				doc =@doc ("the truth value of spatial predicate")),
		@variable (
				name = "sp_date",
				type = IType.BOOL,
				doc = @doc ("the date of spatial predicate")),
		@variable (
				name = "sp_region1",
				type = IType.GEOMETRY,
				doc = @doc ("the first region about spatial predicate")),
		@variable (
				name = "sp_region2",
				type = IType.GEOMETRY,
				doc = @doc ("the second region about spatial predicate")),
		@variable (
				name = "sp_rccRelationship",
				type = IType.MAP,
				doc = @doc ("the RCC relationship with their Matrix sp_rccRelationship (PO, EC, DC, TPP, NTPP or other")),
		@variable (
				name = "subintentions",
				type = IType.LIST,
				doc = @doc ("the subintentions of the predicate")),
		@variable (
				name = "on_hold_until",
				type = IType.NONE,
				doc = @doc ("the list of intention that must be fullfiled before resuming to an intention related to this predicate")),
		@variable (
				name = "super_intention",
				type = IType.NONE,
				doc = @doc ("the super-intention of the predicate")),
		@variable (
				name = "sp_agentGetpredicate",
				type = IType.AGENT,
				doc = @doc ("the agent getting spatial predicate")) 
       })
public class SPredicate implements IValue{

	String sp_name;
	Map<String, Object> sp_rccRelationship;
	Double sp_date;
	String sp_region1;
	String sp_region2;
	IAgent sp_agentGetpredicate;
	boolean sp_is_true = true;
	boolean sp_isUpdated = false;
	List<SMentalState> onHoldUntil;
	List<SMentalState> subintentions;
	SMentalState superIntention;

	@getter ("sp_name")
	public String getSp_name() {
		return sp_name;
	}
	
	@getter ("sp_region2")
	public String getSp_region2() {
		return sp_region2;
	}
	
	public void setSp_region2(String region2) {
		this.sp_region2 = region2;
	}
	
	@getter ("sp_rccRelationship")
	public Map<String, Object> getValues() {
		return sp_rccRelationship;
	}
	public void setValues(Map<String, Object> sp_rccRelationship) {
		this.sp_rccRelationship = sp_rccRelationship;
	}
	
	@getter ("sp_date")
	public Double getSp_date() {
		return sp_date;
	}
	public void setSp_date(Double date) {
		this.sp_date = date;
	}
	
	@getter ("sp_region1")
	public String getSp_region1() {
		return sp_region1;
	}
	public void setSp_region1(String sp_region1) {
		this.sp_region1 = sp_region1;
	}
	
	@getter ("sp_is_true")
	public boolean getSp_is_true() {
		return sp_is_true;
	}
	public void setSp_is_true(boolean val) {
		this.sp_is_true = val;
	}
	
	@getter ("agentgetpredicate")
	public IAgent getSp_agentGetpredicate() {
		return sp_agentGetpredicate;
	}
	public void setSp_agentGetpredicate(IAgent sp_agentGetpredicate) {
		this.sp_agentGetpredicate = sp_agentGetpredicate;
	}

	public void setOnHoldUntil(final List<SMentalState> onHoldUntil) {
		this.onHoldUntil = onHoldUntil;
	}

	@getter ("subintentions")
	public List<SMentalState> getSubintentions() {
		return subintentions;
	}

	@getter ("superIntention")
	public SMentalState getSuperIntention() {
		return superIntention;
	}

	public List<SMentalState> getOnHoldUntil() {
		return onHoldUntil;
	}

	public void setSuperIntention(final SMentalState superPredicate) {
		this.superIntention = superPredicate;
	}

	public void setSubintentions(final List<SMentalState> subintentions) {
		this.subintentions = subintentions;
	}
	
	public SPredicate(String sp_name, Map<String, Object> sp_rccRelationship, Double sp_date, String sp_region1,
			String sp_region2, IAgent sp_agentGetpredicate) {
		super();
		this.sp_name = sp_name;
		this.sp_rccRelationship = sp_rccRelationship;
		this.sp_date = sp_date;
		this.sp_region1 = sp_region1;
		this.sp_region2 = sp_region2;
		this.sp_agentGetpredicate = sp_agentGetpredicate;
	}
	
	public SPredicate(String sp_name, Map<String, Object> sp_rccRelationship, String sp_region1, String sp_region2) {
		super();
		this.sp_name = sp_name;
		this.sp_rccRelationship = sp_rccRelationship;
		this.sp_date = sp_date;
		this.sp_region1 = sp_region1;
		this.sp_region2 = sp_region2;
		this.sp_agentGetpredicate = sp_agentGetpredicate;
	}
	
	public SPredicate() {
		super();
		this.sp_name = "";
		this.sp_agentGetpredicate = null;
	}

	public SPredicate(final String sp_name) {
		super();
		this.sp_name = sp_name;
		this.sp_agentGetpredicate = null;
	}
	
	public SPredicate(final String sp_name,final boolean ist) {
		super();
		this.sp_name = sp_name;
		this.sp_is_true = ist;
	}
	
	public SPredicate(final String sp_name, final Map<String, Object> sp_rccRelationship, final boolean sp_istrue) {
		super();
		this.sp_name = sp_name;
		sp_is_true = sp_istrue;
		this.sp_rccRelationship = sp_rccRelationship;
		this.sp_agentGetpredicate = null;
	}
	
	public SPredicate(final String sp_name, String reg1, String reg2, final Map<String, Object> sp_rccRelationship) {
		super();
		this.sp_name = sp_name;
		this.sp_region1 = reg1;
		this.sp_region2 = reg2;
		this.sp_rccRelationship = sp_rccRelationship;
		this.sp_agentGetpredicate = null;
	}

	public SPredicate(final String sp_name, final Map<String, Object> sp_rccRelationship, final boolean ist, IAgent ag) {
		super();
		this.sp_name = sp_name;
		this.sp_rccRelationship = sp_rccRelationship;
		this.sp_agentGetpredicate = ag;
		this.sp_is_true = ist;
	}
	
	public SPredicate(final String sp_name, final Map<String, Object> sp_rccRelationship, IAgent ag) {
		super();
		this.sp_name = sp_name;
		this.sp_rccRelationship = sp_rccRelationship;
		this.sp_agentGetpredicate = null;
		this.sp_agentGetpredicate = ag;
	}
	
	public SPredicate(final String sp_name, final Map<String, Object> sp_rccRelationship) {
		super();
		this.sp_name = sp_name;
		this.sp_rccRelationship = sp_rccRelationship;
		this.sp_agentGetpredicate = null;
	}
	
	
	public SPredicate(final String sp_name, final IAgent ag) {
		super();
		this.sp_name = sp_name;
		this.sp_agentGetpredicate = ag;
	}
	

	@Override
	public String toString() {
		return serialize(true);
	}
    
	public String serialize(final boolean includingBuiltIn) {
		return "spredicate(" + sp_name + (sp_rccRelationship == null ? "" : "," + sp_rccRelationship) + (sp_agentGetpredicate == null ? "" : "," + sp_agentGetpredicate)
				+ "," + sp_is_true +")";
	} 

	public String stringValue(final IScope scope) throws GamaRuntimeException {
		return "spredicate(" + sp_name + (sp_rccRelationship == null ? "" : "," + sp_rccRelationship) + (sp_agentGetpredicate == null ? "" : "," + sp_agentGetpredicate)
				+ "," + sp_is_true +")";
	}

	public SPredicate copy(final IScope scope) throws GamaRuntimeException {
		return new SPredicate(sp_name, sp_rccRelationship == null ? null : new LinkedHashMap<>(sp_rccRelationship));
	}

	public SPredicate copy() throws GamaRuntimeException {
		if (sp_rccRelationship != null && sp_agentGetpredicate != null) {
			return new SPredicate(sp_name, new LinkedHashMap<>(sp_rccRelationship), sp_is_true, sp_agentGetpredicate);
		}
		if (sp_rccRelationship != null) { return new SPredicate(sp_name, new LinkedHashMap<>(sp_rccRelationship), sp_is_true); }
		return new SPredicate(sp_name);
	}

//	public void updateLifetime() {
//		if (this.lifetime > 0 && !this.isUpdated) {
//			this.lifetime = this.lifetime - 1;
//			this.isUpdated = true;
//		}
//	}

	public boolean isSimilarName(final SPredicate other) {
		if (this == other) { return true; }
		if (other == null) { return false; }
		if (sp_name == null) {
			if (other.sp_name != null) { return false; }
		} else if (!sp_name.equals(other.sp_name)) { return false; }
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (sp_name == null ? 0 : sp_name.hashCode());
		//result = prime * result + (sp_rccRelationship == null ? 0 : sp_rccRelationship.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final SPredicate other = (SPredicate) obj;
		if (sp_name == null) {
			if (other.sp_name != null) { return false; }
		} else if (!sp_name.equals(other.sp_name)) { return false; }

		if (sp_is_true != other.sp_is_true) { return false; }
		// if(lifetime!=-1 || other.lifetime!=1){
		// if(lifetime!=other.lifetime){return false;}
		// }
		/*
		 * if ( sp_rccRelationship == null ) { if ( other.sp_rccRelationship != null ) { return false; } } else //
		 */ if (sp_rccRelationship != null && other.sp_rccRelationship != null && !sp_rccRelationship.isEmpty() && !other.sp_rccRelationship.isEmpty()) {
			final Set<String> keys = sp_rccRelationship.keySet();
			keys.retainAll(other.sp_rccRelationship.keySet());
			for (final String k : keys) {
				if(this.sp_rccRelationship.get(k)==null && other.sp_rccRelationship.get(k)!=null) {return false;}
				if (!sp_rccRelationship.get(k).equals(other.sp_rccRelationship.get(k))) { return false; }
			}
			return true;
		}
	
		 if (sp_agentGetpredicate != null && other.sp_agentGetpredicate != null && !sp_agentGetpredicate.equals(other.sp_agentGetpredicate)) { return false; }

	     if (sp_is_true != other.sp_is_true) {
			/*
			 * if ( sp_rccRelationship == null ) { if ( other.sp_rccRelationship != null ) { return false; } } else
			 */
			if (sp_rccRelationship != null && other.sp_rccRelationship != null && !sp_rccRelationship.isEmpty() && !other.sp_rccRelationship.isEmpty()) {
				final Set<String> keys = sp_rccRelationship.keySet();
				keys.retainAll(other.sp_rccRelationship.keySet());
				for (final String k : keys) {
					if(this.sp_rccRelationship.get(k)==null && other.sp_rccRelationship.get(k)!=null) {return false;}
					if (!sp_rccRelationship.get(k).equals(other.sp_rccRelationship.get(k))) { return false; }
				}
				return true;
			}
	
			return true;
		} else {
			return false;
		} 
	    }

	public boolean equalsSpatialKnowledges(final Object obj) {
		// Ne teste pas l'agent cause.
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final SPredicate other = (SPredicate) obj;
		if (sp_name == null) {
			if (other.sp_name != null) { return false; }
		} else if (!sp_name.equals(other.sp_name)) { return false; }

		if (sp_is_true != other.sp_is_true) { return false; }
		
		if (sp_rccRelationship != null && other.sp_rccRelationship != null && !sp_rccRelationship.isEmpty() && !other.sp_rccRelationship.isEmpty()) {
			final Set<String> keys = sp_rccRelationship.keySet();
			keys.retainAll(other.sp_rccRelationship.keySet());
			for (final String k : keys) {
				if(this.sp_rccRelationship.get(k)==null && other.sp_rccRelationship.get(k)!=null) {return false;}
				if (!sp_rccRelationship.get(k).equals(other.sp_rccRelationship.get(k))) { return false; }
			}
			return true;
		}

		return true;
	}
	
	

	public boolean equalsIntentionPlan(final Object obj) {
		// Only test case where the parameter is not null
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final SPredicate other = (SPredicate) obj;
		if (sp_name == null) {
			if (other.sp_name != null) { return false; }
		} else if (!sp_name.equals(other.sp_name)) { return false; }
		// if (subintentions != null) {
		// if (!subintentions.equals(other.subintentions)) {
		// return false;
		// }
		// }
		// if (superIntention == null) {
		// if (other.superIntention!=null && other.superIntention != null) {
		// return false;
		// }
		// } else if (superIntention.getPredicate() != null) {
		// if (!superIntention.getPredicate().partialEquality(other.superIntention.getPredicate())) {
		// return false;
		// }
		// }
		if (sp_is_true != other.sp_is_true) { return false; }
		// if(lifetime!=-1 || other.lifetime!=1){
		// if(lifetime!=other.lifetime){return false;}
		// }
		if (sp_region1.equals(other.sp_region2) || sp_name.equals(other.sp_name)) { return true; }
		/*
		 * if ( values == null ) { if ( other.values != null ) { return false; } } else
		 */
		if (sp_rccRelationship != null && other.sp_rccRelationship != null && !sp_rccRelationship.isEmpty() && !other.sp_rccRelationship.isEmpty()) {
			final Set<String> keys = sp_rccRelationship.keySet(); //sp_rccRelationship = values
			keys.retainAll(other.sp_rccRelationship.keySet());
			for (final String k : keys) {
				if(this.sp_rccRelationship.get(k)==null && other.sp_rccRelationship.get(k)!=null) {return false;}
				if (!sp_rccRelationship.get(k).equals(other.sp_rccRelationship.get(k))) { return false; }
			}
			return true;
		}
		// if (values != null && other.values != null && !values.equals(other.values)) {
		// return false;
		// }
		/*
		 * if(agentCause==null){ if(other.agentCause!=null){return false;} }else
		 */if (sp_agentGetpredicate != null && other.sp_agentGetpredicate != null && !sp_agentGetpredicate.equals(other.sp_agentGetpredicate)) { return false; }

		return true;
	}

	public boolean equalsButNotTruth(final Object obj) {
		// return true if the predicates are equals but one is true and not the
		// other
		// Doesn't check the lifetime value
		// Used in emotions
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final SPredicate other = (SPredicate) obj;
		if (sp_name == null) {
			if (other.sp_name != null) { return false; }
		} else if (!sp_name.equals(other.sp_name)) { return false; }
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
		// } else if (superIntention.getPredicate() == null) {
		// if (other.superIntention != null) {
		// return false;
		// }
		// } else if (other.superIntention!=null &&
		// !superIntention.getPredicate().partialEquality(other.superIntention.getPredicate())) {
		// return false;
		// }
		if (sp_is_true != other.sp_is_true) {
			if (sp_rccRelationship != null && other.sp_rccRelationship != null && !sp_rccRelationship.isEmpty() && !other.sp_rccRelationship.isEmpty()) {return true;}
			/*
			 * if ( values == null ) { if ( other.values != null ) { return false; } } else
			 */
			if (sp_rccRelationship != null && other.sp_rccRelationship != null && !sp_rccRelationship.isEmpty() && !other.sp_rccRelationship.isEmpty()) {
				final Set<String> keys = sp_rccRelationship.keySet();
				keys.retainAll(other.sp_rccRelationship.keySet());
				for (final String k : keys) {
					if(this.sp_rccRelationship.get(k)==null && other.sp_rccRelationship.get(k)!=null) {return false;}
					if (!sp_rccRelationship.get(k).equals(other.sp_rccRelationship.get(k))) { return false; }
				}
				return true;
			}
			// if (values != null && other.values != null && !values.equals(other.values)) {
			// return false;
			// }
			/*
			 * if(agentCause==null){ if(other.agentCause!=null){return false;} }else
			 */
			// if (agentCause != null && other.agentCause != null && !agentCause.equals(other.agentCause)) {
			// return false;
			// }

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method getType()
	 *
	 * @see msi.gama.common.interfaces.ITyped#getGamlType()
	 */

	public IType<?> getGamlType() {
		return Types.get(SPredicateType.id);
	}
	
}