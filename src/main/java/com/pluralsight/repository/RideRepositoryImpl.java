package com.pluralsight.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {

		List<Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {

		//SimpleJdbcInsert way of doing things, like ORM

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");

		insert.setTableName("ride");
		insert.setColumnNames(columns);

		Map<String,Object> data = new HashMap<>();
		data.put("name",ride.getName());
		data.put("duration",ride.getDuration());

		insert.setGeneratedKeyName("id");

		Number id = insert.executeAndReturnKey(data);

		return getRide(id.intValue());

	}

	private Ride getRide(int id) {
		Ride ride = jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
		return ride;
	}


}
