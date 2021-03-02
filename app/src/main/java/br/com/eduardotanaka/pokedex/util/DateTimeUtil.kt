package br.com.eduardotanaka.pokedex.util

import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtil {

    private const val DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy"
    private const val DEFAULT_LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

    val defaultFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)
    val localDateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATE_TIME_FORMAT)

}