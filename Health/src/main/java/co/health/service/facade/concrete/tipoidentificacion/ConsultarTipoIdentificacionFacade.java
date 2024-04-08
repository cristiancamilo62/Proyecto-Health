package co.health.service.facade.concrete.tipoidentificacion;

import java.util.ArrayList;
import java.util.List;

import co.health.crosscutting.exception.HealthException;
import co.health.crosscutting.exception.concrete.ServiceHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;
import co.health.data.dao.daofactory.DAOFactory;
import co.health.data.dao.daofactory.TipoDAOFactory;
import co.health.data.entity.PacienteEntity;
import co.health.data.entity.TipoIdentificacionEntity;
import co.health.service.businesslogic.concrete.paciente.ConsultarPacienteUseCase;
import co.health.service.businesslogic.concrete.tipoidentificacion.ConsultarTipoIdentificacionUseCase;
import co.health.service.domain.paciente.PacienteDomain;
import co.health.service.domain.paciente.rules.PacienteValidationRule;
import co.health.service.domain.tipoidentificacion.rules.TipoIdentificacionValidationRule;
import co.health.service.dto.TipoIdentificacionDTO;
import co.health.service.facade.FacadeRetorno;
import co.health.service.mapper.dto.concrete.PacienteDTOMapper;
import co.health.service.mapper.dto.concrete.TipoIdentificacionDTOMapper;

public final class ConsultarTipoIdentificacionFacade implements FacadeRetorno<TipoIdentificacionDTO, List<TipoIdentificacionEntity>>{

	@Override
	public final List<TipoIdentificacionEntity> executeRetorno(final TipoIdentificacionDTO dto) {
		List<TipoIdentificacionEntity> resultados = new ArrayList<>();

        final var domain = TipoIdentificacionDTOMapper.convertToDomain(dto);
        TipoIdentificacionValidationRule.ejecutarValidacion(domain);

        final DAOFactory daoFactory = DAOFactory.obtenerDAOFactory(TipoDAOFactory.SQLSERVER);

        try {
            daoFactory.iniciarTransaccion();

            var useCase = new ConsultarTipoIdentificacionUseCase(daoFactory);
            resultados = useCase.executeRetorno(domain);

            daoFactory.confirmarTransaccion();
        } catch (final HealthException exception) {
            daoFactory.cancelarTransacion();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.cancelarTransacion();
            var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000244);
            var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000245);
            throw ServiceHealthException.crear(mensajeUsuario,mensajeTecnico);
        } finally {
            daoFactory.cerrarConexion();
        }
        return resultados;
	}

}
