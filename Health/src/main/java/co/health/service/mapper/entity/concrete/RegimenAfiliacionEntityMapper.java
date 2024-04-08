package co.health.service.mapper.entity.concrete;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilObjeto;
import co.health.data.entity.RegimenAfiliacionEntity;
import co.health.service.domain.regimenafiliacion.RegimenAfiliacionDomain;

import co.health.service.mapper.entity.EntityMapper;

public final class RegimenAfiliacionEntityMapper implements EntityMapper<RegimenAfiliacionEntity, RegimenAfiliacionDomain>{

	private static final EntityMapper<RegimenAfiliacionEntity, RegimenAfiliacionDomain> instancia = new RegimenAfiliacionEntityMapper();
	
	private RegimenAfiliacionEntityMapper() {
		super();
	}
	
	@Override
	public final RegimenAfiliacionDomain toDomain(final RegimenAfiliacionEntity entity) {
		if(UtilObjeto.esNulo(entity)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return RegimenAfiliacionDomain.crear(entity.getId(), entity.getTipo(), entity.getCoberturaServicios());
	}

	@Override
	public final RegimenAfiliacionEntity toEntity(final RegimenAfiliacionDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return RegimenAfiliacionEntity.crear(domain.getId(), domain.getTipo(), domain.getCoberturaServicios());
	}
	
	public static final RegimenAfiliacionDomain convertToDomain(final RegimenAfiliacionEntity entity) {
		return instancia.toDomain(entity);
	}
	
	public static final RegimenAfiliacionEntity convertToEntity(final RegimenAfiliacionDomain domain) {
		return instancia.toEntity(domain);
	}

}
