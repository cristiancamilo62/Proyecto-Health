package co.health.service.businesslogic.validator.concrete.agenda;

import co.health.service.businesslogic.validator.Validator;
import co.health.service.domain.agenda.AgendaDomain;
import co.health.service.domain.agenda.rules.IdAgendaValidationRule;

public final class EliminarAgendaValidator implements Validator<AgendaDomain>{
	
	private static final Validator<AgendaDomain> instancia = new EliminarAgendaValidator();
	
	private  EliminarAgendaValidator() {
		super();
	}
	
	public static final void ejecutarValidacion(final AgendaDomain data) {
		instancia.execute(data);
	}
	
	@Override
	public void execute(final AgendaDomain dato) {
		IdAgendaValidationRule.ejecutarValidacion(dato.getId());
	}

}
