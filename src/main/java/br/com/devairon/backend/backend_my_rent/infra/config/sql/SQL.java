package br.com.devairon.backend.backend_my_rent.infra.config.sql;

public abstract class SQL {
    //TODO: fazer as devidas correções para a consulta
    public static final String GET_PROPERTY_BY_ADDRESS = "SELECT p FROM property WHERE addressId = ?";
}
