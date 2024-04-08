package co.health.service.businesslogic.validator.concrete.tipoidentificacion;

import co.health.service.businesslogic.validator.Validator;
import co.health.service.domain.tipoidentificacion.TipoIdentificacionDomain;

public final class ConsultarTipoIdentificacionValidator implements Validator<TipoIdentificacionDomain>{

	private static final Validator<TipoIdentificacionDomain> instancia = new ConsultarTipoIdentificacionValidator();
	
	private ConsultarTipoIdentificacionValidator() {
		super();
	}
	
	public static final void ejecutarValidacion(final TipoIdentificacionDomain data) {
		instancia.execute(data);
	}
	
	@Override
	public void execute(final TipoIdentificacionDomain dato) {
		
	}

}

