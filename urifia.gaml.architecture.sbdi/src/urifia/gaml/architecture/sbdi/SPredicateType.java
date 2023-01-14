package urifia.gaml.architecture.sbdi;

import java.util.Map;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.precompiler.IConcept;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;
import msi.gaml.types.IType;

@SuppressWarnings("unchecked")
@type(name = "sk_predicate", id = SPredicateType.id, wraps = { SPredicate.class }, concept = { IConcept.TYPE, IConcept.BDI })
@doc("represents a spatial predicate")
public class SPredicateType extends GamaType<SPredicate> {

	public final static int id = IType.AVAILABLE_TYPES + 646654;

	@Override
	public boolean canCastToConst() {
		return true;
	 }

	@SuppressWarnings({ "rawtypes" })
	@Override
	@doc("cast an object as a skpredicate")
	public SPredicate cast(final IScope scope, final Object obj, final Object val, final boolean copy)
			throws GamaRuntimeException {
		if (obj instanceof SPredicate) {
			return (SPredicate) obj;
		}
		if (obj instanceof String) {
			return new SPredicate((String)obj);
		}
		if (obj != null && obj instanceof Map) {
			final Map<String, Object> map = (Map<String, Object>) obj;
			final String the_name = (String) (map.containsKey("name_sk_predicate") ? map.get("name_sk_predicate") : "skpredicate");
			final Map values = (Map) (map.containsKey("name_sk_predicate") ? map.get("values") : null);
			return new SPredicate(the_name, values);
		}
		return null;
	}

	@Override
	public SPredicate getDefault() {
		return null;
	}

}
