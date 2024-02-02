package io.github.devatherock.util;

import lombok.val;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.json.JsonWriterSettings;

public class TestUtil {

	public static String sayHello() {
		return "Hello";
	}

	public static String toJson(BsonArray bsonArray) {
		String json = null;

		if (null != bsonArray) {
			StringBuilder jsonBuilder = new StringBuilder("[");

			for (int index = 0; index < bsonArray.size(); index++) {
				BsonValue value = bsonArray.get(index);

				if (value instanceof BsonInt32) {
					jsonBuilder.append(((BsonInt32) value).getValue());
				} else if (value instanceof BsonDocument) {
					jsonBuilder.append(((BsonDocument) value).toJson());
				} else if (value instanceof BsonArray) {
					jsonBuilder.append(toJson((BsonArray) value));
				} else if (value instanceof BsonBoolean) {
					jsonBuilder.append(((BsonBoolean) value).getValue());
				} else if (value instanceof BsonString) {
					jsonBuilder.append('"');
					jsonBuilder.append(((BsonString) value).getValue());
					jsonBuilder.append('"');
				}
				// TODO Handle other required BsonValue data types like BsonInt64, BsonDouble,
				// BsonTimestamp, etc

				if (index != bsonArray.size() - 1) {
					jsonBuilder.append(", ");
				}
			}

			jsonBuilder.append(']');
			json = jsonBuilder.toString();
		}

		return json;
	}

	public static String toJsonHack(BsonArray bsonArray) {
		BsonDocument temp = new BsonDocument("x", bsonArray);
		String json = temp.toJson(JsonWriterSettings.builder().indent(true).build());

		return json.substring(9, json.length() - 1).stripTrailing();
	}
}
