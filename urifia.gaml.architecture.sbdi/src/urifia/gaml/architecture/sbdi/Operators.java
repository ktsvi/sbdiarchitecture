package urifia.gaml.architecture.sbdi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import msi.gama.metamodel.agent.IAgent;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.example;
import msi.gama.precompiler.GamlAnnotations.no_test;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.test;
import msi.gama.precompiler.GamlAnnotations.usage;
import msi.gama.precompiler.IConcept;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.util.GamaListFactory;
import msi.gama.util.IList;
import msi.gama.util.IMap;
import msi.gaml.types.IType;

@SuppressWarnings ({ "unchecked", "rawtypes" })
public class Operators {

	@operator (
			value = "new_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "creates a new sk_predicate with a given sk_name and adidtional properties (values, agent get the predicate, whether it is true...)",
			masterDoc = true,
			examples = @example (
					value = "new_sk_predicate(\"River in Forest\")",
					isExecutable = false))
	@no_test
	public static SPredicate newSPredicate(final String sk_name) throws GamaRuntimeException {
		return new SPredicate(sk_name);
	}

	@operator (
			value = "new_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new predicate with the given properties (sk_name, values)",
			examples = @example (
					value = "new_sk_predicate(\"villageIsForest\", map([\"villageECforest\\\"::1000]))",
					isExecutable = false))
	@no_test
	public static SPredicate newSPredicate(final String sk_name, final IMap rccRelationShips) throws GamaRuntimeException {
		return new SPredicate(sk_name,rccRelationShips);
	}

	@operator (
			value = "new_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new predicate with the given is_true (sp_name, is_true)",
			examples = @example (
					value = "new_sk_predicate(\"hasWater\", true)",
					isExecutable = false))
	@no_test
	public static SPredicate newSPredicate(final String sp_name, final Boolean ist) throws GamaRuntimeException {
		return new SPredicate(sp_name, ist);
	}

	@operator (
			value = "new_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new predicate with the given properties (sk_name, cause agent)",
			examples = @example (
					value = "new_sk_predicate(\"people to meet\", hunterAgent)",
					isExecutable = false))
	@no_test
	public static SPredicate newSPredicate(final String sk_name, final IAgent agent) throws GamaRuntimeException {
		return new SPredicate(sk_name, agent);
	}

	@operator (
			value = "new_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new predicate with the given properties (sk_name, sk_rccRelationship, sk_is_true)",
			examples = @example (
					value = "new_sk_predicate(\"villageIsForest\", [\"villageECforest\"::1000], true)",
					isExecutable = false))
	@no_test
	public static SPredicate newSPredicate(final String sk_name, final IMap sk_rccRelationships, final Boolean truth)
			throws GamaRuntimeException {
		return new SPredicate(sk_name, sk_rccRelationships, truth);
	}

	// @operator  (
	// value = "new_sk_predicate",
	// can_be_const = true,
	// category = { "BDI" },
	// concept = { IConcept.BDI })
	// @doc (
	// value = "a new predicate with the given properties (sk_name, values, lifetime)",
	// examples = @example (
	// value = "predicate(\"people to meet\", [\"time\"::10], true)",
	// isExecutable = false))
	// @no_test
	// public static SPredicate newSPredicate(final String sk_name, final IMap values, final int lifetime)
	// throws GamaRuntimeException {
	// return new SPredicate(sk_name, values, lifetime);
	// }

	@operator (
			value = "new_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new sk_predicate with the given properties (sk_name, sk_rccRelationship, agentOwn)",
			examples = @example (
					value = "new_sk_predicate(\"villageIsForest\", [\"villageECforest\"::1000], hunterAgent)",
					isExecutable = false))
	@no_test
	public static SPredicate newSPredicate(final String sk_name, final IMap values, final IAgent agent)
			throws GamaRuntimeException {
		return new SPredicate(sk_name, values, agent);
	}

	@operator (
			value = "new_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new sk_predicate with the given properties (sk_name, sk_values, sk_is_true, agentOwner)",
			examples = @example (
					value = "new_sk_predicate(\"villageIsForest\", [\"villageECforest\"::1000], true, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SPredicate newSPredicate(final String sk_name, final IMap values, final Boolean truth, final IAgent agent)
			throws GamaRuntimeException {
		return new SPredicate(sk_name, values, truth, agent);
	}

	@operator (
			value = "set_agent_get",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the agentOwner value of the given sk_predicate",
			examples = @example (
					value = "sk_predicate set_agent_cause hunterAgent",
					isExecutable = false))
	@no_test
	public static SPredicate withAgentGet(final SPredicate sk_predicate, final IAgent agent) throws GamaRuntimeException {
		final SPredicate temp = sk_predicate.copy();
		temp.setSp_agentGetpredicate(agent);
		return temp;
	}

	@operator (
			value = "set_truth",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the is_true value of the given sk_predicate",
			examples = @example (
					value = "sk_predicate set_truth false",
					isExecutable = false))
	@no_test
	public static SPredicate withTruth(final SPredicate sk_predicate, final Boolean truth) throws GamaRuntimeException {
		final SPredicate temp = sk_predicate.copy();
		temp.sp_is_true = truth;
		return temp;
	}

	@operator (
			value = "with_values",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the parameters of the given sk_predicate",
			examples = @example (
					value = "sk_predicate with_values [\"villageECforest\"::1000]", isExecutable = false))
	@no_test
	public static SPredicate withValues(final SPredicate sk_predicate, final IMap values) throws GamaRuntimeException {
		final SPredicate temp = sk_predicate.copy();
		temp.setValues(values);
		return temp;
	}

	@operator (
			value = "add_values",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "add a new value to the map of the given sk_predicate",
			examples = @example (
					value = "sk_predicate add_values [\"villageECforest\"::1000]",
					isExecutable = false))
	@no_test
	public static SPredicate addValues(final SPredicate sk_predicate, final IMap values) throws GamaRuntimeException {
		final SPredicate temp = sk_predicate.copy();
		final Map<String, Object> tempValues = temp.getValues();
		final Set<String> keys = values.keySet();
		for (final String k : keys) {
			tempValues.put(k, values.get(k));
		}
		temp.setValues(tempValues);
		return temp;
	}

	@operator (
			value = "not",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "create a new sk_predicate with the inverse truth value",
			examples = @example (
					value = "not sk_predicate1",
					isExecutable = false))
	@no_test
	public static SPredicate not(final SPredicate spred1) {
		final SPredicate tempPred = spred1.copy();
		tempPred.setSp_is_true(!spred1.getSp_is_true());
		return tempPred;
	}

	@operator (
			value = "and",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "create a new predicate from two others by including them as subintentions",
			examples = @example (
					value = "sk_predicate1 and sk_predicate2",
					isExecutable = false))
	@no_test
	public static SPredicate and(final SPredicate sk_pred1, final SPredicate sk_pred2) {
		final SPredicate tempPred = new SPredicate(sk_pred1.getSp_name() + "_and_" + sk_pred2.getSp_name());
		final List<SMentalState> tempList = new ArrayList<>();
		final SMentalState tempPred1 = new SMentalState("Intention", sk_pred1);
		final SMentalState tempPred2 = new SMentalState("Intention", sk_pred1);
		tempList.add(tempPred1);
		tempList.add(tempPred2);
		tempPred.setSubintentions(tempList);
		final Map<String, Object> tempMap = new HashMap();
		tempMap.put("and", true);
		tempPred.setValues(tempMap);
		return tempPred;
	}

	@operator (
			value = "or",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "create a new predicate from two others by including them as subintentions. It's an exclusive \"or\" ",
			examples = @example (
					value = "predicate1 or predicate2",
					isExecutable = false))
	@no_test
	public static SPredicate or(final SPredicate sk_pred1, final SPredicate sk_pred2) {
		final SPredicate tempPred = new SPredicate(sk_pred1.getSp_name() + "_or_" + sk_pred2.getSp_name());
		final List<SMentalState> tempList = new ArrayList<>();
		final SMentalState tempPred1 = new SMentalState("Intention", sk_pred1);
		final SMentalState tempPred2 = new SMentalState("Intention", sk_pred2);
		tempList.add(tempPred1);
		tempList.add(tempPred2);
		tempPred.setSubintentions(tempList);
		final Map<String, Object> tempMap = new HashMap();
		tempMap.put("or", true);
		tempPred.setValues(tempMap);
		return tempPred;
	}

	@operator (
			value = "eval_when",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "evaluate the facet when of a given plan",
			examples = @example (
					value = "eval_when(planY)",
					isExecutable = false))
	@no_test
	public static Boolean evalWhen(final IScope scope, final BDIPlan plan) {
		return plan.getPlanStatement().getContextExpression() == null
				|| msi.gaml.operators.Cast.asBool(scope, plan.getPlanStatement().getContextExpression().value(scope));
	}

	@operator (
			value = "get_super_intention",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the super intention linked to a smental state",
			examples = @example (
					value = "get_super_intention(get_belief(pred1))",
					isExecutable = false))
	@no_test
	public static SMentalState getSuperIntention(final SPredicate sk_pred1) {
		if (sk_pred1.getSuperIntention() != null) {
			return sk_pred1.getSuperIntention();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_truth",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "evaluate the truth value of a predicate",
			examples = @example (
					value = "get_truth(pred1)",
					isExecutable = false))
	@test ("get_truth(new_sk_predicate('test1'))=true")
	public static Boolean getTruth(final SPredicate sk_pred) {
		if (sk_pred != null) {
			return sk_pred.sp_is_true;
		} else {
			return null;
		}
	}

	@operator (
			value = "get_agent_cause",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "evaluate the agent_get value of a sk_predicate",
			examples = @example (
					value = "get_agent_getPredicates(pred1)",
					isExecutable = false))
	@no_test
	public static IAgent getSp_agentGetpredicate(final SPredicate sk_pred) {
		if (sk_pred != null) {
			return sk_pred.getSp_agentGetpredicate();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_values",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "return the map values of a predicate",
			examples = @example (
					value = "get_values(pred1)",
					isExecutable = false))
	@no_test
	public static Map<String, Object> getValues(final SPredicate sk_pred) {
		if (sk_pred != null) {
			return sk_pred.getValues();
		} else {
			return null;
		}
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true, 
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial_knowledge with the given properties (at least its sk_name, and eventually sk_power, parameters...)",
			masterDoc = true,
			examples = @example (
					value = "new_spatial_knowledge(\"protectedAreaInDistrict1\")",
					isExecutable = false))
	@test("spatialknowledge sk <- new_spatial_knowledge(\"protectedAreaInDistrict1\"); assert sk.sk_name = \"protectedAreaInDistrict1\"; assert sk.sk_power  = 1.0;")
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc ( value = "a new spatial_knowledge with the given properties (sk_name, sk_power)",
		usages = {
			@usage(value = "a new spatial_knowledge with a sk_name and an initial sk_power: ",
				examples = {@example (
					value = "spatial_knowledge(\"PAisInTheVillage\",0.9)",
					isExecutable = false)})})
	@test ("get_sk_power(new_spatial_knowledge('newSKbyName',0.9)) = 0.9")
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial knowledge with the given properties (sk_name, aboutRegion)",
			usages = {
					@usage(value = "a new spatial knowledge with a given sk_name and the sk_predicate it is aboutpred ",
						examples = {@example (value = "new_spatial_knowledge(\"PAisInTheVillage\",protectedAreaIsOverlap)", isExecutable = false),
								@example (value = "new_spatial_knowledge(\"mainRoadisInForest\",mainRoadPOForest)", isExecutable = false)})})			
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final SPredicate aboutpred) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, aboutpred);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial_knowledge with the given properties (sk_name and agent_get)",
			usages = {
					@usage(value = "a new spatial_knowledge with a given sk_name and the agent which has this knowledge ",
						examples = {@example (value = "new_spatial_knowledge(\"ProtectedAreaIsDeconnectedToForest\",hunter1)", isExecutable = false)})})	
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final IAgent agent) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, agent);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new Spatial Knowledge with the given properties (sk_name,power,aboutpred)",
			usages = {
				@usage(value = "Various combinations are possible to create the Spatial Knowledge: (sk_name,power,aboutpred), (sk_name,aboutpred,agentOwner), (sk_name,power,agentOwner)... ",
					examples = {
						@example (value = "new_spatial_knowledge(\"district1IsCrossedByRiver\",0.8,district1PORiver)", isExecutable = false),
						@example (value = "new_spatial_knowledge(\"district1IsCrossedByRiver\",district1PORiver,hunterY)", isExecutable = false),
						@example (value = "new_spatial_knowledge(\"district1IsCrossedByRiver\",0.8,hunterY)", isExecutable = false)
					})})				
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power, final SPredicate aboutpred)
			throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, aboutpred);
	}	

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial_knowledge with the given properties (sk_name,power,decayValue)",
			usages = {
				@usage(value = "A decay value value can be added to define a new spatial knowledge.",
					examples = {
						@example (value = "new_spatial_knowledge(\"villageAInRegion\",0.6,0.01)", isExecutable = false)
					})})				
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power, final Double decayv)
			throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, decayv);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new Spatial Knowledge with the given properties (sk_name, aboutpred, agent_get)")
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final SPredicate aboutpred, final IAgent agentOwner)
			throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, aboutpred, agentOwner);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial knowledge with the given properties (sk_name,power, agentOwner)")
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power, final IAgent agent)
			throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, agent);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial knowledge with the given properties (sk_name, sk_power, aboutpred,  decay)",
			examples = @example (
					value = "new_spatial_knowledge(\"hunterVillageInRegion\", 0.8, VillageInRegion, 0.02)",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power, final SPredicate aboutpred,
			final Double decayv) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, aboutpred, decayv);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial knowledge with the given properties (sk_name,sk_power, decay, agentOwner)",
			examples = @example (
					value = "spatialknowledge(\"hunterVillageInRegion\", 0.8, 0.02, AgentGet)",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power, final Double decayv, final IAgent agent)
			throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, decayv, agent);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial knowledge with the given properties (sk_name, sk_power, aboutpred, agentOwner)",
			examples = @example (
					value = "new_spatial_knowledge(\"thereExistABridge\",0.7, RiverIntersecRoad, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power, final SPredicate aboutpred,
			final IAgent agent) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, aboutpred, agent);
	}

	@operator (
			value = "new_spatial_knowledge",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial knowledge with the given properties (sk_name, sk_power, aboutpred, decay, agent Owner)",
			examples = @example (
					value = "spatialknowledge(\"thereExistABridge\",1.0,RiverIntersecRoad,0.004,hunterAgent)",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge newSpatialKnowledge(final String sk_name, final Double sk_power, final SPredicate aboutpred,
			final Double decay, final IAgent agent) throws GamaRuntimeException {
		return new SpatialKnowledge(sk_name, sk_power, aboutpred, decay, agent);
	}

	@operator (
			value = "set_agent_owner",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the agent (owner) value of the given spatialknowledge",
			examples = @example (
					value = "new_spatial_knowledge set_agent_owner / hunterAgent",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge withAgentOwner(final SpatialKnowledge spatialknowledge, final IAgent agentO) throws GamaRuntimeException {
		spatialknowledge.sk_owner = agentO;
		return spatialknowledge;
	}

	@operator (
			value = "set_sk_power",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the sk_power value of the given spatial knowledge",
			examples = @example (
					value = "spatialknowledge set_sk_power 1.0",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge setSkPower(final SpatialKnowledge spatial_knowledge, final Double sk_power) throws GamaRuntimeException {
		spatial_knowledge.sk_power = sk_power;
		return spatial_knowledge;
	}

	@operator (
			value = "set_decay_value",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the decay value of the given spatial knowledge",
			examples = @example (
					value = "spatialknowledge set_decay_value 12",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge setDecayValue(final SpatialKnowledge spatial_knowledge, final Double decayv) throws GamaRuntimeException {
		spatial_knowledge.sk_decay_value = decayv;
		return spatial_knowledge;
	}

	@operator (
			value = "set_about_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the sk_predicate value of the given spatial knowledge",
			examples = @example (
					value = "spatialknowledge set_about_sk_predicate sk_predicate1",
					isExecutable = false))
	@no_test
	public static SpatialKnowledge setAbout(final SpatialKnowledge sk, final SPredicate aboutskpred) throws GamaRuntimeException {
		sk.about_sk_predicate = aboutskpred;
		return sk;
	}

	@operator (
			value = "get_sk_power",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the sk_power value of the given spatial knowledge",
			examples = @example (
					value = "get_sk_power(SpatialKnowledgeX)",
					isExecutable = false))
	@test ("get_sk_power(new_spatial_knowledge('hunterVillageInRegion',0.9))=1.0")
	@test ("get_sk_power(new_spatial_knowledge('hunterVillageInRegion',1.0,0.1))=1.0")
	public static Double getIntensity(final SpatialKnowledge sk) {
		if (sk != null) {
			return sk.sk_power;
		} else {
			return null;
		}
	}

	@operator (
			value = "get_sk_decay_value",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the decay value of the given spatial knowledge",
			examples = @example (
					value = "get_sk_decay_value(spatial_knowledgeX)",
					isExecutable = false))
	@test ("get_sk_decay_value(new_spatial_knowledge('district1OverlapProtectedArea',1.0,0.002))=0.002")
	public static Double getSkDecayValue(final SpatialKnowledge sk) {
		if (sk != null) {
			return sk.sk_decay_value;
		} else {
			return null;
		}
	}

	@operator (
			value = "get_about_sk_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the sk_predicate value of the given spatial knowledge",
			examples = @example (
					value = "get_about_sk_predicate(spatialknowledge)",
					isExecutable = false))
	@no_test
	public static SPredicate getAboutSkPredicate(final SpatialKnowledge sk) {
		if (sk != null) {
			return sk.about_sk_predicate;
		} else {
			return null;
		}
	}

	@operator (
			value = "get_agent_get_sk",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the agent (that has sk/owner) value of the given SK",
			examples = @example (
					value = "get_agent_get_sk(spatialknowledge)",
					isExecutable = false))
	@no_test
	public static IAgent getAgentgetSk(final SpatialKnowledge sk) {
		if (sk != null) {
			return sk.getSk_owner();
		} else {
			return null;
		}
	}
/*
	@operator (
			value = "new_social_link",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "creates a new social link with another agent (eventually given additional parameters such as the appreciation, dominance, solidarity, and familiarity values).",
			masterDoc = true,
			examples = @example (
					value = "new_social_link(hunterAgent)",
					isExecutable = false))
	@no_test
	public static SocialLink newSocialLink(final IAgent agent) throws GamaRuntimeException {
		return new SocialLink(agent);
	}

	@operator (
			value = "new_social_link",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "creates a new social link toward another agent with given appreciation, dominance, solidarity, and familiarity values",
			examples = @example (
					value = "new_social_link(hunterAgent,0.0,-0.1,0.2,0.1)",
					isExecutable = false))
	@no_test
	public static SocialLink newSocialLink(final IAgent agent, final Double appreciation, final Double dominance,
			final Double solidarity, final Double familiarity) throws GamaRuntimeException {
		return new SocialLink(agent, appreciation, dominance, solidarity, familiarity);
	}

	@operator (
			value = "set_agent",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the agent value of the given social link",
			examples = @example (
					value = "social_link set_agent hunterAgent",
					isExecutable = false))
	@no_test
	public static SocialLink setAgent(final SocialLink social, final IAgent agent) {
		social.setAgent(agent);
		return social;
	}

	@operator (
			value = "set_liking",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the liking value of the given social link",
			examples = @example (
					value = "social_link set_liking 0.4",
					isExecutable = false))
	@no_test
	public static SocialLink setLiking(final SocialLink social, final Double appreciation) throws GamaRuntimeException {
		if (appreciation >= -1.0 && appreciation <= 1.0) {
			social.setLiking(appreciation);
		}
		return social;
	}

	@operator (
			value = "set_dominance",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the dominance value of the given social link",
			examples = @example (
					value = "social_link set_dominance 0.4",
					isExecutable = false))
	@no_test
	public static SocialLink setDominance(final SocialLink social, final Double dominance) throws GamaRuntimeException {
		if (dominance >= -1.0 && dominance < 1.0) {
			social.setDominance(dominance);
		}
		return social;
	}

	@operator (
			value = "set_solidarity",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the solidarity value of the given social link",
			examples = @example (
					value = "social_link set_solidarity 0.4",
					isExecutable = false))
	@no_test
	public static SocialLink setSolidarity(final SocialLink social, final Double solidarity)
			throws GamaRuntimeException {
		if (solidarity >= 0.0 && solidarity <= 1.0) {
			social.setSolidarity(solidarity);
		}
		return social;
	}

	@operator (
			value = "set_familiarity",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the familiarity value of the given social link",
			examples = @example (
					value = "social_link set_familiarity 0.4",
					isExecutable = false))
	@no_test
	public static SocialLink setFamiliarity(final SocialLink social, final Double familiarity)
			throws GamaRuntimeException {
		if (familiarity >= 0.0 && familiarity <= 1.0) {
			social.setFamiliarity(familiarity);
		}
		return social;
	}

	@operator (
			value = "set_trust",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the trust value of the given social link",
			examples = @example (
					value = "social_link set_familiarity 0.4",
					isExecutable = false))
	@no_test
	public static SocialLink setTrust(final SocialLink social, final Double trust) throws GamaRuntimeException {
		if (trust >= -1.0 && trust <= 1.0) {
			social.setTrust(trust);
		}
		return social;
	}

	@operator (
			value = "get_agent",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the agent value of the given social link",
			examples = @example (
					value = "get_agent(social_link1)",
					isExecutable = false))
	@no_test
	public static IAgent getAgent(final SocialLink social) {
		if (social != null) {
			return social.getAgent();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_liking",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the liking value of the given social link",
			examples = @example (
					value = "get_liking(social_link1)",
					isExecutable = false))
	@no_test
	public static Double getLikink(final SocialLink social) {
		if (social != null) {
			return social.getLiking();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_dominance",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the dominance value of the given social link",
			examples = @example (
					value = "get_dominance(social_link1)",
					isExecutable = false))
	@no_test
	public static Double getDominance(final SocialLink social) {
		if (social != null) {
			return social.getDominance();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_solidarity",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the solidarity value of the given social link",
			examples = @example (
					value = "get_solidarity(social_link1)",
					isExecutable = false))
	@no_test
	public static Double getSolidarity(final SocialLink social) {
		if (social != null) {
			return social.getSolidarity();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_familiarity",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the familiarity value of the given social link",
			examples = @example (
					value = "get_familiarity(social_link1)",
					isExecutable = false))
	@no_test
	public static Double getTrust(final SocialLink social) {
		if (social != null) {
			return social.getTrust();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_trust",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the familiarity value of the given social link",
			examples = @example (
					value = "get_familiarity(social_link1)",
					isExecutable = false))
	@no_test
	public static Double getFamiliarity(final SocialLink social) {
		if (social != null) {
			return social.getFamiliarity();
		} else {
			return null;
		}
	}
*/ //Pas necéssaire pour cette etude
	
	// Faire en sorte que l'on puisse utiliser les opérateurs seulement avec le
	// nom de l'agent ?

	// Faire des opérateurs pour créer des états mentaux (en précisant ou non l'agent propriétaire)
	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "creates a new spatial smental state with a given modality (e.g. belief or desire) and various properties (a sk_predicate it is aboutpred, a sk_power, a sk_lifetime, an ower agent and an spatial_knowledge it is aboutpred",
			masterDoc = true,
			examples = @example (
					value = "new_smental_state(\"belief\")",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality) throws GamaRuntimeException {
		return new SMentalState(modality);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate",
			examples = @example (
					value = "new_smental_state(\"belief\", protectedAreaInDistrict)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred) throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate and power",
			examples = @example (
					value = "new_smental_state(\"belief\", protectedAreaInDistrict, 0.7)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred, final Double skpower)
			throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred, skpower);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate and owner agent",
			examples = @example (
					value = "new_smental_state(\"belief\", protectedAreaInDistrictA, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred, final IAgent ag)
			throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate and sk_lifetime",
			examples = @example (
					value = "new_smental_state(\"belief\", VillageIsWet, 13)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred, final Integer life)
			throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred, life);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate, power/strength, and owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\", VillageIsWet, 0.8, HunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred, final Double sk_power,
			final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred, sk_power, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate, power/strength andd lifetime",
			examples = @example (
					value = "new_smental_state(\"belief\", VillageIsWet, 0.8, 10)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred, final Double sk_power,
			final Integer life) throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred, sk_power, life);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate, lifetime, and owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\", raining, 10, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred, final Integer sk_lt,
			final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred, sk_lt, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional sk_predicate, sk_power, sk_lifetime and owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\",ForestOverlapRoadA, 1.0, 100, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SPredicate sk_pred, final Double sk_power,
			final Integer sk_lt, final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred, sk_power, sk_lt, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial smental state it is aboutpred",
			examples = @example (
					value = "new_smental_state(\"belief\", smental_state1)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState sk_pred)
			throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state  with an additional spatial smental state it is aboutpred and a strength",
			examples = @example (
					value = "new_smental_state(\"belief\", mental_state1, 12.3)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState smental, final Double sk_power)
			throws GamaRuntimeException {
		return new SMentalState(modality, smental, sk_power);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial smental state it is aboutpred and the owner  agent",
			examples = @example (
					value = "new_smental_state(\"belief\", mental_state1, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState smental, final IAgent ag)
			throws GamaRuntimeException {
		return new SMentalState(modality, smental, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial smental state it is aboutpred and a lifetime",
			examples = @example (
					value = "new_smental_state(\"belief\", mental_state1, 10)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState smental, final Integer life)
			throws GamaRuntimeException {
		return new SMentalState(modality, smental, life);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial smental state it is aboutpred, a power, and an owner agent",
			examples = @example (
					value = "new_smental_state(\"belief\", mental_state1, 12.2, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState smental, final Double sk_power,
			final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, smental, sk_power, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial smental state it is aboutpred, a power, and a lifetime.",
			examples = @example (
					value = "new_smental_state(\"belief\", mental_state1, 12.3, 10)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState smental, final Double sk_power,
			final Integer life) throws GamaRuntimeException {
		return new SMentalState(modality, smental, sk_power, life);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial smental state it is aboutpred, a power, and an owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\", mental_state1, 10, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState smental, final Integer life,
			final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, smental, life, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial smental state it is aboutpred, a power, lifetime, and an owner agent",
			examples = @example (
					value = "new_smental_state(\"belief\", mental_state1, 12.3, 10, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SMentalState smental, final Double sk_power,
			final Integer life, final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, smental, sk_power, life, ag);
	}

	// Remplacer avec les Ã©motions
	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred",
			examples = @example (
					value = "new_smental_state(\"belief\", my_SKnowlegde)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk_pred) throws GamaRuntimeException {
		return new SMentalState(modality, sk_pred);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred and a sk_power.",
			examples = @example (
					value = "new_smental_state(\"belief\", my_sknowlegde, 1.0)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk, final Double sk_power)
			throws GamaRuntimeException {
		return new SMentalState(modality, sk, sk_power);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred and an owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\", my_sknowlegde, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk, final IAgent ag)
			throws GamaRuntimeException {
		return new SMentalState(modality, sk, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred and a lifetime.",
			examples = @example (
					value = "new_smental_state(\"belief\",  my_sknowlegde, 10)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk, final Integer life)
			throws GamaRuntimeException {
		return new SMentalState(modality, sk, life);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred, a power, and an owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\", my_sknowledge, 0.8, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk, final Double sk_power,
			final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, sk, sk_power, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred, a strength, and a lifetime.",
			examples = @example (
					value = "new_smental_state(\"belief\", my_sknowlegde, 12.3, 10)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk, final Double sk_power,
			final Integer life) throws GamaRuntimeException {
		return new SMentalState(modality, sk, sk_power, life);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred, a lifetime, and an owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\", my_sknowlegde, 10, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk, final Integer life,
			final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, sk, life, ag);
	}

	@operator (
			value = "new_smental_state",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "a new spatial smental state with an additional spatial knowledge it is aboutpred, a power, a lifetime, and an owner agent.",
			examples = @example (
					value = "new_smental_state(\"belief\", my_SKnowledge, 10.3, 10, hunterAgent)",
					isExecutable = false))
	@no_test
	public static SMentalState newSMentalState(final String modality, final SpatialKnowledge sk, final Double sk_power,
			final Integer life, final IAgent ag) throws GamaRuntimeException {
		return new SMentalState(modality, sk, sk_power, life, ag);
	}

	@operator (
			value = "set_ms_modality",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the modality value of the given spatial smental state",
			examples = @example (
					value = "smental state set_ms_modality belief",
					isExecutable = false))
	@no_test
	public static SMentalState setModalitity(final SMentalState smental, final String modality) {
		smental.setModality(modality);
		return smental;
	}

	@operator (
			value = "set_ms_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the spatial_predicate value of the given spatial smental state",
			examples = @example (
					value = "smental state set_ms_predicate sk_pred1",
					isExecutable = false))
	@no_test
	public static SMentalState setPredicate(final SMentalState smental, final SPredicate sk_predicate) {
		smental.setPredicate(sk_predicate);
		return smental;
	}

	@operator (
			value = "set_ms_power",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the strength value of the given smental state",
			examples = @example (
					value = "smental state set_ms_power 1.0",
					isExecutable = false))
	@no_test
	public static SMentalState setSkPower(final SMentalState smental, final Double sk_power) {
		smental.setSk_power(sk_power);
		return smental;
	}

	@operator (
			value = "set_sm_lifetime",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "change the lifetime value of the given smental state",
			examples = @example (
					value = "smental state set_lifetime 1",
					isExecutable = false))
	@no_test
	public static SMentalState setLifetime(final SMentalState smental, final int sk_lt) {
		smental.setSk_lifetime(sk_lt);
		return smental;
	}

	@operator (
			value = "get_modality",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the modality value of the given smental state",
			examples = @example (
					value = "get_modality(mental_state1)",
					isExecutable = false))
	@test ("get_modality(new_smental_state('Belief',new_sk_predicate('test1')))='Belief'")
	public static String getModality(final SMentalState smental) {
		if (smental != null) {
			return smental.getModality();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_ms_predicate",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the predicate value of the given spatial smental state",
			examples = @example (
					value = "get_ms_predicate(smental_state1)",
					isExecutable = false))
	@no_test
	public static SPredicate getSk_predicate(final SMentalState smental) {
		if (smental != null) {
			return smental.getSk_predicate();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_ms_power",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the strength value of the given spatial smental state",
			examples = @example (
					value = "get_ms_power(mental_state1)",
					isExecutable = false))
	@test ("get_ms_power(new_smental_state('Belief',new_sk_predicate('testforSkpred')))=1.0")
	public static Double getStrength(final SMentalState smental) {
		if (smental != null) {
			return smental.getSk_power();
		} else {
			return null;
		}
	}

	@operator (
			value = "get_ms_lifetime",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the lifetime value of the given smental state",
			examples = @example (
					value = "get_ms_lifetime(mental_state1)",
					isExecutable = false))
	@test ("get_ms_lifetime(new_smental_state('Belief',new_sk_predicate('test1'),10))=10")
	public static int getLifetime(final SMentalState smental) {
		if (smental != null) {
			return smental.getSk_lifetime();
		} else {
			return -1;
		}
	}

	@operator (
			value = "get_plan_name",
			can_be_const = true,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the sk_name of a given plan",
			examples = @example (
					value = "get_plan_name(agent.current_plan)",
					isExecutable = false))
	@no_test
	public static String getPlanName(final BDIPlan plan) {
		if (plan != null && plan.getPlanStatement() != null) {
			return plan.getPlanStatement().getName();
		} else {
			return null;
		}
	}

	// example of transformation of actions to operator
	@operator (
			value = "get_beliefs_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the list of beliefs in the belief base which sk predicate has the given sp_name.",
			returns = "the list of beliefs (smental state).",
			examples = { @example (
					value = "get_beliefs_with_name_op(self,\"ForestOverlapRoadA\")",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getBeliefsName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("belief_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_belief_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the belief in the belief base with the given sp_name",
			returns = "the belief (smental state).",
			examples = { @example (
					value = "get_belief_with_name_op(self,\"ForestCrossedByRiver\")",
					equals = "nil") })
	public static SMentalState getBeliefName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("belief_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					return smental;
				}
			}
		}
		return null;
	}

	@operator (
			value = "get_belief_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the belief in the belief base with the given S predicate.",
			returns = "the belief (smental state).",
			examples = { @example (
					value = "get_belief_op(self, sk_predicate(\"DistrictOverlapRiver\"))",
					equals = "nil") })
	public static SMentalState getBelief(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("belief_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) { return smental; }
			}
		}
		return null;
	}

	@operator (
			value = "get_beliefs_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the beliefs in the belief base with the given predicate.",
			returns = "the list of belief (smental state).",
			examples = { @example (
					value = "get_beliefs_op(self, sk_predicate(\"Region_has_water\"))",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getBeliefs(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		final IList<SMentalState> predicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return predicates; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("belief_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					predicates.add(smental);
				}
			}
		}
		return predicates;
	}

	@operator (
			value = "get_desires_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the list of desires in the desire base which predicate has the given sk_name.",
			returns = "the list of desires (smental state).",
			examples = { @example (
					value = "get_desires_with_name_op(self,\"Region_has_water\")",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getDesiresName(final IScope scope, final IAgent ag, final String predicateName)
			throws GamaRuntimeException {
		final IList<SMentalState> predicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return predicates; }
		if (predicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("desire_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && predicateName.equals(smental.getSk_predicate().getSp_name())) {
					predicates.add(smental);
				}
			}
		}
		return predicates;
	}

	@operator (
			value = "get_desire_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the desire in the desire base with the given sk_name.",
			returns = "the desire (smental state).",
			examples = { @example (
					value = "get_desire_with_name_op(self,\"Region_has_water\")",
					equals = "nil") })
	public static SMentalState getDesireName(final IScope scope, final IAgent ag, final String predicateName)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (predicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("desire_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && predicateName.equals(smental.getSk_predicate().getSp_name())) {
					return smental;
				}
			}
		}
		return null;
	}

	@operator (
			value = "get_desire_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the desire in the desire base with the given predicate.",
			returns = "the belief (smental state).",
			examples = { @example (
					value = "get_belief_op(self, sk_predicate(\"Region_has_water\"))",
					equals = "nil") })
	public static SMentalState getDesire(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("desire_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) { return smental; }
			}
		}
		return null;
	}

	@operator (
			value = "get_desires_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the desires in the desire base with the given predicate.",
			returns = "the list of desire (smental state).",
			examples = { @example (
					value = "get_desires_op(self, sk_predicate(\"Region_has_water\"))",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getDesires(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("desire_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_swagueness_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the list of swaguenesses (uncertainties) in the swagueness base which spredicate has the given sk_name.",
			returns = "the list of swaguenss or uncertainties (smental state).",
			examples = { @example (
					value = "get_swagueness_with_name_op(self,\"ForestOverlapRoadA\")",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getSwaguenessesName(final IScope scope, final IAgent ag,
			final String spredicateName) throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("swagueness_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_swagueness_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the swagueness in the swagueness base with the given sk_name.",
			returns = "the swaguenesses (smental state).",
			examples = { @example (
					value = "get_swagueness_with_name_op(self,\"Region_has_River\")",
					equals = "nil") })
	public static SMentalState getSwaguenessName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {

		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("swagueness_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					return smental;
				}
			}
		}
		return null;
	}

	@operator (
			value = "get_swagueness_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the swagueness or swagueness in the uncertainty base with the given S predicate.",
			returns = "the uncertainty (smental state).",
			examples = { @example (
					value = "get_swagueness_op(self,sk_predicate(\"ForestOverlapRoadA\"))",
					equals = "nil") })
	public static SMentalState getSwagueness(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("swagueness_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) { return smental; }
			}
		}
		return null;
	}

	@operator (
			value = "get_swaguenesses_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the swaguenesses in the uncertainty base with the given predicate.",
			returns = "the list of swaguenesses (smental state).",
			examples = { @example (
					value = "get_swaguenesses_op(self,sk_predicate(\"Region_has_river\"))",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getUncertainties(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("swagueness_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_ideals_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the list of ideals in the ideal base which predicate has the given sk_name.",
			returns = "the list of ideals (smental state).",
			examples = { @example (
					value = "get_ideals_with_name_op(self,\"Region_has_water\")",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getIdealsName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("ideal_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_ideal_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the ideal in the ideal base with the given sk_name.",
			returns = "the ideal (smental state).",
			examples = { @example (
					value = "get_ideal_with_name_op(self,\"Region_has_Water\")",
					equals = "nil") })
	public static SMentalState getIdealName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {

		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("ideal_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					return smental;
				}
			}
		}
		return null;
	}

	@operator (
			value = "get_ideal_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the ideal in the ideal base with the given sk_name.",
			returns = "the ideal (smental state).",
			examples = { @example (
					value = "get_ideal_op(self, sk_predicate(\"Village_has_water\"))",
					equals = "nil") })
	public static SMentalState getIdeal(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {

		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("ideal_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) { return smental; }
			}
		}
		return null;
	}

	@operator (
			value = "get_ideals_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the ideal in the ideal base with the given sk_name.",
			returns = "the list of ideals (smental state).",
			examples = { @example (
					value = "get_ideals_op(self, sk_predicate(\"Region_has_water\"))",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getIdeals(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("ideal_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_obligations_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the list of obligations in the obligation base which predicate has the given sk_name.",
			returns = "the list of obligations (smental state).",
			examples = { @example (
					value = "get_obligations_with_name_op(self,\"Region_has_water\")",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getObligationsName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("obligation_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_obligation_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the obligation in the obligation base with the given sk_name.",
			returns = "the obligation (smental state).",
			examples = { @example (
					value = "get_obligation_with_name_op(self,\"Region_has_water\")",
					equals = "nil") })
	public static SMentalState getObligationName(final IScope scope, final IAgent ag, final String predicateName)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (predicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("obligation_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && predicateName.equals(smental.getSk_predicate().getSp_name())) {
					return smental;
				}
			}
		}
		return null;
	}

	@operator (
			value = "get_obligation_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the obligation in the obligation base with the given predicate.",
			returns = "the obligation (smental state).",
			examples = { @example (
					value = "get_obligation_op(self, sk_predicate(\"Region_has_water\"))",
					equals = "nil") })
	public static SMentalState getObligation(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("obligation_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) { return smental; }
			}
		}
		return null;
	}

	@operator (
			value = "get_obligations_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the obligations in the obligation base with the given predicate.",
			returns = "the list of obligations (smental state).",
			examples = { @example (
					value = "get_obligations_op(self, sk_predicate(\"Region_has_water\"))",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getObligations(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		final IList<SMentalState> predicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return predicates; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("obligation_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					predicates.add(smental);
				}
			}
		}
		return predicates;
	}

	@operator (
			value = "get_intentions_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the list of intentions in the intention base which skpredicate has the given sp_name.",
			returns = "the list of intentions (smental state).",
			examples = { @example (
					value = "get_intentions_with_name_op(self,\"Region_has_water\")",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getIntentionsName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("intention_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_intention_with_name_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the intention in the intention base with the given sk_name.",
			returns = "the intention (smental state).",
			examples = { @example (
					value = "get_intention_with_name_op(self,\"Forest_has_water\")",
					isExecutable = false) })
	@no_test
	public static SMentalState getIntentionName(final IScope scope, final IAgent ag, final String spredicateName)
			throws GamaRuntimeException {

		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (spredicateName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("intention_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && spredicateName.equals(smental.getSk_predicate().getSp_name())) {
					return smental;
				}
			}
		}
		return null;
	}

	@operator (
			value = "get_intention_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the intention in the intention base with the given S predicate.",
			returns = "the intention (smental state).",
			examples = { @example (
					value = "get_intention_op(self,sk_predicate(\"Forest_has_water\"))",
					isExecutable = false) })
	@no_test
	public static SMentalState getIntention(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("intention_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) { return smental; }
			}
		}
		return null;
	}

	@operator (
			value = "get_intentions_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the intentions in the intention base with the given predicate.",
			returns = "the list of intentions (smental state).",
			examples = { @example (
					value = "get_intentions_op(self, sk_predicate(\"Region_has_water\"))",
					isExecutable = false) })
	@no_test
	public static IList<SMentalState> getIntentions(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		final IList<SMentalState> spredicates = GamaListFactory.create();
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return spredicates; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("intention_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					spredicates.add(smental);
				}
			}
		}
		return spredicates;
	}

	@operator (
			value = "get_current_intention_op",
			can_be_const = true,
			content_type = SMentalStateType.id,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "get the current intention.",
			returns = "the current intention (smental state).",
			examples = { @example (
					value = "get_current_intention_op(self)",
					equals = "nil") })
	public static SMentalState getCurrentIntention(final IScope scope, final IAgent ag) throws GamaRuntimeException {
		// final SMentalState smentalicate = new SMentalState("Belief");
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return null; }
		final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("intention_base");
		// if (beliefs == null) { return null; }
		if (!beliefs.isEmpty()) { return beliefs.lastValue(scope); }
		return null;
	}

	@operator (
			value = "has_belief_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is a belief aboutpred the given predicate.",
			returns = "true if a belief already exists.",
			examples = { @example (
					value = "has_belief_op(self,sk_predicate(\"district_has_water\"))",
					equals = "false") })
	public static Boolean hasBelief(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("belief_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_belief_with_name_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is a belief aboutpred the given sk_name.",
			returns = "true if a belief already exists.",
			examples = { @example (
					value = "has_belief_with_name_op(self,\"area_has_water\")",
					equals = "false") })
	public static Boolean hasBeliefName(final IScope scope, final IAgent ag, final String sk_predName)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_predName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("belief_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_predName.equals(smental.getSk_predicate().getSp_name())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_desire_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is a desire aboutpred the given predicate.",
			returns = "true if a desire already exists.",
			examples = { @example (
					value = "has_desire_op(self,sk_predicate(\"Region_has_water\"))",
					equals = "false") })
	public static Boolean hasDesire(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("desire_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_desire_with_name_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is a desire aboutpred the given sk_name.",
			returns = "true if a desire already exists.",
			examples = { @example (
					value = "has_desire_with_name_op(self,\"region_has_water\")",
					equals = "false") })
	public static Boolean hasDesireName(final IScope scope, final IAgent ag, final String sk_predName)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_predName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("desire_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_predName.equals(smental.getSk_predicate().getSp_name())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_swagueness_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an swagueness our swagueness aboutpred the given predicate.",
			returns = "true if an swagueness already exists.",
			examples = { @example (
					value = "has_swagueness_op(self,sk_predicate(\"Region_has_water\"))",
					equals = "false") })
	public static Boolean hasSwagueness(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("swagueness_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_swagueness_with_name_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an swagueness aboutpred the given sk_name.",
			returns = "true if an swagueness already exists.",
			examples = { @example (
					value = "has_swagueness_with_name_op(self,\"Region_has_water\")",
					equals = "false") })
	
	public static Boolean hasSwaguenessName(final IScope scope, final IAgent ag, final String sk_predName)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_predName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("swagueness_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_predName.equals(smental.getSk_predicate().getSp_name())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_ideal_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an ideal aboutpred the given predicate.",
			returns = "true if an ideal already exists.",
			examples = { @example (
					value = "has_ideal_op(self,sk_predicate(\"Region_has_water\"))",
					equals = "false") })
	public static Boolean hasIdeal(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("ideal_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_ideal_with_name_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an ideal aboutpred the given sk_name.",
			returns = "true if an ideal already exists.",
			examples = { @example (
					value = "has_ideal_with_name_op(self,\"Region_has_water\")",
					equals = "false") })
	public static Boolean hasIdealName(final IScope scope, final IAgent ag, final String sk_predName)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_predName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("ideal_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_predName.equals(smental.getSk_predicate().getSp_name())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_intention_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an intention aboutpred the given predicate.",
			returns = "true if an intention already exists.",
			examples = { @example (
					value = "has_intention_op(self, sk_predicate(\"Region_has_water\"))",
					equals = "false") })
	public static Boolean hasIntention(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("intention_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_intention_with_name_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an intention aboutpred the given sk_name.",
			returns = "true if an intention already exists.",
			examples = { @example (
					value = "has_intention_with_name_op(self,\"Region_has_water\")",
					equals = "false") })
	public static Boolean hasIntentionName(final IScope scope, final IAgent ag, final String sk_predName)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_predName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("intention_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_predName.equals(smental.getSk_predicate().getSp_name())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_obligation_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an obligation aboutpred the given predicate.",
			returns = "true if an obligation already exists.",
			examples = { @example (
					value = "has_obligation_op(self, sk_predicate(\"Region_has_water\"))",
					equals = "false") })
	public static Boolean hasObligation(final IScope scope, final IAgent ag, final SPredicate sk_pred)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_pred != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("obligation_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_pred.equals(smental.getSk_predicate())) {
					result = true;
				}
			}
		}
		return result;
	}

	@operator (
			value = "has_obligation_with_name_op",
			can_be_const = true,
			content_type = IType.BOOL,
			category = { "BDI" },
			concept = { IConcept.BDI })
	@doc (
			value = "indicates if there already is an obligation aboutpred the given sk_name.",
			returns = "true if an obligation already exists.",
			examples = { @example (
					value = "has_obligation_with_name_op(self,\"Region_has_water\")",
					equals = "false") })
	public static Boolean hasObligationName(final IScope scope, final IAgent ag, final String sk_predName)
			throws GamaRuntimeException {
		Boolean result = false;
		if (!(ag.getSpecies().getArchitecture() instanceof SbdiArchitecture)) { return result; }
		if (sk_predName != null) {
			final IList<SMentalState> beliefs = (IList<SMentalState>) ag.getAttribute("obligation_base");
			for (final SMentalState smental : beliefs) {
				if (smental.getSk_predicate() != null && sk_predName.equals(smental.getSk_predicate().getSp_name())) {
					result = true;
				}
			}
		}
		return result;
	}
}
