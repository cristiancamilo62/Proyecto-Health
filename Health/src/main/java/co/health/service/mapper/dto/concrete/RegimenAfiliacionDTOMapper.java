package co.health.service.mapper.dto.concrete;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilObjeto;
import co.health.service.domain.regimenafiliacion.RegimenAfiliacionDomain;
import co.health.service.dto.RegimenAfiliacionDTO;
import co.health.service.mapper.dto.DTOMapper;


public final class RegimenAfiliacionDTOMapper implements DTOMapper<RegimenAfiliacionDTO, RegimenAfiliacionDomain>{

private static final DTOMapper<RegimenAfiliacionDTO, RegimenAfiliacionDomain> instancia = new RegimenAfiliacionDTOMapper();
	
	private RegimenAfiliacionDTOMapper() {
		super();
	}
	
	@Override
	public final RegimenAfiliacionDomain toDomain(final RegimenAfiliacionDTO dto) {
		if(UtilObjeto.esNulo(dto)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return RegimenAfiliacionDomain.crear(dto.getId(), dto.getTipo(), dto.getCoberturaServicios());
	}

	@Override
	public final RegimenAfiliacionDTO toDTO(final RegimenAfiliacionDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			// TODO:organizar mensajes
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000027);
			throw ServiceHealthException.crear(mensajeUsuario, mensajeTecnico);
		}
		return RegimenAfiliacionDTO.crear(domain.getId(), domain.getTipo(), domain.getCoberturaServicios());
	}
	
	public static final RegimenAfiliacionDomain convertToDomain(final RegimenAfiliacionDTO dto) {
		return instancia.toDomain(dto);
	}
	
	public static final RegimenAfiliacionDTO convertToDTO(final RegimenAfiliacionDomain domain) {
		return instancia.toDTO(domain);
	}
	
}
