package co.health.service.mapper.dto.concrete;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilObjeto;
import co.health.service.domain.eps.EpsDomain;
import co.health.service.dto.EpsDTO;
import co.health.service.mapper.dto.DTOMapper;


public final class EpsDTOMapper implements DTOMapper<EpsDTO, EpsDomain> {
	
private static final DTOMapper<EpsDTO, EpsDomain> instancia = new EpsDTOMapper();
	
	private EpsDTOMapper() {
		super();
	}
	
	@Override
	public final EpsDomain toDomain(final EpsDTO dto) {
		if(UtilObjeto.esNulo(dto)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return EpsDomain.crear(dto.getId(),dto.getNombre());
	}

	@Override
	public final EpsDTO toDTO(final EpsDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return EpsDTO.crear(domain.getId(),domain.getNombre());
	}
	
	public static final EpsDomain convertToDomain(final EpsDTO dto) {
		return instancia.toDomain(dto);
	}
	
	public static final EpsDTO convertToDTO(final EpsDomain domain) {
		return instancia.toDTO(domain);
	}

}
