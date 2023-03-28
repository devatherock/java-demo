package io.github.devatherock.util

import spock.lang.Specification

import java.sql.Date
import java.sql.ResultSet
import io.github.devatherock.util.TestUtil.Employee

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test transform result set'() {
        given:
        ResultSet resultSet = Mock()
        long currentTime = System.currentTimeMillis()

        when:
        Set<Employee> employeesSet = TestUtil.transformResultSet(resultSet)

        then: 'set resultset size'
        4 * resultSet.next() >>> [true, true, true, false]

        and: 'data rows'
        3 * resultSet.getInt(1) >>> [1, 2, 3]
        3 * resultSet.getString('firstName') >>> ['first1', 'first2', 'first3']
        3 * resultSet.getString('lastName') >>> ['last1', 'last2', 'last3']
        3 * resultSet.getString('middleName') >>> ['middle1', 'middle2', 'middle2']
        3 * resultSet.getString('position') >>> ['EMPLOYEE', 'MANAGER', 'MANAGER']
        3 * resultSet.getDate(7) >>> [new Date(currentTime), new Date(currentTime), new Date(currentTime)]
        3 * resultSet.getBigDecimal('salary') >>> [new BigDecimal('10000'), new BigDecimal('15000'), new BigDecimal('20000')]
        3 * resultSet.getInt(6) >>> [2, 3, 0]

        and: 'verify result'
        employeesSet.size() == 3
        def employees = new ArrayList<Employee>(employeesSet)
        employees[0].id == 1
        employees[0].manager
        employees[0].manager.id == 2
        employees[0].manager.manager
        employees[0].manager.manager.id == 3
        employees[1].id == 2
        employees[1].manager
        employees[1].manager.id == 3
        employees[2].id == 3
        !employees[2].manager
    }
}