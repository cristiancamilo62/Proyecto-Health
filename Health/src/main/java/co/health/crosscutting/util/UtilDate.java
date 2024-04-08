package co.health.crosscutting.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Pattern;

import co.health.crosscutting.exception.concrete.CrosscuttingHealthException;
import co.health.crosscutting.messages.CatalogoMensajes;
import co.health.crosscutting.messages.enumerator.CodigoMensaje;

public final class UtilDate {
	private static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	private static final String PATRON_FECHA = "yyyy-MM-dd HH:mm:ss";
	private static final String PATTERN_SOLO_DIGITOS = "\\d{4}-\\d{2}-\\d{2}";
	private static final String FECHA_DEFECTO = "2999-12-31";
	private static final String FECHA_DEFECTO_HORA = "3000-01-12 00:00:00";
	
	

    
	private UtilDate() {
		super();
	}
	

	public static Date crearFechaPorDefecto() {
		try{
        var fechaUtil = formato.parse(FECHA_DEFECTO);
        return new Date(fechaUtil.getTime());
    	
		} catch (final ParseException e) {
	        String mensajeUsuario = "Se ha producido un error al crear la fecha por defecto.";
	        String mensajeTecnico = "Se ha producido un error de formato al analizar la fecha por defecto. Asegúrate de que la cadena tenga el formato 'yyyy-MM-dd'.";
	        throw  CrosscuttingHealthException.crear(mensajeUsuario,mensajeTecnico);
		}
	}
	
	public static final LocalDateTime obtenerFechaDefectoEnTipoLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATRON_FECHA);
        return LocalDateTime.parse(FECHA_DEFECTO_HORA, formatter);
    }
	 
    public static final boolean esFechaNula(final Date fecha) {
        return UtilObjeto.esNulo(fecha);
    }

    public static final boolean cumpleFormatoFecha(final Date fecha) {
        return  Pattern.matches(PATTERN_SOLO_DIGITOS, formato.format(fecha));
	}

    public static final  boolean tieneValorPorDefecto(final Date fecha) {
        return fecha.equals(crearFechaPorDefecto());
    }
    
    public static boolean validarFecha(final LocalDateTime fecha) {
        try {
            var formatter = DateTimeFormatter.ofPattern(PATRON_FECHA);
            var cadenaFormateada = fecha.format(formatter);

            return fecha.equals(LocalDateTime.parse(cadenaFormateada, formatter));
        } catch (final Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000139);
            var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000140);
            throw  CrosscuttingHealthException.crear(mensajeUsuario, mensajeTecnico);
        }
    }
   
    public static boolean validarFechaActual(final Date fecha) {
    	
    	if(!UtilDate.esFechaNula(fecha)) {
    		
        Date fechaHoraActual = (Date) Calendar.getInstance().getTime();

        try {
            return fecha.before(fechaHoraActual);
        } catch (final Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000246);
            var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000247);
            throw CrosscuttingHealthException.crear(mensajeUsuario,mensajeTecnico);
        }
    }
    	return false;
	}
    
}
