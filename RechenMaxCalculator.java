package praktikum2;

import org.graalvm.polyglot.*;
import com.oracle.truffle.js.scriptengine.*;
import javax.script.*;

public class RechenMaxCalculator
{
    public String calculate(final String calc) throws ScriptException {
        final GraalJSScriptEngine engine = GraalJSScriptEngine.create(Engine.newBuilder().option("engine.WarnInterpreterOnly", "false").build(), Context.newBuilder("js").allowIO(false).option("js.ecmascript-version", "2022"));
        final String calc2 = calc.replace('×', '*').replace('÷', '/').replace(',', '.');
        return new StringBuilder().append(engine.eval(calc2)).toString();
    }
}
