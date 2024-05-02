package com.github.supercodingspring.repository.passenger;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerJdbcTemplateDao implements PassengerReposiotry {

    private final JdbcTemplate template;

    public PassengerJdbcTemplateDao(@Qualifier("jdbcTemplate") JdbcTemplate template) {
        this.template = template;
    }

    static RowMapper<Passenger> passengerRowMapper = (((rs, rowNum) ->
            new Passenger(
                    rs.getInt("passenger_id"),
                    rs.getInt("user_id"),
                    rs.getNString("passport_num"))
    ));
    @Override
    public Passenger findPassengerByUserId(Integer userId) {
        return template.queryForObject("SELECT * FROM passenger WHERE user_id = ?", passengerRowMapper, userId);
    }
}
