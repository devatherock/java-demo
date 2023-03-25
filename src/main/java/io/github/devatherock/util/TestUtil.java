package io.github.devatherock.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TestUtil {

	public static String sayHello() {
		return "Hello";
	}

	public static Set<Employee> transformResultSet(ResultSet resultSet) throws SQLException {
		Set<Employee> employees = new LinkedHashSet<>();
		Map<BigInteger, Employee> employeeMap = new HashMap<>();
		Map<BigInteger, BigInteger> employeeToManagerMap = new HashMap<>();

		while (resultSet.next()) {
			BigInteger id = BigInteger.valueOf(resultSet.getInt(1));
			FullName fullName = new FullName(resultSet.getString("firstName"), resultSet.getString("lastName"),
					resultSet.getString("middleName"));
			Position position = Position.valueOf(resultSet.getString("position"));
			LocalDate hired = resultSet.getDate(7).toLocalDate();
			BigDecimal salary = resultSet.getBigDecimal("salary");
			BigInteger managerId = BigInteger.valueOf(resultSet.getInt(6));

			if (managerId != null && !managerId.equals(BigInteger.ZERO)) {
				employeeToManagerMap.put(id, managerId);
			}
			
			Employee employee = new Employee(id, fullName, position, hired, salary, null);
			employees.add(employee);
			employeeMap.put(id, employee);
		}
		
		employeeToManagerMap.forEach((key, value) -> {
			Employee employee = employeeMap.get(key);
			Employee manager = employeeMap.get(employeeToManagerMap.get(key));
			employee.setManager(manager);
		});
		
		return employees;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	public static class Employee {
		private BigInteger id;
		private FullName fullName;
		private Position position;
		private LocalDate hireDate;
		private BigDecimal salary;
		private Employee manager;
	}

	@AllArgsConstructor
	public static class FullName {
		private String firstName;
		private String lastName;
		private String middleName;
	}

	public static enum Position {
		EMPLOYEE, MANAGER
	}
}
