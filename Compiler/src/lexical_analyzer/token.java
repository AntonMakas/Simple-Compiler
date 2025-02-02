package lexical_analyzer;

/*Class to store one token
* func - store enumerator of symbol
* value - store exact value
*/
public class token {
    public Functions func;
    public String value;
    public token(Functions func, String value){
        this.func = func;
        this.value = value;
    }

}
