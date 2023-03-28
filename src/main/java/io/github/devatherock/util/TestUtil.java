package io.github.devatherock.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TestUtil {

    public static String sayHello() {
        return "Hello";
    }

    public static Set<Employee> transformResultSet(ResultSet resultSet) throws SQLException {
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
            employeeMap.put(id, employee);
        }

        employeeToManagerMap.forEach((key, value) -> {
            employeeMap.put(key, getEmployeeWithManager(key, employeeMap, employeeToManagerMap));
        });

        return new LinkedHashSet<>(employeeMap.values());
    }

private static Employee getEmployeeWithManager(BigInteger employeeId, Map<BigInteger, Employee> employeeMap,
											   Map<BigInteger, BigInteger> employeeToManagerMap) {
	Employee employee = employeeMap.get(employeeId);
	Employee manager = employeeMap.get(employeeToManagerMap.get(employeeId));
	Employee employeeWithManager;

	if (null != manager) {
		employeeWithManager = new Employee(
				employee.getId(), employee.getFullName(), employee.getPosition(), employee.getHireDate(),
				employee.getSalary(), getEmployeeWithManager(manager.getId(), employeeMap, employeeToManagerMap));
	} else {
		employeeWithManager = employee;
	}

	return employeeWithManager;
}

    @Getter
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
