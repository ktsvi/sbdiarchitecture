package urifia.gaml.architecture.sbdi;

import java.util.List;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.population.IPopulation;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.skill;
import msi.gama.precompiler.IConcept;
import msi.gama.runtime.IScope;
import msi.gama.runtime.concurrent.GamaExecutorService;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.descriptions.ConstantExpressionDescription;
import msi.gaml.descriptions.IDescription;
import msi.gaml.expressions.IExpression;
import msi.gaml.operators.Cast;
import msi.gaml.operators.Maths;
import msi.gaml.statements.AbstractStatement;
import msi.gaml.statements.IStatement;

@skill (
		name = SbdiArchitectureParallel.PARALLEL_BDI,
		concept = { IConcept.BDI, IConcept.ARCHITECTURE })
@doc ("compute the sbdi architecture in parallel")
@SuppressWarnings ({ "unchecked", "rawtypes" })
public class SbdiArchitectureParallel extends SbdiArchitecture {

	public static final String PARALLEL_BDI = "parallel_bdi";
	IExpression parallel = ConstantExpressionDescription.TRUE_EXPR_DESCRIPTION;

	public class UpdateSpatialKnowledges extends AbstractStatement {

		public UpdateSpatialKnowledges(IDescription desc) {
			super(desc);
		}

		@Override
		protected Object privateExecuteIn(IScope scope) throws GamaRuntimeException {
			// computeSpatialKnowledges(scope);
			return null;
		}

	}

/*	public class UpdateSocialLinks extends AbstractStatement {

		public UpdateSocialLinks(IDescription desc) {
			super(desc);
		}

		@Override
		protected Object privateExecuteIn(IScope scope) throws GamaRuntimeException {
			updateSocialLinks(scope);
			return null;
		}

	}   */

	public class UpdateSpatialKnowledgesPower extends AbstractStatement {

		public UpdateSpatialKnowledgesPower(IDescription desc) {
			super(desc);
		}

		@Override
		protected Object privateExecuteIn(IScope scope) throws GamaRuntimeException {
			updateSpatialKnowledgePower(scope);
			return null;
		}

	}

	public class UpdateLifeTimePredicates extends AbstractStatement {

		public UpdateLifeTimePredicates(IDescription desc) {
			super(desc);
		}

		@Override
		protected Object privateExecuteIn(IScope scope) throws GamaRuntimeException {
			updateLifeTimePredicates(scope);
			return null;
		}

	}

	@Override
	public void preStep(final IScope scope, IPopulation<? extends IAgent> gamaPopulation) {
		final IExpression schedule = gamaPopulation.getSpecies().getSchedule();
		final List<? extends IAgent> agents = 
				schedule == null ? gamaPopulation : Cast.asList(scope, schedule.value(scope));

		GamaExecutorService.execute(scope, new UpdateLifeTimePredicates(null), agents, parallel);
		GamaExecutorService.execute(scope, new UpdateSpatialKnowledgesPower(null), agents, parallel);

		if (_reflexes != null)
			for (final IStatement r : _reflexes) {
				if (!scope.interrupted()) {
					GamaExecutorService.execute(scope, r, agents, ConstantExpressionDescription.FALSE_EXPR_DESCRIPTION);
				}
			}

		if (_perceptionNumber > 0) {
			for (int i = 0; i < _perceptionNumber; i++) {
				if (!scope.interrupted()) {
					PerceiveStatement statement = _perceptions.get(i);
					IExpression par = statement.getParallel() == null ? parallel : statement.getParallel();
					GamaExecutorService.execute(scope, statement, agents, par);
				}
			}
		}
		if (_rulesNumber > 0) {
			for (int i = 0; i < _rulesNumber; i++) {
				RuleStatement statement = _rules.get(i);
				IExpression par = statement.getParallel() == null ? parallel : statement.getParallel();
				GamaExecutorService.execute(scope, statement, agents, par);
			}
		}
/*
		if (_lawsNumber > 0) {
			for (int i = 0; i < _lawsNumber; i++) {
				LawStatement statement = _laws.get(i);
				IExpression par = statement.getParallel() == null ? parallel : statement.getParallel();
				GamaExecutorService.execute(scope, statement, agents, par);
			} */
		}  
/*
		// GamaExecutorService.execute(scope, new UpdateEmotions(null), agents,parallel) ;
		GamaExecutorService.execute(scope, new UpdateSocialLinks(null), agents, parallel);
		if (_copingNumber > 0) {
			for (int i = 0; i < _copingNumber; i++) {
				CopingStatement statement = _coping.get(i);
				IExpression par = statement.getParallel() == null ? parallel : statement.getParallel();
				GamaExecutorService.execute(scope, statement, agents, par);
			}
		}
	}
*/
	@Override
	public Object executeOn(final IScope scope) throws GamaRuntimeException {
		final Boolean use_personality = scope.hasArg(USE_PERSONALITY) ? scope.getBoolArg(USE_PERSONALITY)
				: (Boolean) scope.getAgent().getAttribute(USE_PERSONALITY);
/*		if (use_personality) {
			Double expressivity = (Double) scope.getAgent().getAttribute(EXTRAVERSION);
			Double neurotisme = (Double) scope.getAgent().getAttribute(NEUROTISM);
			Double conscience = (Double) scope.getAgent().getAttribute(CONSCIENTIOUSNESS);
			Double agreeableness = (Double) scope.getAgent().getAttribute(AGREEABLENESS);
			scope.getAgent().setAttribute(CHARISMA, expressivity);
			scope.getAgent().setAttribute(RECEPTIVITY, 1 - neurotisme);
			scope.getAgent().setAttribute(PERSISTENCE_COEFFICIENT_PLANS, Maths.sqrt(scope, conscience));
			scope.getAgent().setAttribute(PERSISTENCE_COEFFICIENT_INTENTIONS, Maths.sqrt(scope, conscience));
			scope.getAgent().setAttribute(OBEDIENCE, Maths.sqrt(scope, (conscience + agreeableness) * 0.5));
		}  */
		// return executePlans(scope);
		Object result = executePlans(scope);
		if (!scope.getAgent().dead()) {
			// Activer la violation des normes
			//updateNormViolation(scope);
			// Mettre à jour le temps de vie des normes
			updateNormLifetime(scope);

			// Part that manage the lifetime of predicates
			// if(result!=null){
			// updateLifeTimePredicates(scope);
			// updateEmotionsIntensity(scope);
			// }
		}
		return result;
	}

}

