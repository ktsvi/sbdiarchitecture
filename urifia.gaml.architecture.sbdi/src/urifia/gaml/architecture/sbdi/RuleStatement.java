package urifia.gaml.architecture.sbdi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import msi.gama.common.interfaces.IKeyword;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.example;
import msi.gama.precompiler.GamlAnnotations.facet;
import msi.gama.precompiler.GamlAnnotations.facets;
import msi.gama.precompiler.GamlAnnotations.inside;
import msi.gama.precompiler.GamlAnnotations.symbol;
import msi.gama.precompiler.IConcept;
import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.descriptions.IDescription;
import msi.gaml.expressions.IExpression;
import msi.gaml.operators.Cast;
import msi.gaml.operators.System;
import msi.gaml.statements.AbstractStatement;
import msi.gaml.types.IType;



@symbol (
		name = RuleStatement.RULE,
		kind = ISymbolKind.SINGLE_STATEMENT,
		with_sequence = false,
		concept = { IConcept.BDI })
@inside (
		symbols = {SbdiArchitecture.SBDI, SbdiArchitectureParallel.PARALLEL_BDI },
		kinds = { ISymbolKind.SPECIES, ISymbolKind.MODEL })
@facets (
		value = { @facet (
				name = RuleStatement.BELIEF,
				type = SPredicateType.id,
				optional = true,
				doc = @doc ("The mandatory belief")),
				@facet (
						name = RuleStatement.DESIRE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory desire")),
				@facet (
						name = RuleStatement.SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The mandatory emotion")),
				@facet (
						name = RuleStatement.SWAGUENESS,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory uncertainty")),
				@facet (
						name = RuleStatement.IDEAL,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory ideal")),
				@facet (
						name = RuleStatement.OBLIGATION,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory obligation")),
				@facet (
						name = RuleStatement.DESIRES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory desires")),
				@facet (
						name = RuleStatement.BELIEFS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory beliefs")),
				@facet (
						name = RuleStatement.SPATIAL_KNOWLEDGES,
						type = IType.LIST,
						of = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The mandatory spatial knowledges")),
				@facet (
						name = RuleStatement.SWAGUENESSES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory swaguenesses")),
				@facet (
						name = RuleStatement.IDEALS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory ideals")),
				@facet (
						name = RuleStatement.OBLIGATIONS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory obligations")),
				@facet (
						name = RuleStatement.NEW_DESIRE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be added")),
				@facet (
						name = RuleStatement.NEW_BELIEF,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be added")),
				@facet (
						name = RuleStatement.NEW_SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The emotion that will be added")),
				@facet (
						name = RuleStatement.NEW_UNCERTAINTY,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be added")),
				@facet (
						name = RuleStatement.NEW_IDEAL,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideal that will be added")),
				@facet (
						name = RuleStatement.NEW_DESIRES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be added")),
				@facet (
						name = RuleStatement.NEW_BELIEFS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be added")),
				@facet (
						name = RuleStatement.NEW_SPATIAL_KNOWLEDGES,
						type = IType.LIST,
						of = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The emotion that will be added")),
				@facet (
						name = RuleStatement.NEW_SWAGUENESSES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be added")),
				@facet (
						name = RuleStatement.NEW_IDEALS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideals that will be added")),
				@facet (
						name = RuleStatement.REMOVE_BELIEFS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_DESIRES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_SPATIAL_KNOWLEDGES,
						type = IType.LIST,
						of = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The emotion that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_IDEALS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideals that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_OBLIGATIONS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The obligation that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_SWAGUENESSES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_BELIEF,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_IDEAL,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideal that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_DESIRE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_INTENTION,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The intention that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The emotion that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_SWAGUENESS,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be removed")),
				@facet (
						name = RuleStatement.REMOVE_OBLIGATION,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The obligation that will be removed")),
				@facet (
						name = IKeyword.WHEN,
						type = IType.BOOL,
						optional = true,
						doc = @doc (" ")),
				@facet (
						name = RuleStatement.THRESHOLD,
						type = IType.FLOAT,
						optional = true,
						doc = @doc ("Threshold linked to the emotion.")),
				@facet (
						name = IKeyword.PARALLEL,
						type = { IType.BOOL, IType.INT },
						optional = true,
						doc = @doc ("setting this facet to 'true' will allow 'perceive' to use concurrency with a parallel_bdi architecture; setting it to an integer will set the threshold under which they will be run sequentially (the default is initially 20, but can be fixed in the preferences). This facet is true by default.")),
				@facet (
						name = RuleStatement.SK_POWER,
						type = { IType.FLOAT, IType.INT },
						optional = true,
						doc = @doc ("The stregth of the mental state created")),
				@facet (
						name = "sk_lifetime",
						type = IType.INT,
						optional = true,
						doc = @doc ("the sk_lifetime value of the mental state created")),
				@facet (
						name = RuleStatement.ALL,
						type = IType.BOOL,
						optional = true,
						doc = @doc ("add a desire for each belief")),
				@facet (
						name = IKeyword.NAME,
						type = IType.ID,
						optional = true,
						doc = @doc ("The name of the rule")) },
		omissible = IKeyword.NAME)
@doc (
		value = "enables to add a desire or a belief or to remove a belief, a desire or an intention if the agent gets the belief or/and desire or/and condition mentioned.",
		examples = {
				@example ("rule belief: new_sk_predicate(\"test\") when: sk_power(0.5) new_desire: new_sk_predicate(\"test\")") })

public class RuleStatement extends AbstractStatement {

	public static final String RULE = "rule";
	public static final String BELIEF = "belief";
	public static final String DESIRE = "desire";
	public static final String SPATIAL_KNOWLEDGES = "spatial_knowledges";
	public static final String SWAGUENESS = "swagueness";
	public static final String IDEAL = "ideal";
	public static final String OBLIGATION = "obligation";
	public static final String RULES = "rules";
	public static final String BELIEFS = "beliefs";
	public static final String DESIRES = "desires";
	public static final String SPATIAL_KNOWLEDGE = "spatial_knowledge";
	public static final String SWAGUENESSES = "swaguenesses";
	public static final String IDEALS = "ideals";
	public static final String OBLIGATIONS = "obligations";
	public static final String NEW_DESIRE = "new_desire";
	public static final String NEW_BELIEF = "new_belief";
	public static final String NEW_SPATIAL_KNOWLEDGE = "new_spatial_knowledge";
	public static final String NEW_UNCERTAINTY = "new_swagueness";
	public static final String NEW_IDEAL = "new_ideal";
	public static final String REMOVE_BELIEF = "remove_belief";
	public static final String REMOVE_DESIRE = "remove_desire";
	public static final String REMOVE_INTENTION = "remove_intention";
	public static final String REMOVE_SPATIAL_KNOWLEDGE = "remove_spatial_knowledge";
	public static final String REMOVE_SWAGUENESS = "remove_swagueness";
	public static final String REMOVE_IDEAL = "remove_ideal";
	public static final String REMOVE_OBLIGATION = "remove_obligation";
	public static final String NEW_DESIRES = "new_desires";
	public static final String NEW_BELIEFS = "new_beliefs";
	public static final String NEW_SPATIAL_KNOWLEDGES = "new_spatial_knowledges";
	public static final String NEW_SWAGUENESSES = "new_swaguenesses";
	public static final String NEW_IDEALS = "new_ideals";
	public static final String REMOVE_BELIEFS = "remove_beliefs";
	public static final String REMOVE_DESIRES = "remove_desires";
	public static final String REMOVE_SPATIAL_KNOWLEDGES = "remove_spatial_knowledges";
	public static final String REMOVE_SWAGUENESSES = "remove_swaguenesses";
	public static final String REMOVE_IDEALS = "remove_ideals";
	public static final String REMOVE_OBLIGATIONS = "remove_obligations";
	public static final String SK_POWER = "sk_power";
	public static final String THRESHOLD = "threshold";
	public static final String ALL = "all";

	final IExpression when;
	final IExpression parallel;
	final IExpression belief;
	final IExpression desire;
	final IExpression spatialknowledge;
	final IExpression swagueness;
	final IExpression ideal;
	final IExpression obligation;
	final IExpression beliefs;
	final IExpression desires;
	final IExpression spatialknowledges;
	final IExpression swaguenesses;
	final IExpression ideals;
	final IExpression obligations;
	final IExpression newBelief;
	final IExpression newDesire;
	final IExpression newSpatialKnowledge;
	final IExpression newSwagueness;
	final IExpression newIdeal;
	final IExpression removeBelief;
	final IExpression removeDesire;
	final IExpression removeIntention;
	final IExpression removeSpatialKnowledge;
	final IExpression removeSwagueness;
	final IExpression removeIdeal;
	final IExpression removeObligation;
	final IExpression newBeliefs;
	final IExpression newDesires;
	final IExpression newSpatialKnowledges;
	final IExpression newSwaguenesses;
	final IExpression newIdeals;
	final IExpression removeBeliefs;
	final IExpression removeDesires;
	final IExpression removeSpatialKnowedge;
	final IExpression removeSwaguenesses;
	final IExpression removeIdeals;
	final IExpression removeObligations;
	final IExpression sk_power;
	final IExpression threshold;
	final IExpression all;
	final IExpression sk_lifetime;

	public RuleStatement(final IDescription desc) {
		super(desc);
		when = getFacet(IKeyword.WHEN);
		belief = getFacet(RuleStatement.BELIEF);
		desire = getFacet(RuleStatement.DESIRE);
		spatialknowledge = getFacet(RuleStatement.SPATIAL_KNOWLEDGE);
		swagueness = getFacet(RuleStatement.NEW_SWAGUENESSES);
		ideal = getFacet(RuleStatement.IDEAL);
		obligation = getFacet(RuleStatement.OBLIGATION);
		beliefs = getFacet(RuleStatement.BELIEFS);
		desires = getFacet(RuleStatement.DESIRES);
		spatialknowledges = getFacet(RuleStatement.NEW_SPATIAL_KNOWLEDGES);
		swaguenesses = getFacet(RuleStatement.SWAGUENESSES);
		ideals = getFacet(RuleStatement.IDEALS);
		obligations = getFacet(RuleStatement.OBLIGATIONS);
		newBelief = getFacet(RuleStatement.NEW_BELIEF);
		newDesire = getFacet(RuleStatement.NEW_DESIRE);
		newSpatialKnowledge = getFacet(RuleStatement.NEW_SPATIAL_KNOWLEDGE);
		newSwagueness = getFacet(RuleStatement.NEW_SWAGUENESSES);
		newIdeal = getFacet(RuleStatement.NEW_IDEAL);
		removeBelief = getFacet(RuleStatement.REMOVE_BELIEF);
		removeDesire = getFacet(RuleStatement.REMOVE_DESIRE);
		removeIntention = getFacet(RuleStatement.REMOVE_INTENTION);
		removeSpatialKnowledge = getFacet(RuleStatement.REMOVE_SPATIAL_KNOWLEDGE);
		removeSwagueness = getFacet(RuleStatement.REMOVE_SWAGUENESS);
		removeIdeal = getFacet(RuleStatement.REMOVE_IDEAL);
		removeObligation = getFacet(RuleStatement.REMOVE_OBLIGATION);
		newBeliefs = getFacet(RuleStatement.NEW_BELIEFS);
		newDesires = getFacet(RuleStatement.NEW_DESIRES);
		newSpatialKnowledges = getFacet(RuleStatement.NEW_SPATIAL_KNOWLEDGES);
		newSwaguenesses = getFacet(RuleStatement.NEW_SWAGUENESSES);
		newIdeals = getFacet(RuleStatement.NEW_IDEALS);
		removeBeliefs = getFacet(RuleStatement.REMOVE_BELIEFS);
		removeDesires = getFacet(RuleStatement.REMOVE_DESIRES);
		removeSpatialKnowedge = getFacet(RuleStatement.REMOVE_SPATIAL_KNOWLEDGES);
		removeSwaguenesses = getFacet(RuleStatement.REMOVE_SWAGUENESSES);
		removeIdeals = getFacet(RuleStatement.REMOVE_IDEALS);
		removeObligations = getFacet(RuleStatement.REMOVE_OBLIGATIONS);
		sk_power = getFacet(RuleStatement.SK_POWER);
		threshold = getFacet(RuleStatement.THRESHOLD);
		sk_lifetime = getFacet("sk_lifetime");
		parallel = getFacet(IKeyword.PARALLEL);
		all = getFacet(RuleStatement.ALL);
	}

	@SuppressWarnings ("unchecked")
	@Override
	protected Object privateExecuteIn(final IScope scope) throws GamaRuntimeException {
		if (newBelief == null && newDesire == null && newSpatialKnowledge == null && newSwagueness == null
				&& removeBelief == null && removeDesire == null && removeIntention == null && removeSpatialKnowledge == null
				&& removeSwagueness == null && newBeliefs == null && newDesires == null && newSpatialKnowledges == null
				&& newSwaguenesses == null && removeBeliefs == null && removeDesires == null && removeSpatialKnowedge == null
				&& removeSwaguenesses == null)
			return null;
		boolean allVal = (all != null) && Cast.asBool(scope, all.value(scope));
		List<SPredicate> predBeliefList = null;
		List<SPredicate> predSwaguenessList = null;
		List<SPredicate> predIdealList = null;
		if (when == null || Cast.asBool(scope, when.value(scope))) {
			final SMentalState tempBelief = new SMentalState("Belief");
			boolean has_belief = true;
			if (belief != null) {
				tempBelief.setPredicate((SPredicate) belief.value(scope));
				has_belief = SbdiArchitecture.hasBelief(scope, tempBelief);
				if (has_belief) {
					predBeliefList = new ArrayList<SPredicate>();
					for (final SMentalState mental : SbdiArchitecture.getBase(scope,
							SbdiArchitecture.BELIEF_BASE)) {
						if (mental.getSk_predicate() != null) {
							if (tempBelief.getSk_predicate().equals(mental.getSk_predicate())) {
								predBeliefList.add(mental.getSk_predicate());
							}
						}
					}
				}
			}
			if (belief == null || SbdiArchitecture.hasBelief(scope, tempBelief)) {
				final SMentalState tempDesire = new SMentalState("Desire");
				if (desire != null) {
					tempDesire.setPredicate((SPredicate) desire.value(scope));
				}
				if (desire == null || SbdiArchitecture.hasDesire(scope, tempDesire)) {
					final SMentalState tempSwagueness = new SMentalState("Swagueness");
					boolean has_swagueness = true;
					if (swagueness != null) {
						tempSwagueness.setPredicate((SPredicate) swagueness.value(scope));
						has_swagueness = SbdiArchitecture.hasSwagueness(scope, tempSwagueness);
						if (has_swagueness) {
							predSwaguenessList = new ArrayList<SPredicate>();
							for (final SMentalState mental : SbdiArchitecture.getBase(scope,
									SbdiArchitecture.SWAGUENESS_BASE)) {
								if (mental.getSk_predicate() != null) {
									if (tempBelief.getSk_predicate().equals(mental.getSk_predicate())) {
										predSwaguenessList.add(mental.getSk_predicate());
									}
								}
							}
						}
					}
					if (swagueness == null || SbdiArchitecture.hasSwagueness(scope, tempSwagueness)) {
						final SMentalState tempIdeal = new SMentalState("Ideal");
						boolean has_ideal = true;
						if (ideal != null) {
							tempIdeal.setPredicate((SPredicate) ideal.value(scope));
							has_ideal = SbdiArchitecture.hasIdeal(scope, tempIdeal);
							if (has_ideal) {
								predIdealList = new ArrayList<SPredicate>();
								for (final SMentalState mental : SbdiArchitecture.getBase(scope,
										SbdiArchitecture.IDEAL_BASE)) {
									if (mental.getSk_predicate() != null) {
										if (tempBelief.getSk_predicate().equals(mental.getSk_predicate())) {
											predIdealList.add(mental.getSk_predicate());
										}
									}
								}
							}
						}
						if (ideal == null || SbdiArchitecture.hasIdeal(scope, tempIdeal)) {
							final SMentalState tempObligation = new SMentalState("Obligation");
							if (obligation != null) {
								tempObligation.setPredicate((SPredicate) obligation.value(scope));
							}
							if (obligation == null || SbdiArchitecture.hasObligation(scope, tempSwagueness)) {
								if (spatialknowledge == null
										|| SbdiArchitecture.hasSpatialKnowledge(scope, (SpatialKnowledge) spatialknowledge.value(scope))) {
									if (beliefs == null || hasBeliefs(scope, (List<SPredicate>) beliefs.value(scope))) {
										if (desires == null
												|| hasDesires(scope, (List<SPredicate>) desires.value(scope))) {
											if (swaguenesses == null || hasSwaguenesses(scope,
													(List<SPredicate>) swaguenesses.value(scope))) {
												if (ideals == null
														|| hasIdeals(scope, (List<SPredicate>) ideals.value(scope))) {
													if (obligations == null || hasObligations(scope,
															(List<SPredicate>) obligations.value(scope))) {
														if (spatialknowledges == null || hasSpatialKnowledges(scope,
																(List<SpatialKnowledge>) spatialknowledges.value(scope))) {

															if (threshold == null || spatialknowledges != null
																	&& threshold != null
																	&& SbdiArchitecture.getSpatialKnowledge(scope,
																			(SpatialKnowledge) spatialknowledge.value(
																					scope)).sk_power >= (Double) threshold
																							.value(scope)) {
																if (newDesire != null) {
																	if (allVal) {
																		if (predBeliefList != null)
																			for (SPredicate sp : predBeliefList) {
																				final SPredicate newDes =
																						(SPredicate) newDesire
																								.value(scope);
																				final SMentalState tempNewDesire =
																						new SMentalState("Desire",
																								newDes);
																				tempNewDesire.getSk_predicate().setValues(
																						(Map<String, Object>) System.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewDesire.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewDesire.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addDesire(scope,
																						null, tempNewDesire);
																			}
																		if (predSwaguenessList != null)
																			for (SPredicate sp : predSwaguenessList) {
																				final SPredicate newDes =
																						(SPredicate) newDesire
																								.value(scope);
																				final SMentalState tempNewDesire =
																						new SMentalState("Desire",
																								newDes);
																				tempNewDesire.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewDesire.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewDesire.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addDesire(scope,
																						null, tempNewDesire);
																			}
																		if (predIdealList != null)
																			for (SPredicate sp : predIdealList) {
																				final SPredicate newDes =
																						(SPredicate) newDesire
																								.value(scope);
																				final SMentalState tempNewDesire =
																						new SMentalState("Desire",
																								newDes);
																				tempNewDesire.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewDesire.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewDesire.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addDesire(scope,
																						null, tempNewDesire);
																			}
																	} else {
																		final SPredicate newDes =
																				(SPredicate) newDesire.value(scope);
																		final SMentalState tempNewDesire =
																				new SMentalState("Desire", newDes);
																		if (sk_power != null) {
																			tempNewDesire.setSk_power(Cast.asFloat(
																					scope, sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempNewDesire.setSk_lifeTime(Cast.asInt(scope,
																					sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addDesire(scope, null,
																				tempNewDesire);

																	}
																}
																if (newBelief != null) {
																	if (allVal) {
																		if (predSwaguenessList != null)
																			for (SPredicate sp : predSwaguenessList) {
																				final SPredicate newDes =
																						(SPredicate) newDesire
																								.value(scope);
																				final SMentalState tempNewDesire =
																						new SMentalState("Desire",
																								newDes);
																				tempNewDesire.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewDesire.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewDesire.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addDesire(scope,
																						null, tempNewDesire);
																			}
																		if (predIdealList != null)
																			for (SPredicate sp : predIdealList) {
																				final SPredicate newDes =
																						(SPredicate) newDesire
																								.value(scope);
																				final SMentalState tempNewDesire =
																						new SMentalState("Desire",
																								newDes);
																				tempNewDesire.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewDesire.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewDesire.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addBelief(scope,
																						tempNewDesire);
																			}
																	} else {
																		final SPredicate newBel =
																				(SPredicate) newBelief.value(scope);
																		final SMentalState tempNewBelief =
																				new SMentalState("Belief", newBel);
																		if (sk_power != null) {
																			tempNewBelief.setSk_power(Cast.asFloat(
																					scope, sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempNewBelief.setSk_lifeTime(Cast.asInt(scope,
																					sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addBelief(scope,
																				tempNewBelief);
																	}
																}
																if (newSpatialKnowledge != null) {
																	final SpatialKnowledge newSkno =
																			(SpatialKnowledge) newSpatialKnowledge.value(scope);
																	SbdiArchitecture.addSpatialKnowledge(scope, newSkno);
																}
																if (newSwagueness != null) {
																	if (allVal) {
																		if (predBeliefList != null)
																			for (SPredicate sp : predBeliefList) {
																				final SPredicate newSwag =
																						(SPredicate) newSwagueness
																								.value(scope);
																				final SMentalState tempNewSwagueness =
																						new SMentalState("Swagueness",
																								newSwag);
																				tempNewSwagueness.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				if (sk_power != null) {
																					tempNewSwagueness.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewSwagueness.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addSwagueness(
																						scope, tempNewSwagueness);
																			}
																		if (predIdealList != null)
																			for (SPredicate sp : predIdealList) {
																				final SPredicate newDes =
																						(SPredicate) newDesire
																								.value(scope);
																				final SMentalState tempNewDesire =
																						new SMentalState("Desire",
																								newDes);
																				tempNewDesire.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewDesire.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewDesire.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addSwagueness(
																						scope, tempNewDesire);
																			}
																	} else {
																		final SPredicate newSwag =
																				(SPredicate) newSwagueness.value(scope);
																		final SMentalState tempNewSwagueness =
																				new SMentalState("Swagueness",
																						newSwag);
																		if (sk_power != null) {
																			tempNewSwagueness.setSk_power(Cast.asFloat(
																					scope, sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempNewSwagueness.setSk_lifeTime(Cast.asInt(
																					scope, sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addSwagueness(scope,
																				tempNewSwagueness);
																	}
																}
																if (newIdeal != null) {
																	if (allVal) {
																		if (predBeliefList != null)
																			for (SPredicate sp : predBeliefList) {
																				final SPredicate newIde =
																						(SPredicate) newIdeal
																								.value(scope);
																				final SMentalState tempNewIdeal =
																						new SMentalState("Ideal",
																								newIde);
																				tempNewIdeal.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewIdeal.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewIdeal.setSk_lifeTime(Cast.asInt(
																							scope,
																							sk_lifetime.value(scope)));
																				}
																				SbdiArchitecture.addIdeal(scope,
																						tempNewIdeal);
																			}
																		if (predSwaguenessList != null)
																			for (SPredicate sp : predSwaguenessList) {
																				final SPredicate newDes =
																						(SPredicate) newDesire
																								.value(scope);
																				final SMentalState tempNewDesire =
																						new SMentalState("Desire",
																								newDes);
																				tempNewDesire.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				if (sk_power != null) {
																					tempNewDesire.setSk_power(
																							Cast.asFloat(scope, sk_power
																									.value(scope)));
																				}
																				if (sk_lifetime != null) {
																					tempNewDesire.setSk_lifeTime(
																							Cast.asInt(scope, sk_lifetime
																									.value(scope)));
																				}
																				SbdiArchitecture.addIdeal(scope,
																						tempNewDesire);
																			}
																	} else {
																		final SPredicate newIde =
																				(SPredicate) newIdeal.value(scope);
																		final SMentalState tempNewIdeal =
																				new SMentalState("Ideal", newIde);
																		if (sk_power != null) {
																			tempNewIdeal.setSk_power(Cast.asFloat(scope,
																					sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempNewIdeal.setSk_lifeTime(Cast.asInt(scope,
																					sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addIdeal(scope,
																				tempNewIdeal);
																	}
																}
																if (removeBelief != null) {
																	if (allVal) {
																		if (predSwaguenessList != null)
																			for (SPredicate sp : predSwaguenessList) {
																				final SPredicate removBel =
																						(SPredicate) removeBelief
																								.value(scope);
																				final SMentalState tempRemoveBelief =
																						new SMentalState("Belief",
																								removBel);
																				tempRemoveBelief.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				SbdiArchitecture.removeBelief(
																						scope, tempRemoveBelief);
																			}
																		if (predIdealList != null)
																			for (SPredicate sp : predIdealList) {
																				final SPredicate removBel =
																						(SPredicate) removeBelief
																								.value(scope);
																				final SMentalState tempRemoveBelief =
																						new SMentalState("Belief",
																								removBel);
																				tempRemoveBelief.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				SbdiArchitecture.removeBelief(
																						scope, tempRemoveBelief);
																			}
																	} else {
																		final SPredicate removBel =
																				(SPredicate) removeBelief.value(scope);
																		final SMentalState tempRemoveBelief =
																				new SMentalState("Belief", removBel);
																		SbdiArchitecture.removeBelief(scope,
																				tempRemoveBelief);
																	}
																}
																if (removeDesire != null) {
																	if (allVal) {
																		if (predBeliefList != null)
																			for (SPredicate sp : predBeliefList) {
																				final SPredicate removeDes =
																						(SPredicate) removeDesire
																								.value(scope);
																				final SMentalState tempRemoveDesire =
																						new SMentalState("Desire",
																								removeDes);
																				tempRemoveDesire.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				SbdiArchitecture.removeDesire(
																						scope, tempRemoveDesire);
																			}
																		if (predSwaguenessList != null)
																			for (SPredicate sp : predSwaguenessList) {
																				final SPredicate removeDes =
																						(SPredicate) removeDesire
																								.value(scope);
																				final SMentalState tempRemoveDesire =
																						new SMentalState("Desire",
																								removeDes);
																				tempRemoveDesire.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				SbdiArchitecture.removeDesire(
																						scope, tempRemoveDesire);
																			}
																		if (predIdealList != null)
																			for (SPredicate sp : predIdealList) {
																				final SPredicate removeDes =
																						(SPredicate) removeDesire
																								.value(scope);
																				final SMentalState tempRemoveDesire =
																						new SMentalState("Desire",
																								removeDes);
																				tempRemoveDesire.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				SbdiArchitecture.removeDesire(
																						scope, tempRemoveDesire);
																			}
																	} else {
																		final SPredicate removeDes =
																				(SPredicate) removeDesire.value(scope);
																		final SMentalState tempRemoveDesire =
																				new SMentalState("Desire", removeDes);
																		SbdiArchitecture.removeDesire(scope,
																				tempRemoveDesire);
																	}
																}
																if (removeIntention != null) {
																	final SPredicate removeInt =
																			(SPredicate) removeIntention.value(scope);
																	final SMentalState tempRemoveIntention =
																			new SMentalState("Intention", removeInt);
																	SbdiArchitecture.removeIntention(scope,
																			tempRemoveIntention);
																}
																if (removeSpatialKnowledge != null) {
																	final SpatialKnowledge removeEmo =
																			(SpatialKnowledge) removeSpatialKnowledge.value(scope);
																	SbdiArchitecture.removeSpatialKnowledge(scope,
																			removeEmo);
																}
																if (removeSpatialKnowledge != null) {
																	if (allVal) {
																		if (predBeliefList != null)
																			for (SPredicate sp : predBeliefList) {
																				final SPredicate removUncert =
																						(SPredicate) removeSwagueness
																								.value(scope);
																				final SMentalState tempRemoveSwagueness =
																						new SMentalState("Swagueness",
																								removUncert);
																				tempRemoveSwagueness.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				SbdiArchitecture.removeSwagueness(
																						scope, tempRemoveSwagueness);
																			}
																		if (predIdealList != null)
																			for (SPredicate sp : predIdealList) {
																				final SPredicate removSwag =
																						(SPredicate) removeSwagueness
																								.value(scope);
																				final SMentalState tempRemoveSwagueness =
																						new SMentalState("Swagueness",
																								removSwag);
																				tempRemoveSwagueness.getSk_predicate()
																						.setValues(
																								(Map<String, Object>) System
																										.opCopy(scope, sp
																												.getValues()));
																				SbdiArchitecture.removeSwagueness(
																						scope, tempRemoveSwagueness);
																			}
																	} else {
																		final SPredicate removSwag =
																				(SPredicate) removeSwagueness
																						.value(scope);
																		final SMentalState tempRemoveSwagueness =
																				new SMentalState("Swagueness",
																						removSwag);
																		SbdiArchitecture.removeSwagueness(scope,
																				tempRemoveSwagueness);
																	}
																}
																if (removeIdeal != null) {
																	if (allVal) {
																		if (predBeliefList != null)
																			for (SPredicate sp : predBeliefList) {
																				final SPredicate removeIde =
																						(SPredicate) removeIdeal
																								.value(scope);
																				final SMentalState tempRemoveIde =
																						new SMentalState("Ideal",
																								removeIde);
																				tempRemoveIde.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				SbdiArchitecture.removeIdeal(scope,
																						tempRemoveIde);
																			}
																		if (predSwaguenessList != null)
																			for (SPredicate sp : predSwaguenessList) {
																				final SPredicate removeIde =
																						(SPredicate) removeIdeal
																								.value(scope);
																				final SMentalState tempRemoveIde =
																						new SMentalState("Ideal",
																								removeIde);
																				tempRemoveIde.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				SbdiArchitecture.removeIdeal(scope,
																						tempRemoveIde);
																			}
																	} else {
																		final SPredicate removeIde =
																				(SPredicate) removeIdeal.value(scope);
																		final SMentalState tempRemoveIde =
																				new SMentalState("Ideal", removeIde);
																		SbdiArchitecture.removeIdeal(scope,
																				tempRemoveIde);
																	}
																}
																if (removeObligation != null) {
																	if (allVal) {
																		if (predBeliefList != null)
																			for (SPredicate sp : predBeliefList) {
																				final SPredicate removeObl =
																						(SPredicate) removeObligation
																								.value(scope);
																				final SMentalState tempRemoveObl =
																						new SMentalState("Obligation",
																								removeObl);
																				tempRemoveObl.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				SbdiArchitecture.removeObligation(
																						scope, tempRemoveObl);
																			}
																		if (predSwaguenessList != null)
																			for (SPredicate sp : predSwaguenessList) {
																				final SPredicate removeObl =
																						(SPredicate) removeObligation
																								.value(scope);
																				final SMentalState tempRemoveObl =
																						new SMentalState("Obligation",
																								removeObl);
																				tempRemoveObl.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				SbdiArchitecture.removeObligation(
																						scope, tempRemoveObl);
																			}
																		if (predIdealList != null)
																			for (SPredicate sp : predIdealList) {
																				final SPredicate removeObl =
																						(SPredicate) removeObligation
																								.value(scope);
																				final SMentalState tempRemoveObl =
																						new SMentalState("Obligation",
																								removeObl);
																				tempRemoveObl.getSk_predicate().setValues(
																						(Map<String, Object>) System
																								.opCopy(scope,
																										sp.getValues()));
																				SbdiArchitecture.removeObligation(
																						scope, tempRemoveObl);
																			}
																	} else {
																		final SPredicate removeObl =
																				(SPredicate) removeObligation
																						.value(scope);
																		final SMentalState tempRemoveObl =
																				new SMentalState("Obligation",
																						removeObl);
																		SbdiArchitecture.removeObligation(scope,
																				tempRemoveObl);
																	}
																}
																if (newDesires != null) {
																	final List<SPredicate> newDess =
																			(List<SPredicate>) newDesires.value(scope);
																	for (final SPredicate newDes : newDess) {
																		final SMentalState tempDesires =
																				new SMentalState("Desire", newDes);
																		if (sk_power != null) {
																			tempDesires.setSk_power(Cast.asFloat(scope,
																					sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempDesires.setSk_lifeTime(Cast.asInt(scope,
																					sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addDesire(scope, null,
																				tempDesires);
																	}
																}
																if (newBeliefs != null) {
																	final List<SPredicate> newBels =
																			(List<SPredicate>) newBeliefs.value(scope);
																	for (final SPredicate newBel : newBels) {
																		final SMentalState tempBeliefs =
																				new SMentalState("Belief", newBel);
																		if (sk_power != null) {
																			tempBeliefs.setSk_power(Cast.asFloat(scope,
																					sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempBeliefs.setSk_lifeTime(Cast.asInt(scope,
																					sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addBelief(scope,
																				tempBeliefs);
																	}
																}
																if (newSpatialKnowledges != null) {
																	final List<SpatialKnowledge> newSknows =
																			(List<SpatialKnowledge>) newSpatialKnowledges.value(scope);
																	for (final SpatialKnowledge newEmo : newSknows)
																		SbdiArchitecture.addSpatialKnowledge(scope, newEmo);
																}
																if (newSwaguenesses != null) {
																	final List<SPredicate> newSwags =
																			(List<SPredicate>) newSwaguenesses
																					.value(scope);
																	for (final SPredicate newSwag : newSwags) {
																		final SMentalState tempSwagunesses =
																				new SMentalState("Swagueness",
																						newSwag);
																		if (sk_power != null) {
																			tempSwagunesses.setSk_power(Cast.asFloat(
																					scope, sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempSwagunesses.setSk_lifeTime(Cast.asInt(
																					scope, sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addSwagueness(scope,
																				tempSwagunesses);
																	}
																}
																if (newIdeals != null) {
																	final List<SPredicate> newIdes =
																			(List<SPredicate>) newIdeals.value(scope);
																	for (final SPredicate newIde : newIdes) {
																		final SMentalState tempIdeals =
																				new SMentalState("Ideal", newIde);
																		if (sk_power != null) {
																			tempIdeals.setSk_power(Cast.asFloat(scope,
																					sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempIdeals.setSk_lifeTime(Cast.asInt(scope,
																					sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addIdeal(scope,
																				tempIdeals);
																	}
																}
																if (removeBeliefs != null) {
																	final List<SPredicate> removBels =
																			(List<SPredicate>) removeBeliefs
																					.value(scope);
																	for (final SPredicate removBel : removBels) {
																		final SMentalState tempRemoveBeliefs =
																				new SMentalState("Belief", removBel);
																		SbdiArchitecture.removeBelief(scope,
																				tempRemoveBeliefs);
																	}
																}
																if (removeDesires != null) {
																	final List<SPredicate> removeDess =
																			(List<SPredicate>) removeDesires
																					.value(scope);
																	for (final SPredicate removeDes : removeDess) {
																		final SMentalState tempRemoveDesires =
																				new SMentalState("Desire", removeDes);
																		SbdiArchitecture.removeDesire(scope,
																				tempRemoveDesires);
																	}
																}
																if (removeSpatialKnowedge != null) {
																	final List<SpatialKnowledge> removeSKnows =
																			(List<SpatialKnowledge>) removeSpatialKnowedge.value(scope);
																	for (final SpatialKnowledge removeSKnow : removeSKnows)
																		SbdiArchitecture.removeSpatialKnowledge(scope,
																				removeSKnow);
																}
																if (removeSwagueness != null) {
																	final List<SPredicate> removSwags =
																			(List<SPredicate>) removeSwagueness
																					.value(scope);
																	for (final SPredicate removSwag : removSwags) {
																		final SMentalState tempRemoveSwaguenesses =
																				new SMentalState("Swagueness",
																						removSwag);
																		SbdiArchitecture.removeSwagueness(scope,
																				tempRemoveSwaguenesses);
																	}
																}
																if (removeIdeals != null) {
																	final List<SPredicate> removeIdes =
																			(List<SPredicate>) removeIdeals.value(scope);
																	for (final SPredicate removeIde : removeIdes) {
																		final SMentalState tempRemoveIdeals =
																				new SMentalState("Ideal", removeIde);
																		SbdiArchitecture.removeIdeal(scope,
																				tempRemoveIdeals);
																	}
																}
																if (removeObligations != null) {
																	final List<SPredicate> removeObls =
																			(List<SPredicate>) removeObligations
																					.value(scope);
																	for (final SPredicate removeObl : removeObls) {
																		final SMentalState tempRemoveObligations =
																				new SMentalState("Obligation",
																						removeObl);
																		SbdiArchitecture.removeObligation(scope,
																				tempRemoveObligations);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	private boolean hasBeliefs(final IScope scope, final List<SPredicate> spredicates) {
		for (final SPredicate sp : spredicates) {
			final SMentalState temp = new SMentalState("Belief", sp);
			if (!SbdiArchitecture.hasBelief(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasDesires(final IScope scope, final List<SPredicate> spredicates) {
		for (final SPredicate sp : spredicates) {
			final SMentalState temp = new SMentalState("Desire", sp);
			if (!SbdiArchitecture.hasDesire(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasSwaguenesses(final IScope scope, final List<SPredicate> spredicates) {
		for (final SPredicate sp : spredicates) {
			final SMentalState temp = new SMentalState("Swagueness", sp);
			if (!SbdiArchitecture.hasSwagueness(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasIdeals(final IScope scope, final List<SPredicate> spredicates) {
		for (final SPredicate sp : spredicates) {
			final SMentalState temp = new SMentalState("Ideal", sp);
			if (!SbdiArchitecture.hasIdeal(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasObligations(final IScope scope, final List<SPredicate> spredicates) {
		for (final SPredicate sp : spredicates) {
			final SMentalState temp = new SMentalState("Swagueness", sp);
			if (!SbdiArchitecture.hasSwagueness(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasSpatialKnowledges(final IScope scope, final List<SpatialKnowledge> spatialknowledge) {
		for (final SpatialKnowledge sk : spatialknowledge) {
			if (!SbdiArchitecture.hasSpatialKnowledge(scope, sk))
				return false;
		}
		return true;
	}

	public IExpression getParallel() {
		return parallel;
	}

}
