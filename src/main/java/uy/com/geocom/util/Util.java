package uy.com.geocom.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;

import uy.com.geocom.exception.GeocomRESTException;


public class Util {
	
	public static LocalDateTime stringToLocalDateTime(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return dateTime;
	}

    public static java.util.Date stringISOToDateTime(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date d = format.parse(date);
            return d;
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static java.util.Date stringToDateTime(String date) throws GeocomRESTException {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            java.util.Date d = format.parse(date);
            return d;
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            throw new GeocomRESTException(HttpStatus.BAD_REQUEST, "Formato de fecha inválido debe ser dd-MM-yyyy");
        }
    }

    public static java.util.Date stringToWholeDateTime(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            java.util.Date d = format.parse(date);
            return d;
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String dateTimeToString(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return formatter.format(date);
    }

    public static String dateTimeISOToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


}
