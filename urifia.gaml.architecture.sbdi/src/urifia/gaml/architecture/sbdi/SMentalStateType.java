package urifia.gaml.architecture.sbdi;

import msi.gama.precompiler.IConcept;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;
import msi.gaml.types.IType;

@SuppressWarnings("unchecked")
@type(name = "smental_state", id = SMentalStateType.id, wraps = { SMentalState.class }, concept = { IConcept.TYPE, IConcept.BDI })
@doc("a type representing a spatial mental state")
public class SMentalStateType extends GamaType<SMentalState>{

	
	public final static int id = IType.AVAILABLE_TYPES + 546658;
	
	@Override
	public boolean canCastToConst() {
		return true;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	@doc("cast an object as a mental state if it is an instance o a mental state")
	public SMentalState cast(final IScope scope, final Object obj, final Object val, final boolean copy)
			throws GamaRuntimeException {
		if (obj instanceof SMentalState) {
			return (SMentalState) obj;
		}
		if (obj instanceof String) {
			return new SMentalState((String) obj);
		}
		return null;
	}

	@Override
	public SMentalState getDefault() {
		return null;
	}
	
}