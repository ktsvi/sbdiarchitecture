package urifia.gaml.architecture.sbdi;

import msi.gama.common.interfaces.IGamlIssue;
import msi.gama.common.interfaces.IKeyword;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.facet;
import msi.gama.precompiler.GamlAnnotations.facets;
import msi.gama.precompiler.GamlAnnotations.inside;
import msi.gama.precompiler.GamlAnnotations.symbol;
import msi.gama.precompiler.IConcept;
import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.compilation.IDescriptionValidator;
import msi.gaml.compilation.annotations.validator;
import msi.gaml.descriptions.IDescription;
import msi.gaml.descriptions.SkillDescription;
import msi.gaml.descriptions.SpeciesDescription;
import msi.gaml.descriptions.StatementDescription;
import msi.gaml.expressions.IExpression;
import msi.gaml.operators.Cast;
import msi.gaml.statements.AbstractStatementSequence;
import msi.gaml.types.IType;
import urifia.gaml.architecture.sbdi.SbdiPlanStatement.SbdiPlanValidator;

@symbol(name = { SbdiArchitecture.PLAN }, kind = ISymbolKind.BEHAVIOR, with_sequence = true, concept = {
		IConcept.BDI })
@inside(kinds = { ISymbolKind.SPECIES, ISymbolKind.MODEL })
@facets(value = { @facet(name = IKeyword.WHEN, type = IType.BOOL, optional = true),
		@facet(name = SbdiArchitecture.FINISHEDWHEN, type = IType.BOOL, optional = true),
		@facet(name = SbdiArchitecture.PRIORITY, type = IType.FLOAT, optional = true),
		@facet(name = IKeyword.NAME, type = IType.ID, optional = true), 
		@facet(name = SbdiPlanStatement.INTENTION, type = SPredicateType.id, optional = true),
		@facet(name = SbdiPlanStatement.SPATIAL_KNOWLEDGE, type = SpatialKnowledgeType.id, optional = true),
		@facet(name = SbdiPlanStatement.THRESHOLD, type = IType.FLOAT, optional = true),
		@facet(name = SbdiArchitecture.INSTANTANEAOUS, type = IType.BOOL, optional = true) }, omissible = IKeyword.NAME)
@validator(SbdiPlanValidator.class)
@doc("define an action plan performed by an agent using the BDI engine")
public class SbdiPlanStatement extends AbstractStatementSequence {

	public static class SbdiPlanValidator implements IDescriptionValidator<StatementDescription> {

		/**
		 * Method validate()
		 * 
		 * @see msi.gaml.compilation.IDescriptionValidator#validate(msi.gaml.descriptions.IDescription)
		 */
		@Override
		public void validate(final StatementDescription description) {
			// Verify that the state is inside a species with fsm control
			final SpeciesDescription species = description.getSpeciesContext();
			final SkillDescription control = species.getControl();
			if (!SbdiArchitecture.class.isAssignableFrom(control.getJavaBase())) {
				description.error("A plan can only be defined in a sbdi architecture species",
						IGamlIssue.WRONG_CONTEXT);
				return;
			}
		}
	}

	public static final String INTENTION = "intention";
	public static final String SPATIAL_KNOWLEDGE = "spatialknowledge";
	public static final String THRESHOLD = "threshold";

	final IExpression _when;
	final IExpression _priority;
	final IExpression _executedwhen;
	final IExpression _instantaneous;
	final IExpression _intention;
	final IExpression _spatialknowledge;
	final IExpression _threshold;

	public IExpression getPriorityExpression() {
		return _priority;
	}

	public IExpression getContextExpression() {
		return _when;
	}

	public IExpression getExecutedExpression() {
		return _executedwhen;
	}

	public IExpression getInstantaneousExpression() {
		return _instantaneous;
	}

	public IExpression getIntentionExpression() {
		return _intention;
	}

	public IExpression getSpatiaKnowledgeExpression() {
		return _spatialknowledge;
	}

	public IExpression getThreshold() {
		return _threshold;
	}

	public SbdiPlanStatement(final IDescription desc) {
		super(desc);
		_when = getFacet(IKeyword.WHEN);
		_priority = getFacet(SbdiArchitecture.PRIORITY);
		_executedwhen = getFacet(SbdiArchitecture.FINISHEDWHEN);
		_instantaneous = getFacet(SbdiArchitecture.INSTANTANEAOUS);
		_intention = getFacet(SbdiPlanStatement.INTENTION);
		_spatialknowledge = getFacet(SbdiPlanStatement.SPATIAL_KNOWLEDGE);
		_threshold = getFacet(SbdiPlanStatement.THRESHOLD);
		setName(desc.getName());
	}

	@Override
	public Object privateExecuteIn(final IScope scope) throws GamaRuntimeException {
		if (_when == null || Cast.asBool(scope, _when.value(scope))) {
			return super.privateExecuteIn(scope);
		}
		return null;
	}

	public Double computePriority(final IScope scope) throws GamaRuntimeException {
		return Cast.asFloat(scope, _priority.value(scope));
	}
}
