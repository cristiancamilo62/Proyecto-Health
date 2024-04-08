package co.health.service.domain.paciente.rules;

import co.health.service.domain.ValidationRule;
import co.health.service.domain.eps.rules.NombreEpsValidationRule;
import co.health.service.domain.paciente.support.InformacionAfiliacionPacienteDomain;
import co.health.service.domain.regimenafiliacion.rules.TipoRegimenAfiliacionValidationRule;

public final class InformacionAfiliacionPacienteValidationRule implements ValidationRule<InformacionAfiliacionPacienteDomain>{
	
	private static final ValidationRule<InformacionAfiliacionPacienteDomain> instancia = new InformacionAfiliacionPacienteValidationRule();
	
	private   InformacionAfiliacionPacienteValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final InformacionAfiliacionPacienteDomain dato) {
		instancia.validar(dato);
	}

	@Override
	public void validar(final InformacionAfiliacionPacienteDomain dato) {
		NombreEpsValidationRule.ejecutarValidacion(dato.getEps().getNombre());
		TipoRegimenAfiliacionValidationRule.ejecutarValidacion(dato.getRegimenAfiliacion().getTipo());
	}
}
