package co.health.service.businesslogic.concrete.tipoidentificacion;

import java.util.List;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilObjeto;
import co.health.data.dao.TipoIdentificacionDAO;
import co.health.data.dao.daofactory.DAOFactory;
import co.health.data.entity.TipoIdentificacionEntity;
import co.health.service.businesslogic.UseCaseRetorno;
import co.health.service.domain.tipoidentificacion.TipoIdentificacionDomain;
import co.health.service.dto.TipoIdentificacionDTO;
import co.health.service.dto.support.TipoServicioDTO;
import co.health.service.mapper.entity.concrete.TipoIdentificacionEntityMapper;

public final class ConsultarTipoIdentificacionUseCase implements UseCaseRetorno<TipoIdentificacionDomain, List<TipoIdentificacionEntity>>{
	
	private DAOFactory factoria;
	
	public ConsultarTipoIdentificacionUseCase(final DAOFactory factoria) {
		setFactoria(factoria);
	}
	

	@Override
	public final List<TipoIdentificacionEntity> executeRetorno(final TipoIdentificacionDomain domain) {
		var entity = TipoIdentificacionEntityMapper.convertToEntity(domain);
		if(getTipoIdentificacionDAO().consultar(entity).isEmpty()) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000244);
			throw ServiceHealthException.crear(mensajeUsuario);
		}
		return getTipoIdentificacionDAO().consultar(entity);

	}
	
	private final TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return getFactoria().obtenerTipoIdentificacionDAO();
	}

	private final DAOFactory getFactoria() {
		return factoria;
	}

	private final void setFactoria(final DAOFactory factoria) {
		if(UtilObjeto.esNulo(factoria)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000244);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000243);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		this.factoria = factoria;
	}



	
	

}
