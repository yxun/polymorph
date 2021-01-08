package type;

import util.Validator;

public abstract class Element {
    protected final String id;
    
    protected Element(Builder builder) {
        id = builder.id;
        Validator.checkString(id);
    }

    /**
     * Unique id for the element within a resource (for internal references).
     * This may be any string value that does not contain spaces.
     * 
     * @return
     *   An immutable object of type {@link String} that may be null.
     */
    public String getId() {
        return id;
    }

    /**
     * @return
     *   true if the element can be cast to the requested elementType
     */
    public <T extends Element> boolean is(Class<T> elementType) {
        return elementType.isInstance(this);
    }

    /**
     * @throws ClassCastException
     *   when this element cannot be cast to the requested elementType
     */
    public <T extends Element> T as(Class<T> elementType) {
        return elementType.cast(this);
    }

    public boolean hasValue() {
        return false;
    }

    public boolean hasChildren() {
        return false;
    }

    /**
     * Create a new Builder from the contents of this Element
     */
    public abstract Builder toBuilder();

    public static abstract class Builder {
        protected String id;

        protected Builder() {}

        /** 
         * Unique id for the element within a resource (for internal references).
         * This may be any string value that does not contain spaces.
         * 
         * @param id
         *   Unique id for inter-element referencing
         * 
         * @return
         *   A reference to this Builder instance
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public abstract Element build();

        protected Builder from(Element element) {
            id = element.id;
            return this;
        }
    }
    
}
