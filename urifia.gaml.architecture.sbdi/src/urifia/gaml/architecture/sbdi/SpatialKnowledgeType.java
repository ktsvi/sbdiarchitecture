package urifia.gaml.architecture.sbdi;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.precompiler.IConcept;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;
import msi.gaml.types.IType;

@SuppressWarnings("unchecked")
@type(name = "spatialknowledge", id = SpatialKnowledgeType.id, wraps = { SpatialKnowledge.class }, concept = { IConcept.TYPE, IConcept.BDI })
@doc("represents the type SpatialKnowledge ")

public class SpatialKnowledgeType extends GamaType<SpatialKnowledge> {

	public final static int id = IType.AVAILABLE_TYPES + 546656;

	@Override
	public boolean canCastToConst() {
		return true;
	}

	@Override
	@doc("cast an object instance of spatial_knowledge as an spatial_knowledge")
	public SpatialKnowledge cast(final IScope scope, final Object obj, final Object val, final boolean copy)
			throws GamaRuntimeException {
		if (obj instanceof SpatialKnowledge) {
			return (SpatialKnowledge) obj;
		}
		return null;
	}

	@Override
	public SpatialKnowledge getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
