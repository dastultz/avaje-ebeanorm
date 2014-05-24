package com.avaje.ebeaninternal.server.type;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import com.avaje.ebeaninternal.server.core.BasicTypeConverter;

/**
 * ScalarType for java.util.Date.
 */
public class ScalarTypeUtilDate {

	public static class TimestampType extends ScalarTypeBaseDateTime<java.util.Date> {

		public TimestampType() {
			super(java.util.Date.class, false, Types.TIMESTAMP);
		}

		public java.util.Date read(DataReader dataReader) throws SQLException {
			Timestamp timestamp = dataReader.getTimestamp();
			if (timestamp == null) {
				return null;
			} else {
				return new java.util.Date(timestamp.getTime());
			}
		}

		public void bind(DataBind b, java.util.Date value)
				throws SQLException {
			if (value == null) {
				b.setNull(Types.TIMESTAMP);
			} else {

				Timestamp timestamp = new Timestamp(value.getTime());
				b.setTimestamp(timestamp);
			}
		}

		public Object toJdbcType(Object value) {
			return BasicTypeConverter.toTimestamp(value);
		}

		public java.util.Date toBeanType(Object value) {
			return BasicTypeConverter.toUtilDate(value);
		}

		
		
		@Override
        public Date convertFromTimestamp(Timestamp ts) {
		    return new java.util.Date(ts.getTime());
        }

        @Override
        public Timestamp convertToTimestamp(Date t) {
            return new Timestamp(t.getTime());
        }

		public java.util.Date parseDateTime(long systemTimeMillis) {
			return new java.util.Date(systemTimeMillis);
		}
	}

	public static class DateType extends ScalarTypeBaseDate<java.util.Date> {

		public DateType() {
			super(Date.class, false, Types.DATE);
		}

		@Override
        public Date convertFromDate(java.sql.Date ts) {
            return new java.util.Date(ts.getTime());
        }

        @Override
        public java.sql.Date convertToDate(Date t) {
            return new java.sql.Date(t.getTime());
        }

		public Object toJdbcType(Object value) {
			return BasicTypeConverter.toDate(value);
		}

		public java.util.Date toBeanType(Object value) {
			return BasicTypeConverter.toUtilDate(value);
		}
	}
}
