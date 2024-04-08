package co.health.service.mapper.entity.concrete;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilObjeto;
import co.health.data.entity.EpsEntity;
import co.health.service.domain.eps.EpsDomain;
import co.health.service.mapper.entity.EntityMapper;

public final class EpsEntityMapper implements EntityMapper<EpsEntity, EpsDomain> {

	private static final EntityMapper<EpsEntity, EpsDomain> instancia = new EpsEntityMapper();
	
	private EpsEntityMapper() {
		super();
	}
	
	@Override
	public final EpsDomain toDomain(final EpsEntity entity) {
		if(UtilObjeto.esNulo(entity)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return EpsDomain.crear(entity.getId(),entity.getNombre());
	}

	@Override
	public final EpsEntity toEntity(final EpsDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return EpsEntity.crear(domain.getId(),domain.getNombre());
	}
	
	public static final EpsDomain convertToDomain(final EpsEntity entity) {
		return instancia.toDomain(entity);
	}
	
	public static final EpsEntity convertToEntity(final EpsDomain domain) {
		return instancia.toEntity(domain);
	}

}
