package io.github.devatherock.util

import io.github.devatherock.entities.Export
import spock.lang.Specification

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test read xml'() {
        given:
        String xml =
        '''
        <export xmlns="http://eu.europa.ec/fpi/fsd/export" generationDate="2023-03-09T14:00:16.479+01:00" globalFileId="151636">
            <sanctionEntity designationDetails="1" unitedNationId="1" euReferenceNumber="EU.27.29" logicalId="13">
                <remark>UNSC RESOLUTION 1483</remark>
                <remark>UNSC RESOLUTION 1485</remark>
            </sanctionEntity>
            <sanctionEntity designationDetails="2" unitedNationId="2" euReferenceNumber="EU.27.28" logicalId="14">
               <remark>UNSC RESOLUTION 1484</remark>
               <remark>UNSC RESOLUTION 1486</remark>
            </sanctionEntity>"
        </export>
        '''.stripIndent()

        when:
        Export export = TestUtil.readXML(xml)

        then:
        export.generationDate == '2023-03-09T14:00:16.479+01:00'
        export.globalFileId == '151636'
        export.sanctionEntity.size() == 2
        export.sanctionEntity[0].designationDetails == '1'
        export.sanctionEntity[0].unitedNationId == '1'
        export.sanctionEntity[0].euReferenceNumber == 'EU.27.29'
        export.sanctionEntity[0].logicalId == '13'
        export.sanctionEntity[0].remark.size() == 2
        export.sanctionEntity[0].remark[0] == 'UNSC RESOLUTION 1483'
        export.sanctionEntity[0].remark[1] == 'UNSC RESOLUTION 1485'
        export.sanctionEntity[1].designationDetails == '2'
        export.sanctionEntity[1].unitedNationId == '2'
        export.sanctionEntity[1].euReferenceNumber == 'EU.27.28'
        export.sanctionEntity[1].logicalId == '14'
        export.sanctionEntity[1].remark[0] == 'UNSC RESOLUTION 1484'
        export.sanctionEntity[1].remark[1] == 'UNSC RESOLUTION 1486'
    }
}