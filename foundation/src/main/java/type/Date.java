package type;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

import util.Validator;

/**
 * Date A date or partial date (e.g. year or year + month).
 * The format is a union of the schema types gYear, gYearMonth and date.
 * Dates Shall be valid dates.
 */
public class Date extends Element {
    public static final DateTimeFormatter PARSER_FORMATTER = DateTimeFormatter.ofPattern("[yyyy[-MM[-dd]]]");

    private final TemporalAccessor value;

    private volatile int hashCode;

    private Date(Builder builder) {
        super(builder);
        value = builder.value;
        Validator.checkValueType(value, LocalDate.class, YearMonth.class, Year.class);
        Validator.requireValueOrChildren(this);
    }

    /**
     * The actual value
     * @return
     *   An immutable object of type {@link java.time.TemporalAccessor} that may be null.
     */
    public TemporalAccessor getValue() {
        return value;
    }

    public boolean isPartial() {
        return !(value instanceof LocalDate);
    }

    @Override
    public boolean hasValue() {
        return (value != null);
    }
    
    @Override
    public boolean hasChildren() {
        return super.hasChildren();
    }

    public static Date of(TemporalAccessor value) {
        return Date.builder().value(value).build();
    }

    public static Date of(String value) {
        return Date.builder().value(value).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Date other = (Date) obj;
        return Objects.equals(id, other.id) &&
            Objects.equals(value, other.value);
    }
    
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Objects.hash(id, value);
            hashCode = result;
        }
        return result;
    }

    @Override
    public Builder toBuilder() {
        return new Builder().from(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Element.Builder {
        private TemporalAccessor value;

        private Builder() {
            super();
        }

        /**
         * unique id for the element within a resource (for internal references)
         * 
         * @param id
         *   xml:id (or equivalent in JSON)
         * 
         * @return
         *   A reference to this Builder instance
         */
        @Override
        public Builder id(String id) {
            return (Builder) super.id(id);
        }

        /**
         * The actual value
         * 
         * @param value
         *   Primitive value for date
         * 
         * @return
         *   A reference to this Builder instance
         */
        public Builder value(TemporalAccessor value) {
            this.value = value;
            return this;
        }
        
        public Builder value(String value) {
            this.value = PARSER_FORMATTER.parseBest(value, LocalDate::from, YearMonth::from, Year::from);
            return this;
        }

        /**
         * Build the {@link Date}
         * 
         * @return
         *   An immutable object of type {@link Date}
         * @throws IllegalStateException
         *   If the current state cannot be built into a valid Date per the base specification
         */
        @Override
        public Date build() {
            return new Date(this);
        }

        protected Builder from(Date date) {
            super.from(date);
            value = date.value;
            return this;
        }
    }
}
