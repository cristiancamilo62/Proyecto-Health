package co.health.service.mapper.dto.concrete.support;

import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.crosscutting.util.UtilObjeto;
import co.health.service.domain.paciente.support.InformacionAfiliacionPacienteDomain;
import co.health.service.dto.support.InformacionAfiliacionPacienteDTO;
import co.health.service.mapper.dto.DTOMapper;
import co.health.service.mapper.dto.concrete.EpsDTOMapper;
import co.health.service.mapper.dto.concrete.RegimenAfiliacionDTOMapper;

public final class InformacionAfiliacionPacienteDTOMapper implements DTOMapper<InformacionAfiliacionPacienteDTO, InformacionAfiliacionPacienteDomain>{

	private static final DTOMapper<InformacionAfiliacionPacienteDTO, InformacionAfiliacionPacienteDomain> instancia = new InformacionAfiliacionPacienteDTOMapper();
	
	private InformacionAfiliacionPacienteDTOMapper() {
		super();
	}
	
	@Override
	public final InformacionAfiliacionPacienteDomain toDomain(final InformacionAfiliacionPacienteDTO dto) {
		if(UtilObjeto.esNulo(dto)) {
			throw ServiceHealthException.crear(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004),
					CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000113));
		
		}
		return InformacionAfiliacionPacienteDomain.crear(false,RegimenAfiliacionDTOMapper.convertToDomain(dto.getRegimenAfiliacion()),
				EpsDTOMapper.convertToDomain(dto.getEps()));
	}

	@Override
	public final InformacionAfiliacionPacienteDTO toDTO(final InformacionAfiliacionPacienteDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			throw ServiceHealthException.crear(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000004),
					CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000114));
		
		}
		return InformacionAfiliacionPacienteDTO.crear(false, RegimenAfiliacionDTOMapper.convertToDTO(domain.getRegimenAfiliacion()),
				EpsDTOMapper.convertToDTO(domain.getEps()));
	}
	
	public static final InformacionAfiliacionPacienteDomain convertToDomain(final InformacionAfiliacionPacienteDTO dto) {
		return instancia.toDomain(dto);
	}
	
	public static final InformacionAfiliacionPacienteDTO convertToDTO(final InformacionAfiliacionPacienteDomain domain) {
		return instancia.toDTO(domain);
	}

}
