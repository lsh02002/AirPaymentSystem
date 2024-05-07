package com.github.supercodingspring.repository.passenger;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PassengerJdbcTemplateDao implements PassengerReposiotry {

    private final JdbcTemplate template;

    public PassengerJdbcTemplateDao(@Qualifier("jdbcTemplate2") JdbcTemplate template) {
        this.template = template;
    }

    static RowMapper<Passenger> passengerRowMapper = (((rs, rowNum) ->
            new Passenger(
                    rs.getInt("passenger_id"),
                    rs.getInt("user_id"),
                    rs.getNString("passport_num"))
    ));
    @Override
    public Optional<Passenger> findPassengerByUserId(Integer userId) {
        Passenger passenger = template.queryForObject("SELECT * FROM passenger WHERE user_id = ?", passengerRowMapper, userId);
        return Optional.ofNullable(passenger);
    }
}
