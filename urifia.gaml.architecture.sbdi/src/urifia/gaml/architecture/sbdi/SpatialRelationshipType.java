package urifia.gaml.architecture.sbdi;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.precompiler.IConcept;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;
import msi.gaml.types.IType;

@SuppressWarnings("unchecked")
@type(name = "spatialrelationship", id = SpatialRelationshipType.id, wraps = { SpatialKnowledge.class }, concept = { IConcept.TYPE, IConcept.BDI })
@doc("represents the type spatialrelationship")

public class SpatialRelationshipType extends GamaType<SpatialRelationship> {

	public final static int id = IType.AVAILABLE_TYPES + 546657;

	@Override
	public boolean canCastToConst() {
		return true;
	}

	@Override
	@doc("cast an object instance of spatial_relationship as an spatial_relationship")
	public SpatialRelationship cast(final IScope scope, final Object obj, final Object val, final boolean copy)
			throws GamaRuntimeException {
		if (obj instanceof SpatialKnowledge) {
			return (SpatialRelationship) obj;
		}
		return null;
	}

	@Override
	public SpatialRelationship getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
