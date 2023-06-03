package com.spring.boot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String status = "";

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String add(Student std) {
		String query1 = "select * from student where SID ='" + std.getSid() + "'";
		List<Student> stdList = jdbcTemplate.query(query1, (resultSet, i) -> {
			Student saveStudent = new Student();
			saveStudent.setSid(resultSet.getString("SID"));
			saveStudent.setSname(resultSet.getString("SNAME"));
			saveStudent.setSaddr(resultSet.getString("SADDR"));
			return saveStudent;
		});
		if (stdList.isEmpty()) {
			String query2 = "insert into student values('" + std.getSid() + "','" + std.getSname() + "','"
					+ std.getSaddr() + "')";
			int rowCount = jdbcTemplate.update(query2);
			if (rowCount == 1) {
				status = "success";
			} else {
				status = "failure";
			}
		} else {
			status = "existed";
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student sto = null;
		String query1 = "select * from student where SID ='" + sid + "'";
		List<Student> stdList = jdbcTemplate.query(query1, (resultSet, i) -> {
			Student std = new Student();
			std.setSid(resultSet.getString("SID"));
			std.setSname(resultSet.getString("SNAME"));
			std.setSaddr(resultSet.getString("SADDR"));
			return std;
		});
		if (stdList.isEmpty()) {
			sto = null;
		} else {
			sto = stdList.get(0);
		}
		return sto;
	}

	@Override
	public String update(Student sto) {
		String query = "update student set SNAME = '" + sto.getSname() + "',SADDR ='" + sto.getSaddr()
				+ "' where SID = '" + sto.getSid() + "'";
		int rowCount = jdbcTemplate.update(query);
		if (rowCount == 1) {
			status = "success";
		} else {
			status = "failure";
		}

		return status;
	}

	@Override
	public String delete(String sid) {
		String query1 = "select * from student where SID ='" + sid + "'";
		List<Student> stdList = jdbcTemplate.query(query1, (resultSet, i) -> {
			Student std = new Student();
			std.setSid(resultSet.getString("SID"));
			std.setSname(resultSet.getString("SNAME"));
			std.setSaddr(resultSet.getString("SADDR"));
			return std;
		});
		if (stdList.isEmpty()) {
			status = "notexisted";
		} else {
			String query2 = "delete from student where SID = '" + sid + "'";
			int rowCount = jdbcTemplate.update(query2);
			if (rowCount == 1) {
				status = "success";
			} else {
				status = "failure";
			}
		}
		return status;
	}

}
