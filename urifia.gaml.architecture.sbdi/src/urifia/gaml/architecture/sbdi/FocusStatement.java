package urifia.gaml.architecture.sbdi;

import msi.gama.common.interfaces.IKeyword;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.example;
import msi.gama.precompiler.GamlAnnotations.facet;
import msi.gama.precompiler.GamlAnnotations.facets;
import msi.gama.precompiler.GamlAnnotations.inside;
import msi.gama.precompiler.GamlAnnotations.symbol;
import msi.gama.precompiler.IConcept;
import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.GAMA;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.util.GamaMapFactory;
import msi.gama.util.IContainer;
import msi.gama.util.IList;
import msi.gama.util.IMap;
import msi.gaml.descriptions.IDescription;
import msi.gaml.expressions.IExpression;
import msi.gaml.operators.Cast;
import msi.gaml.statements.AbstractStatement;
import msi.gaml.types.IType;
import msi.gaml.types.Types;

@symbol (
		name = FocusStatement.FOCUS,
		kind = ISymbolKind.SINGLE_STATEMENT,
		with_sequence = false,
		concept = { IConcept.BDI })
@inside (
		kinds = { ISymbolKind.BEHAVIOR, ISymbolKind.SEQUENCE_STATEMENT })
@facets (
		value = { @facet (
				name = IKeyword.ID,
				type = IType.STRING,
				optional = true,
				doc = @doc ("the identifier of the focus")),
				@facet (
						name = FocusStatement.VAR,
						type = { IType.NONE, IType.LIST, IType.CONTAINER },
						optional = true,
						doc = @doc ("the variable of the perceived agent you want to add to your beliefs")),
				@facet (
						name = FocusStatement.EXPRESSION,
						type = IType.NONE,
						optional = true,
						doc = @doc ("an expression that will be the value kept in the belief")),
				@facet (
						name = IKeyword.WHEN,
						type = IType.BOOL,
						optional = true,
						doc = @doc ("A boolean value to focus only with a certain condition")),
				@facet (
						name = FocusStatement.SK_LIFETIME,
						type = IType.INT,
						optional = true,
						doc = @doc ("the sk_lifetime value of the created belief")),
				@facet (
						name = FocusStatement.TRUTH,
						type = IType.BOOL,
						optional = true,
						doc = @doc ("the truth value of the created belief")),
				@facet (
						name = FocusStatement.AGENT_GET_PREDICATE,
						type = IType.AGENT,
						optional = true,
						doc = @doc ("the agentGetpredicate value of the created belief (can be nil")),
				@facet (
						name = FocusStatement.BELIEF,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The predicate to focus on the beliefs of the other agent")),
				@facet (
						name = FocusStatement.DESIRE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The predicate to focus on the desires of the other agent")),
				@facet (
						name = FocusStatement.SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("The spatial_knowledge to focus on the emotions of the other agent")),
				@facet (
						name = FocusStatement.SWAGUENESS,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The predicate to focus on the uncertainties of the other agent")),
				@facet (
						name = FocusStatement.IDEAL,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("The predicate to focus on the ideals of the other agent")),
				@facet (
						name = FocusStatement.ISSWAGUENESS,
						type = IType.BOOL,
						optional = true,
						doc = @doc ("a boolean to indicate if the mental state created is an swagueness")),
				@facet (
						name = FocusStatement.SK_POWER,
						type = { IType.FLOAT, IType.INT },
						optional = true,
						doc = @doc ("The priority of the created sk predicate")) })
@doc (
		value = "enables to directly add a belief from the variable of a perceived specie.",
		examples = {
				@example ("focus var:speed /*where speed is a variable from a species that is being perceived*/") })
public class FocusStatement extends AbstractStatement {

	public static final String FOCUS = "focus";
	public static final String SK_POWER = "sk_power";
	public static final String EXPRESSION = "expression";
	public static final String VAR = "var";
	public static final String BELIEF = "belief";
	public static final String DESIRE = "desire";
	public static final String SWAGUENESS = "swagueness";
	public static final String IDEAL = "ideal";
	public static final String SPATIAL_KNOWLEDGE = "spatial_knowledge";
	public static final String SK_LIFETIME = "sk_lifetime";
	public static final String TRUTH = "truth";
	public static final String AGENT_GET_PREDICATE = "agent_get_predicate";
	public static final String ISSWAGUENESS = "is_swagueness";

	final IExpression nameExpression;
	final IExpression variable;
	final IExpression expression;
	final IExpression belief;
	final IExpression desire;
	final IExpression swagueness;
	final IExpression ideal;
	final IExpression spatial_knowledge;
	final IExpression when;
	final IExpression sk_power;
	final IExpression sk_lifetime;
	final IExpression truth;
	final IExpression agentGetPredicate;
	final IExpression isSwagueness;

	public FocusStatement(final IDescription desc) {
		super(desc);
		nameExpression = getFacet(IKeyword.ID);
		variable = getFacet(FocusStatement.VAR);
		expression = getFacet(FocusStatement.EXPRESSION);
		belief = getFacet(FocusStatement.BELIEF);
		desire = getFacet(FocusStatement.DESIRE);
		swagueness = getFacet(FocusStatement.SWAGUENESS);
		ideal = getFacet(FocusStatement.IDEAL);
		spatial_knowledge = getFacet(FocusStatement.SPATIAL_KNOWLEDGE);
		when = getFacet(IKeyword.WHEN);
		sk_power = getFacet(FocusStatement.SK_POWER);
		sk_lifetime = getFacet(FocusStatement.SK_LIFETIME);
		truth = getFacet(FocusStatement.TRUTH);
		agentGetPredicate = getFacet(FocusStatement.AGENT_GET_PREDICATE);
		isSwagueness = getFacet(FocusStatement.ISSWAGUENESS);
	}

	@SuppressWarnings ("rawtypes")
	@Override
	protected Object privateExecuteIn(final IScope scope) throws GamaRuntimeException {
		if (when == null || Cast.asBool(scope, when.value(scope))) {
			final IAgent[] stack = scope.getAgentsStack();
			final IAgent mySelfAgent = stack[stack.length > 2 ? 1 : 0];
			IScope scopeMySelf = null;
			if (mySelfAgent != null) {
				scopeMySelf = mySelfAgent.getScope().copy("in FocusStatement");
				scopeMySelf.push(mySelfAgent);
			}
			SPredicate tempPred;
			if (variable != null) {
			
				if (variable.value(scope) instanceof IContainer) {
					String namePred;
					if (nameExpression != null) {
						namePred = (String) nameExpression.value(scope);
					} else {
						namePred = variable.getName() + "_" + scope.getAgent().getSpeciesName();
					}
					String nameVarTemp;
					final IMap<String, Object> tempValues = GamaMapFactory.create(Types.STRING, Types.NO_TYPE, 1);
					final IList<?> variablesTemp =
							((IContainer<?, ?>) variable.value(scope)).listValue(scope, null, true);
					for (int temp = 0; temp < variablesTemp.length(scope); temp++) {
						final Object temp2 = variablesTemp.get(temp);
						nameVarTemp = "test" + temp;
						tempValues.put(nameVarTemp + "_value", Cast.asInt(scope, temp2));
					}
					tempPred = new SPredicate(namePred, tempValues.copy(scope));
					if (truth != null) {
						tempPred.setSp_is_true(Cast.asBool(scope, truth.value(scope)));
					}
					if (agentGetPredicate != null) {
						tempPred.setSp_agentGetpredicate((IAgent) agentGetPredicate.value(scope));
					} else {
						tempPred.setSp_agentGetpredicate(scope.getAgent());
					}
					SMentalState tempBelief;
					if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
						if (sk_power != null) {
							tempBelief = new SMentalState("Swagueness", tempPred,
									Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Swagueness", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
						}
					} else {
						if (sk_power != null) {
							tempBelief =
									new SMentalState("Belief", tempPred, Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Belief", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
						}
					}
				} else {
					String namePred;
					if (nameExpression != null) {
						namePred = (String) nameExpression.value(scope);
					} else {
						namePred = variable.getName() + "_" + scope.getAgent().getSpeciesName();
					}
					final String nameVar = variable.getName();
					final IMap<String, Object> tempValues = GamaMapFactory.create(Types.STRING, Types.NO_TYPE, 1);
					if (expression != null) {
						tempValues.put(nameVar + "_value", expression.value(scope));
					} else {
						tempValues.put(nameVar + "_value", variable.value(scope));
					}
					tempPred = new SPredicate(namePred, tempValues);
					if (truth != null) {
						tempPred.setSp_is_true(Cast.asBool(scope, truth.value(scope)));
					}
					if (agentGetPredicate != null) {
						tempPred.setSp_agentGetpredicate((IAgent) agentGetPredicate.value(scope));
					} else {
						tempPred.setSp_agentGetpredicate(scope.getAgent());
					}
					SMentalState tempBelief;
					if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
						if (sk_power != null) {
							tempBelief = new SMentalState("Uncertainty", tempPred,
									Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Uncertainty", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scopeMySelf, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
						}
					} else {
						if (sk_power != null) {
							tempBelief =
									new SMentalState("Belief", tempPred, Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Belief", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
						}
					}
				}
			} else {
				if (belief != null) {
					SMentalState temporaryBelief = new SMentalState("Belief");
					temporaryBelief.setPredicate((SPredicate) belief.value(scope));
					if (SbdiArchitecture.hasBelief(scope, temporaryBelief)) {
						SMentalState tempBelief = null;
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							tempBelief = new SMentalState("Uncertainty");
						} else {
							tempBelief = new SMentalState("Belief");
						}
						for (final SMentalState temp : SbdiArchitecture.getBase(scope, "belief_base")) {
							if (temp.equals(temporaryBelief)) {
								temporaryBelief = temp;
							}
						}
						tempBelief.setSMentalState(temporaryBelief);
						if (sk_power != null) {
							tempBelief.setSk_power(Cast.asFloat(scope, sk_power.value(scope)));
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifetime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
							}
						} else {
							if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
							}
						}
					}
				}
				if (desire != null) {
					SMentalState temporaryBelief = new SMentalState("Desire");
					temporaryBelief.setPredicate((SPredicate) desire.value(scope));
					if (SbdiArchitecture.hasDesire(scope, temporaryBelief)) {
						SMentalState tempBelief = null;
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							tempBelief = new SMentalState("Swagueness");
						} else {
							tempBelief = new SMentalState("Belief");
						}
						for (final SMentalState temp : SbdiArchitecture.getBase(scope, "desire_base")) {
							if (temp.equals(temporaryBelief)) {
								temporaryBelief = temp;
							}
						}
						tempBelief.setSMentalState(temporaryBelief);
						if (sk_power != null) {
							tempBelief.setSk_power(Cast.asFloat(scope, sk_power.value(scope)));
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
							}
						} else {
							if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
							}
						}
					}
				}
				if (swagueness != null) {
					SMentalState temporaryBelief = new SMentalState("Uncertainty");
					temporaryBelief.setPredicate((SPredicate) swagueness.value(scope));
					if (SbdiArchitecture.hasSwagueness(scope, temporaryBelief)) {
						SMentalState tempBelief = null;
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							tempBelief = new SMentalState("Uncertainty");
						} else {
							tempBelief = new SMentalState("Belief");
						}
						for (final SMentalState temp : SbdiArchitecture.getBase(scope, "uncertainty_base")) {
							if (temp.equals(temporaryBelief)) {
								temporaryBelief = temp;
							}
						}
						tempBelief.setSMentalState(temporaryBelief);
						if (sk_power != null) {
							tempBelief.setSk_power(Cast.asFloat(scope, sk_power.value(scope)));
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
							}
						} else {
							if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
							}
						}
					}
				}
				if (ideal != null) {
					SMentalState temporaryBelief = new SMentalState("Ideal");
					temporaryBelief.setPredicate((SPredicate) ideal.value(scope));
					if (SbdiArchitecture.hasIdeal(scope, temporaryBelief)) {
						SMentalState tempBelief = null;
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							tempBelief = new SMentalState("Uncertainty");
						} else {
							tempBelief = new SMentalState("Belief");
						}
						for (final SMentalState temp : SbdiArchitecture.getBase(scope, "ideal_base")) {
							if (temp.equals(temporaryBelief)) {
								temporaryBelief = temp;
							}
						}
						tempBelief.setSMentalState(temporaryBelief);
						if (sk_power != null) {
							tempBelief.setSk_power(Cast.asFloat(scope, sk_power.value(scope)));
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
							}
						} else {
							if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
							}
						}
					}
				}
				if (spatial_knowledge != null) {
					SpatialKnowledge temporarySpatialKnowledge = (SpatialKnowledge) spatial_knowledge.value(scope);
					if (SbdiArchitecture.hasSpatialKnowledge(scope, temporarySpatialKnowledge)) {
						SMentalState tempBelief = null;
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							tempBelief = new SMentalState("Uncertainty");
						} else {
							tempBelief = new SMentalState("Belief");
						}
						for (final SpatialKnowledge temp : SbdiArchitecture.getSpatialKnowledgeBase(scope, "emotion_base")) {
							if (temp.equals(temporarySpatialKnowledge)) {
								temporarySpatialKnowledge = temp;
							}
						}
						tempBelief.setSpatialknowledge(temporarySpatialKnowledge);
						if (sk_power != null) {
							tempBelief.setSk_power(Cast.asFloat(scope, sk_power.value(scope)));
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
							if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
							}
						} else {
							if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
								SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
							}
						}
					}
				}
				if (expression != null) {
					String namePred;
					if (nameExpression != null) {
						namePred = (String) nameExpression.value(scope);
					} else {
						namePred = "expression" + "_" + scope.getAgent().getSpeciesName();
					}
					final String nameVar = "expression";
					final IMap<String, Object> tempValues = GamaMapFactory.create(Types.NO_TYPE, Types.NO_TYPE, 1);
					tempValues.put(nameVar + "_value", expression.value(scope));
					tempPred = new SPredicate(namePred, tempValues);
					if (truth != null) {
						tempPred.setSp_is_true(Cast.asBool(scope, truth.value(scope)));
					}
					if (agentGetPredicate != null) {
						tempPred.setSp_agentGetpredicate((IAgent) agentGetPredicate.value(scope));
					} else {
						tempPred.setSp_agentGetpredicate(scope.getAgent());
					}
					SMentalState tempBelief;
					if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
						if (sk_power != null) {
							tempBelief = new SMentalState("Uncertainty", tempPred,
									Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Uncertainty", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
						}
					} else {
						if (sk_power != null) {
							tempBelief =
									new SMentalState("Belief", tempPred, Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Belief", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
						}
					}
				}
				if (variable == null && belief == null && desire == null && swagueness == null && ideal == null
						&& spatial_knowledge == null && expression == null) {
					String namePred = null;
					if (nameExpression != null) {
						namePred = (String) nameExpression.value(scope);
					}
					// final Map<String, Object> tempValues = new IMap<String, Object>(1, null, null);
					tempPred = new SPredicate(namePred/* , tempValues */);
					if (truth != null) {
						tempPred.setSp_is_true(Cast.asBool(scope, truth.value(scope)));
					}
					if (agentGetPredicate != null) {
						tempPred.setSp_agentGetpredicate((IAgent) agentGetPredicate.value(scope));
					} else {
						tempPred.setSp_agentGetpredicate(scope.getAgent());
					}
					SMentalState tempBelief;
					if (isSwagueness != null && (Boolean) isSwagueness.value(scopeMySelf)) {
						if (sk_power != null) {
							tempBelief = new SMentalState("Uncertainty", tempPred,
									Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Uncertainty", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasSwagueness(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addSwagueness(scopeMySelf, tempBelief);
						}
					} else {
						if (sk_power != null) {
							tempBelief =
									new SMentalState("Belief", tempPred, Cast.asFloat(scope, sk_power.value(scope)));
						} else {
							tempBelief = new SMentalState("Belief", tempPred);
						}
						if (sk_lifetime != null) {
							tempBelief.setSk_lifeTime(Cast.asInt(scope, sk_lifetime.value(scope)));
						}
						if (!SbdiArchitecture.hasBelief(scopeMySelf, tempBelief)) {
							SbdiArchitecture.addBelief(scopeMySelf, tempBelief);
						}
					}
				}
			}
			GAMA.releaseScope(scopeMySelf);
		}
		return null;
	}
}
