package washington.domain.propose;

public enum Time {
    /**午前10～12時*/
    MORNING("午前", 10, 12),
    /**午後1～3時*/
    AFTERNOON1("午後１", 13, 15),
    /**午後3～5時*/
    AFTERNOON2("午後２", 15, 17),
    /**午後5～7時*/
    AFTERNOON3("午後３", 17, 19),
    ;

    private final String name;
    private final int begin;
    private final int end;

    Time(String name, int begin, int end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public String format(String template) {
        return template
                .replace("$name", this.name)
                .replace("$begin", String.valueOf(this.begin))
                .replace("$end", String.valueOf(this.end));
    }
}
