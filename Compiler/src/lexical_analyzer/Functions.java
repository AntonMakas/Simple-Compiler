package lexical_analyzer;

/*Enumerator class that helps to separate symbols by type*/
public enum Functions {
    add("add"),
    sub("sub"),
    mul("mul"),
    div("div"),
    mod("mod"),
    pow("pow"),
    tern("tern"),
    Lbracket("Lbracket"),
    Rbracket("Rbracket"),
    digit("digit"),
    comma("comma"),
    error("Incorrect token");

    private String str;
    Functions(String str){
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
