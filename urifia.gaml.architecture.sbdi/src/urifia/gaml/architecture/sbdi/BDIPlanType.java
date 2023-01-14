package urifia.gaml.architecture.sbdi;


import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.precompiler.IConcept;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;
import msi.gaml.types.IType;


@SuppressWarnings("unchecked")
@type(name = "BDIPlan", id = BDIPlanType.id, wraps = { BDIPlan.class }, concept = { IConcept.TYPE, IConcept.BDI })
@doc("a type representing a plan for the SBDI engine")
public class BDIPlanType extends GamaType<BDIPlan> {

	public final static int id = IType.AVAILABLE_TYPES + 546655;

	@Override
	public boolean canCastToConst() {
		return true;
	}

	@Override
	@doc("cast an object into a SBDIPlan if it is an instance of a SBDIPlan")
	public BDIPlan cast(final IScope scope, final Object obj, final Object val, final boolean copy)
			throws GamaRuntimeException {
		if (obj instanceof BDIPlan) {
			return (BDIPlan) obj;
		}
		/*
		 * if ( obj != null && obj instanceof Map ) { Map<String, Object> map =
		 * (Map<String, Object>) obj; String nm = (String)
		 * (map.containsKey("name") ? map.get("name") : "predicate"); Double pr
		 * = (Double) (map.containsKey("priority") ? map.get("priority") : 1.0);
		 * Map values = (Map) (map.containsKey("name") ? map.get("values") :
		 * null); return new Predicate(nm, pr, values); }
		 */
		return null;
	}

	@Override
	public BDIPlan getDefault() {
		return null;
	}

}

