package co.health.service.businesslogic.concrete.agenda;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilObjeto;
import co.health.data.dao.AgendaDAO;
import co.health.data.dao.daofactory.DAOFactory;
import co.health.service.businesslogic.UseCase;
import co.health.service.domain.agenda.AgendaDomain;

public final class EliminarAgendaUseCase implements UseCase<AgendaDomain>{

	private DAOFactory factoria;

	public EliminarAgendaUseCase(final DAOFactory factoria) {
		setFactoria(factoria);
	}
	
	@Override
	public void execute(AgendaDomain domain) {
		// TODO Auto-generated method stub
		
	}
	
	private final DAOFactory getFactoria() {
		return factoria;
	}
	
	private final AgendaDAO getAgendaDAO() {
		return getFactoria().obtenerAgendaDAO();
	}

	private final void setFactoria(final DAOFactory factoria) {
		if(UtilObjeto.esNulo(factoria)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000239);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000240);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		this.factoria = factoria;
	}


}
