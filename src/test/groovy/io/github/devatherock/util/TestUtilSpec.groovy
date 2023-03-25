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
        3 * resultSet.next() >>> [true, true, false]

        and: 'data rows'
        2 * resultSet.getInt(1) >>> [1, 2]
        2 * resultSet.getString('firstName') >>> ['first1', 'first2']
        2 * resultSet.getString('lastName') >>> ['last1', 'last2']
        2 * resultSet.getString('middleName') >>> ['middle1', 'middle2']
        2 * resultSet.getString('position') >>> ['EMPLOYEE', 'MANAGER']
        2 * resultSet.getDate(7) >>> [new Date(currentTime), new Date(currentTime)]
        2 * resultSet.getBigDecimal('salary') >>> [new BigDecimal('10000'), new BigDecimal('15000')]
        2 * resultSet.getInt(6) >>> [2, 0]

        and: 'verify result'
        employeesSet.size() == 2
        def employees = new ArrayList<Employee>(employeesSet)
        employees[0].id == 1
        employees[0].manager
        employees[0].manager.id == 2
        employees[1].id == 2
        !employees[1].manager
    }
}