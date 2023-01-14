package urifia.gaml.architecture.sbdi;

import java.util.ArrayList;
import java.util.List;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.precompiler.GamlAnnotations.action;
import msi.gama.precompiler.GamlAnnotations.arg;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.example;
import msi.gama.precompiler.GamlAnnotations.skill;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.precompiler.IConcept;
import msi.gama.runtime.GAMA;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.util.GamaListFactory;
import msi.gama.util.IList;
import msi.gaml.architecture.reflex.ReflexArchitecture;
import msi.gaml.compilation.ISymbol;
import msi.gaml.species.ISpecies;
import msi.gaml.statements.IStatement;
import msi.gaml.types.IType;
import msi.gaml.types.Types;



@vars ({ @variable (
		name = SbdiArchitecture.PERSISTENCE_COEFFICIENT_PLANS,
		type = IType.FLOAT,
		init = "1.0",
		doc = @doc ("plan persistence")),
		@variable (
				name = SbdiArchitecture.PERSISTENCE_COEFFICIENT_INTENTIONS,
				type = IType.FLOAT,
				init = "1.0",
				doc = @doc ("intention persistence")),
		@variable (
				name = SbdiArchitecture.PROBABILISTIC_CHOICE,
				type = IType.BOOL,
				init = "false",
				doc = @doc ("indicates if the choice is deterministic or probabilistic")),
		@variable (
				name = SbdiArchitecture.USE_SPATIAL_KNOWLEDGES_ARCHITECTURE,
				type = IType.BOOL,
				init = "false",
				doc = @doc ("indicates if the spatial engine is used and are automaticaly taken into account and computed")),
		@variable (
				name = SbdiArchitecture.USE_NORMS,
				type = IType.BOOL,
				init = "false",
				doc = @doc ("indicates if the normative engine is used")),
		@variable (
				name = SbdiArchitecture.BELIEF_BASE,
				type = IType.LIST,
				of = SMentalStateType.id,
				init = "[]",
				doc = @doc ("the belief base of the agent")),
		@variable (
				name = SbdiArchitecture.LAST_THOUGHTS,
				type = IType.LIST,
				init = "[]",
				doc = @doc ("the list of the last thoughts of the agent")),
		@variable (
				name = SbdiArchitecture.INTENTION_BASE,
				type = IType.LIST,
				of = SMentalStateType.id,
				init = "[]",
				doc = @doc ("the intention base of the agent")),
		@variable (
				name = SbdiArchitecture.SPATIAL_KNOWLEDGE_BASE,
				type = IType.LIST,
				of = SpatialKnowledgeType.id,
				init = "[]",
				doc = @doc ("the spatial_knowledge base of the agent")),
		@variable (
				name = SbdiArchitecture.DESIRE_BASE,
				type = IType.LIST,
				of = SMentalStateType.id,
				init = "[]",
				doc = @doc ("the desire base of the agent")),
		@variable (
				name = SbdiArchitecture.OBLIGATION_BASE,
				type = IType.LIST,
				of = SMentalStateType.id,
				init = "[]",
				doc = @doc ("the obligation base of the agent")),
		@variable (
				name = SbdiArchitecture.SWAGUENESS_BASE,
				type = IType.LIST,
				of = SMentalStateType.id,
				init = "[]",
				doc = @doc ("the uncertainty base of the agent")),
		@variable (
				name = SbdiArchitecture.IDEAL_BASE,
				type = IType.LIST,
				of = SMentalStateType.id,
				init = "[]",
				doc = @doc ("the ideal base of the agent")),
		@variable (
				name = SbdiArchitecture.LAW_BASE,
				type = IType.LIST,
				of = IType.NONE,
				init = "[]",
				doc = @doc ("the law base of the agent")),
		@variable (
				name = SbdiArchitecture.PLAN_BASE,
				type = IType.LIST,
				of = BDIPlanType.id,
				init = "[]",
				doc = @doc ("the plan base of the agent")),
		@variable (
				name = SbdiArchitecture.NORM_BASE,
				type = IType.LIST,
				of = NormType.id,
				init = "[]",
				doc = @doc ("the norm base of the agent")),
		@variable (
				name = SbdiArchitecture.CURRENT_PLAN,
				type = IType.NONE/* BDIPlanType.id */,
				doc = @doc ("thecurrent plan of the agent")),
		@variable (
				name = SbdiArchitecture.CURRENT_NORM,
				type = IType.NONE/* NormType.id */,
				doc = @doc ("the current norm of the agent")) })
@skill (
		name = SbdiArchitecture.SBDI,
		concept = { IConcept.BDI, IConcept.ARCHITECTURE })
@doc ("this architecture enables to define a behaviour using BDI. It is an implementation of the SBDI architecture (Behaviour with Spatial dimension and norms)")
@SuppressWarnings ({ "unchecked", "rawtypes" })
public class SbdiArchitecture extends ReflexArchitecture{

	public static final String SBDI = "sbdi";
	public static final String PLAN = "plan";
	public static final String PRIORITY = "priority";
	public static final String FINISHEDWHEN = "finished_when";
	public static final String PERSISTENCE_COEFFICIENT_PLANS = "plan_persistence";
	public static final String PERSISTENCE_COEFFICIENT_INTENTIONS = "intention_persistence";
	public static final String USE_SPATIAL_KNOWLEDGES_ARCHITECTURE = "use_spatial_knowledge_architecture";
	public static final String USE_NORMS = "use_norms";
	public static final String PROBABILISTIC_CHOICE = "probabilistic_choice";
	public static final String INSTANTANEAOUS = "instantaneous";

	// INFORMATION THAT CAN BE DISPLAYED
	public static final String LAST_THOUGHTS = "thinking";
	public static final Integer LAST_THOUGHTS_SIZE = 5;

	public static final String SPATIAL_KNOWLEDGE = "spatial_knowledge";
	public static final String SK_PREDICATE = "sk_predicate";
	public static final String SK_PREDICATE_NAME = "sp_name";
	public static final String SK_PREDICATE_VALUE = "value"; //or sp_rccRelationship;  
	public static final String SK_PREDICATE_PRIORITY = "priority";
	public static final String SK_PREDICATE_PARAMETERS = "parameters";
	public static final String SK_PREDICATE_ONHOLD = "on_hold_until";
	public static final String SK_PREDICATE_TODO = "todo";
	public static final String SK_PREDICATE_SUBINTENTIONS = "subintentions";
	public static final String USE_PERSONALITY = "use_personality";
	public static final String SK_PREDICATE_DATE = "date";
	public static final String BELIEF_BASE = "belief_base";
	public static final String IDEAL_BASE = "ideal_base";
	public static final String REMOVE_DESIRE_AND_INTENTION = "desire_also";
	public static final String DESIRE_BASE = "desire_base";
	public static final String OBLIGATION_BASE = "obligation_base";
	public static final String INTENTION_BASE = "intention_base"; 
	public static final String SPATIAL_KNOWLEDGE_BASE = "spatial_knowledge_base";
	//public static final String SOCIALLINK_BASE = "social_link_base";
	public static final String EVERY_VALUE = "every_possible_value";
	public static final String PLAN_BASE = "plan_base";
	public static final String NORM_BASE = "norm_base";
	public static final String CURRENT_PLAN = "current_plan";
	public static final String CURRENT_NORM = "current_norm";
	public static final String SWAGUENESS_BASE = "swagueness_base";
	public static final String LAW_BASE = "law_base";

		//
		// WARNING
		// AD: These values depend on the scope (i.e. the agent)
		// An architecture should be stateless and stock the scope dependent values
		// in the agent(s).
		protected final List<BDIPlan> _plans = new ArrayList<>();
		protected final List<PerceiveStatement> _perceptions = new ArrayList<>();
		protected final List<RuleStatement> _rules = new ArrayList<>();
		protected final List<CopingStatement> _coping = new ArrayList<>();
		//protected final List<LawStatement> _laws = new ArrayList<>();
		protected final List<Norm> _norms = new ArrayList<>();
		protected int _plansNumber = 0;
		protected int _perceptionNumber = 0;
		protected boolean iscurrentplaninstantaneous = false;
		protected int _lawsNumber = 0;
		protected int _rulesNumber = 0;
		protected int _copingNumber = 0;
		protected int _normNumber = 0;

		@Override
		protected void clearBehaviors() {
			super.clearBehaviors();
			_plans.clear();
			_rules.clear();
			_coping.clear();
			_perceptions.clear();
			//_laws.clear();
			_norms.clear();
		//	_sanctions.clear();
		}

		@Override
		public void setChildren(final Iterable<? extends ISymbol> children) {
			clearBehaviors();
			for (final ISymbol c : children) {
				addBehavior((IStatement) c);
			}
		}

		@Override
		public void addBehavior(final IStatement c) {
			if (c instanceof SbdiPlanStatement) {
				// final String statementKeyword = c.getDescription().getKeyword();
				_plans.add(new BDIPlan((SbdiPlanStatement) c));
				_plansNumber++;
			} else if (c instanceof PerceiveStatement) {
				// final String statementKeyword = c.getDescription().getKeyword();
				_perceptions.add((PerceiveStatement) c);
				_perceptionNumber++;
			} else if (c instanceof RuleStatement) {
				// final String statementKeyword = c.getDescription().getKeyword();
				_rules.add((RuleStatement) c);
				_rulesNumber++;
			} else if (c instanceof CopingStatement) {
				// final String statementKeyword = c.getDescription().getKeyword();
				_coping.add((CopingStatement) c);
				_copingNumber++;
			/*}  else if (c instanceof LawStatement) {
				// final String statementKeyword = c.getDescription().getKeyword();
				_laws.add((LawStatement) c);
				_lawsNumber++;  */
			} else if (c instanceof NormStatement) {
				// final String statementKeyword = c.getDescription().getKeyword();
				_norms.add(new Norm((NormStatement) c));
				_normNumber++;
			/*} else if (c instanceof SanctionStatement) {
				// final String statementKeyword = c.getDescription().getKeyword();
				_sanctions.add(new Sanction((SanctionStatement) c));
				_sanctionNumber++; */
			} else {
				super.addBehavior(c);
			}
		}

		@Override
		public Object executeOn(final IScope scope) throws GamaRuntimeException {
			super.executeOn(scope);
			final IAgent agent = scope.getAgent();
			if (agent.dead()) { return null; }
		/*	final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			if (use_personality) {
				final Double expressivity = (Double) scope.getAgent().getAttribute(EXTRAVERSION);
				final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
				final Double conscience = (Double) scope.getAgent().getAttribute(CONSCIENTIOUSNESS);
				final Double agreeableness = (Double) scope.getAgent().getAttribute(AGREEABLENESS);
				scope.getAgent().setAttribute(CHARISMA, expressivity);
				scope.getAgent().setAttribute(RECEPTIVITY, 1 - neurotisme);
				scope.getAgent().setAttribute(PERSISTENCE_COEFFICIENT_PLANS, Maths.sqrt(scope, conscience));
				scope.getAgent().setAttribute(PERSISTENCE_COEFFICIENT_INTENTIONS, Maths.sqrt(scope, conscience));
				scope.getAgent().setAttribute(OBEDIENCE, Maths.sqrt(scope, (conscience + agreeableness) * 0.5));
			}
			if (_sanctionNumber > 0) {
				scope.getAgent().setAttribute(SANCTION_BASE, _sanctions);
			} */
			if (_perceptionNumber > 0) {
				for (int i = 0; i < _perceptionNumber; i++) {
					_perceptions.get(i).executeOn(scope);
					if (agent.dead()) { return null; }
				}
			}
			if (_rulesNumber > 0) {
				for (int i = 0; i < _rulesNumber; i++) {
					_rules.get(i).executeOn(scope);
					if (agent.dead()) { return null; }
				}
			}
			// cleanObligation(scope);
		/*	if (_lawsNumber > 0) {
				for (int i = 0; i < _lawsNumber; i++) {
					_laws.get(i).executeOn(scope);
					if (agent.dead()) { return null; }
				}
			} 
			// computeEmotions(scope);
			updateSocialLinks(scope);
			if (_copingNumber > 0) {
				for (int i = 0; i < _copingNumber; i++) {
					_coping.get(i).executeOn(scope);
					if (agent.dead()) { return null; }
				}
			}*/
			final Object result = executePlans(scope);
			if (!scope.getAgent().dead()) {
				// Activer la violation des normes
				//updateNormViolation(scope);
				// Mettre à jour le temps de vie des normes
				updateNormLifetime(scope);

				// if (!agent.dead()) {
				// Part that manage the lifetime of predicates
				// if (result != null) {
				updateLifeTimePredicates(scope);
				updateSpatialKnowledgePower(scope);
				// }
			}
			return result;
		}

		protected final Object executePlans(final IScope scope) {
			Object result = null;
			if (_plansNumber > 0 || _normNumber > 0) {
				boolean loop_instantaneous_plans = true;
				while (loop_instantaneous_plans) {
					loop_instantaneous_plans = false;
					final IAgent agent = getCurrentAgent(scope);
					if (agent.dead()) { return null; }
					//agent.setAttribute(LAW_BASE, _laws);
					agent.setAttribute(PLAN_BASE, _plans);
					agent.setAttribute(NORM_BASE, _norms);
					//agent.setAttribute(SANCTION_BASE, _sanctions);
					//final Boolean usingPersistence = (Boolean) agent.getAttribute(USE_PERSISTENCE);
					final IList<SMentalState> intentionBase = scope.hasArg(INTENTION_BASE) ? scope.getListArg(INTENTION_BASE)
							: (IList<SMentalState>) agent.getAttribute(INTENTION_BASE);
					Double persistenceCoefficientPlans = 1.0;
					Double persistenceCoefficientintention = 1.0;
					/*if (usingPersistence) {
						persistenceCoefficientPlans = scope.hasArg(PERSISTENCE_COEFFICIENT_PLANS)
								? scope.getFloatArg(PERSISTENCE_COEFFICIENT_PLANS)
								: (Double) agent.getAttribute(PERSISTENCE_COEFFICIENT_PLANS);
						persistenceCoefficientintention = scope.hasArg(PERSISTENCE_COEFFICIENT_INTENTIONS)
								? scope.getFloatArg(PERSISTENCE_COEFFICIENT_INTENTIONS)
								: (Double) agent.getAttribute(PERSISTENCE_COEFFICIENT_INTENTIONS);

					} */
					BDIPlan _persistentTask = (BDIPlan) agent.getAttribute(CURRENT_PLAN);
					Norm _persistentNorm = (Norm) agent.getAttribute(CURRENT_NORM);
					// RANDOMLY REMOVE (last)INTENTION
					Boolean flipResultintention = msi.gaml.operators.Random.opFlip(scope, persistenceCoefficientintention);
					while (!flipResultintention && intentionBase.size() > 0) {
						flipResultintention = msi.gaml.operators.Random.opFlip(scope, persistenceCoefficientintention);
						if (intentionBase.size() > 0) {
							final int toremove = intentionBase.size() - 1;
							final SPredicate previousint = intentionBase.get(toremove).getSk_predicate();
							intentionBase.remove(toremove);
							final String think = "check what happens if I remove: " + previousint;
							addThoughts(scope, think);
							_persistentTask = null;
							agent.setAttribute(CURRENT_PLAN, _persistentTask);
							_persistentNorm = null;
							agent.setAttribute(CURRENT_NORM, _persistentNorm);
						}
					}
					// If current intention has no plan/norm or is on hold, choose a new
					// Desire/Obligation
					SMentalState intentionTemp;
					if (currentIntention(scope) != null) {
						intentionTemp = currentIntention(scope);
					} else {
						intentionTemp = new SMentalState("Intention", currentIntention(scope));
					}
					if (testOnHold(scope, intentionTemp) || currentIntention(scope) == null
							|| currentIntention(scope).getSk_predicate() == null
							|| listExecutablePlans(scope).isEmpty() && listExecutableNorms(scope).isEmpty()) {
						/*if (!selectObligationWithHighestPriority(scope)) {
							selectDesireWithHighestPriority(scope);
						}*/
						_persistentTask = null;
						agent.setAttribute(CURRENT_PLAN, _persistentTask);
						_persistentNorm = null;
						agent.setAttribute(CURRENT_NORM, _persistentNorm);
					}

					_persistentTask = (BDIPlan) agent.getAttribute(CURRENT_PLAN);
					_persistentNorm = (Norm) agent.getAttribute(CURRENT_NORM);
					// if((currentIntention(scope)!=null) && (_persistentTask!=null)
					// &&
					// !(_persistentTask._intention.value(scope).equals(currentIntention(scope)))){
					// _persistentTask = null;
					// agent.setAttribute(CURRENT_PLAN, _persistentTask);
					// }
					final Boolean flipResult = msi.gaml.operators.Random.opFlip(scope, persistenceCoefficientPlans);

					if (!flipResult) {
						if (_persistentTask != null) {
							addThoughts(scope, "check what happens if I stop: " + _persistentTask.getName());
						}
						_persistentTask = selectExecutablePlanWithHighestPriority(scope);
						agent.setAttribute(CURRENT_PLAN, _persistentTask);

						if (_persistentTask != null) {
							addThoughts(scope, "lets do instead " + _persistentTask.getName());
						}

					}
					if (currentIntention(scope) == null) {
						addThoughts(scope, "I want nothing...");
						// update the lifetime of beliefs
						// updateLifeTimePredicates(scope);
						// updateEmotionsIntensity(scope);
						return null;
					}
					// check and choose a norm to apply to the current intention
					if (_persistentNorm == null && currentIntention(scope) != null
							&& currentIntention(scope).getSk_predicate() == null) {
						/*if (!selectObligationWithHighestPriority(scope)) {
							selectDesireWithHighestPriority(scope);
						} */
						if (currentIntention(scope) != null && currentIntention(scope).getSk_predicate() == null) {
							addThoughts(scope, "I want nothing...");
							// update the lifetime of beliefs
							// updateLifeTimePredicates(scope);
							// updateEmotionsIntensity(scope);
							return null;
						}
						_persistentNorm = selectExecutableNormWithHighestPriority(scope);
						agent.setAttribute(CURRENT_NORM, _persistentNorm);
						if (currentIntention(scope) != null && _persistentTask != null) {
							addThoughts(scope, "ok, new intention: " + currentIntention(scope).getSk_predicate()
									+ " with norm " + _persistentNorm.getName());
						}
					}
					// choose a plan for the current intention
					if (_persistentNorm == null && _persistentTask == null && currentIntention(scope) != null
							&& currentIntention(scope).getSk_predicate() == null) {
						selectDesireWithHighestPriority(scope);
						if (currentIntention(scope) != null && currentIntention(scope).getSk_predicate() == null) {
							addThoughts(scope, "I want nothing...");
							// update the lifetime of beliefs
							// updateLifeTimePredicates(scope);
							// updateEmotionsIntensity(scope);
							return null;
						}
						_persistentTask = selectExecutablePlanWithHighestPriority(scope);
						agent.setAttribute(CURRENT_PLAN, _persistentTask);
						if (currentIntention(scope) != null && _persistentTask != null) {
							addThoughts(scope, "ok, new intention: " + currentIntention(scope).getSk_predicate()
									+ " with plan " + _persistentTask.getName());
						}
					}
					if (currentIntention(scope) != null && _persistentTask == null
							&& currentIntention(scope).getSk_predicate() != null) {
						_persistentNorm = selectExecutableNormWithHighestPriority(scope);
						agent.setAttribute(CURRENT_NORM, _persistentNorm);
						if (_persistentNorm == null) {
							_persistentTask = selectExecutablePlanWithHighestPriority(scope);
							agent.setAttribute(CURRENT_PLAN, _persistentTask);
						} else {
							agent.setAttribute(CURRENT_PLAN, _persistentTask);
						}
						if (_persistentNorm != null) {
							addThoughts(scope, "use norm : " + _persistentNorm.getName());
						}
						if (_persistentTask != null) {
							addThoughts(scope, "use plan : " + _persistentTask.getName());
						}
					}
					if (_persistentNorm != null) {
						if (!agent.dead()) {
							result = _persistentNorm.getNormStatement().executeOn(scope);
							boolean isExecuted = false;
							if (_persistentNorm.getNormStatement().getExecutedExpression() != null) {
								isExecuted = msi.gaml.operators.Cast.asBool(scope,
										_persistentNorm.getNormStatement().getExecutedExpression().value(scope));
							}
							if (this.iscurrentplaninstantaneous) {
								loop_instantaneous_plans = true;
							}
							if (isExecuted) {
								_persistentNorm = null;
								agent.setAttribute(CURRENT_NORM, _persistentNorm);

							}
						}
					}
					if (_persistentTask != null) {
						if (!agent.dead()) {
							result = _persistentTask.getPlanStatement().executeOn(scope);
							boolean isExecuted = false;
							if (_persistentTask.getPlanStatement().getExecutedExpression() != null) {
								isExecuted = msi.gaml.operators.Cast.asBool(scope,
										_persistentTask.getPlanStatement().getExecutedExpression().value(scope));
							}
							if (this.iscurrentplaninstantaneous) {
								loop_instantaneous_plans = true;
							}
							if (isExecuted) {
								_persistentTask = null;
								agent.setAttribute(CURRENT_PLAN, _persistentTask);

							}
						}
					}
				}
			}

			return result;
		}

		protected final Boolean selectDesireWithHighestPriority(final IScope scope) {
			// Réduire la liste des désires potentiellement intentionable en fonction des valeurs des plans
			final IAgent agent = getCurrentAgent(scope);
			final Boolean is_probabilistic_choice = scope.hasArg(PROBABILISTIC_CHOICE)
					? scope.getBoolArg(PROBABILISTIC_CHOICE) : (Boolean) agent.getAttribute(PROBABILISTIC_CHOICE);
			final List<BDIPlan> listPlans = getPlans(scope);
			final List<Norm> listNorm = getNorms(scope);

			if (is_probabilistic_choice) {
				final List<SMentalState> desireBaseTest = GamaListFactory.create();
				/* = getBase(scope, DESIRE_BASE) */;
				for (final SMentalState tempDesire : getBase(scope, DESIRE_BASE)) {
					if (listPlans != null) {
						for (final BDIPlan tempPlan : listPlans) {
							final SbdiPlanStatement tempPlanStatement = tempPlan.getPlanStatement();
							if (((SPredicate) tempPlanStatement.getIntentionExpression().value(scope))
									.equalsIntentionPlan(tempDesire.getSk_predicate())) {
								desireBaseTest.add(tempDesire);
							}
						}
					}
					for (final Norm tempNorm : listNorm) {
						final NormStatement tempPlanStatement = tempNorm.getNormStatement();
						if (((SPredicate) tempPlanStatement.getIntentionExpression().value(scope))
								.equalsIntentionPlan(tempDesire.getSk_predicate())) {
							desireBaseTest.add(tempDesire);
						}
					}
				}
				final IList<SMentalState> desireBase = getBase(scope, DESIRE_BASE);
				final IList<SMentalState> intentionBase = getBase(scope, INTENTION_BASE);
				if (desireBase.size() > 0) {
					SMentalState newIntention = desireBase.get(0)/* .anyValue(scope) */;
					double newIntStrength;
					final double priority_list[] = new double[desireBaseTest.size()/* .length(scope) */];
					for (int i = 0; i < desireBaseTest.size()/* .length(scope) */; i++) {
						priority_list[i] = desireBaseTest.get(i).getSk_power();
					}
					final IList priorities = GamaListFactory.create(scope, Types.FLOAT, priority_list);
					final int index_choice = msi.gaml.operators.Random.opRndChoice(scope, priorities);
					newIntention = desireBaseTest.get(index_choice);
					newIntStrength = desireBaseTest.get(index_choice).getSk_power();
					if (desireBaseTest.size() > intentionBase.size()) {
						while (intentionBase.contains(newIntention)) {
							final int index_choice2 = msi.gaml.operators.Random.opRndChoice(scope, priorities);
							newIntention = desireBaseTest.get(index_choice2);
							newIntStrength = desireBaseTest.get(index_choice2).getSk_power();
						}
					}
					SMentalState newIntentionState = null;
					if (newIntention.getSk_predicate() != null) {
						newIntentionState = new SMentalState("Intention", newIntention.getSk_predicate(), newIntStrength,
								newIntention.getSk_lifetime(), scope.getAgent());
					}
					if (newIntention.getSMentalState() != null) {
						newIntentionState = new SMentalState("Intention", newIntention.getSMentalState(), newIntStrength,
								newIntention.getSk_lifetime(), scope.getAgent());
					}
					if (newIntention.getSk_predicate() != null && newIntention.getSk_predicate().getSubintentions() == null) {
						if (!intentionBase.contains(newIntentionState)) {
							intentionBase.addValue(scope, newIntentionState);
							return true;
						}
					} else {
						if (newIntention.getSk_predicate() != null) {
							for (int i = 0; i < newIntention.getSk_predicate().getSubintentions().size(); i++) {
								if (!desireBase.contains(newIntention.getSk_predicate().getSubintentions().get(i))) {
									desireBase.addValue(scope, newIntention.getSk_predicate().getSubintentions().get(i));
								}
							}
							newIntention.getSk_predicate().setOnHoldUntil(newIntention.getSk_predicate().getSubintentions());
							if (!intentionBase.contains(newIntentionState)) {
								intentionBase.addValue(scope, newIntentionState);
								return true;
							}
						}
					}
				}
			} else {
				final List<SMentalState> desireBaseTest = GamaListFactory.create();
				final IList<SMentalState> desires = getBase(scope, DESIRE_BASE);
				scope.getRandom().shuffleInPlace(desires);
				for (final SMentalState tempDesire : desires) {
					if (listPlans != null) {
						for (final BDIPlan tempPlan : listPlans) {
							if (tempPlan == null) {
								continue;
							}
							final SbdiPlanStatement tempPlanStatement = tempPlan.getPlanStatement();
							if (tempPlan.getPlanStatement() == null) {
								continue;
							}
							if (tempPlan.getPlanStatement().getIntentionExpression() == null
									|| tempPlan.getPlanStatement().getIntentionExpression().value(scope) == null) {
								desireBaseTest.add(tempDesire);
								continue;
							}
							if (((SPredicate) tempPlanStatement.getIntentionExpression().value(scope))
									.equalsIntentionPlan(tempDesire.getSk_predicate())) {
								desireBaseTest.add(tempDesire);
							}
						}
					}
					for (final Norm tempNorm : listNorm) {
						final NormStatement tempPlanStatement = tempNorm.getNormStatement();
						if (tempPlanStatement.getIntentionExpression() != null
								&& ((SPredicate) tempPlanStatement.getIntentionExpression().value(scope))
										.equalsIntentionPlan(tempDesire.getSk_predicate())) {
							desireBaseTest.add(tempDesire);
						}
					}
				}
				final IList<SMentalState> desireBase = getBase(scope, DESIRE_BASE);
				final IList<SMentalState> intentionBase = getBase(scope, INTENTION_BASE);
				double maxpriority = Double.MIN_VALUE;
				if (desireBaseTest.size() > 0 && intentionBase != null) {
					SMentalState newIntention = null;// desireBase.anyValue(scope);
					for (final SMentalState desire : desireBaseTest) {

						if (desire.getSk_power() > maxpriority) {
							if (!intentionBase.contains(desire)) {
								maxpriority = desire.getSk_power();
								newIntention = desire;
							}
						}
					}
					if (newIntention != null) {
						SMentalState newIntentionState = null;
						if (newIntention.getSk_predicate() != null) {
							newIntentionState = new SMentalState("Intention", newIntention.getSk_predicate(), maxpriority,
									newIntention.getSk_lifetime(), scope.getAgent());
						}
						if (newIntention.getSMentalState() != null) {
							newIntentionState = new SMentalState("Intention", newIntention.getSMentalState(), maxpriority,
									newIntention.getSk_lifetime(), scope.getAgent());
						}
						if (newIntention.getSk_predicate() != null && newIntention.getSk_predicate().getSubintentions() == null) {
							if (!intentionBase.contains(newIntentionState)) {
								intentionBase.addValue(scope, newIntentionState);
								return true;
							}
						} else {
							if (newIntention.getSk_predicate() != null) {
								for (int i = 0; i < newIntention.getSk_predicate().getSubintentions().size(); i++) {
									if (!desireBase.contains(newIntention.getSk_predicate().getSubintentions().get(i))) {
										desireBase.addValue(scope, newIntention.getSk_predicate().getSubintentions().get(i));
									}
								}
								newIntention.getSk_predicate().setOnHoldUntil(newIntention.getSk_predicate().getSubintentions());
								if (!intentionBase.contains(newIntentionState)) {
									intentionBase.addValue(scope, newIntentionState);
									return true;
								}
							}
						}
					}
				}
			}

			return false;
		}

		// Faire la même chose pour choisir la norm à appliquer, en l'appelant avant.
		protected final BDIPlan selectExecutablePlanWithHighestPriority(final IScope scope) {
			final IAgent agent = getCurrentAgent(scope);
			final Boolean is_probabilistic_choice = scope.hasArg(PROBABILISTIC_CHOICE)
					? scope.getBoolArg(PROBABILISTIC_CHOICE) : (Boolean) agent.getAttribute(PROBABILISTIC_CHOICE);

			BDIPlan resultStatement = null;

			double highestPriority = Double.MIN_VALUE;
			final List<BDIPlan> temp_plan = new ArrayList<>();
			final IList priorities = GamaListFactory.create(Types.FLOAT);
			final List<BDIPlan> plansCopy = new ArrayList(_plans);
			scope.getRandom().shuffleInPlace(plansCopy);
			for (final Object BDIPlanstatement : plansCopy) {
				final SbdiPlanStatement statement = ((BDIPlan) BDIPlanstatement).getPlanStatement();
				final boolean isContextConditionSatisfied = statement.getContextExpression() == null
						|| msi.gaml.operators.Cast.asBool(scope, statement.getContextExpression().value(scope));
				final boolean isIntentionConditionSatisfied = statement.getIntentionExpression() == null
						|| statement.getIntentionExpression().value(scope) == null
						|| ((SPredicate) statement.getIntentionExpression().value(scope))
								.equalsIntentionPlan(currentIntention(scope).getSk_predicate());
				final boolean isSpatialKnowledgeConditionSatisfied = statement.getSpatiaKnowledgeExpression() == null
						|| getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE).contains(statement.getSpatiaKnowledgeExpression().value(scope));
				final boolean thresholdSatisfied = statement.getThreshold() == null
						|| statement.getSpatiaKnowledgeExpression() != null && SbdiArchitecture.getSpatialKnowledge(scope,
								(SpatialKnowledge) statement.getSpatiaKnowledgeExpression().value(scope)).sk_power >= (Double) statement
										.getThreshold().value(scope);
				if (isContextConditionSatisfied && isIntentionConditionSatisfied && isSpatialKnowledgeConditionSatisfied
						&& thresholdSatisfied) {
					if (is_probabilistic_choice) {
						temp_plan.add((BDIPlan) BDIPlanstatement);
					} else {
						double currentPriority = 1.0;
						if (statement.getFacet(SbdiArchitecture.PRIORITY) != null) {
							currentPriority =
									msi.gaml.operators.Cast.asFloat(scope, statement.getPriorityExpression().value(scope));
						}

						if (highestPriority < currentPriority) {
							highestPriority = currentPriority;
							resultStatement = (BDIPlan) BDIPlanstatement;
						}
					}
				}
			}
			if (is_probabilistic_choice) {
				if (!temp_plan.isEmpty()) {
					for (final Object statement : temp_plan) {
						if (((BDIPlan) statement).getPlanStatement().hasFacet(PRIORITY)) {
							priorities.add(msi.gaml.operators.Cast.asFloat(scope,
									((BDIPlan) statement).getPlanStatement().getPriorityExpression().value(scope)));
						} else {
							priorities.add(1.0);
						}
					}
					final int index_plan = msi.gaml.operators.Random.opRndChoice(scope, priorities);
					resultStatement = temp_plan.get(index_plan);
				}
			}

			iscurrentplaninstantaneous = false;
			if (resultStatement != null) {
				if (resultStatement.getPlanStatement().getFacet(SbdiArchitecture.INSTANTANEAOUS) != null) {
					iscurrentplaninstantaneous = msi.gaml.operators.Cast.asBool(scope,
							resultStatement.getPlanStatement().getInstantaneousExpression().value(scope));
				}
			}

			return resultStatement;
		}

		protected final Norm selectExecutableNormWithHighestPriority(final IScope scope) {
			// Doit sélectionner une norme sociale ou une norme obligatoire
			final IAgent agent = getCurrentAgent(scope);
			final Double obedienceValue = (Double) scope.getAgent().getAttribute("obedience");
			final Boolean is_probabilistic_choice = scope.hasArg(PROBABILISTIC_CHOICE)
					? scope.getBoolArg(PROBABILISTIC_CHOICE) : (Boolean) agent.getAttribute(PROBABILISTIC_CHOICE);

			Norm resultStatement = null;

			double highestPriority = Double.MIN_VALUE;
			final List<Norm> temp_norm = new ArrayList<>();
			final IList priorities = GamaListFactory.create(Types.FLOAT);
			for (final Norm tempNorm : getNorms(scope)) {
				tempNorm.setSanctioned(false);
				;
			}
			final List<Norm> normsCopy = new ArrayList(_norms);
			scope.getRandom().shuffleInPlace(normsCopy);
			for (final Object Normstatement : normsCopy) {
				final NormStatement statement = ((Norm) Normstatement).getNormStatement();
				final boolean isContextConditionSatisfied = statement.getContextExpression() == null
						|| msi.gaml.operators.Cast.asBool(scope, statement.getContextExpression().value(scope));
				boolean isIntentionConditionSatisfied = false;
				if (statement.getIntentionExpression() != null && statement.getIntentionExpression().value(scope) != null) {
					isIntentionConditionSatisfied = ((SPredicate) statement.getIntentionExpression().value(scope))
							.equalsIntentionPlan(currentIntention(scope).getSk_predicate());
				}
				boolean isObligationConditionSatisfied = false;
				if (statement.getObligationExpression() != null && statement.getObligationExpression().value(scope) != null
						&& hasObligation(scope, new SMentalState("Obligation",
								(SPredicate) statement.getObligationExpression().value(scope)))) {
					isObligationConditionSatisfied = ((SPredicate) statement.getObligationExpression().value(scope))
							.equalsIntentionPlan(currentIntention(scope).getSk_predicate());
				}
				final boolean thresholdSatisfied = statement.getThreshold() == null
						|| obedienceValue >= (Double) statement.getThreshold().value(scope);

				if (isContextConditionSatisfied && isObligationConditionSatisfied && thresholdSatisfied) {
					if (is_probabilistic_choice) {
						temp_norm.add((Norm) Normstatement);
					} else {
						double currentPriority = 1.0;
						if (statement.getFacet(SbdiArchitecture.PRIORITY) != null) {
							currentPriority =
									msi.gaml.operators.Cast.asFloat(scope, statement.getPriorityExpression().value(scope));
						}

						if (highestPriority < currentPriority) {
							highestPriority = currentPriority;
							resultStatement = (Norm) Normstatement;
						}
						// Norm normToChange = null;
						// for(Norm tempNorm : getNorms(scope)){
						// if(tempNorm.getNormStatement()!=null && tempNorm.getNormStatement().equals(statement)){
						// normToChange=tempNorm;
						// }
						// }
						// if(normToChange!=null){
						// normToChange.setSanctioned(false);
						// removeFromBase(scope,normToChange);
						// addToBase(scope,normToChange);
						// }
					}
				}

				if (isContextConditionSatisfied && isIntentionConditionSatisfied && thresholdSatisfied) {
					if (is_probabilistic_choice) {
						temp_norm.add((Norm) Normstatement);
					} else {
						double currentPriority = 1.0;
						if (statement.getFacet(SbdiArchitecture.PRIORITY) != null) {
							currentPriority =
									msi.gaml.operators.Cast.asFloat(scope, statement.getPriorityExpression().value(scope));
						}

						if (highestPriority < currentPriority) {
							highestPriority = currentPriority;
							resultStatement = (Norm) Normstatement;
						}
					}
				}
			}
			if (is_probabilistic_choice) {
				if (!temp_norm.isEmpty()) {
					for (final Object statement : temp_norm) {
						if (((NormStatement) statement).hasFacet(PRIORITY)) {
							priorities.add(msi.gaml.operators.Cast.asFloat(scope,
									((SbdiPlanStatement) statement).getPriorityExpression().value(scope)));
						} else {
							priorities.add(1.0);
						}
					}
					final int index_plan = msi.gaml.operators.Random.opRndChoice(scope, priorities);
					resultStatement = temp_norm.get(index_plan);
				}
			}

			iscurrentplaninstantaneous = false;
			if (resultStatement != null) {
				if (resultStatement.getNormStatement().getFacet(SbdiArchitecture.INSTANTANEAOUS) != null) {
					iscurrentplaninstantaneous = msi.gaml.operators.Cast.asBool(scope,
							resultStatement.getNormStatement().getInstantaneousExpression().value(scope));
				}
			}

			return resultStatement;
		}

		protected void updateLifeTimePredicates(final IScope scope) {
			for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
				mental.sk_isUpdated = false;
			}
			for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
				mental.sk_isUpdated = false;
			}
			for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
				mental.sk_isUpdated = false;
			}
			for (final SMentalState mental : getBase(scope, SWAGUENESS_BASE)) {
				mental.sk_isUpdated = false;
			}
			for (final SMentalState mental : getBase(scope, OBLIGATION_BASE)) {
				mental.sk_isUpdated = false;
			}
			for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
				mental.updateSk_lifetime();
			}
			for (final SMentalState mental : listBeliefsLifeTimeNull(scope)) {
				removeBelief(scope, mental);
			}
			for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
				mental.updateSk_lifetime();
			}
			for (final SMentalState mental : listDesiresLifeTimeNull(scope)) {
				removeDesire(scope, mental);
			}
			for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
				mental.updateSk_lifetime();
			}
			for (final SMentalState mental : listIntentionsLifeTimeNull(scope)) {
				removeIntention(scope, mental);
			}
			for (final SMentalState mental : getBase(scope, SWAGUENESS_BASE)) {
				mental.updateSk_lifetime();
			}
			for (final SMentalState mental : listUncertaintyLifeTimeNull(scope)) {
				removeSwagueness(scope, mental);
			}
			for (final SMentalState mental : getBase(scope, OBLIGATION_BASE)) {
				mental.updateSk_lifetime();
			}
			for (final SMentalState mental : listObligationLifeTimeNull(scope)) {
				removeObligation(scope, mental);
			}
		}

		private List<SMentalState> listBeliefsLifeTimeNull(final IScope scope) {
			final List<SMentalState> tempPred = new ArrayList<>();
			for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
				if (mental.getSk_lifetime() == 0) {
					tempPred.add(mental);
				}
			}
			return tempPred;
		}

		private List<SMentalState> listDesiresLifeTimeNull(final IScope scope) {
			final List<SMentalState> tempPred = new ArrayList<>();
			for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
				if (mental.getSk_lifetime() == 0) {
					tempPred.add(mental);
				}
			}
			return tempPred;
		}

		private List<SMentalState> listIntentionsLifeTimeNull(final IScope scope) {
			final List<SMentalState> tempPred = new ArrayList<>();
			for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
				if (mental.getSk_lifetime() == 0) {
					tempPred.add(mental);
				}
			}
			return tempPred;
		}

		private List<SMentalState> listUncertaintyLifeTimeNull(final IScope scope) {
			final List<SMentalState> tempPred = new ArrayList<>();
			for (final SMentalState mental : getBase(scope, SWAGUENESS_BASE)) {
				if (mental.getSk_lifetime() == 0) {
					tempPred.add(mental);
				}
			}
			return tempPred;
		}

		private List<SMentalState> listObligationLifeTimeNull(final IScope scope) {
			final List<SMentalState> tempPred = new ArrayList<>();
			for (final SMentalState mental : getBase(scope, OBLIGATION_BASE)) {
				if (mental.getSk_lifetime() == 0) {
					tempPred.add(mental);
				}
			}
			return tempPred;
		}

		protected final List<SbdiPlanStatement> listExecutablePlans(final IScope scope) {
			final List<SbdiPlanStatement> plans = new ArrayList<>();
			final List<BDIPlan> plansCopy = new ArrayList(_plans);
			scope.getRandom().shuffleInPlace(plansCopy);
			for (final BDIPlan BDIPlanstatement : plansCopy) {
				final SbdiPlanStatement statement = BDIPlanstatement.getPlanStatement();

				if (statement.getContextExpression() != null
						&& !msi.gaml.operators.Cast.asBool(scope, statement.getContextExpression().value(scope))) {
					continue;
				}
				if (currentIntention(scope) != null) {
					if (statement.getIntentionExpression() == null
							|| (SPredicate) statement.getIntentionExpression().value(scope) == null
							|| ((SPredicate) statement.getIntentionExpression().value(scope))
									.equalsIntentionPlan(currentIntention(scope).getSk_predicate())) {
						plans.add(statement);
					}
				}
				// }
			}
			return plans;
		}

		protected final List<NormStatement> listExecutableNorms(final IScope scope) {
			final List<NormStatement> norms = new ArrayList<>();
			final List<Norm> normsCopy = new ArrayList(_norms);
			scope.getRandom().shuffleInPlace(normsCopy);
			for (final Norm Normstatement : normsCopy) {
				final NormStatement statement = Normstatement.getNormStatement();

				if (statement.getContextExpression() != null
						&& !msi.gaml.operators.Cast.asBool(scope, statement.getContextExpression().value(scope))) {
					continue;
				}
				if (currentIntention(scope) != null) {
					if (statement.getIntentionExpression() != null
							&& (SPredicate) statement.getIntentionExpression().value(scope) != null
							&& ((SPredicate) statement.getIntentionExpression().value(scope))
									.equalsIntentionPlan(currentIntention(scope).getSk_predicate())) {
						norms.add(statement);
					}
					if (statement.getObligationExpression() != null
							&& (SPredicate) statement.getObligationExpression().value(scope) != null
							&& ((SPredicate) statement.getObligationExpression().value(scope))
									.equalsIntentionPlan(currentIntention(scope).getSk_predicate())) {
						norms.add(statement);
					}
				}
			}

			return norms;
		}

		// private void cleanObligation(final IScope scope) {
		// final List<SMentalState> tempPred = new ArrayList<>();
		// for (final SMentalState mental : getBase(scope, OBLIGATION_BASE)) {
		// if (mental.getLifeTime() <= 0) {
		// tempPred.add(mental);
		// }
		// }
		// for (final SMentalState mental : tempPred) {
		// removeObligation(scope, mental);
		// }
		// }

		public IList<String> getThoughts(final IScope scope) {
			final IAgent agent = getCurrentAgent(scope);
			final IList<String> thoughts = (IList<String>) agent.getAttribute(LAST_THOUGHTS);
			return thoughts;
		}

		public IList<String> addThoughts(final IScope scope, final String think) {
			final IAgent agent = getCurrentAgent(scope);
			final IList<String> thoughts = (IList<String>) agent.getAttribute(LAST_THOUGHTS);
			final IList newthoughts = GamaListFactory.create(Types.STRING);
			newthoughts.add(think);
			if (thoughts != null && thoughts.size() > 0) {
				newthoughts.addAll(thoughts.subList(0, Math.min(LAST_THOUGHTS_SIZE - 1, thoughts.size())));
			}
			agent.setAttribute(LAST_THOUGHTS, newthoughts);
			return newthoughts;
		}

		public boolean testOnHold(final IScope scope, final SMentalState intention) {
			if (intention == null) { return false; }
			if (intention.getSk_predicate() == null) { return false; }
			if (intention.onHoldUntil == null) { return false; }
			if (intention.getSk_predicate().getValues() != null) {
				if (intention.getSk_predicate().getValues().containsKey("and")) {
					final Object cond = intention.getSk_predicate().onHoldUntil;
					if (cond instanceof ArrayList) {
						if (((ArrayList) cond).size() == 0) {
							final IList desbase = getBase(scope, DESIRE_BASE);
							final IList intentionbase = getBase(scope, INTENTION_BASE);
							desbase.remove(intention);
							intentionbase.remove(intention);
							for (final Object statement : getBase(scope, SbdiArchitecture.INTENTION_BASE)) {
								final List<SMentalState> statementSubintention =
										((SMentalState) statement).getSk_predicate().getSubintentions();
								if (statementSubintention != null) {
									if (statementSubintention.contains(intention)) {
										statementSubintention.remove(intention);
									}
								}
								final List<SMentalState> statementOnHoldUntil =
										((SMentalState) statement).getSk_predicate().getOnHoldUntil();
								if (statementOnHoldUntil != null) {
									if (statementOnHoldUntil.contains(intention)) {
										statementOnHoldUntil.remove(intention);
									}
								}
							}
							return false;
						} else {
							return true;
						}
					}
				}
				if (intention.getSk_predicate().getValues().containsKey("or")) {
					final Object cond = intention.getSk_predicate().onHoldUntil;
					if (cond instanceof ArrayList) {
						if (((ArrayList) cond).size() <= 1) {
							final IList desbase = getBase(scope, DESIRE_BASE);
							final IList intentionbase = getBase(scope, INTENTION_BASE);
							desbase.remove(intention);
							intentionbase.remove(intention);
							if (((ArrayList) cond).size() == 1) {
								if (desbase.contains(((ArrayList) cond).get(0))) {
									desbase.remove(((ArrayList) cond).get(0));
								}
								for (final Object statement : getBase(scope, SbdiArchitecture.INTENTION_BASE)) {
									final List<SMentalState> statementSubintention =
											((SMentalState) statement).getSk_predicate().getSubintentions();
									if (statementSubintention != null) {
										if (statementSubintention.contains(intention)) {
											statementSubintention.remove(intention);
										}
									}
									final List<SMentalState> statementOnHoldUntil =
											((SMentalState) statement).getSk_predicate().getOnHoldUntil();
									if (statementOnHoldUntil != null) {
										if (statementOnHoldUntil.contains(intention)) {
											statementOnHoldUntil.remove(intention);
										}
									}
								}
							}
							return false;
						} else {
							return true;
						}
					}
				}
			}
			final Object cond = intention.onHoldUntil;
			if (cond instanceof ArrayList) {
				final IList desbase = getBase(scope, DESIRE_BASE);
				if (desbase.isEmpty()) { return false; }
				for (final Object subintention : (ArrayList) cond) {
					if (desbase.contains(subintention)) { return true; }
				}
				addThoughts(scope, "no more subintention for" + intention);
				/* Must remove the current plan to change for a new one */
				final IAgent agent = getCurrentAgent(scope);
				BDIPlan _persistentTask = (BDIPlan) agent.getAttribute(CURRENT_PLAN);
				_persistentTask = null;
				agent.setAttribute(CURRENT_PLAN, _persistentTask);
				return false;

			}
			// if (cond instanceof String) {
			// final Object res = msi.gaml.operators.System.opEvalGaml(scope,
			// (String) cond);
			// if (Cast.asBool(scope, res) == false) {
			// return true;
			// }
			//
			// }
			return false;

		}

		@action (
				name = "get_plans",
				doc = @doc (
						value = "get the list of plans.",
						returns = "the list of BDI plans.",
						examples = { @example ("get_plans()") }))
		public List<BDIPlan> getPlans(final IScope scope) {
			if (_plans.size() > 0) { return _plans; }
			return null;
		}

		// faire des actions get_plan("name") et is_current_plan("name")
		@action (
				name = "get_plan",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("the name of the planto get")) },
				doc = @doc (
						value = "get the first plan with the given name",
						returns = "a BDIPlan",
						examples = { @example ("get_plan(name)") }))
		public BDIPlan getPlan(final IScope scope) {
			final String namePlan = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			for (final BDIPlan tempPlan : _plans) {
				if (tempPlan.getPlanStatement().getName().equals(namePlan)) { return tempPlan; }
			}
			return null;
		}

		@action (
				name = "is_current_plan",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("the name of the plan to test")) },
				doc = @doc (
						value = "tell if the current plan has the same name as tested",
						returns = "true if the current plan has the same name",
						examples = { @example ("is_current_plan(name)") }))
		public Boolean isCurrentPlan(final IScope scope) {
			final String namePlan = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			for (final BDIPlan tempPlan : _plans) {
				if (tempPlan.getPlanStatement().getName().equals(namePlan)) { return true; }
			}
			return false;
		}

		@action (
				name = "get_current_plan",
				doc = @doc (
						value = "get the current plan.",
						returns = "the current plans.",
						examples = { @example ("get_current_plan()") }))
		public BDIPlan getCurrentPlans(final IScope scope) {
			final IAgent agent = getCurrentAgent(scope);
			final BDIPlan result = (BDIPlan) agent.getAttribute(CURRENT_PLAN);
			return result;
		}

		/*public static List<LawStatement> getLaws(final IScope scope) {
			final IAgent agent = scope.getAgent();
			return scope.hasArg(LAW_BASE) ? scope.getListArg(LAW_BASE) : (List<LawStatement>) agent.getAttribute(LAW_BASE);
		} */

		public static List<Norm> getNorms(final IScope scope) {
			final IAgent agent = scope.getAgent();
			return scope.hasArg(NORM_BASE) ? scope.getListArg(NORM_BASE) : (List<Norm>) agent.getAttribute(NORM_BASE);
		}

		/*public static List<Sanction> getSanctions(final IScope scope) {
			final IAgent agent = scope.getAgent();
			return scope.hasArg(SANCTION_BASE) ? scope.getListArg(SANCTION_BASE)
					: (List<Sanction>) agent.getAttribute(SANCTION_BASE);
		}  */

		public static IList<SMentalState> getBase(final IScope scope, final String basename) {
			final IAgent agent = scope.getAgent();
			return scope.hasArg(basename) ? scope.getListArg(basename) : (IList<SMentalState>) agent.getAttribute(basename);
		}

		public static IList<SpatialKnowledge> getSpatialKnowledgeBase(final IScope scope, final String basename) {
			final IAgent agent = scope.getAgent();
			return scope.hasArg(basename) ? scope.getListArg(basename) : (IList<SpatialKnowledge>) agent.getAttribute(basename);
		}
		

		/*public static IList<SocialLink> getSocialBase(final IScope scope, final String basename) {
			final IAgent agent = scope.getAgent();
			return scope.hasArg(basename) ? scope.getListArg(basename) : (IList<SocialLink>) agent.getAttribute(basename);
		}*/

		public static boolean removeFromBase(final IScope scope, final SMentalState predicateItem,
				final String factBaseName) {
			final IList<SMentalState> factBase = getBase(scope, factBaseName);
			return factBase.remove(predicateItem);
		}

		public static boolean removeFromBase(final IScope scope, final SpatialKnowledge skItem, final String factBaseName) {
			final IList<SpatialKnowledge> factBase = getSpatialKnowledgeBase(scope, factBaseName);
			return factBase.remove(skItem);
		}

		/*public static boolean removeFromBase(final IScope scope, final SocialLink socialItem, final String factBaseName) {
			final IList<SocialLink> factBase = getSocialBase(scope, factBaseName);
			return factBase.remove(socialItem);
		}*/

		public static boolean removeFromBase(final IScope scope, final Norm normItem) {
			final List<Norm> factBase = getNorms(scope);
			return factBase.remove(normItem);
		}

		public static boolean addToBase(final IScope scope, final SMentalState mentalItem, final String factBaseName) {
			return addToBase(scope, mentalItem, getBase(scope, factBaseName));
		}


		
		
		public static boolean addToBase(final IScope scope, final SpatialKnowledge skItem, final String factBaseName) {
			return addToBase(scope, skItem, getSpatialKnowledgeBase(scope, factBaseName));
		}

		/*public static boolean addToBase(final IScope scope, final SocialLink socialItem, final String factBaseName) {
			return addToBase(scope, socialItem, getSocialBase(scope, factBaseName));
		}*/

		public static boolean addToBase(final IScope scope, final Norm normItem) {
			final List<Norm> factBase = getNorms(scope);
			return factBase.add(normItem);
		}

		public static boolean addToBase(final IScope scope, final SMentalState mentalItem,
				final IList<SMentalState> factBase) {
			if (!factBase.contains(mentalItem)) {
				// factBase.remove(mentalItem);

				// mentalItem.setDate(scope.getClock().getTimeElapsedInSeconds());
				return factBase.add(mentalItem);
			}
			return false;
		}

		public static boolean addToBase(final IScope scope, final SpatialKnowledge predicateItem, final IList<SpatialKnowledge> factBase) {
			factBase.remove(predicateItem);
			// if(!factBase.contains(predicateItem)){
			return factBase.add(predicateItem);
			// }
			// return false;
		}

	/*	public static boolean addToBase(final IScope scope, final SocialLink socialItem, final IList<SocialLink> factBase) {
			factBase.remove(socialItem);
			// if(!factBase.contains(socialItem)){
			return factBase.add(socialItem);
			// }
			// return false;
		}   */

		// le add belief crée les émotion joie, sadness, satisfaction, disapointment, relief, fear_confirmed, pride, shame,
		// admiration, reproach
		public static Boolean addBelief(final IScope scope, final SMentalState predicateDirect) {
			final Boolean use_spatial_knowledge_architecture =
					scope.hasArg(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE) ? scope.getBoolArg(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE)
							: (Boolean) scope.getAgent().getAttribute(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE);
			SMentalState predTemp = null;
			if (predicateDirect != null) {
			/*	if (use_spatial_knowledge_architecture) {
					createJoyFromPredicate(scope, predicateDirect);
					createSatisfactionFromMentalState(scope, predicateDirect); // satisfaction, disapointment, relief,
																				// fear_confirmed
					createPrideFromMentalState(scope, predicateDirect); // pride, shame, admiration, reproach
					createHappyForFromMentalState(scope, predicateDirect); // (seulement si le prédicat est sur une
																			// émotion).
				} */
				for (final SMentalState predTest : getBase(scope, SbdiArchitecture.BELIEF_BASE)) {
					if (predTest.getSk_predicate() != null && predicateDirect.getSk_predicate() != null
							&& predTest.getSk_predicate().equalsButNotTruth(predicateDirect.getSk_predicate())) {
						predTemp = predTest;
					}
				}
				if (predTemp != null) {
					removeFromBase(scope, predTemp, BELIEF_BASE);
				}
				if (getBase(scope, SbdiArchitecture.INTENTION_BASE).contains(predicateDirect)) {
					removeFromBase(scope, predicateDirect, DESIRE_BASE);
					removeFromBase(scope, predicateDirect, INTENTION_BASE);
					scope.getAgent().setAttribute(CURRENT_PLAN, null);
					scope.getAgent().setAttribute(CURRENT_NORM, null);
				}
				if (getBase(scope, SbdiArchitecture.SWAGUENESS_BASE).contains(predicateDirect)) {
					removeFromBase(scope, predicateDirect, SWAGUENESS_BASE);
				}
				if (getBase(scope, SbdiArchitecture.OBLIGATION_BASE).contains(predicateDirect)) {
					removeFromBase(scope, predicateDirect, OBLIGATION_BASE);
				}
				for (final SMentalState predTest : getBase(scope, SbdiArchitecture.SWAGUENESS_BASE)) {
					if (predTest.getSk_predicate() != null && predicateDirect.getSk_predicate() != null
							&& predTest.getSk_predicate().equalsButNotTruth(predicateDirect.getSk_predicate())) {
						predTemp = predTest;
					}
				}
				if (predTemp != null) {
					removeFromBase(scope, predTemp, SWAGUENESS_BASE);
				}
				for (final Object statement : getBase(scope, SbdiArchitecture.INTENTION_BASE)) {
					List<SMentalState> statementSubintention = null;
					if (((SMentalState) statement).getSk_predicate() != null) {
						statementSubintention = ((SMentalState) statement).getSubintentions();
					}
					if (statementSubintention != null) {
						if (statementSubintention.contains(predicateDirect)) {
							statementSubintention.remove(predicateDirect);
						}
					}
					List<SMentalState> statementOnHoldUntil = null;
					if (((SMentalState) statement).getSk_predicate() != null) {
						statementOnHoldUntil = ((SMentalState) statement).getOnHoldUntil();
					}
					if (statementOnHoldUntil != null) {
						if (statementOnHoldUntil.contains(predicateDirect)) {
							statementOnHoldUntil.remove(predicateDirect);
						}
					}
				}
				predicateDirect.setSk_owner(scope.getAgent());
				return addToBase(scope, predicateDirect, BELIEF_BASE);
			}

			return false;
		}

		@action (
				name = "add_belief",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to add as a belief")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the stregth of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "add the predicate in the belief base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddBelief(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState tempState;
			if (predicateDirect != null) {
				tempState = new SMentalState("Belief", predicateDirect);
			} else {
				tempState = new SMentalState("Belief");
			}
			if (po != null) {
				tempState.setSk_power(po);
				if (life > 0) {
					tempState.setSk_lifeTime(life);
				}
			} else {
				if (life > 0) {
					tempState.setSk_lifeTime(life);
				}
			}
			tempState.setSk_owner(scope.getAgent());
			return addBelief(scope, tempState);

		}

		@action (
				name = "add_directly_belief",
				args = { @arg (
						name = "belief",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("belief to add in th belief base")) },
				doc = @doc (
						value = "add the belief in the belief base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddDirectlyBelief(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("belief") ? scope.getArg("belief", SMentalStateType.id) : null);
			if (predicateDirect != null && predicateDirect.getModality().equals("Belief")) {
				predicateDirect.setSk_owner(scope.getAgent());
				return addBelief(scope, predicateDirect);
			}
			return false;

		}

		@action (
				name = "add_belief_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("predicate to add as a belief")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the stregth of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "add the predicate in the belief base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddBeliefMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState stateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState tempState;
			if (stateDirect != null) {
				tempState = new SMentalState("Belief", stateDirect);
			} else {
				tempState = new SMentalState("Belief");
			}
			if (po != null) {
				tempState.setSk_power(po);
				if (life > 0) {
					tempState.setSk_lifeTime(life);
				}
			} else {
				if (life > 0) {
					tempState.setSk_lifeTime(life);
				}
			}
			tempState.setSk_owner(scope.getAgent());
			return addBelief(scope, tempState);

		}

		// va déclencher les émotions happy_for, sorry_for, resentment et gloating
		@action (
				name = "add_belief_spatial_knowledge",
				args = { @arg (
						name = "emotion",
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("emotion to add as a belief")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the stregth of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "add the belief about an spatial_knowledge in the belief base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddBeliefSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge stateDirect =
					(SpatialKnowledge) (scope.hasArg("spatial_knowledge") ? scope.getArg("spatial_knowledge", SpatialKnowledgeType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState tempState;
			if (stateDirect != null) {
				tempState = new SMentalState("Belief", stateDirect);
			} else {
				tempState = new SMentalState("Belief");
			}
			if (po != null) {
				tempState.setSk_power(po);
				if (life > 0) {
					tempState.setSk_lifeTime(life);
				}
			} else {
				if (life > 0) {
					tempState.setSk_lifeTime(life);
				}
			}
			tempState.setSk_owner(scope.getAgent());
			return addBelief(scope, tempState);

		}

		public static Boolean hasBelief(final IScope scope, final SMentalState predicateDirect) {
			return getBase(scope, BELIEF_BASE).contains(predicateDirect);

		}

		public static Boolean hasDesire(final IScope scope, final SMentalState predicateDirect) {
			return getBase(scope, DESIRE_BASE).contains(predicateDirect);
		}

		@action (
				name = "has_belief",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "check if the predicates is in the belief base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestBelief(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState tempState = new SMentalState("Belief", predicateDirect);
			if (predicateDirect != null) { return hasBelief(scope, tempState); }
			return false;
		}

		@action (
				name = "has_belief_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicate to check")) },
				doc = @doc (
						value = "check if the predicate is in the belief base.",
						returns = "true if it is in the base.",
						examples = { @example ("has_belief_with_name(\"has_water\")") }))
		public Boolean hasBeliefName(final IScope scope) throws GamaRuntimeException { 
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				final SMentalState tempState = new SMentalState("Belief", new SPredicate(predicateName));
				return hasBelief(scope, tempState);
			}
			return null;
		}

		@action (
				name = "has_belief_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "check if the mental state is in the belief base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestBeliefMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final SMentalState tempState = new SMentalState("Belief", predicateDirect);
			if (predicateDirect != null) { return hasBelief(scope, tempState); }
			return false;
		}

		@action (
				name = "get_belief",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to get")) },
				doc = @doc (
						value = "return the belief about the predicate in the belief base (if several, returns the first one).",
						returns = "the belief about the predicate if it is in the base.",
						examples = { @example ("get_belief(new_predicate(\"has_water\", true))") }))
		public SMentalState getBelief(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
					if (mental.getSk_predicate() != null) {
						if (predicateDirect.equals(mental.getSk_predicate())) { return mental; }
						if (predicateDirect.equalsButNotTruth(mental.getSk_predicate())) { return mental; }
					}
				}

			}
			return null;

		}

		@action (
				name = "get_belief_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to get")) },
				doc = @doc (
						value = "return the belief about the mental state in the belief base (if several, returns the first one).",
						returns = "the belief about the mental state if it is in the base.",
						examples = { @example ("get_belief(new_mental_state(\"Desire\", predicate1))") }))
		public SMentalState getBeliefMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
					if (mental.getSMentalState() != null) {
						if (predicateDirect.equals(mental.getSMentalState())) { return mental; }
					}
				}

			}
			return null;

		}

		@action (
				name = "get_belief_spatial_knowledge",
				args = { @arg (
						name = "spatial_knowledge",
						type = SpatialKnowledgeType.id,
						optional = false,
						doc = @doc ("spatial_knowledge about which the belief to get is")) },
				doc = @doc (
						value = "return the belief about the spatial_knowledge in the belief base (if several, returns the first one).",
						returns = "the belief about the spatial_knowledge if it is in the base.",
						examples = { @example ("get_belief(new_mental_state(\"Desire\", skpredicate1))") }))
		public SMentalState getBeliefSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge predicateDirect =
					(SpatialKnowledge) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
					if (mental.getSMentalState() != null) {
						if (predicateDirect.equals(mental.getSknow())) { return mental; }
					}
				}

			}
			return null;

		}

		@action (
				name = "get_belief_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicate to check")) },
				doc = @doc (
						value = "get the predicates is in the belief base (if several, returns the first one).",
						returns = "the predicate if it is in the base.",
						examples = { @example ("get_belief_with_name(\"has_water\")") }))
		public SMentalState getBeliefName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
					if (mental.getSk_predicate() != null && predicateName.equals(mental.getSk_predicate().getSp_name())) {
						return mental;
					}
				}
			}
			return null;
		}

		@action (
				name = "get_beliefs_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicates to check")) },
				doc = @doc (
						value = "get the list of predicates is in the belief base with the given name.",
						returns = "the list of beliefs (mental state).",
						examples = { @example ("get_belief(\"has_water\")") }))
		public IList<SMentalState> getBeliefsName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			final IList<SMentalState> predicates = GamaListFactory.create();
			if (predicateName != null) {
				for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
					if (mental.getSk_predicate() != null && predicateName.equals(mental.getSk_predicate().getSp_name())) {
						predicates.add(mental);
					}
				}
			}
			return predicates;
		}

		@action (
				name = "get_beliefs",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "get the list of predicates in the belief base",
						returns = "the list of beliefs (mental state).",
						examples = { @example ("get_beliefs(\"has_water\")") }))
		public IList<SMentalState> getBeliefs(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final IList<SMentalState> predicates = GamaListFactory.create();
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
					if (mental.getSk_predicate() != null) {
						if (predicateDirect.equals(mental.getSk_predicate())) {
							predicates.add(mental);
						}
						if (predicateDirect.equalsButNotTruth(mental.getSk_predicate())) {
							predicates.add(mental);
						}
					}
				}
			}
			return predicates;
		}

		@action (
				name = "get_beliefs_metal_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "get the list of bliefs in the belief base containing the mental state",
						returns = "the list of beliefs (mental state).",
						examples = { @example ("get_beliefs_mental_state(\"has_water\")") }))
		public IList<SMentalState> getBeliefsMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final IList<SMentalState> predicates = GamaListFactory.create();
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, BELIEF_BASE)) {
					if (mental.getSMentalState() != null) {
						if (predicateDirect.equals(mental.getSMentalState())) {
							predicates.add(mental);
						}
					}
				}
			}
			return predicates;
		}

		@action (
				name = "is_current_intention",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "check if the predicates is the current intention (last entry of intention base).",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean iscurrentIntention(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SPredicate currentIntention;
			if (currentIntention(scope) != null) {
				currentIntention = currentIntention(scope).getSk_predicate();
			} else {
				currentIntention = null;
			}

			if (predicateDirect != null && currentIntention != null) { return predicateDirect.equals(currentIntention); }

			return false;
		}

		@action (
				name = "is_current_intention_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "check if the mental state is the current intention (last entry of intention base).",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean iscurrentIntentionMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final SMentalState currentIntention = currentIntention(scope).getSMentalState();

			if (predicateDirect != null && currentIntention != null) { return predicateDirect.equals(currentIntention); }

			return false;
		}

		@action (
				name = "get_current_intention",
				doc = @doc (
						value = "returns the current intention (last entry of intention base).",
						returns = "the current intention",
						examples = { @example ("") }))
		public SMentalState currentIntention(final IScope scope) throws GamaRuntimeException {
			final IList<SMentalState> intentionBase = getBase(scope, INTENTION_BASE);
			if (intentionBase == null) { return null; }
			if (!intentionBase.isEmpty()) { return intentionBase.lastValue(scope); }
			return null;
		}

		@action (
				name = "has_desire",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "check if the predicates is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestDesire(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				final SMentalState temp = new SMentalState("Desire", predicateDirect);
				return getBase(scope, DESIRE_BASE).contains(temp);
			}
			return false;
		}

		@action (
				name = "has_desire_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicate to check")) },
				doc = @doc (
						value = "check if the prediate is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("has_desire_with_name(\"has_water\")") }))
		public Boolean hasDesireName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				final SMentalState tempState = new SMentalState("Desire", new SPredicate(predicateName));
				return hasDesire(scope, tempState);
			}
			return null;
		}

		@action (
				name = "has_desire_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "check if the mental state is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestDesireMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				final SMentalState temp = new SMentalState("Desire", predicateDirect);
				return getBase(scope, DESIRE_BASE).contains(temp);
			}
			return false;
		}

		@action (
				name = "current_intention_on_hold",
				args = { @arg (
						name = "until",
						type = IType.NONE,
						optional = true,
						doc = @doc ("the current intention is put on hold (fited plan are not considered) until specific condition is reached. Can be an expression (which will be tested), a list (of subintentions), or nil (by default the condition will be the current list of subintentions of the intention)")) },

				doc = @doc (
						value = "puts the current intention on hold until the specified condition is reached or all subintentions are reached (not in desire base anymore).",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primOnHoldIntention(final IScope scope) throws GamaRuntimeException {
			SMentalState predicate = null;
			if (currentIntention(scope) != null) {
				predicate = currentIntention(scope);
			}
			final Object until = scope.hasArg("until") ? scope.getArg("until", IType.NONE) : null;
			if (predicate != null) {
				if (until == null) {
					final List<SMentalState> subintention = predicate.subintentions;
					if (subintention != null && !subintention.isEmpty()) {
						predicate.onHoldUntil = subintention;
					}
				} else {
					if (predicate.onHoldUntil == null) {
						predicate.onHoldUntil = GamaListFactory.create(Types.get(SMentalStateType.id));
					}
					if (predicate.getSubintentions() == null) {
						predicate.subintentions = GamaListFactory.create(Types.get(SMentalStateType.id));
					}
					final SMentalState tempState = new SMentalState("Intention", predicate.getSk_predicate());
					final SMentalState tempUntil = new SMentalState("Desire", (SPredicate) until);
					tempUntil.setSuperIntention(tempState);
					predicate.onHoldUntil.add(tempUntil);
					predicate.getSubintentions().add(tempUntil);
					addToBase(scope, tempUntil, DESIRE_BASE);
				}
			}
			return true;
		}

		@action (
				name = "add_subintention",
				args = { @arg (
						name = SK_PREDICATE,
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("the intention that receives the sub_intention")),
						@arg (
								name = SK_PREDICATE_SUBINTENTIONS,
								type = SPredicateType.id,
								optional = false,
								doc = @doc ("the predicate to add as a subintention to the intention")),
						@arg (
								name = "add_as_desire",
								type = IType.BOOL,
								optional = true,
								doc = @doc ("add the subintention as a desire as well (by default, false) ")) },
				doc = @doc (
						value = "adds the predicates is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean addSubIntention(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicate =
					(SMentalState) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SMentalStateType.id) : null);
			final SPredicate subpredicate = (SPredicate) (scope.hasArg(SK_PREDICATE_SUBINTENTIONS)
					? scope.getArg(SK_PREDICATE_SUBINTENTIONS, SPredicateType.id) : null);

			if (predicate == null || subpredicate == null) { return false; }
			final Boolean addAsDesire =
					(Boolean) (scope.hasArg("add_as_desire") ? scope.getArg("add_as_desire", IType.BOOL) : false);
			SMentalState superState = null;
			for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
				if (mental != null && predicate.equals(mental)) {
					superState = mental;
					break;
				}
			}
			if (superState == null) { return false; }

			if (predicate.getSubintentions() == null) {
				predicate.subintentions = GamaListFactory.create(Types.get(SMentalStateType.id));
			}
			final SMentalState subState = new SMentalState("Desire", subpredicate);
			subpredicate.setSuperIntention(superState);
			predicate.getSubintentions().add(subState);
			subState.sk_owner = superState.sk_owner;
			if (addAsDesire) {
				addToBase(scope, subState, DESIRE_BASE);
			}
			return true;
		}

		public static Boolean addDesire(final IScope scope, final SMentalState superPredicate, final SMentalState predicate) {
			if (superPredicate != null && superPredicate.getSk_predicate() != null) {
				if (superPredicate.getSk_predicate().getSubintentions() == null) {
					superPredicate.getSk_predicate().subintentions = GamaListFactory.create(Types.get(SPredicateType.id));
				}
				if (predicate.getSk_predicate() != null) {
					predicate.getSk_predicate().setSuperIntention(superPredicate);
				}
				superPredicate.getSk_predicate().getSubintentions().add(predicate);
			}
			predicate.setSk_owner(scope.getAgent());
			addToBase(scope, predicate, DESIRE_BASE);

			return false;
		}

		@action (
				name = "add_desire",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to add as a desire")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the stregth of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")),
						@arg (
								name = SK_PREDICATE_TODO,
								type = SPredicateType.id,
								optional = true,
								doc = @doc ("add the desire as a subintention of this parameter")), },
				doc = @doc (
						value = "adds the predicates is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primAddDesire(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			if (predicateDirect != null) {
				final SPredicate superpredicate =
						(SPredicate) (scope.hasArg(SK_PREDICATE_TODO) ? scope.getArg(SK_PREDICATE_TODO, SPredicateType.id) : null);
				final SMentalState tempPred = new SMentalState("Desire", predicateDirect);
				final SMentalState tempSuper = new SMentalState("Intention", superpredicate);
				if (po != null) {
					tempPred.setSk_power(po);
				}
				if (life > 0) {
					tempPred.setSk_lifeTime(life);
				}
				tempPred.setSk_owner(scope.getAgent());
				return addDesire(scope, tempSuper, tempPred);
			}
			return false;
		}

		@action (
				name = "add_directly_desire",
				args = { @arg (
						name = "desire",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("desire to add in th belief base")) },
				doc = @doc (
						value = "add the desire in the desire base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddDirectlyDesire(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("desire") ? scope.getArg("desire", SMentalStateType.id) : null);
			if (predicateDirect != null && predicateDirect.getModality().equals("Desire")) {
				predicateDirect.setSk_owner(scope.getAgent());
				return addDesire(scope, null, predicateDirect);
			}
			return false;

		}

		@action (
				name = "add_desire_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("sk_mental_state to add as a desire")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the stregth of the desire")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the desire")),
						@arg (
								name = SK_PREDICATE_TODO,
								type = SPredicateType.id,
								optional = true,
								doc = @doc ("add the desire as a subintention of this parameter")), },
				doc = @doc (
						value = "adds the mental state is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primAddDesireMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState stateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			if (stateDirect != null) {
				final SPredicate superpredicate =
						(SPredicate) (scope.hasArg(SK_PREDICATE_TODO) ? scope.getArg(SK_PREDICATE_TODO, SPredicateType.id) : null);
				final SMentalState tempPred = new SMentalState("Desire", stateDirect);
				final SMentalState tempSuper = new SMentalState("Intention", superpredicate);
				if (po != null) {
					tempPred.setSk_power(po);
				}
				if (life > 0) {
					tempPred.setSk_lifeTime(life);
				}
				tempPred.setSk_owner(scope.getAgent());
				return addDesire(scope, tempSuper, tempPred);
			}

			return false;
		}

		@action (
				name = "add_desire_spatial_knowledge",
				args = { @arg (
						name = "spatial_knowledge",
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("spatial_knowledge to add as a desire")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the strength of the desire")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the desire")),
						@arg (
								name = SK_PREDICATE_TODO,
								type = SPredicateType.id,
								optional = true,
								doc = @doc ("add the desire as a subintention of this parameter")), },
				doc = @doc (
						value = "adds the spatial_knowledge in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primAddDesireEmotion(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge stateDirect =
					(SpatialKnowledge) (scope.hasArg("spatial_knowledge") ? scope.getArg("spatial_knowledge", SpatialKnowledgeType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			if (stateDirect != null) {
				final SPredicate superpredicate =
						(SPredicate) (scope.hasArg(SK_PREDICATE_TODO) ? scope.getArg(SK_PREDICATE_TODO, SPredicateType.id) : null);
				final SMentalState tempPred = new SMentalState("Desire", stateDirect);
				final SMentalState tempSuper = new SMentalState("Intention", superpredicate);
				if (po != null) {
					tempPred.setSk_power(po);
				}
				if (life > 0) {
					tempPred.setSk_lifeTime(life);
				}
				tempPred.setSk_owner(scope.getAgent());
				return addDesire(scope, tempSuper, tempPred);
			}

			return false;
		}

		@action (
				name = "get_desire",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "get the sk predicates is in the desire base (if several, returns the first one).",
						returns = "the sk predicate if it is in the base.",
						examples = { @example ("get_desire(new_predicate(\"has_water\", true))") }))
		public SMentalState getDesire(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
					if (mental.getSk_predicate() != null && predicateDirect.equals(mental.getSk_predicate())) { return mental; }
				}
			}
			return null;
		}

		@action (
				name = "get_desire_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "get the mental state is in the desire base (if several, returns the first one).",
						returns = "the predicate if it is in the base.",
						examples = { @example ("get_desire(new_predicate(\"has_water\", true))") }))
		public SMentalState getDesireMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
					if (mental.getSMentalState() != null && predicateDirect.equals(mental.getSMentalState())) {
						return mental;
					}
				}
			}
			return null;
		}

		@action (
				name = "get_desires",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("name of the predicates to check")) },
				doc = @doc (
						value = "get the list of predicates is in the desire base",
						returns = "the list of desires.",
						examples = { @example ("get_desires(\"has_water\")") }))
		public IList<SMentalState> getDesires(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final IList<SMentalState> predicates = GamaListFactory.create();
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
					if (mental.getSk_predicate() != null && predicateDirect.equals(mental.getSk_predicate())) {
						predicates.add(mental);
					}
				}
			}
			return predicates;
		}

		@action (
				name = "get_desires_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("name of the mental states to check")) },
				doc = @doc (
						value = "get the list of mental states is in the desire base",
						returns = "the list of mental states.",
						examples = { @example ("get_desires_mental_state(\"Belief\",predicte1)") }))
		public IList<SMentalState> getDesiresMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final IList<SMentalState> predicates = GamaListFactory.create();
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
					if (mental.getSMentalState() != null && predicateDirect.equals(mental.getSMentalState())) {
						predicates.add(mental);
					}
				}
			}
			return predicates;
		}

		@action (
				name = "get_desire_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicate to check")) },
				doc = @doc (
						value = "get the predicates is in the belief base (if several, returns the first one).",
						returns = "the predicate if it is in the base.",
						examples = { @example ("get_desire_with_name(\"has_water\")") }))
		public SMentalState getDesireName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
					if (mental.getSk_predicate() != null && predicateName.equals(mental.getSk_predicate().getSp_name())) {
						return mental;
					}
				}
			}
			return null;
		}

		@action (
				name = "get_desires_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicates to check")) },
				doc = @doc (
						value = "get the list of predicates is in the belief base with the given name.",
						returns = "the list of predicates.",
						examples = { @example ("get_belief(\"has_water\")") }))
		public List<SMentalState> getDesiresName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			final List<SMentalState> predicates = GamaListFactory.create();
			if (predicateName != null) {
				for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
					if (mental.getSk_predicate() != null && predicateName.equals(mental.getSk_predicate().getSp_name())) {
						predicates.add(mental);
					}
				}
			}
			return predicates;
		}

		public static Boolean removeBelief(final IScope scope, final SMentalState pred) {
			return getBase(scope, BELIEF_BASE).remove(pred);
		}

		@action (
				name = "remove_belief",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to remove")) },
				doc = @doc (
						value = "removes the predicate from the belief base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveBelief(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				final SMentalState temp = new SMentalState("Belief", predicateDirect);
				return removeBelief(scope, temp);
			}
			return false;
		}

		@action (
				name = "remove_belief_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to remove")) },
				doc = @doc (
						value = "removes the mental state from the belief base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveBeliefMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				final SMentalState temp = new SMentalState("Belief", predicateDirect);
				return removeBelief(scope, temp);
			}
			return false;
		}

		@action (
				name = "replace_belief",
				args = { @arg (
						name = "old_sk_predicate",
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to remove")),
						@arg (
								name = SK_PREDICATE,
								type = SPredicateType.id,
								optional = false,
								doc = @doc ("sk_predicate to add")) },
				doc = @doc (
						value = "replace the old predicate by the new one.",
						returns = "true if the old predicate is in the base.",
						examples = { @example ("") }))
		public Boolean primPlaceBelief(final IScope scope) throws GamaRuntimeException {
			final SPredicate oldPredicate =
					(SPredicate) (scope.hasArg("old_sk_predicate") ? scope.getArg("old_sk_predicate", SPredicateType.id) : null);
			boolean ok = true;
			if (oldPredicate != null) {
				ok = getBase(scope, BELIEF_BASE).remove(new SMentalState("Belief", oldPredicate));
			} else {
				ok = false;
			}
			final SPredicate newPredicate =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (newPredicate != null) {
				final SMentalState temp = new SMentalState("Belief", newPredicate);
				// SPredicate current_intention = currentIntention(scope);
				if (getBase(scope, SbdiArchitecture.INTENTION_BASE)
						.contains(new SMentalState("Intention", newPredicate))) {
					removeFromBase(scope, temp, DESIRE_BASE);
					removeFromBase(scope, temp, INTENTION_BASE);
				}
				if (getBase(scope, SbdiArchitecture.DESIRE_BASE).contains(new SMentalState("Desire", newPredicate))) {
					removeFromBase(scope, temp, DESIRE_BASE);
				}
				for (final Object statement : getBase(scope, SbdiArchitecture.INTENTION_BASE)) {
					if (((SMentalState) statement).getSk_predicate() != null) {
						final List<SMentalState> statementSubintention =
								((SMentalState) statement).getSk_predicate().getSubintentions();
						if (statementSubintention != null) {
							if (statementSubintention.contains(temp)) {
								statementSubintention.remove(temp);
							}
						}
						final List<SMentalState> statementOnHoldUntil =
								((SMentalState) statement).getSk_predicate().getOnHoldUntil();
						if (statementOnHoldUntil != null) {
							if (statementOnHoldUntil.contains(temp)) {
								statementOnHoldUntil.remove(temp);
							}
						}
					}
				}
				return addToBase(scope, temp, BELIEF_BASE);
			}
			return ok;
		}

		public static Boolean removeDesire(final IScope scope, final SMentalState pred) {
			getBase(scope, DESIRE_BASE).remove(pred);
			getBase(scope, INTENTION_BASE).remove(pred);
			for (final Object statement : getBase(scope, SbdiArchitecture.INTENTION_BASE)) {
				if (((SMentalState) statement).getSk_predicate() != null) {
					final List<SMentalState> statementSubintention =
							((SMentalState) statement).getSk_predicate().getSubintentions();
					if (statementSubintention != null) {
						if (statementSubintention.contains(pred)) {
							statementSubintention.remove(pred);
						}
					}
					final List<SMentalState> statementOnHoldUntil =
							((SMentalState) statement).getSk_predicate().getOnHoldUntil();
					if (statementOnHoldUntil != null) {
						if (statementOnHoldUntil.contains(pred)) {
							statementOnHoldUntil.remove(pred);
						}
					}
				}
			}
			return true;
		}

		@action (
				name = "remove_desire",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to remove from desire base")) },
				doc = @doc (
						value = "removes the predicates from the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveDesire(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				final SMentalState temp = new SMentalState("Desire", predicateDirect);
				return removeDesire(scope, temp);
			}
			return false;
		}

		@action (
				name = "remove_desire_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to remove from desire base")) },
				doc = @doc (
						value = "removes the mental state from the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveDesireMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				final SMentalState temp = new SMentalState("Desire", predicateDirect);
				return removeDesire(scope, temp);
			}
			return false;
		}

		@action (
				name = "add_intention",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to check")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the stregth of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "check if the predicates is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primAddIntention(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (predicateDirect != null) {
				temp = new SMentalState("Intention", predicateDirect);
			} else {
				temp = new SMentalState("Intention");
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addToBase(scope, temp, INTENTION_BASE);

		}

		@action (
				name = "add_intention_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("predicate to add as an intention")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the stregth of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "check if the predicates is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primAddIntentionMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState stateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (stateDirect != null) {
				temp = new SMentalState("Intention", stateDirect);
			} else {
				temp = new SMentalState("Intention");
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addToBase(scope, temp, INTENTION_BASE);

		}

		@action (
				name = "add_intention_spatial_knowledge",
				args = { @arg (
						name = "spatial_knowledge",
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("spatial_knowledge to add as an intention")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the strength/power of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "check if the sk predicates is in the desire base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primAddIntentionSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge stateDirect =
					(SpatialKnowledge) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (stateDirect != null) {
				temp = new SMentalState("Intention", stateDirect);
			} else {
				temp = new SMentalState("Intention");
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addToBase(scope, temp, INTENTION_BASE);

		}

		@action (
				name = "get_intention",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("sk_predicate to check")) },
				doc = @doc (
						value = "get the sk predicates in the intention base (if several, returns the first one).",
						returns = "the mental state if it is in the base.",
						examples = { @example ("get_intention(new_predicate(\"has_water\", true))") }))
		public SMentalState getIntention(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
					if (mental.getSk_predicate() != null && predicateDirect.equals(mental.getSk_predicate())) { return mental; }
				}
			}
			return null;
		}

		@action (
				name = "get_intention_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "get the mental state is in the intention base (if several, returns the first one).",
						returns = "the mental state if it is in the base.",
						examples = { @example ("get_belief(new_predicate(\"has_water\", true))") }))
		public SMentalState getIntentionMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
					if (mental.getSMentalState() != null && predicateDirect.equals(mental.getSMentalState())) {
						return mental;
					}
				}
			}
			return null;
		}

		@action (
				name = "get_intentions",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("name of the predicates to check")) },
				doc = @doc (
						value = "get the list of predicates is in the intention base",
						returns = "the list of intentions.",
						examples = { @example ("get_intentions(\"has_water\")") }))
		public IList<SMentalState> getIntentions(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final IList<SMentalState> predicates = GamaListFactory.create();
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
					if (mental.getSk_predicate() != null && predicateDirect.equals(mental.getSk_predicate())) {
						predicates.add(mental);
					}
				}
			}
			return predicates;
		}

		@action (
				name = "get_intentions_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "get the list of mental state is in the intention base",
						returns = "the list of intentions.",
						examples = { @example ("get_intentions_mental_state(\"Desire\",predicate1)") }))
		public IList<SMentalState> getIntentionsMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final IList<SMentalState> predicates = GamaListFactory.create();
			if (predicateDirect != null) {
				for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
					if (mental.getSMentalState() != null && predicateDirect.equals(mental.getSMentalState())) {
						predicates.add(mental);
					}
				}
			}
			return predicates;
		}

		@action (
				name = "get_intention_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicate to check")) },
				doc = @doc (
						value = "get the predicates is in the belief base (if several, returns the first one).",
						returns = "the predicate if it is in the base.",
						examples = { @example ("get_intention_with_name(\"has_water\")") }))
		public SMentalState getIntentionName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
					if (mental.getSk_predicate() != null && predicateName.equals(mental.getSk_predicate().getSp_name())) {
						return mental;
					}
				}
			}
			return null;
		}

		@action (
				name = "get_intentions_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicates to check")) },
				doc = @doc (
						value = "get the list of predicates is in the belief base with the given name.",
						returns = "the list of predicates.",
						examples = { @example ("get_belief(\"has_water\")") }))
		public List<SMentalState> getIntentionsName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			final List<SMentalState> predicates = GamaListFactory.create();
			if (predicateName != null) {
				for (final SMentalState mental : getBase(scope, INTENTION_BASE)) {
					if (mental.getSk_predicate() != null && predicateName.equals(mental.getSk_predicate().getSp_name())) {
						predicates.add(mental);
					}
				}
			}
			return predicates;
		}

		public static Boolean removeIntention(final IScope scope, final SMentalState pred) {
			getBase(scope, INTENTION_BASE).remove(pred);
			for (final Object statement : getBase(scope, SbdiArchitecture.INTENTION_BASE)) {
				if (((SMentalState) statement).getSk_predicate() != null) {
					final List<SMentalState> statementSubintention = ((SMentalState) statement).getSubintentions();
					if (statementSubintention != null) {
						if (statementSubintention.contains(pred)) {
							statementSubintention.remove(pred);
						}
					}
					final List<SMentalState> statementOnHoldUntil = ((SMentalState) statement).getOnHoldUntil();
					if (statementOnHoldUntil != null) {
						if (statementOnHoldUntil.contains(pred)) {
							statementOnHoldUntil.remove(pred);
						}
					}
				}
			}
			return true;
		}

		@action (
				name = "remove_intention",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("intention's predicate to remove")),
						@arg (
								name = REMOVE_DESIRE_AND_INTENTION,
								type = IType.BOOL,
								optional = true,
								doc = @doc ("removes also desire")) },
				doc = @doc (
						value = "removes the predicates from the intention base.",
						returns = "true if it is removed from the base.",
						examples = { @example ("") }))
		public Boolean primRemoveIntention(final IScope scope) throws GamaRuntimeException {

			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState temp = new SMentalState("Intention", predicateDirect);
			if (predicateDirect != null) {
				final Boolean dodesire =
						scope.hasArg(REMOVE_DESIRE_AND_INTENTION) ? scope.getBoolArg(REMOVE_DESIRE_AND_INTENTION) : false;
				// getBase(scope, INTENTION_BASE).remove(temp);
				if (dodesire) {
					getBase(scope, DESIRE_BASE).remove(temp);
					getBase(scope, OBLIGATION_BASE).remove(temp);
				}
				if (currentIntention(scope) != null && predicateDirect.equals(currentIntention(scope).getSk_predicate())) {
					scope.getAgent().setAttribute(CURRENT_PLAN, null);
					scope.getAgent().setAttribute(CURRENT_NORM, null);
				}
				for (final Object statement : getBase(scope, SbdiArchitecture.INTENTION_BASE)) {
					if (((SMentalState) statement).getSk_predicate() != null) {
						final List<SMentalState> statementSubintention =
								((SMentalState) statement).getSk_predicate().getSubintentions();
						if (statementSubintention != null) {
							if (statementSubintention.contains(temp)) {
								statementSubintention.remove(temp);
							}
						}
						final List<SMentalState> statementOnHoldUntil =
								((SMentalState) statement).getSk_predicate().getOnHoldUntil();
						if (statementOnHoldUntil != null) {
							if (statementOnHoldUntil.contains(temp)) {
								statementOnHoldUntil.remove(temp);
							}
						}
					}
				}
				getBase(scope, INTENTION_BASE).remove(temp);

				return true;
			}

			return false;
		}

		@action (
				name = "remove_intention_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("intention's mental state to remove")),
						@arg (
								name = REMOVE_DESIRE_AND_INTENTION,
								type = IType.BOOL,
								optional = false,
								doc = @doc ("removes also desire")) },
				doc = @doc (
						value = "removes the mental state from the intention base.",
						returns = "true if it is removed from the base.",
						examples = { @example ("") }))
		public Boolean primRemoveIntentionMentalState(final IScope scope) throws GamaRuntimeException {

			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final SMentalState temp = new SMentalState("Intention", predicateDirect);
			if (predicateDirect != null) {
				final Boolean dodesire =
						scope.hasArg(REMOVE_DESIRE_AND_INTENTION) ? scope.getBoolArg(REMOVE_DESIRE_AND_INTENTION) : false;
				getBase(scope, INTENTION_BASE).remove(temp);
				if (dodesire) {
					getBase(scope, DESIRE_BASE).remove(temp);
				}

				return true;
			}

			return false;
		}

		@action (
				name = "clear_beliefs",
				doc = @doc (
						value = "clear the belief base",
						returns = "true if the base is cleared correctly",
						examples = { @example ("") }))
		public Boolean primClearBelief(final IScope scope) {
			getBase(scope, BELIEF_BASE).clear();
			return true;
		}

		@action (
				name = "clear_desires",
				doc = @doc (
						value = "clear the desire base",
						returns = "true if the base is cleared correctly",
						examples = { @example ("") }))
		public Boolean primClearDesire(final IScope scope) {
			getBase(scope, DESIRE_BASE).clear();
			return true;
		}

		@action (
				name = "clear_intentions",
				doc = @doc (
						value = "clear the intention base",
						returns = "true if the base is cleared correctly",
						examples = { @example ("") }))
		public Boolean primClearIntention(final IScope scope) {
			getBase(scope, INTENTION_BASE).clear();
			scope.getAgent().setAttribute(CURRENT_PLAN, null);
			return true;
		}

		public static Boolean clearIntention(final IScope scope) {
			getBase(scope, INTENTION_BASE).clear();
			scope.getAgent().setAttribute(CURRENT_PLAN, null);
			return true;
		}

		@action (
				name = "remove_all_beliefs",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to remove")) },
				doc = @doc (
						value = "removes the predicates from the belief base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveAllBelief(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				final SMentalState temp = new SMentalState("Belief", predicateDirect);
				getBase(scope, BELIEF_BASE).removeAllOccurrencesOfValue(scope, temp);
				return true;
			}
			return false;
		}

		@action (
				name = "clear_spatial_knowledges",
				doc = @doc (
						value = "clear the spatial_knowledge base",
						returns = "true if the base is cleared correctly",
						examples = { @example ("") }))
		public Boolean primClearSpatialKnowledge(final IScope scope) {
			getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE).clear();
			return true;
		}

		protected void updateSpatialKnowledgePower(final IScope scope) {
			for (final SpatialKnowledge spatkwno : getSpatialKnowledgeBase(scope, SbdiArchitecture.SPATIAL_KNOWLEDGE_BASE)) {
				spatkwno.decayIntensity();
			}
			for (final SpatialKnowledge spatkwno : listSpatialKnowledgesNull(scope)) {
				removeFromBase(scope, spatkwno, SbdiArchitecture.SPATIAL_KNOWLEDGE_BASE);
			}
		}

/*		protected void computeSpatialknowledges(final IScope scope) {
			// Etape 0, demander à l'utilisateur s'il veut ou non utiliser cette
			// architecture
			// Etape 1, créer les émotions par rapport à la cognition (modèle thèse
			// de Carole). Cette étape va être dissociée d'ici.
			final IAgent agent = getCurrentAgent(scope);
			final Boolean use_spatial_knowledge_architecture = scope.hasArg(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE)
					? scope.getBoolArg(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE) : (Boolean) agent.getAttribute(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE);
			if (use_spatial_knowledge_architecture) {
				// createJoy(scope);
				// createSadness(scope);
				// createHope(scope);
				// createFear(scope);
				// createSatisfaction(scope);
				// createFearConfirmed(scope);
				// createRelief(scope);
				// createDisappointment(scope);
				createSpatialKnowledgesRelatedToOthers(scope);
				// createPrideAndShameAndAdmirationAndReproach(scope);
				// createGratification(scope);
				// createRemorse(scope);
				// createGratitude(scope);
				// createAnger(scope);
			}
		}  
		*/

		// va démarrer le calcul de gratification et gratitude
		// private void createJoy(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// for (final SMentalState predTest : getBase(scope, SbdiArchitecture.BELIEF_BASE)) {
		// if (getBase(scope, SbdiArchitecture.DESIRE_BASE).contains(predTest)) {
		// if (predTest.getSk_predicate() != null) {
		// final Emotion joy = new Emotion("joy", predTest.getSk_predicate());
		// final IAgent agentTest = predTest.getSk_predicate().getAgentCause();
		// if (agentTest != null) {
		// joy.setAgentCause(agentTest);
		// }
		// // ajout de l'intensité
		// Double intensity = 1.0;
		// Double decay = 0.0;
		// if (use_personality) {
		// final Double neurotisme = (Double) agent.getAttribute(NEUROTISM);
		// SMentalState desire = null;
		// for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
		// if (mental.getSk_predicate() != null
		// && predTest.getSk_predicate().equals(mental.getSk_predicate())) {
		// desire = mental;
		// }
		// }
		// // Faire ce calcul seulement si le désire à une force (vérifier le no value)
		// if (desire != null) {
		// intensity = desire.getStrength() * (1 + (0.5 - neurotisme));
		// if (intensity > 1.0) {
		// intensity = 1.0;
		// }
		// if (intensity < 0) {
		// intensity = 0.0;
		// }
		// }
		// // 0.00028=1/3600
		// // final Double test = scope.getSimulation().getTimeStep(scope);
		// decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
		// }
		// joy.setIntensity(intensity);
		// joy.setDecay(decay);
		// // Dans le add_emotion, s'assurer que l'intensité ne dépasse pas 1.0
		// addEmotion(scope, joy);
		// }
		// }
		// }
		// }

		// va démarrer le calcul de gratification , remorse, anger et gratitude
/*		private static void createJoyFromPredicate(final IScope scope, final SMentalState predTest) {
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			if (predTest.getSk_predicate() != null) {
				if (getBase(scope, SbdiArchitecture.DESIRE_BASE).contains(predTest)) {
					final Emotion joy = new Emotion("joy", predTest.getSk_predicate());
					final IAgent agentTest = predTest.getSk_predicate().getAgentCause();
					if (agentTest != null) {
						joy.setAgentCause(agentTest);
					}
					// ajout de l'intensité
					Double intensity = 1.0;
					Double decay = 0.0;
					if (use_personality) {
						final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
						SMentalState desire = null;
						for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
							if (mental.getSk_predicate() != null && predTest.getSk_predicate().equals(mental.getSk_predicate())) {
								desire = mental;
							}
						}
						// Faire ce calcul seulement si le désire à une force (vérifier le no value)
						if (desire != null && desire.getStrength() >= 0.0 && predTest.getStrength() >= 0.0) {
							intensity = predTest.getStrength() * desire.getStrength() * (1 + (0.5 - neurotisme));
							if (intensity > 1.0) {
								intensity = 1.0;
							}
							if (intensity < 0) {
								intensity = 0.0;
							}
						}
						// 0.00028=1/3600
						decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
					}
					joy.setIntensity(intensity);
					joy.setDecay(decay);
					addEmotion(scope, joy);
					createGratificationGratitudeFromJoy(scope, joy);

				} else {
					for (final SMentalState pred : getBase(scope, DESIRE_BASE)) {
						if (pred.getSk_predicate() != null) {
							if (predTest.getSk_predicate().equalsButNotTruth(pred.getSk_predicate())) {
								final Emotion sadness = new Emotion("sadness", predTest.getSk_predicate());
								final IAgent agentTest = predTest.getSk_predicate().getAgentCause();
								if (agentTest != null) {
									sadness.setAgentCause(agentTest);
								}
								// ajout de l'intensité
								Double intensity = 1.0;
								Double decay = 0.0;
								if (use_personality) {
									final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
									final SMentalState desire = pred;
									// Faire ce calcul seulement si le désire à une force (vérifier le no value)
									if (desire.getStrength() >= 0.0 && predTest.getStrength() >= 0.0) {
										intensity =
												predTest.getStrength() * desire.getStrength() * (1 + (0.5 - neurotisme));
										if (intensity > 1.0) {
											intensity = 1.0;
										}
										if (intensity < 0) {
											intensity = 0.0;
										}
									}
									// 0.00028=1/3600
									decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;

								}
								sadness.setIntensity(intensity);
								sadness.setDecay(decay);
								addEmotion(scope, sadness);
								createRemorseAngerFromSadness(scope, sadness);
							}
						}
					}
				}
			}
		}
*/
		// private void createSadness(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// // A améliorer en termes de rapidité de calcul
		// for (final SMentalState predTest : getBase(scope, SbdiArchitecture.BELIEF_BASE)) {
		// for (final SMentalState desireTest : getBase(scope, SbdiArchitecture.DESIRE_BASE)) {
		// if (predTest.getSk_predicate() != null && desireTest.getSk_predicate() != null
		// && predTest.getSk_predicate().equalsButNotTruth(desireTest.getSk_predicate())) {
		// final Emotion sadness = new Emotion("sadness", predTest.getSk_predicate());
		// final IAgent agentTest = predTest.getSk_predicate().getAgentCause();
		// if (agentTest != null) {
		// sadness.setAgentCause(agentTest);
		// }
		// // ajout de l'intensité
		// Double intensity = 1.0;
		// Double decay = 0.0;
		// if (use_personality) {
		// final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
		// final SMentalState desire = desireTest;
		// // Faire ce calcul seulement si le désire à une force (vérifier le no value)
		// if (desire != null) {
		// intensity = desire.getStrength() * (1 + (0.5 - neurotisme));
		// if (intensity > 1.0) {
		// intensity = 1.0;
		// }
		// if (intensity < 0) {
		// intensity = 0.0;
		// }
		// }
		// // 0.00028=1/3600
		// decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
		//
		// }
		// sadness.setIntensity(intensity);
		// sadness.setDecay(decay);
		// addEmotion(scope, sadness);
		// }
		// }
		// }
		// }

/*		private static void createHopeFromMentalState(final IScope scope, final SMentalState predTest) {
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			if (predTest.getSk_predicate() != null) {
				if (getBase(scope, SbdiArchitecture.DESIRE_BASE).contains(predTest)) {
					final Emotion hope = new Emotion("hope", predTest.getSk_predicate());
					final IAgent agentTest = predTest.getSk_predicate().getAgentCause();
					if (agentTest != null) {
						hope.setAgentCause(agentTest);
					}
					// ajout de l'intensité
					Double intensity = 1.0;
					Double decay = 0.0;
					if (use_personality) {
						final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
						SMentalState desire = null;
						for (final SMentalState mental : getBase(scope, DESIRE_BASE)) {
							if (mental.getSk_predicate() != null && predTest.getSk_predicate().equals(mental.getSk_predicate())) {
								desire = mental;
							}
						}
						if (desire != null && desire.getStrength() >= 0.0 && predTest.getStrength() >= 0.0) {
							intensity = predTest.getStrength() * desire.getStrength() * (1 + (0.5 - neurotisme));
							if (intensity > 1.0) {
								intensity = 1.0;
							}
							if (intensity < 0) {
								intensity = 0.0;
							}
						}
						// 0.00028=1/3600
						decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
					}
					hope.setIntensity(intensity);
					hope.setDecay(decay);
					addEmotion(scope, hope);

				} else {
					for (final SMentalState pred : getBase(scope, DESIRE_BASE)) {
						if (pred.getSk_predicate() != null) {
							if (predTest.getSk_predicate().equalsButNotTruth(pred.getSk_predicate())) {
								final Emotion fear = new Emotion("fear", predTest.getSk_predicate());
								final IAgent agentTest = predTest.getSk_predicate().getAgentCause();
								if (agentTest != null) {
									fear.setAgentCause(agentTest);
								}
								// ajout de l'intensité
								Double intensity = 1.0;
								Double decay = 0.0;
								if (use_personality) {
									final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
									final SMentalState desire = pred;
									// Faire ce calcul seulement si le désire à une force (vérifier le no value)
									if (desire.getStrength() >= 0.0 && predTest.getStrength() >= 0.0) {
										intensity =
												predTest.getStrength() * desire.getStrength() * (1 + (0.5 - neurotisme));
										if (intensity > 1.0) {
											intensity = 1.0;
										}
										if (intensity < 0) {
											intensity = 0.0;
										}
									}
									// 0.00028=1/3600
									decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;

								}
								fear.setIntensity(intensity);
								fear.setDecay(decay);
								addEmotion(scope, fear);
							}
						}
					}
				}
			}

		}

		private static void createSatisfactionFromMentalState(final IScope scope, final SMentalState predicateDirect) {
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			if (predicateDirect.getSk_predicate() != null) {
				final IList<SpatialKnowledge> emoTemps = getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE).copy(scope);
				for (final Emotion emo : emoTemps) {
					if (emo.getName().equals("hope")) {
						if (emo.getAbout() != null && emo.getAbout().equalsEmotions(predicateDirect.getSk_predicate())) {
							Emotion satisfaction = null;
							Emotion joy = null;
							final IAgent agentTest = emo.getAgentCause();
							if (emo.getNoIntensity()) {
								satisfaction = new Emotion("satisfaction", emo.getAbout());
								if (agentTest != null) {
									satisfaction.setAgentCause(agentTest);
								}
								joy = new Emotion("joy", emo.getAbout());
								if (agentTest != null) {
									joy.setAgentCause(agentTest);
								}
							} else {
								// On décide de transmettre l'intensité de l'émotion
								// précédente.
								satisfaction = new Emotion("satisfaction", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									satisfaction.setAgentCause(agentTest);
								}
								joy = new Emotion("joy", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									joy.setAgentCause(agentTest);
								}
							}
							Double decay = 0.0;
							if (use_personality) {
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
										* satisfaction.getIntensity();
							}
							satisfaction.setDecay(decay);
							joy.setDecay(decay);
							addEmotion(scope, satisfaction);
							addEmotion(scope, joy);
							removeEmotion(scope, emo);
						}
						if (emo.getAbout() != null && emo.getAbout().equalsButNotTruth(predicateDirect.getSk_predicate())) {
							Emotion disappointment = null;
							Emotion sadness = null;
							final IAgent agentTest = emo.getAgentCause();
							if (emo.getNoIntensity()) {
								disappointment = new Emotion("disappointment", emo.getAbout());
								if (agentTest != null) {
									disappointment.setAgentCause(agentTest);
								}
								sadness = new Emotion("sadness", emo.getAbout());
								if (agentTest != null) {
									sadness.setAgentCause(agentTest);
								}
							} else {
								// On décide de transmettre l'intensité de
								// l'émotion précédente.
								disappointment = new Emotion("disappointment", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									disappointment.setAgentCause(agentTest);
								}
								sadness = new Emotion("sadness", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									sadness.setAgentCause(agentTest);
								}
							}
							Double decay = 0.0;
							if (use_personality) {
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
										* disappointment.getIntensity();

							}
							disappointment.setDecay(decay);
							sadness.setDecay(decay);
							addEmotion(scope, disappointment);
							addEmotion(scope, sadness);
							removeEmotion(scope, emo);
						}
					}
					if (emo.getName().equals("fear")) {
						if (emo.getAbout() != null && emo.getAbout().equalsEmotions(predicateDirect.getSk_predicate())) {
							Emotion fearConfirmed = null;
							Emotion sadness = null;
							final IAgent agentTest = emo.getAgentCause();
							if (emo.getNoIntensity()) {
								fearConfirmed = new Emotion("fear_confirmed", emo.getAbout());
								if (agentTest != null) {
									fearConfirmed.setAgentCause(agentTest);
								}
								sadness = new Emotion("sadness", emo.getAbout());
								if (agentTest != null) {
									sadness.setAgentCause(agentTest);
								}
							} else {
								// On décide de transmettre l'intensité de l'émotion
								// précédente.
								fearConfirmed = new Emotion("fear_confirmed", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									fearConfirmed.setAgentCause(agentTest);
								}
								sadness = new Emotion("sadness", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									sadness.setAgentCause(agentTest);
								}
							}
							Double decay = 0.0;
							if (use_personality) {
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
										* fearConfirmed.getIntensity();

							}
							fearConfirmed.setDecay(decay);
							sadness.setDecay(decay);
							addEmotion(scope, fearConfirmed);
							addEmotion(scope, sadness);
							removeEmotion(scope, emo);
						}
						if (emo.getAbout() != null && emo.getAbout().equalsButNotTruth(predicateDirect.getSk_predicate())) {
							Emotion relief = null;
							Emotion joy = null;
							final IAgent agentTest = emo.getAgentCause();
							if (emo.getNoIntensity()) {
								relief = new Emotion("relief", emo.getAbout());
								if (agentTest != null) {
									relief.setAgentCause(agentTest);
								}
								joy = new Emotion("joy", emo.getAbout());
								if (agentTest != null) {
									joy.setAgentCause(agentTest);
								}
							} else {
								// On décide de transmettre l'intensité de
								// l'émotion précédente.
								relief = new Emotion("relief", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									relief.setAgentCause(agentTest);
								}
								joy = new Emotion("joy", emo.getIntensity(), emo.getAbout());
								if (agentTest != null) {
									joy.setAgentCause(agentTest);
								}
							}
							Double decay = 0.0;
							if (use_personality) {
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
										* relief.getIntensity();

							}
							relief.setDecay(decay);
							joy.setDecay(decay);
							addEmotion(scope, relief);
							addEmotion(scope, joy);
							removeEmotion(scope, emo);
						}
					}
				}
			}
		}
*/
		// private void createSatisfaction(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("hope")) {
		// if (emo.getAbout() != null) {
		// final SMentalState temp = new SMentalState("Belief", emo.getAbout());
		// if (getBase(scope, SbdiArchitecture.BELIEF_BASE).contains(temp)) {
		// Emotion satisfaction = null;
		// Emotion joy = null;
		// final IAgent agentTest = emo.getAgentCause();
		// if (emo.getNoIntensity()) {
		// satisfaction = new Emotion("satisfaction", emo.getAbout());
		// if (agentTest != null) {
		// satisfaction.setAgentCause(agentTest);
		// }
		// joy = new Emotion("joy", emo.getAbout());
		// if (agentTest != null) {
		// joy.setAgentCause(agentTest);
		// }
		// } else {
		// // On décide de transmettre l'intensité de l'émotion
		// // précédente.
		// satisfaction = new Emotion("satisfaction", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// satisfaction.setAgentCause(agentTest);
		// }
		// joy = new Emotion("joy", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// joy.setAgentCause(agentTest);
		// }
		// }
		// Double decay = 0.0;
		// if (use_personality) {
		// final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
		// decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
		// * satisfaction.getIntensity();
		// }
		// satisfaction.setDecay(decay);
		// joy.setDecay(decay);
		// addEmotion(scope, satisfaction);
		// addEmotion(scope, joy);
		// removeEmotion(scope, emo);
		// }
		// }
		// }
		// }
		// }

		// private void createFearConfirmed(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("fear")) {
		// if (emo.getAbout() != null) {
		// final SMentalState temp = new SMentalState("Belief", emo.getAbout());
		// if (getBase(scope, SbdiArchitecture.BELIEF_BASE).contains(temp)) {
		// Emotion fearConfirmed = null;
		// Emotion sadness = null;
		// final IAgent agentTest = emo.getAgentCause();
		// if (emo.getNoIntensity()) {
		// fearConfirmed = new Emotion("fear_confirmed", emo.getAbout());
		// if (agentTest != null) {
		// fearConfirmed.setAgentCause(agentTest);
		// }
		// sadness = new Emotion("sadness", emo.getAbout());
		// if (agentTest != null) {
		// sadness.setAgentCause(agentTest);
		// }
		// } else {
		// // On décide de transmettre l'intensité de l'émotion
		// // précédente.
		// fearConfirmed = new Emotion("fear_confirmed", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// fearConfirmed.setAgentCause(agentTest);
		// }
		// sadness = new Emotion("sadness", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// sadness.setAgentCause(agentTest);
		// }
		// }
		// Double decay = 0.0;
		// if (use_personality) {
		// final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
		// decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
		// * fearConfirmed.getIntensity();
		//
		// }
		// fearConfirmed.setDecay(decay);
		// sadness.setDecay(decay);
		// addEmotion(scope, fearConfirmed);
		// addEmotion(scope, sadness);
		// removeEmotion(scope, emo);
		// }
		// }
		// }
		// }
		// }
		//
		// private void createRelief(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("fear")) {
		// if (emo.getAbout() != null) {
		// for (final SMentalState beliefTest : getBase(scope, SbdiArchitecture.BELIEF_BASE)) {
		// if (beliefTest.getSk_predicate() != null
		// && emo.getAbout().equalsButNotTruth(beliefTest.getSk_predicate())) {
		// Emotion relief = null;
		// Emotion joy = null;
		// final IAgent agentTest = emo.getAgentCause();
		// if (emo.getNoIntensity()) {
		// relief = new Emotion("relief", beliefTest.getSk_predicate());
		// if (agentTest != null) {
		// relief.setAgentCause(agentTest);
		// }
		// joy = new Emotion("joy", beliefTest.getSk_predicate());
		// if (agentTest != null) {
		// joy.setAgentCause(agentTest);
		// }
		// } else {
		// // On décide de transmettre l'intensité de
		// // l'émotion précédente.
		// relief = new Emotion("relief", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// relief.setAgentCause(agentTest);
		// }
		// joy = new Emotion("joy", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// joy.setAgentCause(agentTest);
		// }
		// }
		// Double decay = 0.0;
		// if (use_personality) {
		// final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
		// decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
		// * relief.getIntensity();
		//
		// }
		// relief.setDecay(decay);
		// joy.setDecay(decay);
		// addEmotion(scope, relief);
		// addEmotion(scope, joy);
		// removeEmotion(scope, emo);
		// }
		// }
		// }
		// }
		// }
		// }

		// private void createDisappointment(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("hope")) {
		// if (emo.getAbout() != null) {
		// for (final SMentalState beliefTest : getBase(scope, SbdiArchitecture.BELIEF_BASE)) {
		// if (beliefTest.getSk_predicate() != null
		// && emo.getAbout().equalsButNotTruth(beliefTest.getSk_predicate())) {
		// Emotion disappointment = null;
		// Emotion sadness = null;
		// final IAgent agentTest = emo.getAgentCause();
		// if (emo.getNoIntensity()) {
		// disappointment = new Emotion("disappointment", beliefTest.getSk_predicate());
		// if (agentTest != null) {
		// disappointment.setAgentCause(agentTest);
		// }
		// sadness = new Emotion("sadness", beliefTest.getSk_predicate());
		// if (agentTest != null) {
		// sadness.setAgentCause(agentTest);
		// }
		// } else {
		// // On décide de transmettre l'intensité de
		// // l'émotion précédente.
		// disappointment = new Emotion("disappointment", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// disappointment.setAgentCause(agentTest);
		// }
		// sadness = new Emotion("sadness", emo.getIntensity(), emo.getAbout());
		// if (agentTest != null) {
		// sadness.setAgentCause(agentTest);
		// }
		// }
		// Double decay = 0.0;
		// if (use_personality) {
		// final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
		// decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
		// * disappointment.getIntensity();
		//
		// }
		// disappointment.setDecay(decay);
		// sadness.setDecay(decay);
		// addEmotion(scope, disappointment);
		// addEmotion(scope, sadness);
		// removeEmotion(scope, emo);
		// }
		// }
		// }
		// }
		// }
		// }

		/*
		private static void createHappyForFromMentalState(final IScope scope, final SMentalState predicateDirect) {
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			if (predicateDirect.getSknow() != null) {
				final SpatialKnowledge sk = predicateDirect.getSknow();
				if (sk.getSk_name().equals("")) {
					final IAgent agentTemp = sk.getSk_owner();
					if (!getSocialBase(scope, SOCIALLINK_BASE).isEmpty()) {
						for (final SocialLink temp : getSocialBase(scope, SOCIALLINK_BASE)) {
							if (temp.getAgent().equals(agentTemp)) {
								if (temp.getLiking() > 0.0) {
									final Emotion happyFor = new Emotion("happy_for", emo.getAbout(), agentTemp);
									Double intensity = 1.0;
									Double decay = 0.0;
									if (use_personality) {
										final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
										final Double amicability = (Double) scope.getAgent().getAttribute(AGREEABLENESS);
										intensity = emo.getIntensity() * temp.getLiking() * (1 - (0.5 - amicability));
										if (intensity > 1.0) {
											intensity = 1.0;
										}
										if (intensity < 0) {
											intensity = 0.0;
										}
										decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
												* happyFor.getIntensity();
									}
									happyFor.setIntensity(intensity);
									happyFor.setDecay(decay);
									addEmotion(scope, happyFor);
								}
								if (temp.getLiking() < 0.0) {
									final Emotion resentment = new Emotion("resentment", emo.getAbout(), agentTemp);
									Double intensity = 1.0;
									Double decay = 0.0;
									if (use_personality) {
										final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
										final Double amicability = (Double) scope.getAgent().getAttribute(AGREEABLENESS);
										intensity = emo.getIntensity() * -temp.getLiking() * (1 + (0.5 - amicability));
										if (intensity > 1.0) {
											intensity = 1.0;
										}
										if (intensity < 0) {
											intensity = 0.0;
										}
										decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
												* resentment.getIntensity();
									}
									resentment.setIntensity(intensity);
									resentment.setDecay(decay);
									addEmotion(scope, resentment);
								}
							}
						}
					}
				}
				if (emo.getName().equals("sadness")) {
					final IAgent agentTemp = emo.getOwner();
					if (!getSocialBase(scope, SOCIALLINK_BASE).isEmpty()) {
						for (final SocialLink temp : getSocialBase(scope, SOCIALLINK_BASE)) {
							if (temp.getAgent().equals(agentTemp)) {
								if (temp.getLiking() > 0.0) {
									final Emotion sorryFor = new Emotion("sorry_for", emo.getAbout(), agentTemp);
									Double intensity = 1.0;
									Double decay = 0.0;
									if (use_personality) {
										final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
										final Double amicability = (Double) scope.getAgent().getAttribute(AGREEABLENESS);
										intensity = emo.getIntensity() * temp.getLiking() * (1 - (0.5 - amicability));
										if (intensity > 1.0) {
											intensity = 1.0;
										}
										if (intensity < 0) {
											intensity = 0.0;
										}
										decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
												* sorryFor.getIntensity();
									}
									sorryFor.setIntensity(intensity);
									sorryFor.setDecay(decay);
									addEmotion(scope, sorryFor);
								}
								if (temp.getLiking() < 0.0) {
									final Emotion gloating = new Emotion("gloating", emo.getAbout(), agentTemp);
									Double intensity = 1.0;
									Double decay = 0.0;
									if (use_personality) {
										final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
										final Double amicability = (Double) scope.getAgent().getAttribute(AGREEABLENESS);
										intensity = emo.getIntensity() * -temp.getLiking() * (1 + (0.5 - amicability));
										if (intensity > 1.0) {
											intensity = 1.0;
										}
										if (intensity < 0) {
											intensity = 0.0;
										}
										decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
												* gloating.getIntensity();
									}
									gloating.setIntensity(intensity);
									gloating.setDecay(decay);
									addEmotion(scope, gloating);
								}
							}
						}
					}
				}

			}
		}   

		private void createEmotionsRelatedToOthers(final IScope scope) {
			final IAgent agent = getCurrentAgent(scope);
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) agent.getAttribute(USE_PERSONALITY);
			// Regroupe le happy_for, sorry_for, resentment et gloating.
			if (!getSocialBase(scope, SOCIALLINK_BASE).isEmpty()) {
				for (final SocialLink temp : getSocialBase(scope, SOCIALLINK_BASE)) {
					if (temp.getLiking() > 0.0) {
						final IAgent agentTemp = temp.getAgent();
						IScope scopeAgentTemp = null;
						if (agentTemp != null) {
							scopeAgentTemp = agentTemp.getScope().copy("in SbdiArchitecture");
							scopeAgentTemp.push(agentTemp);
						}
						for (final Emotion emo : getSpatialKnowledgeBase(scopeAgentTemp, EMOTION_BASE)) {
							if (emo.getName().equals("joy")) {
								final Emotion happyFor = new Emotion("happy_for",
										/* formule à changer *//*emo.getIntensity() * temp.getLiking(), emo.getAbout(),
										agentTemp);
								final Double decay = 0.0;
								if (use_personality) {

								}
								happyFor.setDecay(decay);
								addEmotion(scope, happyFor);
							}
							if (emo.getName().equals("sadness")) {
								final Emotion sorryFor = new Emotion("sorry_for",
										/* formule à changer *//*emo.getIntensity() * temp.getLiking(), emo.getAbout(),
										agentTemp);
								final Double decay = 0.0;
								if (use_personality) {

								}
								sorryFor.setDecay(decay);
								addEmotion(scope, sorryFor);
							}
						}
						GAMA.releaseScope(scopeAgentTemp);
					}
					if (temp.getLiking() < 0.0) {
						final IAgent agentTemp = temp.getAgent();
						IScope scopeAgentTemp = null;
						if (agentTemp != null) {
							scopeAgentTemp = agentTemp.getScope().copy("in SbdiArchitecture");
							scopeAgentTemp.push(agentTemp);
						}
						for (final Emotion emo : getSpatialKnowledgeBase(scopeAgentTemp, EMOTION_BASE)) {
							if (emo.getName().equals("joy")) {
								final Emotion resentment = new Emotion("resentment",
										/* formule à changer *//*emo.getIntensity() * -temp.getLiking(), emo.getAbout(),
										agentTemp);
								final Double decay = 0.0;
								if (use_personality) {

								}
								resentment.setDecay(decay);
								addEmotion(scope, resentment);
							}
							if (emo.getName().equals("sadness")) {
								final Emotion gloating = new Emotion("gloating",
										/* formule à changer *//*emo.getIntensity() * -temp.getLiking(), emo.getAbout(),
										agentTemp);
								final Double decay = 0.0;
								if (use_personality) {

								}
								gloating.setDecay(decay);
								addEmotion(scope, gloating);
							}
						}
						GAMA.releaseScope(scopeAgentTemp);
					}
				}
			}
		}

		private static void createPrideFromMentalState(final IScope scope, final SMentalState predicateDirect) {
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			if (predicateDirect.getSk_predicate() != null) {
				for (final SMentalState temp : getBase(scope, SbdiArchitecture.IDEAL_BASE)) {
					if (temp.getSk_predicate() != null && temp.getSk_predicate().equals(predicateDirect.getSk_predicate())) {
						if (temp.getStrength() > 0.0) {
							if (predicateDirect.getSk_predicate().getSp_agentGetpredicate() != null
									&& predicateDirect.getSk_predicate().getAgentCause().equals(scope.getAgent())) {
								final Emotion pride = new Emotion("pride", predicateDirect.getSk_predicate());
								pride.setAgentCause(scope.getAgent());
								// ajout de l'intensité
								Double intensity = 1.0;
								Double decay = 0.0;
								if (use_personality) {
									final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
									final Double openness = (Double) scope.getAgent().getAttribute(OPENNESS);
									intensity = predicateDirect.getStrength() * temp.getStrength() * (1 + (0.5 - openness));
									if (intensity > 1.0) {
										intensity = 1.0;
									}
									if (intensity < 0) {
										intensity = 0.0;
									}
									decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
											* pride.getIntensity();
								}
								pride.setIntensity(intensity);
								pride.setDecay(decay);
								addEmotion(scope, pride);
							}
							if (predicateDirect.getSk_predicate().getAgentCause() != null) {
								final Emotion admiration = new Emotion("admiration", predicateDirect.getSk_predicate());
								admiration.setAgentCause(predicateDirect.getSk_predicate().getAgentCause());
								// ajout de l'intensité
								Double intensity = 1.0;
								Double decay = 0.0;
								if (use_personality) {
									final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
									final Double openness = (Double) scope.getAgent().getAttribute(OPENNESS);
									intensity = predicateDirect.getStrength() * temp.getStrength() * (1 + (0.5 - openness));
									if (intensity > 1.0) {
										intensity = 1.0;
									}
									if (intensity < 0) {
										intensity = 0.0;
									}
									decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
											* admiration.getIntensity();
								}
								admiration.setIntensity(intensity);
								admiration.setDecay(decay);
								addEmotion(scope, admiration);
							}
						}
						if (temp.getStrength() < 1.0) {
							if (predicateDirect.getSk_predicate().getAgentCause() != null
									&& predicateDirect.getSk_predicate().getAgentCause().equals(scope.getAgent())) {
								final Emotion shame = new Emotion("shame", predicateDirect.getSk_predicate());
								shame.setAgentCause(scope.getAgent());
								// ajout de l'intensité
								Double intensity = 1.0;
								Double decay = 0.0;
								if (use_personality) {
									final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
									final Double openness = (Double) scope.getAgent().getAttribute(OPENNESS);
									intensity =
											-predicateDirect.getStrength() * temp.getStrength() * (1 + (0.5 - openness));
									if (intensity > 1.0) {
										intensity = 1.0;
									}
									if (intensity < 0) {
										intensity = 0.0;
									}
									decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
											* shame.getIntensity();
								}
								shame.setIntensity(intensity);
								shame.setDecay(decay);
								addEmotion(scope, shame);
							}
							if (predicateDirect.getSk_predicate().getAgentCause() != null) {
								final Emotion reproach = new Emotion("reproach", predicateDirect.getSk_predicate());
								reproach.setAgentCause(predicateDirect.getSk_predicate().getAgentCause());
								// ajout de l'intensité
								Double intensity = 1.0;
								Double decay = 0.0;
								if (use_personality) {
									final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
									final Double openness = (Double) scope.getAgent().getAttribute(OPENNESS);
									intensity =
											-predicateDirect.getStrength() * temp.getStrength() * (1 + (0.5 - openness));
									if (intensity > 1.0) {
										intensity = 1.0;
									}
									if (intensity < 0) {
										intensity = 0.0;
									}
									decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme
											* reproach.getIntensity();
								}
								reproach.setIntensity(intensity);
								reproach.setDecay(decay);
								addEmotion(scope, reproach);
							}
						}
					}
				}
			}
		}
*/
		// va démarrer le calcul de gratification, remorse, gratitude et anger, peut-�tre pas
		// private void createPrideAndShameAndAdmirationAndReproach(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// // inverser les boucles pour gagner du temps (la base des idéaux est censée être moins fournie que la base des
		// // croyances)
		// for (final SMentalState predTest : getBase(scope, SbdiArchitecture.BELIEF_BASE)) {
		// if (predTest.getSk_predicate() != null && predTest.getSk_predicate().getAgentCause() != null
		// && predTest.getSk_predicate().getAgentCause().equals(scope.getAgent())) {
		// if (getBase(scope, SbdiArchitecture.IDEAL_BASE).contains(predTest)) {
		// for (final SMentalState temp : getBase(scope, SbdiArchitecture.IDEAL_BASE)) {
		// if (temp.equals(predTest)) {
		// if (temp.getStrength() > 0.0) {
		// final Emotion pride = new Emotion("pride", predTest.getSk_predicate());
		// pride.setAgentCause(scope.getAgent());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// pride.setIntensity(intensity);
		// pride.setDecay(decay);
		// addEmotion(scope, pride);
		// }
		// if (temp.getStrength() < 0.0) {
		// final Emotion shame = new Emotion("shame", predTest.getSk_predicate());
		// shame.setAgentCause(scope.getAgent());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// shame.setIntensity(intensity);
		// shame.setDecay(decay);
		// addEmotion(scope, shame);
		// }
		// }
		// }
		// }
		// } else {
		// if (predTest.getSk_predicate() != null && predTest.getSk_predicate().getAgentCause() != null) {
		// if (getBase(scope, SbdiArchitecture.IDEAL_BASE).contains(predTest)) {
		// for (final SMentalState temp : getBase(scope, SbdiArchitecture.IDEAL_BASE)) {
		// if (temp.equals(predTest)) {
		// if (temp.getStrength() > 0.0) {
		// final Emotion admiration = new Emotion("admiration", predTest.getSk_predicate());
		// admiration.setAgentCause(predTest.getSk_predicate().getAgentCause());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// admiration.setIntensity(intensity);
		// admiration.setDecay(decay);
		// addEmotion(scope, admiration);
		// }
		// if (temp.getStrength() < 0.0) {
		// final Emotion reproach = new Emotion("reproach", predTest.getSk_predicate());
		// reproach.setAgentCause(predTest.getSk_predicate().getAgentCause());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// reproach.setIntensity(intensity);
		// reproach.setDecay(decay);
		// addEmotion(scope, reproach);
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }

		/*pas besoin
		private static void createGratificationGratitudeFromJoy(final IScope scope, final SpatialKnowledge sk) {
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			final IList<SpatialKnowledge> skTemps = getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE).copy(scope);
			for (final SpatialKnowledge skTemp : skTemps) {
				if (skTemp.getSk_name().equals("pride")) {
					if (skTemp.getAbout_sk_predicate() != null && sk.getAbout_sk_predicate() != null && sk.getAbout_sk_predicate().getSp_agentGetpredicate()!= null) {
						if (skTemp.getAbout().equals(sk.getAbout())
								&& emo.getAbout().getAgentCause().equals(scope.getAgent())) {
							final Emotion gratification = new Emotion("gratification", emoTemp.getAbout());
							gratification.setAgentCause(emo.getAgentCause());
							// ajout de l'intensité
							Double intensity = 1.0;
							Double decay = 0.0;
							if (use_personality) {
								// Mettre les formules de calcul d'intensit� et de d�croissance
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								if (!emo.getNoIntensity() && !emoTemp.getNoIntensity()) {
									intensity = emo.getIntensity() * emoTemp.getIntensity();
								}
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
							}
							gratification.setIntensity(intensity);
							gratification.setDecay(decay);
							addEmotion(scope, gratification);
						}
					}
				}
				if (emoTemp.getName().equals("admiration")) {
					if (emoTemp.getAbout() != null && emo.getAbout() != null && emo.getAbout().getAgentCause() != null
							&& emoTemp.getAbout().getAgentCause() != null) {
						if (emoTemp.getAbout().equals(emo.getAbout())
								&& emo.getAbout().getAgentCause().equals(emoTemp.getAbout().getAgentCause())) {
							final Emotion gratitude = new Emotion("gratitude", emoTemp.getAbout());
							gratitude.setAgentCause(emo.getAgentCause());
							// ajout de l'intensité
							Double intensity = 1.0;
							Double decay = 0.0;
							if (use_personality) {
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								if (!emo.getNoIntensity() && !emoTemp.getNoIntensity()) {
									intensity = emo.getIntensity() * emoTemp.getIntensity();
								}
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
							}
							gratitude.setIntensity(intensity);
							gratitude.setDecay(decay);
							addEmotion(scope, gratitude);
						}
					}
				}
			}
		}

		private static void createRemorseAngerFromSadness(final IScope scope, final Emotion emo) {
			final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
					: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
			final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE).copy(scope);
			for (final Emotion emoTemp : emoTemps) {
				if (emoTemp.getName().equals("shame")) {
					if (emoTemp.getAbout() != null && emo.getAbout() != null && emo.getAbout().getAgentCause() != null) {
						if (emoTemp.getAbout().equals(emo.getAbout())
								&& emo.getAbout().getAgentCause().equals(scope.getAgent())) {
							final Emotion remorse = new Emotion("remorse", emoTemp.getAbout());
							remorse.setAgentCause(emo.getAgentCause());
							// ajout de l'intensité
							Double intensity = 1.0;
							Double decay = 0.0;
							if (use_personality) {
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								if (!emo.getNoIntensity() && !emoTemp.getNoIntensity()) {
									intensity = emo.getIntensity() * emoTemp.getIntensity();
								}
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
							}
							remorse.setIntensity(intensity);
							remorse.setDecay(decay);
							addEmotion(scope, remorse);
						}
					}
				}
				if (emoTemp.getName().equals("reproach")) {
					if (emoTemp.getAbout() != null && emo.getAbout() != null && emo.getAbout().getAgentCause() != null
							&& emoTemp.getAbout().getAgentCause() != null) {
						if (emoTemp.getAbout().equals(emo.getAbout())
								&& emo.getAbout().getAgentCause().equals(emoTemp.getAbout().getAgentCause())) {
							final Emotion anger = new Emotion("anger", emoTemp.getAbout());
							anger.setAgentCause(emo.getAgentCause());
							// ajout de l'intensité
							Double intensity = 1.0;
							Double decay = 0.0;
							if (use_personality) {
								final Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
								if (!emo.getNoIntensity() && !emoTemp.getNoIntensity()) {
									intensity = emo.getIntensity() * emoTemp.getIntensity();
								}
								decay = scope.getSimulation().getTimeStep(scope) * 0.00028 * neurotisme * intensity;
							}
							anger.setIntensity(intensity);
							anger.setDecay(decay);
							addEmotion(scope, anger);
						}
					}
				}
			}
		}
                  */
		// private void createGratification(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("pride")) {
		// for (final Emotion emoTemp : emoTemps) {
		// if (emoTemp.getName().equals("joy") && emo.getAbout().equals(emoTemp.getAbout())) {
		// final Emotion gratification = new Emotion("gratification", emoTemp.getAbout());
		// gratification.setAgentCause(emo.getAgentCause());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// gratification.setIntensity(intensity);
		// gratification.setDecay(decay);
		// addEmotion(scope, gratification);
		// }
		// }
		// }
		// }
		// }
		//
		// private void createRemorse(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("shame")) {
		// for (final Emotion emoTemp : emoTemps) {
		// if (emoTemp.getName().equals("sadness") && emo.getAbout().equals(emoTemp.getAbout())) {
		// final Emotion remorse = new Emotion("remorse", emoTemp.getAbout());
		// remorse.setAgentCause(emo.getAgentCause());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// remorse.setIntensity(intensity);
		// remorse.setDecay(decay);
		// addEmotion(scope, remorse);
		// }
		// }
		// }
		// }
		// }
		//
		// private void createGratitude(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("admiration")) {
		// for (final Emotion emoTemp : emoTemps) {
		// if (emoTemp.getName().equals("joy") && emo.getAbout().equals(emoTemp.getAbout())) {
		// final Emotion gratitude = new Emotion("gratitude", emoTemp.getAbout());
		// gratitude.setAgentCause(emo.getAgentCause());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// gratitude.setIntensity(intensity);
		// gratitude.setDecay(decay);
		// addEmotion(scope, gratitude);
		// }
		// }
		// }
		// }
		// }

		// private void createAnger(final IScope scope) {
		// final IAgent agent = getCurrentAgent(scope);
		// final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
		// : (Boolean) agent.getAttribute(USE_PERSONALITY);
		// final IList<Emotion> emoTemps = getSpatialKnowledgeBase(scope, EMOTION_BASE)
		// .cloneWithContentType(getSpatialKnowledgeBase(scope, EMOTION_BASE).getGamlType());
		// for (final Emotion emo : emoTemps) {
		// if (emo.getName().equals("reproach")) {
		// for (final Emotion emoTemp : emoTemps) {
		// if (emoTemp.getName().equals("sadness") && emo.getAbout().equals(emoTemp.getAbout())) {
		// final Emotion anger = new Emotion("anger", emoTemp.getAbout());
		// anger.setAgentCause(emo.getAgentCause());
		// // ajout de l'intensité
		// final Double intensity = 1.0;
		// final Double decay = 0.0;
		// if (use_personality) {
		//
		// }
		// anger.setIntensity(intensity);
		// anger.setDecay(decay);
		// addEmotion(scope, anger);
		// }
		// }
		// }
		// }
		// }

		private List<SpatialKnowledge> listSpatialKnowledgesNull(final IScope scope) {
			final List<SpatialKnowledge> tempPred = new ArrayList<>();
			for (final SpatialKnowledge pred : getSpatialKnowledgeBase(scope, SbdiArchitecture.SPATIAL_KNOWLEDGE_BASE)) {
				if (pred.getSk_power() <= 0 && pred.getSk_power() != -1.0) {
					tempPred.add(pred);
				}
			}
			return tempPred;
		}

		@action (
				name = "add_spatial_knowledge",
				args = { @arg (
						name = SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("add sk to the base")) },
				doc = @doc (
						value = "add the sk to the SK base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge skDirect = (SpatialKnowledge) (scope.hasArg(SPATIAL_KNOWLEDGE) ? scope.getArg(SPATIAL_KNOWLEDGE, SpatialKnowledgeType.id) : null);
			return addSpatialKnowledge(scope, skDirect);
		}

		public static boolean addSpatialKnowledge(final IScope scope, final SpatialKnowledge sk) {
			SpatialKnowledge newSk = sk;
			if (!sk.getNoSk_power() && hasSpatialKnowledge(scope, sk)) {
				final SpatialKnowledge oldSk = getSpatialKnowledge(scope, sk);
				if (!oldSk.getNoSk_power()) {
					newSk = new SpatialKnowledge(sk.getSk_name(), sk.getSk_power() + oldSk.getSk_power(), sk.getAbout_sk_predicate(),
							/* Math.min(sk.getDecay(), oldSk.getDecay()), */ sk.getSk_owner());
					if (oldSk.getSk_power() >= sk.getSk_power()) {
						newSk.setSk_decay_value(oldSk.getSk_decay_value());
					} else {
						newSk.setSk_decay_value(sk.getSk_decay_value());
					}
					if (newSk.getSk_power()> 1.0) {
						newSk.setSk_power(1.0);
					}
				}
			}
			newSk.setSk_owner(scope.getAgent());
			return addToBase(scope, newSk, SPATIAL_KNOWLEDGE_BASE);
		}

		@action (
				name = "has_spatial_knowledge",
				args = { @arg (
						name = SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("spatial_knowledge to check")) },
				doc = @doc (
						value = "check if the sk is in the belief base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge skDirect = (SpatialKnowledge) (scope.hasArg(SPATIAL_KNOWLEDGE) ? scope.getArg(SPATIAL_KNOWLEDGE, SpatialKnowledgeType.id) : null);
			if (skDirect != null) { return hasSpatialKnowledge(scope, skDirect); }
			return false;
		}

		@action (
				name = "has_spatial_knowledge_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the sk to check")) },
				doc = @doc (
						value = "check if the sk is in the sk base.",
						returns = "true if it is in the base.",
						examples = { @example ("has_spatial_knowledge_with_name(\"village_has_water\")") }))
		public Boolean hasSpatialKnowledgeName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				final SpatialKnowledge tempSk = new SpatialKnowledge(predicateName);
				return hasSpatialKnowledge(scope, tempSk);
			}
			return null;
		}

		public static Boolean hasSpatialKnowledge(final IScope scope, final SpatialKnowledge sk) {
			return getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE).contains(sk);
		}

		@action (
				name = "get_spatial_knowledge",
				args = { @arg (
						name = SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = false,
						doc = @doc ("spatial_knowledge to get")) },
				doc = @doc (
						value = "get the spatial_knowledge in the spatial_knowledge base (if several, returns the first one).",
						returns = "the spatial_knowledge if it is in the base.",
						examples = { @example ("get_belief(new_predicate(\"has_water\", true))") }))
		public SpatialKnowledge getSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge skDirect = (SpatialKnowledge) (scope.hasArg(SPATIAL_KNOWLEDGE) ? scope.getArg(SPATIAL_KNOWLEDGE, SpatialKnowledgeType.id) : null);
			if (skDirect != null) {
				for (final SpatialKnowledge sk : getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE)) {
					if (skDirect.equals(sk)) { return sk; }
				}
			}
			return null;
		}

		@action (
				name = "get_spatialknowledge_with_name",
				args = { @arg (
						name = "sk_name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the spatial knowledge to check")) },
				doc = @doc (
						value = "get the spatial knowledge is in the spatial_knowledge base (if several, returns the first one).",
						returns = "the spatial knowledge if it is in the base.",
						examples = { @example ("get_spatialknowledge_with_name(\"farmIsOverlap\")") }))
		public SpatialKnowledge getSkName(final IScope scope) throws GamaRuntimeException {
			final String sk_name = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (sk_name != null) {
				for (final SpatialKnowledge sk : getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE)) {
					if (sk_name.equals(sk.getSk_name())) { return sk; }
				}
			}
			return null;
		}

		public static SpatialKnowledge getSpatialKnowledge(final IScope scope, final SpatialKnowledge skDirect) {
			for (final SpatialKnowledge sk : getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE)) {
				if (skDirect.equals(sk)) { return sk; }
			}
			return null;
		}

		public static Boolean removeSpatialKnowledge(final IScope scope, final SpatialKnowledge sk) {
			return getSpatialKnowledgeBase(scope, SPATIAL_KNOWLEDGE_BASE).remove(sk);
		}

		@action (
				name = "remove_spatial_knowledge",
				args = { @arg (
						name = SPATIAL_KNOWLEDGE,
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("sk to remove")) },
				doc = @doc (
						value = "removes the sk from the SK Base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge skDirect = (SpatialKnowledge) (scope.hasArg(SPATIAL_KNOWLEDGE) ? scope.getArg(SPATIAL_KNOWLEDGE, SpatialKnowledgeType.id) : null);
			if (skDirect != null) { return removeSpatialKnowledge(scope, skDirect); }
			return false;
		}

		// Peut-être mettre un replace emotion.

		// Déclencher la création des Spatial Knowledge peur et espoir
		public static Boolean addSwagueness(final IScope scope, final SMentalState predicate) {
			final Boolean use_spatial_knowledge_architecture =
					scope.hasArg(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE) ? scope.getBoolArg(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE)
							: (Boolean) scope.getAgent().getAttribute(USE_SPATIAL_KNOWLEDGES_ARCHITECTURE);
			SMentalState predTemp = null;
			for (final SMentalState predTest : getBase(scope, SbdiArchitecture.BELIEF_BASE)) {
				if (predTest.getSk_predicate() != null && predicate.getSk_predicate() != null
						&& predTest.getSk_predicate().equalsButNotTruth(predicate.getSk_predicate())) {
					predTemp = predTest;
				}
			}
			if (predTemp != null) {
				removeFromBase(scope, predTemp, BELIEF_BASE);
			}
			for (final SMentalState predTest : getBase(scope, SbdiArchitecture.SWAGUENESS_BASE)) {
				if (predTest.getSk_predicate() != null && predicate.getSk_predicate() != null
						&& predTest.getSk_predicate().equalsButNotTruth(predicate.getSk_predicate())) {
					predTemp = predTest;
				}
			}
			if (predTemp != null) {
				removeFromBase(scope, predTemp, SWAGUENESS_BASE);
			}
	/*		if (use_emotion_architecture) {
				createHopeFromMentalState(scope, predicate);
			}   */
			predicate.setSk_owner(scope.getAgent());
			return addToBase(scope, predicate, SWAGUENESS_BASE);
		}

		@action (
				name = "add_swagueness",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("spredicate to add")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the strength of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "add a predicate in the swagueness base.",
						returns = "true it works.",
						examples = { @example ("") }))
		public Boolean primAddSwagueness(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (predicateDirect != null) {
				temp = new SMentalState("Swagueness", predicateDirect);
			} else {
				temp = new SMentalState("Swagueness");
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addSwagueness(scope, temp);

		}

		@action (
				name = "add_directly_swagueness",
				args = { @arg (
						name = "swagueness",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("swagueness to add in the swagueness base")) },
				doc = @doc (
						value = "add the swagueness in the swagueness base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddDirectlyUncertainty(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("swagueness") ? scope.getArg("swagueness", SMentalStateType.id) : null);
			if (predicateDirect != null && predicateDirect.getModality().equals("Swagueness")) {
				predicateDirect.setSk_owner(scope.getAgent());
				return addSwagueness(scope, predicateDirect);
			}
			return false;

		}

		@action (
				name = "add_swagueness_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to add as an uncertainty")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the power of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "add a predicate in the swagueness base.",
						returns = "true it works.",
						examples = { @example ("") }))
		public Boolean primAddSwaguenessMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState stateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (stateDirect != null) {
				temp = new SMentalState("Swagueness", stateDirect);
			} else {
				temp = new SMentalState("Swagueness");
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addSwagueness(scope, temp);

		}

		@action (
				name = "add_swagueness_spatial_knowledge",
				args = { @arg (
						name = "spatial_knowledge",
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("spatial_knowledge to add as an uncertainty")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the power of the belief")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the belief")) },
				doc = @doc (
						value = "add a predicate in the uncertainty base.",
						returns = "true it works.",
						examples = { @example ("") }))
		public Boolean primAddSwaguenessSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge stateDirect =
					(SpatialKnowledge) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (stateDirect != null) {
				temp = new SMentalState("Swagueness", stateDirect);
			} else {
				temp = new SMentalState("Swagueness");
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addSwagueness(scope, temp);

		}

		@action (
				name = "get_swagueness",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to return")) },
				doc = @doc (
						value = "get the predicates is in the uncertainty base (if several, returns the first one).",
						returns = "the uncertainty (mental state) if it is in the base.",
						examples = { @example ("get_uncertainty(new_predicate(\"has_water\", true))") }))
		public SMentalState getSwagueness(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState pred : getBase(scope, SWAGUENESS_BASE)) {
					if (pred.getSk_predicate() != null && predicateDirect.equals(pred.getSk_predicate())) { return pred; }
				}
			}
			return null;
		}

		@action (
				name = "get_swagueness_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to return")) },
				doc = @doc (
						value = "get the spatial mental state is in the swagueness base (if several, returns the first one).",
						returns = "the spatial mental state if it is in the base.",
						examples = { @example ("get_swagueness(new_predicate(\"has_river\", true))") }))
		public SMentalState getSwaguenessMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState pred : getBase(scope, SWAGUENESS_BASE)) {
					if (pred.getSMentalState() != null && predicateDirect.equals(pred.getSMentalState())) { return pred; }
				}
			}
			return null;
		}

		public static Boolean hasSwagueness(final IScope scope, final SMentalState predicateDirect) {
			return getBase(scope, SWAGUENESS_BASE).contains(predicateDirect);
		}

		@action (
				name = "has_swagueness",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "check if the predicates is in the swagueness base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestSwagueness(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState temp = new SMentalState("Swagueness", predicateDirect);
			if (predicateDirect != null) { return hasSwagueness(scope, temp); }
			return false;
		}

		@action (
				name = "has_swagueness_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the swagueness to check")) },
				doc = @doc (
						value = "check if the predicate is in the swagueness base.",
						returns = "true if it is in the base.",
						examples = { @example ("has_swagueness_with_name(\"intersection_of_road_and_river\")") }))
		public Boolean hasSwaguenessName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				final SMentalState tempState = new SMentalState("Swagueness", new SPredicate(predicateName));
				return hasSwagueness(scope, tempState);
			}
			return null;
		}

		@action (
				name = "has_swagueness_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("spatial mental state to check")) },
				doc = @doc (
						value = "check if the spatial mental state is in the swagueness base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestSwaguenessMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final SMentalState temp = new SMentalState("Swagueness", predicateDirect);
			if (predicateDirect != null) { return hasSwagueness(scope, temp); }
			return false;
		}

		public static Boolean removeSwagueness(final IScope scope, final SMentalState pred) {
			return getBase(scope, SWAGUENESS_BASE).remove(pred);
		}

		@action (
				name = "remove_swagueness",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("sk predicate to remove")) },
				doc = @doc (
						value = "removes the sk predicates from the swagueness base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveSwagueness(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState temp = new SMentalState("Swagueness", predicateDirect);
			if (predicateDirect != null) { return removeSwagueness(scope, temp); }
			return false;
		}

		@action (
				name = "remove_swagueness_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to remove")) },
				doc = @doc (
						value = "removes the mental state from the swagueness base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveSwaguenessMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final SMentalState temp = new SMentalState("Swagueness", predicateDirect);
			if (predicateDirect != null) { return removeSwagueness(scope, temp); }
			return false;
		}

		// Peut-être mettre plus tard un replace Swagueness

		@action (
				name = "clear_swaguenesses",
				doc = @doc (
						value = "clear the swagueness base",
						returns = "true if the base is cleared correctly",
						examples = { @example ("") }))
		public Boolean primClearSwagueness(final IScope scope) {
			getBase(scope, SWAGUENESS_BASE).clear();
			return true;
		}

		public static Boolean addIdeal(final IScope scope, final SMentalState predicate) {
			predicate.setSk_owner(scope.getAgent());
			return addToBase(scope, predicate, IDEAL_BASE);
		}

		@action (
				name = "add_ideal",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to add as an ideal")),
						@arg (
								name = "praiseworthiness",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the praiseworthiness value of the ideal")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the ideal")) },
				doc = @doc (
						value = "add a sk predicate in the ideal base.",
						returns = "true it works.",
						examples = { @example ("") }))
		public Boolean primAddIdeal(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final Double po =
					(Double) (scope.hasArg("praiseworthiness") ? scope.getArg("praiseworthiness", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (predicateDirect != null) {
				temp = new SMentalState("Ideal", predicateDirect);
			} else {
				temp = new SMentalState();
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addIdeal(scope, temp);
		}

		@action (
				name = "add_directly_ideal",
				args = { @arg (
						name = "ideal",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("ideal to add in the ideal base")) },
				doc = @doc (
						value = "add the ideal in the ideal base.",
						returns = "true if it is added in the base.",
						examples = { @example ("") }))
		public Boolean primAddDirectlyIdeal(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("ideal") ? scope.getArg("ideal", SMentalStateType.id) : null);
			if (predicateDirect != null && predicateDirect.getModality().equals("Ideal")) {
				predicateDirect.setSk_owner(scope.getAgent());
				return addIdeal(scope, predicateDirect);
			}
			return false;

		}

		@action (
				name = "add_ideal_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to add as an ideal")),
						@arg (
								name = "praiseworthiness",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the praiseworthiness value of the ideal")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the ideal")) },
				doc = @doc (
						value = "add a predicate in the ideal base.",
						returns = "true it works.",
						examples = { @example ("") }))
		public Boolean primAddIdealMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState stateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po =
					(Double) (scope.hasArg("praiseworthiness") ? scope.getArg("praiseworthiness", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (stateDirect != null) {
				temp = new SMentalState("Ideal", stateDirect);
			} else {
				temp = new SMentalState();
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addIdeal(scope, temp);
		}

		@action (
				name = "add_ideal_spatial_knowledge",
				args = { @arg (
						name = "spatial_knowledge",
						type = SpatialKnowledgeType.id,
						optional = true,
						doc = @doc ("spatial_knowledge to add as an ideal")),
						@arg (
								name = "praiseworthiness",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the praiseworthiness value of the ideal")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the ideal")) },
				doc = @doc (
						value = "add a predicate in the ideal base.",
						returns = "true it works.",
						examples = { @example ("") }))
		public Boolean primAddIdealSpatialKnowledge(final IScope scope) throws GamaRuntimeException {
			final SpatialKnowledge stateDirect =
					(SpatialKnowledge) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final Double po =
					(Double) (scope.hasArg("praiseworthiness") ? scope.getArg("praiseworthiness", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (stateDirect != null) {
				temp = new SMentalState("Ideal", stateDirect);
			} else {
				temp = new SMentalState();
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addIdeal(scope, temp);
		}

		@action (
				name = "get_ideal",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to check ad an ideal")) },
				doc = @doc (
						value = "get the ideal about the predicate in the ideal base (if several, returns the first one).",
						returns = "the ideal if it is in the base.",
						examples = { @example ("get_ideal(new_predicate(\"has_water\", true))") }))
		public SMentalState getIdeal(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState pred : getBase(scope, IDEAL_BASE)) {
					if (pred.getSk_predicate() != null && predicateDirect.equals(pred.getSk_predicate())) { return pred; }
				}
			}
			return null;
		}

		@action (
				name = "get_ideal_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = false,
						doc = @doc ("mental state to return")) },
				doc = @doc (
						value = "get the mental state in the ideal base (if several, returns the first one).",
						returns = "the ideal (mental state) if it is in the base.",
						examples = { @example ("get_ideal(new_predicate(\"has_water\", true))") }))
		public SMentalState getIdealMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState pred : getBase(scope, IDEAL_BASE)) {
					if (pred.getSMentalState() != null && predicateDirect.equals(pred.getSMentalState())) { return pred; }
				}
			}
			return null;
		}

		public static Boolean hasIdeal(final IScope scope, final SMentalState predicateDirect) {
			return getBase(scope, IDEAL_BASE).contains(predicateDirect);
		}

		@action (
				name = "has_ideal",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "check if the predicates is in the ideal base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestIdeal(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState temp = new SMentalState("Ideal", predicateDirect);
			if (predicateDirect != null) { return hasIdeal(scope, temp); }
			return false;
		}

		@action (
				name = "has_ideal_with_name",
				args = { @arg (
						name = "name",
						type = IType.STRING,
						optional = false,
						doc = @doc ("name of the predicate to check")) },
				doc = @doc (
						value = "check if the predicate is in the ideal base.",
						returns = "true if it is in the base.",
						examples = { @example ("has_belief_with_name(\"has_water\")") }))
		public Boolean hasIdealName(final IScope scope) throws GamaRuntimeException {
			final String predicateName = (String) (scope.hasArg("name") ? scope.getArg("name", IType.STRING) : null);
			if (predicateName != null) {
				final SMentalState tempState = new SMentalState("Ideal", new SPredicate(predicateName));
				return hasIdeal(scope, tempState);
			}
			return null;
		}

		@action (
				name = "has_ideal_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("mental state to check")) },
				doc = @doc (
						value = "check if the mental state is in the ideal base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestIdealMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final SMentalState temp = new SMentalState("Ideal", predicateDirect);
			if (predicateDirect != null) { return hasIdeal(scope, temp); }
			return false;
		}

		public static Boolean removeIdeal(final IScope scope, final SMentalState pred) {
			return getBase(scope, IDEAL_BASE).remove(pred);
		}

		@action (
				name = "remove_ideal",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to remove")) },
				doc = @doc (
						value = "removes the predicates from the ideal base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveIdeal(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState temp = new SMentalState("Ideal", predicateDirect);
			if (predicateDirect != null) { return removeIdeal(scope, temp); }
			return false;
		}

		@action (
				name = "remove_ideal_mental_state",
				args = { @arg (
						name = "sk_mental_state",
						type = SMentalStateType.id,
						optional = true,
						doc = @doc ("metal state to remove")) },
				doc = @doc (
						value = "removes the mental state from the ideal base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveIdealMentalState(final IScope scope) throws GamaRuntimeException {
			final SMentalState predicateDirect =
					(SMentalState) (scope.hasArg("sk_mental_state") ? scope.getArg("sk_mental_state", SMentalStateType.id) : null);
			final SMentalState temp = new SMentalState("Ideal", predicateDirect);
			if (predicateDirect != null) { return removeIdeal(scope, temp); }
			return false;
		}

		@action (
				name = "clear_ideals",
				doc = @doc (
						value = "clear the ideal base",
						returns = "true if the base is cleared correctly",
						examples = { @example ("") }))
		public Boolean primClearIdeal(final IScope scope) {
			getBase(scope, IDEAL_BASE).clear();
			return true;
		}

		public static Boolean addObligation(final IScope scope, final SMentalState predicate) {
			predicate.setSk_owner(scope.getAgent());
			clearIntention(scope);
			final IAgent agent = scope.getAgent();
			agent.setAttribute(SbdiArchitecture.CURRENT_PLAN, null);
			return addToBase(scope, predicate, OBLIGATION_BASE);
		}

		@action (
				name = "add_obligation",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to add as an obligation")),
						@arg (
								name = "sk_power",
								type = IType.FLOAT,
								optional = true,
								doc = @doc ("the strength value of the obligation")),
						@arg (
								name = "sk_lifetime",
								type = IType.INT,
								optional = true,
								doc = @doc ("the lifetime of the obligation")) },
				doc = @doc (
						value = "add a predicate in the ideal base.",
						returns = "true it works.",
						examples = { @example ("") }))
		public Boolean primAddObligation(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final Double po = (Double) (scope.hasArg("sk_power") ? scope.getArg("sk_power", IType.FLOAT) : null);
			final int life = (int) (scope.hasArg("sk_lifetime") ? scope.getArg("sk_lifetime", IType.INT) : -1);
			SMentalState temp;
			if (predicateDirect != null) {
				temp = new SMentalState("Obligation", predicateDirect);
			} else {
				temp = new SMentalState();
			}
			if (po != null) {
				temp.setSk_power(po);
			}
			if (life > 0) {
				temp.setSk_lifeTime(life);
			}
			temp.setSk_owner(scope.getAgent());
			return addObligation(scope, temp);
		}

		@action (
				name = "get_obligation",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = false,
						doc = @doc ("predicate to return")) },
				doc = @doc (
						value = "get the predicates in the obligation base (if several, returns the first one).",
						returns = "the obligation (mental state) if it is in the base.",
						examples = { @example ("get_obligation(new_predicate(\"has_water\", true))") }))
		public SMentalState getObligation(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			if (predicateDirect != null) {
				for (final SMentalState pred : getBase(scope, OBLIGATION_BASE)) {
					if (pred.getSk_predicate() != null && predicateDirect.equals(pred.getSk_predicate())) { return pred; }
				}
			}
			return null;
		}

		public static Boolean hasObligation(final IScope scope, final SMentalState predicateDirect) {
			return getBase(scope, OBLIGATION_BASE).contains(predicateDirect);
		}

		@action (
				name = "has_obligation",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to check")) },
				doc = @doc (
						value = "check if the predicates is in the obligation base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primTestObligation(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState temp = new SMentalState("Obligation", predicateDirect);
			if (predicateDirect != null) { return hasObligation(scope, temp); }
			return false;
		}

		public static Boolean removeObligation(final IScope scope, final SMentalState pred) {
			return getBase(scope, OBLIGATION_BASE).remove(pred);
		}

		@action (
				name = "remove_obligation",
				args = { @arg (
						name = SK_PREDICATE,
						type = SPredicateType.id,
						optional = true,
						doc = @doc ("predicate to remove")) },
				doc = @doc (
						value = "removes the spredicates from the obligation base.",
						returns = "true if it is in the base.",
						examples = { @example ("") }))
		public Boolean primRemoveObligation(final IScope scope) throws GamaRuntimeException {
			final SPredicate predicateDirect =
					(SPredicate) (scope.hasArg(SK_PREDICATE) ? scope.getArg(SK_PREDICATE, SPredicateType.id) : null);
			final SMentalState temp = new SMentalState("Obligation", predicateDirect);
			if (predicateDirect != null) { return removeObligation(scope, temp); }
			return false;
		} 


		public void updateNormLifetime(final IScope scope) {
			for (final Norm tempNorm : getNorms(scope)) {
				if (tempNorm != null) {
					tempNorm.updateLifeime();
				}
			}
		}

		@Override
		public boolean init(final IScope scope) throws GamaRuntimeException {
			super.init(scope);
			// _consideringScope = scope;
			return true;
		}

		@Override
		public void verifyBehaviors(final ISpecies context) {}

}
