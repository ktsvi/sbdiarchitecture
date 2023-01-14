package urifia.gaml.architecture.sbdi;


import java.util.List;

import msi.gama.common.interfaces.IKeyword;
import msi.gama.precompiler.IConcept;
import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.example;
import msi.gama.precompiler.GamlAnnotations.facet;
import msi.gama.precompiler.GamlAnnotations.facets;
import msi.gama.precompiler.GamlAnnotations.inside;
import msi.gama.precompiler.GamlAnnotations.symbol;
import msi.gaml.descriptions.IDescription;
import msi.gaml.expressions.IExpression;
import msi.gaml.operators.Cast;
import msi.gaml.statements.AbstractStatementSequence;
import msi.gaml.types.IType;

@symbol (
		name = CopingStatement.COPING,
		kind = ISymbolKind.BEHAVIOR,
		with_sequence = true,
		concept = { IConcept.BDI })
@inside (
		symbols = { SbdiArchitecture.SBDI, SbdiArchitectureParallel.PARALLEL_BDI },
		kinds = { ISymbolKind.SPECIES, ISymbolKind.MODEL })
@facets (
		value = { @facet (
				name = CopingStatement.BELIEF,
				type = SPredicateType.id,
				optional = true,
				doc = @doc ("The mandatory belief")),
				@facet (
						name = CopingStatement.DESIRE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory desire")),
				@facet (
						name = CopingStatement.SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The mandatory emotion")),
				@facet (
						name = CopingStatement.SWAGUENESS,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory uncertainty")),
				@facet (
						name = CopingStatement.IDEAL,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory ideal")),
				@facet (
						name = CopingStatement.OBLIGATION,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory obligation")),
				@facet (
						name = CopingStatement.DESIRES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory desires")),
				@facet (
						name = CopingStatement.BELIEFS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory beliefs")),
				@facet (
						name = CopingStatement.SPATIAL_KNOWLEDGES,
						type = IType.LIST,
						of = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The mandatory emotions")),
				@facet (
						name = CopingStatement.SWAGUENESSES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory uncertainties")),
				@facet (
						name = CopingStatement.IDEALS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory ideals")),
				@facet (
						name = CopingStatement.OBLIGATIONS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The mandatory obligations")),
				@facet (
						name = CopingStatement.NEW_DESIRE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be added")),
				@facet (
						name = CopingStatement.NEW_BELIEF,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be added")),
				@facet (
						name = CopingStatement.NEW_SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The emotion that will be added")),
				@facet (
						name = CopingStatement.NEW_SWAGUENESS,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be added")),
				@facet (
						name = CopingStatement.NEW_IDEAL,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideal that will be added")),
				@facet (
						name = CopingStatement.NEW_DESIRES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be added")),
				@facet (
						name = CopingStatement.NEW_BELIEFS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be added")),
				@facet (
						name = CopingStatement.NEW_SPATIAL_KNOWLEDGES,
						type = IType.LIST,
						of = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The emotion that will be added")),
				@facet (
						name = CopingStatement.NEW_SWAGUENESSES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be added")),
				@facet (
						name = CopingStatement.NEW_IDEALS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideals that will be added")),
				@facet (
						name = CopingStatement.REMOVE_BELIEFS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_DESIRES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_SPATIAL_KNOWLEDGES,
						type = IType.LIST,
						of = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The emotion that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_IDEALS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideals that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_OBLIGATIONS,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The obligation that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_SWAGUENESSES,
						type = IType.LIST,
						of = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_BELIEF,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The belief that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_IDEAL,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The ideal that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_DESIRE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The desire that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_INTENTION,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The intention that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The spatial knowledge that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_SWAGUENESS,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The uncertainty that will be removed")),
				@facet (
						name = CopingStatement.REMOVE_OBLIGATION,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The obligation that will be removed")),
				@facet (
						name = IKeyword.WHEN,
						type = IType.BOOL,
						optional = true,
						doc = @doc (" ")),
				@facet (
						name = CopingStatement.THRESHOLD,
						type = IType.FLOAT,
						optional = true,
						doc = @doc ("Threshold linked to the emotion.")),
				@facet (
						name = IKeyword.PARALLEL,
						type = { IType.BOOL, IType.INT },
						optional = true,
						doc = @doc ("setting this facet to 'true' will allow 'perceive' to use concurrency with a parallel_bdi architecture; setting it to an integer will set the threshold under which they will be run sequentially (the default is initially 20, but can be fixed in the preferences). This facet is true by default.")),
				@facet (
						name = CopingStatement.SK_POWER,
						type = { IType.FLOAT, IType.INT },
						optional = true,
						doc = @doc ("The stregth of the mental state created")),
				@facet (
						name = "sk_lifetime",
						type = IType.INT,
						optional = true,
						doc = @doc ("the sk_lifetime value of the mental state created")),
				@facet (
						name = IKeyword.NAME,
						type = IType.ID,
						optional = true,
						doc = @doc ("The name of the rule")) },
		omissible = IKeyword.NAME)
@doc (
		value = "enables to add or remove spatial mantal states depending on the emotions of the agent, after the emotional engine and before the cognitive or normative engine.",
		examples = {
				@example ("coping spatial_knowledge: new_spatial_knowledge(\"PO\") when: MpoB-B(1) new_desire: new_sk_predicate(\"PO\")") })

public class CopingStatement extends AbstractStatementSequence{
	
	public static final String COPING = "coping";
	public static final String BELIEF = "belief";
	public static final String DESIRE = "desire";
	public static final String SPATIAL_KNOWLEDGE = "spatial_knowledge";
	public static final String SWAGUENESS = "swagueness";
	public static final String IDEAL = "ideal";
	public static final String OBLIGATION = "obligation";
	public static final String RULES = "rules";
	public static final String BELIEFS = "beliefs";
	public static final String DESIRES = "desires";
	public static final String SPATIAL_KNOWLEDGES = "spatial_knowledges";
	public static final String SWAGUENESSES = "swaguenesses";
	public static final String IDEALS = "ideals";
	public static final String OBLIGATIONS = "obligations";
	public static final String NEW_DESIRE = "new_desire";
	public static final String NEW_BELIEF = "new_belief";
	public static final String NEW_SPATIAL_KNOWLEDGE = "new_spatial_knowledge";
	public static final String NEW_SWAGUENESS = "new_swagueness";
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

	final IExpression when;
	final IExpression parallel;
	final IExpression belief;
	final IExpression desire;
	final IExpression spatial_knowledge;
	final IExpression swagueness;
	final IExpression ideal;
	final IExpression obligation;
	final IExpression beliefs;
	final IExpression desires;
	final IExpression spatial_knowledges;
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
	final IExpression removeSpatialKnowledges;
	final IExpression removeSwaguenesses;
	final IExpression removeIdeals;
	final IExpression removeObligations;
	final IExpression sk_power;
	final IExpression threshold;
	final IExpression sk_lifetime;
	
	public CopingStatement(IDescription desc) {
		super(desc);
		when = getFacet(IKeyword.WHEN);
		belief = getFacet(CopingStatement.BELIEF);
		desire = getFacet(CopingStatement.DESIRE);
		spatial_knowledge = getFacet(CopingStatement.SPATIAL_KNOWLEDGE);
		swagueness = getFacet(CopingStatement.SWAGUENESS);
		ideal = getFacet(CopingStatement.IDEAL);
		obligation = getFacet(CopingStatement.OBLIGATION);
		beliefs = getFacet(CopingStatement.BELIEFS);
		desires = getFacet(CopingStatement.DESIRES);
		spatial_knowledges = getFacet(CopingStatement.SPATIAL_KNOWLEDGES);
		swaguenesses = getFacet(CopingStatement.SWAGUENESSES);
		ideals = getFacet(CopingStatement.IDEALS);
		obligations = getFacet(CopingStatement.OBLIGATIONS);
		newBelief = getFacet(CopingStatement.NEW_BELIEF);
		newDesire = getFacet(CopingStatement.NEW_DESIRE);
		newSpatialKnowledge = getFacet(CopingStatement.NEW_SPATIAL_KNOWLEDGE);
		newSwagueness = getFacet(CopingStatement.NEW_SWAGUENESS);
		newIdeal = getFacet(CopingStatement.NEW_IDEAL);
		removeBelief = getFacet(CopingStatement.REMOVE_BELIEF);
		removeDesire = getFacet(CopingStatement.REMOVE_DESIRE);
		removeIntention = getFacet(CopingStatement.REMOVE_INTENTION);
		removeSpatialKnowledge = getFacet(CopingStatement.REMOVE_SPATIAL_KNOWLEDGE);
		removeSwagueness = getFacet(CopingStatement.REMOVE_SWAGUENESS);
		removeIdeal = getFacet(CopingStatement.REMOVE_IDEAL);
		removeObligation = getFacet(CopingStatement.REMOVE_OBLIGATION);
		newBeliefs = getFacet(CopingStatement.NEW_BELIEFS);
		newDesires = getFacet(CopingStatement.NEW_DESIRES);
		newSpatialKnowledges = getFacet(CopingStatement.NEW_SPATIAL_KNOWLEDGES);
		newSwaguenesses = getFacet(CopingStatement.NEW_SWAGUENESSES);
		newIdeals = getFacet(CopingStatement.NEW_IDEALS);
		removeBeliefs = getFacet(CopingStatement.REMOVE_BELIEFS);
		removeDesires = getFacet(CopingStatement.REMOVE_DESIRES);
		removeSpatialKnowledges = getFacet(CopingStatement.REMOVE_SPATIAL_KNOWLEDGES);
		removeSwaguenesses = getFacet(CopingStatement.REMOVE_SWAGUENESSES);
		removeIdeals = getFacet(CopingStatement.REMOVE_IDEALS);
		removeObligations = getFacet(CopingStatement.REMOVE_OBLIGATIONS);
		sk_power = getFacet(CopingStatement.SK_POWER);
		threshold = getFacet(CopingStatement.THRESHOLD);
		sk_lifetime = getFacet("sk_lifetime");
		parallel = getFacet(IKeyword.PARALLEL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object privateExecuteIn(IScope scope) throws GamaRuntimeException {
		if (newBelief == null && newDesire == null && newSpatialKnowledge == null && newSwagueness == null
				&& removeBelief == null && removeDesire == null && removeIntention == null && removeSpatialKnowledge == null
				&& removeSwagueness == null && newBeliefs == null && newDesires == null && newSpatialKnowledges == null
				&& newSwaguenesses == null && removeBeliefs == null && removeDesires == null && removeSpatialKnowledges == null
				&& removeSwaguenesses == null)
			return null;
		if (when == null || Cast.asBool(scope, when.value(scope))) {
			final SMentalState tempBelief = new SMentalState("Belief");
			if (belief != null) {
			tempBelief.setPredicate((SPredicate) belief.value(scope));
			}
			if (belief == null || SbdiArchitecture.hasBelief(scope, tempBelief)) {
				final SMentalState tempDesire = new SMentalState("Desire");
				if (desire != null) {
					tempDesire.setPredicate((SPredicate) desire.value(scope));
				}
				if (desire == null || SbdiArchitecture.hasDesire(scope, tempDesire)) {
					final SMentalState tempSwagueness = new SMentalState("Swagueness");
					if (swagueness != null) {
						tempSwagueness.setPredicate((SPredicate) swagueness.value(scope));
					}
					if (swagueness == null || SbdiArchitecture.hasSwagueness(scope, tempSwagueness)) {
						final SMentalState tempIdeal = new SMentalState("Ideal");
						if (ideal != null) {
						tempIdeal.setPredicate((SPredicate) ideal.value(scope));
						}
						if (ideal == null || SbdiArchitecture.hasIdeal(scope, tempIdeal)) {
							final SMentalState tempObligation = new SMentalState("Obligation");
							if (obligation != null) {
								tempObligation.setPredicate((SPredicate) obligation.value(scope));
							}
							if (obligation == null || SbdiArchitecture.hasObligation(scope, tempSwagueness)) {
								if (spatial_knowledge == null
										|| SbdiArchitecture.hasSpatialKnowledge(scope, (SpatialKnowledge) spatial_knowledge.value(scope))) {
									if (beliefs == null || hasBeliefs(scope, (List<SPredicate>) beliefs.value(scope))) {
										if (desires == null
												|| hasDesires(scope, (List<SPredicate>) desires.value(scope))) {
											if (swaguenesses == null || hasSwaguenesses(scope,
													(List<SPredicate>) swaguenesses.value(scope))) {
												if (ideals == null
														|| hasIdeals(scope, (List<SPredicate>) ideals.value(scope))) {
													if (obligations == null || hasObligations(scope,
															(List<SPredicate>) obligations.value(scope))) {
														if (spatial_knowledges == null || hasSpatialKnowledges(scope,
																(List<SpatialKnowledge>) spatial_knowledges.value(scope))) {
															if (threshold == null || spatial_knowledge != null
																	&& threshold != null
																	&& SbdiArchitecture.getSpatialKnowledge(scope,
																			(SpatialKnowledge) spatial_knowledge.value(
																					scope)).sk_power >= (Double) threshold
																							.value(scope)) {
																if (newDesire != null) {
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
																if (newBelief != null) {
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
																if (newSpatialKnowledge != null) {
																	final SpatialKnowledge newSpatKno =
																			(SpatialKnowledge) newSpatialKnowledge.value(scope);
																	SbdiArchitecture.addSpatialKnowledge(scope, newSpatKno);
																}
																if (newSwagueness != null) {
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
																if (newIdeal != null) {
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
																if (removeBelief != null) {
																	final SPredicate removBel =
																			(SPredicate) removeBelief.value(scope);
																	final SMentalState tempRemoveBelief =
																			new SMentalState("Belief", removBel);
																	SbdiArchitecture.removeBelief(scope,
																			tempRemoveBelief);
																}
																if (removeDesire != null) {
																	final SPredicate removeDes =
																			(SPredicate) removeDesire.value(scope);
																	final SMentalState tempRemoveDesire =
																			new SMentalState("Desire", removeDes);
																	SbdiArchitecture.removeDesire(scope,
																			tempRemoveDesire);
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
																if (removeSwagueness != null) {
																	final SPredicate removUncert =
																			(SPredicate) removeSwagueness
																					.value(scope);
																	final SMentalState tempRemoveUncertainty =
																			new SMentalState("Swagueness",
																					removUncert);
																	SbdiArchitecture.removeSwagueness(scope,
																			tempRemoveUncertainty);
																}
																if (removeIdeal != null) {
																	final SPredicate removeIde =
																			(SPredicate) removeIdeal.value(scope);
																	final SMentalState tempRemoveIde =
																			new SMentalState("Ideal", removeIde);
																	SbdiArchitecture.removeIdeal(scope,
																			tempRemoveIde);
																}
																if (removeObligation != null) {
																	final SPredicate removeObl =
																			(SPredicate) removeObligation
																					.value(scope);
																	final SMentalState tempRemoveObl =
																			new SMentalState("Obligation",
																					removeObl);
																	SbdiArchitecture.removeObligation(scope,
																			tempRemoveObl);
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
																	final List<SpatialKnowledge> newSpatSknos =
																			(List<SpatialKnowledge>) newSpatialKnowledges.value(scope);
																	for (final SpatialKnowledge newSpatSkno : newSpatSknos)
																		SbdiArchitecture.addSpatialKnowledge(scope, newSpatSkno);
																}
																if (newSwaguenesses != null) {
																	final List<SPredicate> newSwags =
																			(List<SPredicate>) newSwaguenesses
																					.value(scope);
																	for (final SPredicate newSwag : newSwags) {
																		final SMentalState tempSwaguenesses =
																				new SMentalState("Swagueness",
																						newSwag);
																		if (sk_power != null) {
																			tempSwaguenesses.setSk_power(Cast.asFloat(
																					scope, sk_power.value(scope)));
																		}
																		if (sk_lifetime != null) {
																			tempSwaguenesses.setSk_lifeTime(Cast.asInt(
																					scope, sk_lifetime.value(scope)));
																		}
																		SbdiArchitecture.addSwagueness(scope,
																				tempSwaguenesses);
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
																if (removeSpatialKnowledges != null) {
																	final List<SpatialKnowledge> removeSpatSknos =
																			(List<SpatialKnowledge>) removeSpatialKnowledges.value(scope);
																	for (final SpatialKnowledge removeSpatSkno : removeSpatSknos)
																		SbdiArchitecture.removeSpatialKnowledge(scope,
																				removeSpatSkno);
																}
																if (removeSwaguenesses != null) {
																	final List<SPredicate> removSwags =
																			(List<SPredicate>) removeSwaguenesses
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
		return super.privateExecuteIn(scope);
//		return null;
	}
	
	private boolean hasBeliefs(final IScope scope, final List<SPredicate> predicates) {
		for (final SPredicate p : predicates) {
			final SMentalState temp = new SMentalState("Belief", p);
			if (!SbdiArchitecture.hasBelief(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasDesires(final IScope scope, final List<SPredicate> predicates) {
		for (final SPredicate p : predicates) {
			final SMentalState temp = new SMentalState("Desire", p);
			if (!SbdiArchitecture.hasDesire(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasSwaguenesses(final IScope scope, final List<SPredicate> predicates) {
		for (final SPredicate p : predicates) {
			final SMentalState temp = new SMentalState("Swagueness", p);
			if (!SbdiArchitecture.hasSwagueness(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasIdeals(final IScope scope, final List<SPredicate> predicates) {
		for (final SPredicate p : predicates) {
			final SMentalState temp = new SMentalState("Ideal", p);
			if (!SbdiArchitecture.hasIdeal(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasObligations(final IScope scope, final List<SPredicate> predicates) {
		for (final SPredicate sp : predicates) {
			final SMentalState temp = new SMentalState("Swagueness", sp);
			if (!SbdiArchitecture.hasSwagueness(scope, temp))
				return false;
		}
		return true;
	}

	private boolean hasSpatialKnowledges(final IScope scope, final List<SpatialKnowledge> sknows) {
		for (final SpatialKnowledge p : sknows) {
			if (!SbdiArchitecture.hasSpatialKnowledge(scope, p))
				return false;
		}
		return true;
	}

	public IExpression getParallel() {
		return parallel;
	}
	
}
