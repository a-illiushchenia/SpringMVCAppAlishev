package com.ailliushcheia.springmvcappalishev.dao;

import com.ailliushcheia.springmvcappalishev.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        //вариант со своим RowMapper
//        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
        //вариант с библиотечным RowMapper
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        //вариант со своим RowMapper
//        return jdbcTemplate.query("SELECT * FROM Person where id=?", new PersonMapper(), id)
//                .stream().findAny().orElse(null);
        return jdbcTemplate.query("SELECT * FROM Person where id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Person person){

        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatePerson){

        jdbcTemplate.update("UPDATE Person SET name = ?, age = ?, email = ? WHERE id = ?",
                updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }
}
