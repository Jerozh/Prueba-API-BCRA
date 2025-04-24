para poder utilizar tienen que buscar una apikey en la pagina: https://estadisticasbcra.com/api/registracion 
agregarla en sus variables de entorno como API_KEY.

url disponibles para prueba: 
http://localhost:8080/api/bcra/ultimo -> obtener el valor del icl actualizado
http://localhost:8080/api/bcra/datos -> historico de icl
http://localhost:8080/api/bcra/alquiler-ajustado?alquilerBase=100000&fechaInicio=2025-01-01&mesesEntreAjustes=3 -> forma de calcular la actualizacion automatica de un valor de alquiler base 
usando el icl y cantidad de 3 meses 
