package co.health.service.domain.agenda.rules;

import java.time.LocalDateTime;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilDate;
import co.health.crosscutting.util.UtilObjeto;
import co.health.service.domain.ValidationRule;
import co.health.service.domain.agenda.AgendaDomain;

public class FechaFinAgendaValidationRule implements ValidationRule<AgendaDomain>{
	private static final ValidationRule<AgendaDomain> instancia = new FechaFinAgendaValidationRule();
	
	private FechaFinAgendaValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final AgendaDomain dato) {
		instancia.validar(dato);
	}


	@Override
	public final void validar(final AgendaDomain dato) {
		validarObligatoriedad(dato.getFechaFin());
		validarFormato(dato.getFechaFin());
		validarHora(dato.getFechaInicio(), dato.getFechaFin());
		
	}

	private final void validarObligatoriedad(final LocalDateTime dato) {
		if(UtilObjeto.esNulo(dato)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000199);
			throw ServiceHealthException.crear(mensajeUsuario);
		}
	}

	private final void validarFormato(final LocalDateTime dato) {
		if(!UtilDate.validarFecha(dato)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000200);
			throw ServiceHealthException.crear(mensajeUsuario);
		}
	}
	
	private final void validarHora(final LocalDateTime datoInicio, final LocalDateTime datoFin) {
		
		if(!(datoFin.isAfter(datoInicio))) {	
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000242);
			throw ServiceHealthException.crear(mensajeUsuario);
		}
		
	}
	
}
