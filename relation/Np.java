package relation;

/**
 * Enum with np's regex string.
 */
public enum Np {
    Regex {
        @Override
        public String toString() {
            return "<np>[^<]+</np>";
        }
    };
}
