package io.github.devatherock.util

import org.bson.BsonArray
import org.bson.BsonBoolean
import org.bson.BsonDocument
import org.bson.BsonInt32
import org.bson.BsonString
import spock.lang.Specification

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test to json'() {
        expect:
        TestUtil.toJson(testArray()) == '[1, 2, {"key_one": 3}, [4], false, "hello"]'
    }

    void 'test to json hack'() {
        given:
        String expected = '''
        [
            1,
            2,
            {
              "key_one": 3
            },
            [
              4
            ],
            false,
            "hello"        
          ]'''.stripIndent().trim()

        expect:
        TestUtil.toJsonHack(testArray()).trim() == expected
    }

    private BsonArray testArray() {
        BsonArray bsonArray = new BsonArray()
        bsonArray.add(new BsonInt32(1))
        bsonArray.add(new BsonInt32(2))
        bsonArray.add(new BsonDocument("key_one", new BsonInt32(3)))
        bsonArray.add(new BsonArray([new BsonInt32(4)]))
        bsonArray.add(new BsonBoolean(false))
        bsonArray.add(new BsonString("hello"))

        return bsonArray
    }
}